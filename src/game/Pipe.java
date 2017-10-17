package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

class Pipe {

    private int height;
    private int space;
    private int xPos;
    private Image upperPipe;
    private Image lowerPipe;

    Pipe() {
        init();
    }

    private void init() {
        loadImages();
        space = 100;
        height = (int) (Settings.PIPE_MIN_HEIGHT + (Settings.PIPE_MAX_HEIGHT - Settings.PIPE_MIN_HEIGHT) * Math.random());
        xPos = Settings.WINDOW_WIDTH + getPipeWidth();
    }

    private void loadImages() {
        try {
            upperPipe = ImageIO.read(new File("assets/tube1.png"));
            lowerPipe = ImageIO.read(new File("assets/tube2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void draw(Graphics g) {
        g.drawImage(lowerPipe, xPos, height + (space / 2), null);
        g.drawImage(upperPipe, xPos, height - (space / 2) - upperPipe.getHeight(null), null);
    }

    int[] getCollisionBorders() {
        int[] collisionBorders = new int[4];
        collisionBorders[0] = xPos;
        collisionBorders[1] = xPos + getPipeWidth();
        collisionBorders[2] = height - (space / 2);
        collisionBorders[3] = height + (space / 2);
        return collisionBorders;
    }

    void move() {
        xPos -= Settings.MOVE_SPEED;
    }

    int getXPos() {
        return xPos;
    }

    int getPipeWidth() {
        return upperPipe.getWidth(null);
    }

    int getHeight() {
        return height;
    }
}
