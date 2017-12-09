import GameSprites.GameObject;

import java.util.LinkedList;
import java.awt.Graphics;

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
        for (GameObject go:sprites
                ) {
            go.render(g);
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
        for (GameObject go:sprites
                ) {
            if (!check) {
                check = go.holdcheck(mouseX, mouseY);
            }
        }
    }

    public void addObject(GameObject gameobject){
        sprites.add(gameobject);
    }

    public void removeObject(GameObject gameobject){
        sprites.remove(gameobject);
    }
}
