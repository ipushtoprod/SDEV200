import java.util.ArrayList;
import java.util.List;

public class PlayingField {
    private static final int GRID_SIZE = 10;
    private final Cell[][] grid;
    private final List<Ship> ships;
    private int remainingShips;

    public PlayingField() {
        this.grid = new Cell[GRID_SIZE][GRID_SIZE];
        this.ships = new ArrayList<>();
        this.remainingShips = 0;
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                grid[row][col] = new Cell();
            }
        }
    }

    public boolean placeShip(Ship ship, int startRow, int startCol) {

        if (!isValidPlacement(ship, startRow, startCol)) {
            return false;
        }

        int size = ship.getSize();
        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            for (int col = startCol; col < startCol + size; col++) {
                grid[startRow][col].setOccupied(true);
                grid[startRow][col].setShip(ship);
            }
        } else {
            for (int row = startRow; row < startRow + size; row++) {
                grid[row][startCol].setOccupied(true);
                grid[row][startCol].setShip(ship);
            }
        }

        ships.add(ship);
        remainingShips++;
        return true;
    }

    private boolean isValidPlacement(Ship ship, int startRow, int startCol) {
        int size = ship.getSize();
        
        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            if (startCol + size > GRID_SIZE) return false;
        } else {
            if (startRow + size > GRID_SIZE) return false;
        }

        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            for (int col = startCol; col < startCol + size; col++) {
                if (grid[startRow][col].isOccupied()) {
                    return false;
                }
            }
        } else {
            for (int row = startRow; row < startRow + size; row++) {
                if (grid[row][startCol].isOccupied()) {
                    return false;
                }
            }
        }

        return true;
    }

    public AttackResult receiveAttack(int row, int col) {
        if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        Cell cell = grid[row][col];
        if (cell.isHit()) {
            return AttackResult.ALREADY_HIT;
        }

        cell.setHit(true);

        if (cell.isOccupied()) {
            Ship ship = cell.getShip();
            ship.hit();
            
            if (ship.isSunk()) {
                remainingShips--;
                return AttackResult.SUNK;
            }
            return AttackResult.HIT;
        }
        
        return AttackResult.MISS;
    }

    public boolean allShipsSunk() {
        return remainingShips == 0;
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public enum AttackResult {
        HIT, MISS, SUNK, ALREADY_HIT
    }
}