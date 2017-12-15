package GameSprites;

import java.util.LinkedHashSet;

/**
 * Game Object class, for interactable objects
 * Adds check for being held and dragged around to base sprite
 */
public abstract class GameObject implements Sprites {
    protected int x, y;
    protected int width, height;
    protected boolean holdable = false;
    protected boolean held = false;
    protected boolean released = false;
    protected double scale = 1;
    protected double renderScale = 1;
    protected double grabLenience = 0.1;
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
        if (held) {
            held = false;
            released = true;
        }
    }
    public boolean holdcheck(int mouseX, int mouseY){
        if (holdable) {
            double scalar = this.scale * this.renderScale;
            double width = this.width*scalar;
            System.out.println(scalar);
            double height = this.height*scalar;
            int hitX = this.x-(int)(width/2)+this.width/2;
            int hitY = this.y-(int)(height/2)+this.width/2;
            if ((mouseX >= hitX+(width*grabLenience)) && (mouseX <= hitX + width - (width*grabLenience)) && (mouseY >= hitY + (height*grabLenience)) && (mouseY <= hitY + height - (height*grabLenience))) {
                held = true;
                return true;
            }
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
