
import javax.swing.*;

public class Window extends JFrame {

	private static final long serialVersionUID = 6904930471192886669L;

	public Window() { init(); }

    private void init() {
        add(new Board());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flappy Bird");
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) { new Window(); }
}
