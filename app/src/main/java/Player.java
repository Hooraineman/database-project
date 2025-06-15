import javax.swing.*;
import java.awt.*;

public class Player extends Entity {
    int dx, dy;
     private static final String PLAYER_IMAGE_URL = "C:\\Users\\admin\\Downloads\\WhatsApp Image 2025-05-19 at 7.41.14 PM.jpeg";

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(PLAYER_IMAGE_URL).getImage();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }
}
