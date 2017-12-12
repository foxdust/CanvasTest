package GameSprites;

import java.awt.Graphics;

/**
 * Interface for visible objects
 */
public interface Sprites {
    void setX(int x);
    void setY(int y);
    int getX(int x);
    int getY(int y);
    void tick(int mouseX, int mouseY);
    void render(Graphics g);
}
