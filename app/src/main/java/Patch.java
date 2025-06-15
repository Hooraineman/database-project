import javax.swing.*;
import java.awt.*;

public class Patch extends Entity {
    private static final String PATCH_IMAGE_URL = "C:\\Users\\admin\\Downloads\\WhatsApp Image 2025-05-19 at 7.41.13 PM.jpeg";

    public Patch(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(PATCH_IMAGE_URL).getImage();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }
}

