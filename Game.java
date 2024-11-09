import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Game extends JPanel {

    private static final int WINDOW_WIDTH = 1400;
    private static final int WINDOW_HEIGHT = 800;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Player player;

    public Game() {
        player = new Player(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);

        // Key listener for controlling player movement
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) upPressed = true;
                if (key == KeyEvent.VK_DOWN) downPressed = true;
                if (key == KeyEvent.VK_LEFT) leftPressed = true;
                if (key == KeyEvent.VK_RIGHT) rightPressed = true;
            }

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) upPressed = false;
                if (key == KeyEvent.VK_DOWN) downPressed = false;
                if (key == KeyEvent.VK_LEFT) leftPressed = false;
                if (key == KeyEvent.VK_RIGHT) rightPressed = false;
            }
        });

        this.setFocusable(true);
    }

    // Game loop
    public void gameLoop() {
        // Main game loop
        long lastTime = System.nanoTime();
        final double nsPerTick = 1000000000.0 / 60.0;  // 60 FPS
        double delta = 0;
        
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            try {
                Thread.sleep(2);  // Sleep a little to prevent 100% CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Update game state
    private void update() {
        if (upPressed) player.move(0, -5);
        if (downPressed) player.move(0, 5);
        if (leftPressed) player.move(-5, 0);
        if (rightPressed) player.move(5, 0);
    }

    // Render game scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game Engine");
        Game game = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(game);
        frame.setVisible(true);

        game.gameLoop();  // Start the game loop
    }
}
