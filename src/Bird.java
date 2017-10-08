import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Bird {

    public int height;
    private int birdState;
    private int fitness;
    private double velocity;
    private boolean dead;
    private Image[] images;

    public Bird() { init(); }

    private void init() {
        height = 250;
        velocity = 0;
        birdState = 1;
        loadImages();
    }

    private void loadImages() {
        images = new Image[3];
        try {
            images[0] = ImageIO.read(new File("assets/bird0.png"));
            images[1] = ImageIO.read(new File("assets/bird1.png"));
            images[2] = ImageIO.read(new File("assets/bird2.png"));
        } catch (Exception e) {}
    }

    public void draw(Graphics g) {
        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, isDead() ? 0.5f : 1.0f));
        g.drawImage(images[birdState], Settings.BIRD_X_POS, height, null);
    }

    private void setVelocity(double newVelocity) {
        velocity = newVelocity;
        if (velocity < -0.3) { birdState = 0; }
        else if (velocity > 0.3) { birdState = 2; }
        else { birdState = 1; }
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
            setVelocity(-3);
        }
    }

    public void addFitness() { fitness++; }
    
    public int getFitness() { return fitness; }

    private void kill() { dead = true; }

    public boolean isDead() { return dead; }
}
