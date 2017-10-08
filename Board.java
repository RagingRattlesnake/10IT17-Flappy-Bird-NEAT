import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private int iterationCounter;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Ground ground;
    private Background background;
    private Timer timer;

    public Board() { init(); }

    private void init() {
        bird = new Bird();
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
        for (Pipe pipe : pipes) { pipe.draw(g); }
        ground.draw(g);
        bird.draw(g);
        g.drawString("Fitness: " + bird.getFitness(), 20, Settings.WINDOW_HEIGHT - 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (iterationCounter == 100) { pipes.add(new Pipe()); }
        else if (iterationCounter > 100) { iterationCounter = -1; }
        iterationCounter++;
        for (Pipe pipe : pipes) { pipe.move(); }
        if (pipes.get(0).getXPos() < -pipes.get(0).getPipeWidth()) { pipes.remove(0); }
        
        ground.move();

        bird.move();
        bird.checkCollision(pipes.get(0).getCollisionBorders());
        bird.checkCollision(ground.getCollisionBorders());
        if (bird.isDead()) { timer.stop(); }
        else { bird.addFitness(); }
        repaint();
    }

    public class KeyListener extends KeyAdapter {

        private boolean gameStarted;

        public KeyListener() { gameStarted = false; }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!gameStarted) {
                gameStarted = true;
                timer.start();
            }
            bird.jump();
        }
    }
}