package GameSprites;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class CircleSprite extends GameObject {
    int OldX = 0;
    int OldY = 0;

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

        double rad = Math.atan2(300 - (y+(height/2)), (x+(width/2)) - 300);
        double angle = (rad * (180/Math.PI));
        double radius = Math.sqrt( (((x+(width/2)) - 300)*((x+(width/2)) - 300)) + (((y+(height/2)) - 300)*((y+(height/2)) - 300)));
        if (angle < 0) { angle += 360; }

        Stroke STROKE = new BasicStroke(3f);
        Color ARC_COLOR = Color.red;
        Arc2D arc = new Arc2D.Double(300-(radius) , 300-(radius), radius*2, radius*2, 0 , angle, Arc2D.OPEN);;
        if ((OldX != x)||(OldY != y)){
            OldX = x;
            OldY = y;
            System.out.println(rad+" - "+radius+" - "+angle);
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(STROKE);
        g2.setColor(ARC_COLOR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Line2D.Double(this.x+(this.width/2), this.y+(this.width/2), 300, 300));
        if (arc != null) {
            g2.draw(arc);
        }
        g2.dispose();
    }
}
