package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

class Background {

    private Image image;
    private int imageOffset;

    Background() {
        init();
    }

    private void init() {
        loadImage();
        imageOffset = 0;
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("assets/bg.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void move() {
        imageOffset += 1;
        imageOffset %= image.getWidth(null);
    }

    void draw(Graphics g) {
        for (int x = -imageOffset; x < Settings.WINDOW_WIDTH; x += image.getWidth(null)) {
            g.drawImage(image, x, Settings.BACKGROUND_HEIGHT_OFFSET, null);
        }
    }
}
