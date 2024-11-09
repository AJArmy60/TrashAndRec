import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private int x, y;
    private int width = 50;
    private int height = 50;
    private Image playerImage;

    // Game world dimensions
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        
        // Load the player image
        try {
            playerImage = ImageIO.read(new File("C:/Users/mvp15/OneDrive/Documents/Hackathon/TrashAndRec-3/art/player.png")); // Make sure the image is in the correct path
            width = playerImage.getWidth(null);  // Set the width of the player to the image's width
            height = playerImage.getHeight(null); // Set the height of the player to the image's height
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method moves the player, making sure they don't move outside the boundaries.
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Draw the player image on the screen
    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, null);  // Draw the image at the (x, y) position
    }
}
