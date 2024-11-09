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
    private Image playerImage;

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
    // This method moves the player, making sure they don't move outside the boundaries.

    
    public void swing(int input) {
        // 1 is left swing
        // 2 is right swing
        if(input == 1){
            updatePlayerImage(1);
        }
        else if(input == 2) {
            updatePlayerImage(2);
        }
        else{
            updatePlayerImage(3);
        }
    }

    public void updatePlayerImage(int state){
        switch (state){
            case 1:
                this.setPlayerImage("left"); //player swing left
                break;
            case 2:
                this.setPlayerImage("right"); //player swing right
                break;
            default:
                this.setPlayerImage("default"); //default stance
        }
    
    }

    public void setPlayerImage(String imageName){
        try {
            this.playerImage = ImageIO.read(new File("art\\player.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Draw the player image on the screen
    public void draw(Graphics g) {
        g.drawImage(currentFrame, x, y, width, height, null);
    }
}
