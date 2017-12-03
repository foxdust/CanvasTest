package GameSprites;

import java.awt.Graphics;

public interface Sprites {
    public void setX(int x);
    public void setY(int y);
    public int getX(int x);
    public int getY(int y);
    public void tick(int mouseX, int mouseY);
    public void render(Graphics g);
}
