package game;

import neat.NeuralNetwork;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Bird{

    public int height;
    private int birdState;
    private int fitness;
    private double velocity;
    private boolean dead;
    private BufferedImage[] images;


    private NeuralNetwork network;

    public Bird() {
        init();
    }

    private void init() {
        height = 250;
        velocity = 0;
        birdState = 1;
        loadImages();
        network = new NeuralNetwork();
    }

    private void loadImages() {
        images = new BufferedImage[3];
        try {
            images[0] = ImageIO.read(new File("assets/bird0.png"));
            images[1] = ImageIO.read(new File("assets/bird1.png"));
            images[2] = ImageIO.read(new File("assets/bird2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, isDead() ? 0.5f : 1.0f));
        AffineTransform at = new AffineTransform();
        at.translate(Settings.BIRD_X_POS + (images[birdState].getWidth() / 2), height + (images[birdState].getHeight() / 2));
        at.rotate(rotation(-Math.PI / 2 - 0.3, Math.PI / 2, ((velocity + 10) / 21)));
        at.translate(-images[0].getWidth() / 2, -images[0].getHeight() / 2);
        g.drawImage(images[birdState], at, null);
    }

    private void setVelocity(double newVelocity) {
        velocity = newVelocity;
        if (velocity < -0.3) {
            birdState = 0;
        } else if (velocity > 0.3) {
            birdState = 2;
        } else {
            birdState = 1;
        }
    }

    private double rotation(double a, double b, double f) {
        return a + f * (b - a);
    }

    public void move() {
        if (!dead) {
            setVelocity(velocity + 0.2);
            height += velocity + (Math.pow(velocity / 2, 3)) / 3;
        }
    }

    public void checkCollision(int[] borders) {
        if (
                borders[0] < Settings.BIRD_X_POS + images[birdState].getWidth(null) && borders[1] > Settings.BIRD_X_POS && (
                        height < borders[2] || height + images[birdState].getHeight(null) > borders[3]
                )) {
            if (borders[1] > Settings.BIRD_X_POS + images[birdState].getWidth(null) && borders[0] < Settings.BIRD_X_POS && height + images[birdState].getHeight(null) > borders[3]) {
                height = borders[3] - images[birdState].getHeight(null); //Höhenkorrektur
            }
            kill();
        }
    }

    public void jump() {
        if (!dead) {
            setVelocity(-3.2);
        }
    }

    public void addFitness() {
        fitness++;
    }

    public int getFitness() {
        return fitness;
    }

    public NeuralNetwork getNetwork() {
        return network;
    }

    private void kill() {
        dead = true;
        Settings.ANZAHL_VOEGEL--;
    }

    public boolean isDead() {
        return dead;
    }

}
