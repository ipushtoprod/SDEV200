import javafx.scene.Parent;

//basic ship stats and tying in ship "health" with the length of the ship
public class Ship extends Parent {
    public int length;
    public boolean horizontal = false;

    private int durability;

    public Ship(int length, boolean horizontal) {
        this.length = length;
        this.horizontal = horizontal;
        durability = length;
    }

    public void damage() {
        durability--;
    }

    public boolean isOperational() {
        return durability > 0;
    }
}
