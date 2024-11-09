import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

public class Bin {
    private int xPosition;
    private int yPosition;
    private Boolean binType;
    private Image binImage;

    public Bin(int x, int y, Boolean binType){
        this.xPosition = x;
        this.yPosition = y;
        this.binType = binType;
        if(binType){ // trashbin
            try {
                binImage = ImageIO.read(new File("art\\Trashbin.png")); // Make sure the image is in the correct path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{ //recycle bin
            try {
                binImage = ImageIO.read(new File("art\\RecycleBin.png")); // Make sure the image is in the correct path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Draw the player image on the screen
    public void draw(Graphics g) {
        g.drawImage(binImage, xPosition, yPosition, null);  // Draw the image at the (x, y) position
    }
}