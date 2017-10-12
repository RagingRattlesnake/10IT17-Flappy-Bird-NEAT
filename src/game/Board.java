package game;

import neat.NeuralNetwork;
import neat.Neuroevolution;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private static final long serialVersionUID = -2469276924387568876L;
    private Neuroevolution neuvol = new Neuroevolution();
    private int iterationCounter;
    private ArrayList<Bird> birds = new ArrayList<>();
    private ArrayList<Pipe> pipes;
    private Ground ground;
    private Background background;
    private Timer timer;
    private ArrayList<NeuralNetwork> gen;
    public Board() {
        init();
    }

    private void init() {
        gen = neuvol.nextGeneration();
        for(NeuralNetwork nn: gen){
            birds.add(new Bird());
        }
        pipes = new ArrayList<>();
        pipes.add(new Pipe());
        ground = new Ground();
        background = new Background();

        timer = new Timer(25, this);
        iterationCounter = 0;

        addKeyListener(new KeyListener());
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        ground.draw(g);

        for(Bird b: birds){
            b.draw((Graphics2D) g);
        }
        //g.drawString("Fitness: " + birds.get(0).getFitness(), 20, Settings.WINDOW_HEIGHT - 40);
        g.drawString("Anzahl Vögel: " + birds.size(), 600, Settings.WINDOW_HEIGHT-40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (iterationCounter == 50) {
            pipes.add(new Pipe());
        } else if (iterationCounter > 50) {
            iterationCounter = -1;
        }
        iterationCounter++;
        for (Pipe pipe : pipes) {
            pipe.move();
        }
        if (pipes.get(0).getXPos() < -pipes.get(0).getPipeWidth()) {
            pipes.remove(0);
        }

        ground.move();
        for(Bird bird: birds) {
            bird.move();
            bird.checkCollision(pipes.get(0).getCollisionBorders());
            bird.checkCollision(ground.getCollisionBorders());
            if (bird.isDead()) {
                timer.stop();
            } else {
                bird.addFitness();
            }
        }
        repaint();
    }

    public class KeyListener extends KeyAdapter {

        private boolean gameStarted;

        public KeyListener() {
            gameStarted = false;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!gameStarted) {
                gameStarted = true;
                timer.start();
            }
            for(Bird bird: birds){
                bird.jump();
                if(birds.indexOf(bird) % 2 == 0){
                    bird.jump();
                }
            }

        }
    }
}