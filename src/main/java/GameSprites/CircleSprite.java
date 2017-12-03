package GameSprites;

import java.awt.*;

public class CircleSprite extends GameObject {

    public CircleSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX(int x) {
        return x;
    }

    public int getY(int y) {
        return y;
    }

    public void tick() {
        x+= 1;
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(this.x, this.y, this.width,this.height);

        g.setColor(Color.RED);
        g.drawLine(this.x+(this.width/2), this.y+(this.width/2), 300, 300);

        double rad = Math.atan2(300 - (y+(height/2)), (x+(width/2)) - 300);
        int angle = (int) (rad * (180/Math.PI));
        if (angle < 0) { angle += 360; }
        int radius = (int)Math.sqrt( ((x - 300)*(x - 300)) + ((y - 300)*(y - 300)));
        g.drawArc(300-(radius/2) , 300-(radius/2), radius, radius,0, angle);


    }
}
