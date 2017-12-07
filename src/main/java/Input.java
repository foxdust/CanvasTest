import GameSprites.CircleSprite;
import GameSprites.GameObject;
import sun.awt.image.ImageWatched;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Input extends KeyAdapter {

    private Handler handler;
    private LinkedHashSet<Integer> keys = new LinkedHashSet<>();

    public Input(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        keys.add(e.getKeyCode());
        System.out.println(keys);
        LinkedList<CircleSprite> sprites = handler.getSprites();
        for (CircleSprite go:sprites
             ) {
            go.setKeys(keys);
        }
        System.out.println((char)e.getKeyCode());
    }
    public void keyReleased(KeyEvent e){
        keys.remove(e.getKeyCode());
        System.out.println(keys);
        LinkedList<CircleSprite> sprites = handler.getSprites();
        for (CircleSprite go : sprites
                ) {
            go.setKeys(keys);
        }
    }
}
