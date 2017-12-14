import GameSprites.CircleSprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Canvas implements Runnable, MouseListener, MouseMotionListener{
    private static final long serialVersionUID = -6704652798820883887L;
    private Thread thread;
    private boolean running;
    private int WIDTH = 800;
    private int HEIGHT = 600;
    private int mouseX = 0;
    private int mouseY = 0;
    private int angle = 0;
    private BufferedImage pizza;
    private Handler handler = new Handler();
    private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};


    private Main(){

        ResourceLoader resource = new ResourceLoader();

        pizza = new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
        try {
            pizza = ImageIO.read(Objects.requireNonNull(ResourceLoader.getResource("images/pizza.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addKeyListener(new Input(handler));
        for(int i = 0; i < 10; i++) {
            CircleSprite cs = new CircleSprite((int)Math.floor(Math.random()*300+100), (int)Math.floor(Math.random()*300+100));
            cs.setColor(colors[(int)Math.floor(Math.random()*colors.length)]);
            handler.addObject(cs);
        }
        new View(800, 600, "Spinny", this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.requestFocus();
    }
    public static void main(String[] args) {
        new Main();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now; while(delta >=1) {
                tick(); delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0; }
        } stop();
    }

    private void tick(){
        angle++;
        handler.tick(mouseX, mouseY);
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);
        g.setColor(Color.GRAY);
        g.fillOval(100, 100, 400, 400);
        g.drawLine(300,300,800,300);

        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        // The required drawing location
        int drawLocationX = 300;
        int drawLocationY = 300;

        // Rotation information

        double rotationRequired = Math.toRadians (angle);
        double locationX = pizza.getWidth() / 2;
        double locationY = pizza.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g2.drawImage(op.filter(pizza, null), drawLocationX-pizza.getWidth()/2, drawLocationY-pizza.getHeight()/2, null);
        //g2.fillOval(drawLocationX-340/2, drawLocationY-340/2, 340, 340);

        g2.dispose();

        handler.render(g);

        bs.show();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        handler.holdcheck(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handler.letgo();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
