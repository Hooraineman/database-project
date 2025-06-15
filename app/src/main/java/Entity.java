import java.awt.*;

public abstract class Entity {
    int x, y, size = 40;
    Image image;

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public abstract void draw(Graphics g);
}
