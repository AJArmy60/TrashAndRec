import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private int x, y;
    private int width = 64;  // Width of the sprite (64px)
    private int height = 64; // Height of the sprite (64px)
    private BufferedImage sprite;  // The sprite image (only one frame for now)
    private BufferedImage currentFrame; // The current frame to display

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        // Load the sprite (which is a single 64x64 image)
        try {
            sprite = ImageIO.read(new File("art\\PlayerTileset.png"));
            currentFrame = sprite; // Initially, just use the single sprite as the current frame
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method moves the player and updates the animation frames.
    public void move(int dx) {
        // Calculate the potential new position
        int newX = x + dx;

        // If moving to the right, flip the sprite horizontally
        if (dx > 0) {
            currentFrame = flipImage(sprite); // Flip sprite horizontally for right movement
        }
        // If moving to the left, keep the sprite as is
        else if (dx < 0) {
            currentFrame = sprite; // Use the normal sprite for left movement
        }

        // Update X position
        x = newX;
    }

    // Flip the sprite horizontally for right movement
    private BufferedImage flipImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, img.getType());
        Graphics2D g = flipped.createGraphics();
        g.drawImage(img, w, 0, -w, h, null);  // Flip image horizontally
        g.dispose();
        return flipped;
    }

    // Draw the player image on the screen
    public void draw(Graphics g) {
        g.drawImage(currentFrame, x, y, width, height, null);
    }
}
