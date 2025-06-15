import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Virus extends Entity {
    Random rand = new Random();
    private static final String VIRUS_IMAGE_URL = "C:\\Users\\admin\\Downloads\\WhatsApp Image 2025-05-19 at 7.41.13 PM (1).jpeg";

    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(VIRUS_IMAGE_URL).getImage();
    }

    public void moveRandom() {
        x += (rand.nextInt(3) - 1) * size;
        y += (rand.nextInt(3) - 1) * size;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }
}

