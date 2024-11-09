import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;

public class Game extends JPanel {

    private long startTime;
    private JLabel timeLabel;
    private Timer timer;

    //file reader and array stuff
    private static Path filePathNotes;
    private static List<String> lines;
    private static int[] notes;
    private static int index = 0;

    private static long currentTime = 0;
    private static long elapsedTime = 0;
    
    
        //music stuff
        // private static File audioFile;
        // private static String filePathAudio;
        // private static AudioInputStream audioStream;
        // private static AudioFormat format;
        // private static Clip audioClip;
    
        private static String recycleSound = "sounds\\RecycleSound.wav";
            private static String trashSound = "sounds\\TrashSound.wav";
                
                    //private String[] lines = new String[];
                
                    private static final int WINDOW_WIDTH = 1400;
                    private static final int WINDOW_HEIGHT = 800;
                
                    // private boolean upPressed = false;
                    // private boolean downPressed = false;
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
                        // this.addKeyListener(new KeyAdapter() {
                        //     public void keyPressed(KeyEvent e) {
                        //         int key = e.getKeyCode();
                        //         // if (key == KeyEvent.VK_UP) upPressed = true;
                        //         // if (key == KeyEvent.VK_DOWN) downPressed = true;
                        //         if (key == KeyEvent.VK_LEFT) leftPressed = true;
                        //         if (key == KeyEvent.VK_RIGHT) rightPressed = true;
                        //     }
                
                        //     public void keyReleased(KeyEvent e) {
                        //         int key = e.getKeyCode();
                        //         // if (key == KeyEvent.VK_UP) upPressed = false;
                        //         // if (key == KeyEvent.VK_DOWN) downPressed = false;
                        //         if (key == KeyEvent.VK_LEFT) leftPressed = false;
                        //         if (key == KeyEvent.VK_RIGHT) rightPressed = false;
                        //     }
                        // });
                
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
                        currentTime = System.currentTimeMillis();
                        elapsedTime = currentTime - startTime;
                
                        // Format the elapsed time to be displayed
                        DecimalFormat df = new DecimalFormat("#,###");
                        timeLabel.setText(df.format(elapsedTime));  // Update the label with formatted time
                        if (elapsedTime == notes[index]){
                                        playSound(trashSound);
                                        index++;
                                       System.out.println(elapsedTime);
                                    }
                    }
    
                    // private static void hitSound(){
                    //                     while (elapsedTime != 0){
                    //                     if (elapsedTime == notes[index])
                    //                     playSound(trashSound);
                    //                     index++;
                    //                 }
                    //             }
                            
                                // Render game scene
                                @Override
                                protected void paintComponent(Graphics g) {
                                    super.paintComponent(g);
                                    player.draw(g);
                                }
                            
                               public static void fileReader(){
                                   filePathNotes = Paths.get("musicMap\\musicMap.txt");
                               
                                       try {
                                           // Read all lines from the file into a List using Files.lines()
                                           lines = Files.readAllLines(filePathNotes);
                               
                                           // Convert List to an Array
                                           notes = new int[lines.size()];
                               
                                           // Output the array contents (lines)
                                           int index = 0;
                                           for (String line : lines) {
                                               try {
                                                   // Parse each line as an integer and store it in the array
                                                   notes[index] = Integer.parseInt(line.trim());
                                                   index++;
                                               } catch (NumberFormatException e) {
                                                   System.out.println("Skipping invalid integer: " + line);
                                               }
                                           }
                                           for (int num : notes) {
                                System.out.println(num);
                            }
                
                               
                                       } catch (IOException e) {
                                           // Handle file not found or reading errors
                                           e.printStackTrace();
                               
                                  }
                               }
                            
                               public static void playSound(String audioPath){
                               
                                new Thread(() -> {
                                       try {
                        
                                        File audioFile = new File(audioPath);
                        
                        
                                           // Get an AudioInputStream from the file
                                           AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                               
                                           // Get the audio format
                                           //format = audioStream.getFormat();
                               
                                           // Get a clip to play the audio
                                           Clip audioClip = AudioSystem.getClip();
                                           audioClip.open(audioStream);  // Open the clip with the audio stream
                               
                                           // Start playing the audio clip
                                           audioClip.start();
                               
                                           // Wait until the audio finishes playing (blocking call)
                                          // System.out.println("Playing audio...");
                                           Thread.sleep(audioClip.getMicrosecondLength() / 1000);  // Wait for the clip to finish
                                           //Thread.sleep(1000);
                                           // Close the clip after playing
                                           audioClip.close();
                            
                        
                               
                                       } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                                           // Handle errors (such as file not found or unsupported format)
                                           e.printStackTrace();
                                       }
                                    }).start();
                                   }
                                  
                                      
                                  
                               
                               
                                   
                               
                                   public static void main(String[] args) {
                                       JFrame frame = new JFrame("Trash and Rec");
                                       Game game = new Game();
                                       fileReader();
                                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       frame.setBackground(Color.CYAN);
                                       frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                                       frame.add(game);
                                       frame.setVisible(true);
                                    //    playSound(trashSound);
                                    //    playSound(recycleSound);
                                    //     playSound(trashSound);
                                    //     playSound(recycleSound);
                                        System.out.print(notes[1]);
                                        //hitSound();
           
        game.gameLoop();  // Start the game loop
    }
    
}
