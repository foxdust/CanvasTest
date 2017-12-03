import GameSprites.CircleSprite;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable, MouseListener, MouseMotionListener{
    private static final long serialVersionUID = -6704652798820883887L;
    private Thread thread;
    private boolean running;
    private int WIDTH = 800;
    private int HEIGHT = 600;
    private int mouseX = 0;
    private int mouseY = 0;
    private Handler handler = new Handler();

    public Main(){
        CircleSprite test = new CircleSprite(100, 20);
        CircleSprite test2 = new CircleSprite(100, 100);
        handler.addObject(test);
        handler.addObject(test2);
        new View(800, 600, "Spinny", this);
        addMouseMotionListener(this);
        addMouseListener(this);
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
                System.out.println("FPS: "+ frames);
                frames = 0; }
        } stop();
    }

    public void tick(){
        handler.tick(mouseX, mouseY);
    }
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);
        g.setColor(Color.BLUE);
        g.fillOval(290, 290, 20, 20);
        g.drawLine(300,300,800,300);
        handler.render(g);

        bs.show();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
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
