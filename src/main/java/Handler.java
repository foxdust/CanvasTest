import GameSprites.GameObject;

import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Handler for all objects, loops through tick and render functions
 * Also loops through checking for a held object and letting it go
 */
public class Handler {
    private LinkedList<GameObject> sprites = new LinkedList<>();

    public LinkedList getSprites(){
        return sprites;
    }

    public void tick(int mouseX, int mouseY){
        for (GameObject go:sprites
                ) {
            go.tick(mouseX, mouseY);
            go.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).render(g);
        }
    }

    public void letgo(){
        for (GameObject go:sprites
                ) {
            go.letgo();
        }
    }

    public void holdcheck(int mouseX, int mouseY){
        boolean check = false;
        GameObject obj = null;
        for (int i = sprites.size()-1; i >= 0; i--){
            if (!check) {
                check = sprites.get(i).holdcheck(mouseX, mouseY);
                if (check) {
                    obj = sprites.get(i);
                }
            }
        }
        if (obj != null) {
            sprites.remove(obj);
            sprites.add(obj);
        }
    }

    public void addObject(GameObject gameobject){
        sprites.add(gameobject);
    }

    public void removeObject(GameObject gameobject){
        sprites.remove(gameobject);
    }
}
