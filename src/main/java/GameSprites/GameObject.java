package GameSprites;

import java.util.LinkedHashSet;

/**
 * Game Object class, for interactable objects
 * Adds check for being held and dragged around to base sprite
 */
public abstract class GameObject implements Sprites {
    protected int x, y;
    protected int width, height;
    protected boolean held = false;
    protected LinkedHashSet<Integer> keys = new LinkedHashSet<>();

    public GameObject(){
        width = 32;
        height = 32;
        x = 0;
        y = 0;
    }
    public GameObject(int x, int y){
    }
    public void letgo(){
        held = false;
    }
    public boolean holdcheck(int mouseX, int mouseY){
        if ((mouseX >= x)&&(mouseX <= x+width)&&(mouseY >= y)&&(mouseY <= y+height)) {
            held = true;
            return true;
        }
        return false;
    }
    public void tick(int mouseX, int mouseY){
        if (held){
            setX(mouseX-(width/2));
            setY(mouseY-(height/2));
        }
    }
    public void tick(){

    }
    public void setKeys(LinkedHashSet keys){
        this.keys = keys;
    }
}
