// import java.awt.*;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;
// import javax.swing.*;

// public class GamePanel extends JPanel {
//     private BufferedImage background; // The background image

//     public GamePanel() {
//         try {
//             // Load the background image
//             background = ImageIO.read(new File("background.png"));
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         // Draw the background image
//         g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

//         // Draw other game objects here (like the player)
//         // Example: player.draw(g);
//     }
// }
