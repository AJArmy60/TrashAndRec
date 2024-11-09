import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.text.DecimalFormat;
import javax.swing.*;

public class Game extends JPanel {

    private long startTime;
    private JLabel timeLabel;
    private Timer timer;

    //file reader and array stuff
    private static Path filePath;
    private static List<String> lines;
    private static String[] notes;

    //private String[] lines = new String[];

    private static final int WINDOW_WIDTH = 1400;
    private static final int WINDOW_HEIGHT = 800;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Player player;

    public Game() {

        //Timer Stuff
        timeLabel = new JLabel("0", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timeLabel.setForeground(Color.BLACK);

        timeLabel.setBounds(WINDOW_WIDTH/2, WINDOW_HEIGHT/6, 100, 40);  // Adjust position and size
        setLayout(null);  // Use absolute positioning
        add(timeLabel);

        startTime = System.currentTimeMillis();

        timer = new Timer(1, e -> updateTime());
        timer.start();
        
        player = new Player(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);

        // Key listener for controlling player movement
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_A) leftPressed = true;
                if (key == KeyEvent.VK_D) rightPressed = true;
            }

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_A) leftPressed = false;
                if (key == KeyEvent.VK_D) rightPressed = false;
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
        // if (upPressed) player.move(0, -5);
        // if (downPressed) player.move(0, 5);
        if (leftPressed) player.swing(1); //swing left
        if (rightPressed) player.swing(2); //swing right
    }

    private void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        // Format the elapsed time to be displayed
        DecimalFormat df = new DecimalFormat("#,###");
        timeLabel.setText(df.format(elapsedTime/1000));  // Update the label with formatted time
    }

    // Render game scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }

   public static void fileReader(){
       filePath = Paths.get("Music map\\musicMap.txt");
   
           try {
               // Read all lines from the file into a List using Files.lines()
               lines = Files.readAllLines(filePath);
   
               // Convert List to an Array
               notes = lines.toArray(new String[0]);
   
               // Output the array contents (lines)
               for (String line : notes) {
                   System.out.println(line);
               }
   
           } catch (IOException e) {
               // Handle file not found or reading errors
               e.printStackTrace();
   
      }
   }
       
   


    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Trash and Rec");
        
        // Create the game panel and add it to the frame
        // GamePanel gamePanel = new GamePanel();
        // frame.add(gamePanel);
        Game game = new Game();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.CYAN);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(game);
        frame.setVisible(true);
        fileReader();
        game.gameLoop();  // Start the game loop
    }
    
}
