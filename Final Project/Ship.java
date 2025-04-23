public class Ship {
    public enum Orientation { HORIZONTAL, VERTICAL }

    private final String name;
    private final int size;
    private final Orientation orientation;
    private int hits;

    public Ship(String name, int size, Orientation orientation) {
        this.name = name;
        this.size = size;
        this.orientation = orientation;
        this.hits = 0;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public Orientation getOrientation() {
        return orientation;
    }
}