package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Background {

    private Image image;

    public Background() {
        init();
    }

    private void init() {
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("assets/bg.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, 0, Settings.BACKGROUND_HEIGHT_OFFSET, null);
    }
}
