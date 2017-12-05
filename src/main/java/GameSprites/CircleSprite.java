package GameSprites;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class CircleSprite extends GameObject {
    int OldX = 0;
    int OldY = 0;
    double angle = 160;
    double radius = 30;
    double tempX;
    double tempY;
    double rad = 0;

    public CircleSprite(int x, int y) {
        this.x = x;
        tempX = x;
        this.y = y;
        tempY = y;
    }

    public void setX(int x) {
        rad = Math.atan2((y+(height/2)) - 300, (x+(width/2)) - 300);
        angle = Math.toDegrees(rad);
        this.x = x;
        tempX = x;
    }

    public void setY(int y) {
        rad = Math.atan2((y+(height/2)) - 300, (x+(width/2)) - 300);
        angle = Math.toDegrees(rad);
        this.y = y;
        tempY = y;
    }

    public int getX(int x) {
        return x;
    }

    public int getY(int y) {
        return y;
    }

    public void tick() {
        if (!held) {
            rad = Math.atan2((tempY+(height/2)) - 300, (tempX+(width/2)) - 300);
            angle = Math.toDegrees(rad);
            radius = Math.sqrt( (((tempX+(width/2)) - 300)*((tempX+(width/2)) - 300)) + (((tempY+(height/2)) - 300)*((tempY+(height/2)) - 300)));
            if (angle < 0) { angle += 360; }
            angle+= 5;
            if (angle > 360) { angle -= 360; }
            System.out.println(angle);
            rad = Math.toRadians(angle);
            System.out.println(rad);

            tempX = (300 -(width/2) + Math.cos(rad) * radius);
            tempY = (300 -(height/2) + Math.sin(rad) * radius);
            x = (int) tempX;
            y = (int) tempY;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(this.x, this.y, this.width,this.height);

        double radz = Math.atan2(300 - (y+(height/2)), (x+(width/2)) - 300);
        double anglez = (radz * (180/Math.PI));
        double radiusz = Math.sqrt( (((x+(width/2)) - 300)*((x+(width/2)) - 300)) + (((y+(height/2)) - 300)*((y+(height/2)) - 300)));
        if (anglez < 0) { anglez += 360; }

        Stroke STROKE = new BasicStroke(3f);
        Color ARC_COLOR = Color.red;
        Arc2D arc = new Arc2D.Double(300-(radiusz) , 300-(radiusz), radiusz*2, radiusz*2, 0 , anglez, Arc2D.OPEN);;
        if ((OldX != x)||(OldY != y)){
            OldX = x;
            OldY = y;
            //System.out.println(rad+" - "+radius+" - "+angle);
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
