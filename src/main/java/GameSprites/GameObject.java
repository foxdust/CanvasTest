package GameSprites;

public abstract class GameObject implements Sprites {
    protected int x, y;
    protected int width, height;
    protected boolean held = false;
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
            x = mouseX-(width/2);
            y = mouseY-(height/2);
        }
    }
}
