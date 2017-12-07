package GameSprites;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public class CircleSprite extends GameObject {
    double angle = 160;
    double radius = 30;
    double tempX;
    double tempY;
    double rad = 0;
    Color color = Color.RED;

    public CircleSprite(int x, int y) {
        this.x = x;
        tempX = x;
        this.y = y;
        tempY = y;
    }

    public void setColor(Color color) {
        this.color = color;
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
        if (true) {
            rad = Math.atan2((tempY+(height/2)) - 300, (tempX+(width/2)) - 300);
            angle = Math.toDegrees(rad);
            radius = Math.sqrt( (((tempX+(width/2)) - 300)*((tempX+(width/2)) - 300)) + (((tempY+(height/2)) - 300)*((tempY+(height/2)) - 300)));
            if (!held) {
                angle += 1;
            }
            rad = Math.toRadians(angle);
            tempX = (300 -(width/2) + Math.cos(rad) * radius);
            tempY = (300 -(height/2) + Math.sin(rad) * radius);
            x = (int) tempX;
            y = (int) tempY;
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(this.x, this.y, this.width,this.height);
/*
        double radz = Math.atan2(300 - (y+(height/2)), (x+(width/2)) - 300);
        double anglez = (radz * (180/Math.PI));
        double radiusz = Math.sqrt( (((x+(width/2)) - 300)*((x+(width/2)) - 300)) + (((y+(height/2)) - 300)*((y+(height/2)) - 300)));
*/
        double anglez = -angle;
        if (anglez < 0) { anglez += 360; }


        Stroke STROKE = new BasicStroke(3f);
        Color ARC_COLOR = Color.red;
        Arc2D arc = new Arc2D.Double(300-(radius) , 300-(radius), radius*2, radius*2, 0 , anglez, Arc2D.OPEN);;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(STROKE);
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Line2D.Double(this.x+(this.width/2), this.y+(this.width/2), 300, 300));
        if (arc != null) {
            g2.draw(arc);
        }
        g2.dispose();
    }
}
