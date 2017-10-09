
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ground {

    private Image image;
    private int imageOffset;

    public Ground() { init(); }

    private void init() { 
    	loadImage(); 
    	imageOffset = 0;
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("assets/ground.png"));
        } catch (Exception e) {}
    }

    public void draw(Graphics g) {
        for (int x = -imageOffset; x < Settings.WINDOW_WIDTH; x += image.getWidth(null)) {
            g.drawImage(image, x, Settings.GROUND_HEIGHT, null);
        }
    }
    
    public void move() {
    	imageOffset += Settings.MOVE_SPEED;
    	imageOffset %= image.getWidth(null);
    }

    public int[] getCollisionBorders() {
        int[] collisionBorders = new int[4];
        collisionBorders[0] = 0;
        collisionBorders[1] = Settings.WINDOW_WIDTH;
        collisionBorders[2] = 0;
        collisionBorders[3] = Settings.GROUND_HEIGHT;
        return collisionBorders;
    }
}
