package GameSprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * This is now a Pizza Topping sprite rather than a circle!
 */
public class CircleSprite extends GameObject {
    private double angle = 160;
    private double radius = 25;
    private double tempX;
    private double tempY;
    private double rad = 0;
    private double defaultrotation;
    private double currentRotation;
    private Color color = Color.RED;

    private BufferedImage image = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);

    public CircleSprite(int x, int y) {
        holdable = true;
        this.x = x;
        tempX = x;
        this.y = y;
        tempY = y;
        width = 32;
        height = 32;
        scale = Math.random()*1.2+0.8;
        defaultrotation = Math.random()*Math.PI*2;
        currentRotation = defaultrotation;

        String[] options = {"images/blackolive.png", "images/mushroom.png", "images/pepperoni.png", "images/sausage.png"};
        try {
            String topping = options[(int) Math.floor(Math.random()*options.length)];
            System.out.println(topping);
            image = ImageIO.read(Objects.requireNonNull(ResourceLoader.getResource(topping)));
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
        else if (keys.contains(38)){
            renderScale-=0.01;
        }
        else if (keys.contains(40)){
            renderScale+=0.01;
        }
        rad = Math.toRadians(angle);
        tempX = (300 -(width/2) + Math.cos(rad) * radius);
        tempY = (300 -(height/2) + Math.sin(rad) * radius);
        x = (int) tempX;
        y = (int) tempY;
    }

    public void render(Graphics g) {
        g.setColor(color);

        Stroke STROKE = new BasicStroke(1f);
        Color ARC_COLOR = Color.red;
        Arc2D arc = new Arc2D.Double(300-(radius) , 300-(radius), radius*2, radius*2, 0 , 360, Arc2D.OPEN);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(STROKE);
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
//        if (arc != null) {
//            g2.draw(arc);
//       }

        double scalar = this.scale * this.renderScale;

        double width = this.width*scalar;
        double height = this.height*scalar;

        double rotationRequired = Math.toRadians (angle);
        double locationX = this.width*scalar / 2;
        double locationY = this.height*scalar / 2;
        if (released) {
            this.released = false;
            double tempRotation = Math.toDegrees(currentRotation);
            if (tempRotation < 0) {
                tempRotation = 360 + tempRotation;
            }
            double tempAngle = angle;
            if (tempAngle < 0){
                tempAngle = 360 + tempAngle;
            }
            double tempNewRotation = tempRotation - tempAngle;
            if (tempNewRotation > 180) {
                tempNewRotation = tempNewRotation - 360;
            }
            System.out.println(tempRotation+" "+tempAngle+" "+tempNewRotation);
            defaultrotation = Math.toRadians(tempNewRotation);
        }
        if (!held) {
            currentRotation = rotationRequired + defaultrotation;
        }
        if (held) {
            //System.out.println(Math.toDegrees(currentRotation));
        }
        AffineTransform tx = AffineTransform.getRotateInstance(currentRotation, locationX, locationY);
        tx.concatenate(AffineTransform.getScaleInstance(width/(double)image.getWidth(), height/(double)image.getHeight()));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g2.drawImage(op.filter(image, null), this.x-(int)(width/2)+this.width/2, this.y-(int)(height/2)+this.width/2, null);
        g2.drawRect(this.x-(int)(width/2)+this.width/2, this.y-(int)(height/2)+this.width/2, (int)width, (int)height);
        g2.dispose();
    }
}
