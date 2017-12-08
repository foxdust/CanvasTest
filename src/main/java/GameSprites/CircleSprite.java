package GameSprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CircleSprite extends GameObject {
    double angle = 160;
    double rotationAngle = 0;
    double radius = 25;
    double tempX;
    double tempY;
    double rad = 0;
    double scalar = 0;
    int defaultrotation = 90;
    boolean check = false;
    Color color = Color.RED;

    BufferedImage image = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);

    public CircleSprite(int x, int y) {
        this.x = x;
        tempX = x;
        this.y = y;
        tempY = y;
        width = 32;
        height = 32;
        scalar = Math.random()*1.2+0.8;
        defaultrotation = (int) Math.floor(Math.random()*360);

        String[] options = {"images/blackolive.png", "images/mushroom.png", "images/pepperoni.png", "images/sausage.png"};
        try {
            String topping = options[(int) Math.floor(Math.random()*options.length)];
            System.out.println(topping);
            image = ImageIO.read(ResourceLoader.getResource(topping));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if ((!held)&&(radius <= 200)) {
                angle += 1;
            }
            if (keys.contains(37)){
                angle-=1;
            }
            else if (keys.contains(39)){
                angle+=1;
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
        //g.fillOval(this.x, this.y, this.width,this.height);
/*
        double radz = Math.atan2(300 - (y+(height/2)), (x+(width/2)) - 300);
        double anglez = (radz * (180/Math.PI));
        double radiusz = Math.sqrt( (((x+(width/2)) - 300)*((x+(width/2)) - 300)) + (((y+(height/2)) - 300)*((y+(height/2)) - 300)));
*/
        double anglez = -angle;
        if (anglez < 0) { anglez += 360; }


        Stroke STROKE = new BasicStroke(1f);
        Color ARC_COLOR = Color.red;
//        Arc2D arc = new Arc2D.Double(300-(radius) , 300-(radius), radius*2, radius*2, 0 , anglez, Arc2D.OPEN);;
        Arc2D arc = new Arc2D.Double(300-(radius) , 300-(radius), radius*2, radius*2, 0 , 360, Arc2D.OPEN);;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(STROKE);
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.draw(new Line2D.Double(this.x+(this.width/2), this.y+(this.width/2), 300, 300));
        if (arc != null) {
//            g2.draw(arc);
        }

        // Rotation information

        double width = this.width*scalar;
        double height = this.height*scalar;

        double rotationRequired = Math.toRadians (angle);
        rotationAngle += 5;
        double locationX = this.width*scalar / 2;
        double locationY = this.height*scalar / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired+defaultrotation, locationX, locationY);
        //tx.concatenate(AffineTransform.getTranslateInstance(x1, x2));
        tx.concatenate(AffineTransform.getScaleInstance((double)width/(double)image.getWidth(), (double)height/(double)image.getHeight()));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g2.drawImage(op.filter(image, null), this.x-(int)(width/2)+this.width/2, this.y-(int)(height/2)+this.width/2, null);


        g2.dispose();
    }
}
