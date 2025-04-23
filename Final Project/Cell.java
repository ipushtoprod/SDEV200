public class Cell {
    private boolean isOccupied;
    private boolean isHit;
    private Ship ship;

    public Cell() {
        this.isOccupied = false;
        this.isHit = false;
        this.ship = null;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
    public boolean isHit() {
        return isHit;
    }
    public Ship getShip() {
        return ship;
    }
    
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    public void setHit(boolean hit) {
        isHit = hit;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }
}