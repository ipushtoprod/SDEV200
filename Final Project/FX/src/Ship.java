import javafx.scene.Parent;

public class Ship extends Parent {
    public int category;
    public boolean horizontal = false;

    private int durability;

    public Ship(int category, boolean horizontal) {
        this.category = category;
        this.horizontal = horizontal;
        durability = category;
    }

    public void damage() {
        durability--;
    }

    public boolean isOperational() {
        return durability > 0;
    }
}