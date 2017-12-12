import GameSprites.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Input class, handles keypress checks
 */
public class Input extends KeyAdapter {

    private Handler handler;
    private LinkedHashSet<Integer> keys = new LinkedHashSet<>();

    public Input(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        keys.add(e.getKeyCode());
        System.out.println(keys);
        LinkedList<GameObject> sprites = handler.getSprites();
        for (GameObject go:sprites
             ) {
            go.setKeys(keys);
        }
        System.out.println((char)e.getKeyCode());
    }
    public void keyReleased(KeyEvent e){
        keys.remove(e.getKeyCode());
        System.out.println(keys);
        LinkedList<GameObject> sprites = handler.getSprites();
        for (GameObject go : sprites
                ) {
            go.setKeys(keys);
        }
    }
}
