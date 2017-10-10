package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class Window extends JFrame {

    private static final long serialVersionUID = 6904930471192886669L;

    private Window() {
        init();
    }

    private void init() {
        add(new Board());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flappy game.Bird");
        try {
            setIconImage(ImageIO.read(new File("assets/bird0.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
