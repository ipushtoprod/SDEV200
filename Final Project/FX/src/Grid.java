import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends Parent {
    private VBox columns = new VBox();
    private boolean isOpponent = false;
    public int remainingShips = 5;

    // handles mouse listening and attaches stuff for each tile
    public Grid(boolean isOpponent, EventHandler<? super MouseEvent> handler) {
        this.isOpponent = isOpponent;
        for (int row = 0; row < 10; row++) {
            HBox column = new HBox();
            for (int col = 0; col < 10; col++) {
                Tile t = new Tile(col, row, this);
                t.setOnMouseClicked(handler);
                column.getChildren().add(t);
            }

            columns.getChildren().add(column);
        }

        getChildren().add(columns);
    }

    //ship placement logic, decreasing ship lengths by one each time a ship is placed. colors tiles when they are filled with a ship.
    public boolean placeShip(Ship ship, int col, int row) {
        if (canPlaceShip(ship, col, row)) {
            int length = ship.length;

            if (!ship.horizontal) {
                for (int i = row; i < row + length; i++) {
                    Tile tile = getTile(col, i);
                    tile.ship = ship;
                    if (!isOpponent) {
                        tile.setFill(Color.CYAN);
                        tile.setStroke(Color.BLACK);
                    }
                }
            }
            else {
                for (int i = col; i < col + length; i++) {
                    Tile tile = getTile(i, row);
                    tile.ship = ship;
                    if (!isOpponent) {
                        tile.setFill(Color.CYAN);
                        tile.setStroke(Color.BLACK);
                    }
                }
            }

            return true;
        }

        return false;
    }

    public Tile getTile(int col, int row) {
        if (!isPositionValid(col, row)) {
            return null;
        }
        return (Tile)((HBox)columns.getChildren().get(row)).getChildren().get(col);
    }

    //really questionable way of making sure that ships can't stack, has some issues but it works
    private Tile[] getAdjacentTiles(int col, int row) {
        Point2D[] positions = new Point2D[] {
                new Point2D(col - 1, row),
                new Point2D(col + 1, row),
                new Point2D(col, row - 1),
                new Point2D(col, row + 1)
        };

        List<Tile> adjacentTiles = new ArrayList<Tile>();

        for (Point2D pos : positions) {
            if (isPositionValid(pos)) {
                adjacentTiles.add(getTile((int)pos.getX(), (int)pos.getY()));
            }
        }

        return adjacentTiles.toArray(new Tile[0]);
    }

    //see above
    private boolean canPlaceShip(Ship ship, int col, int row) {
        int length = ship.length;

        if (!ship.horizontal) {
            for (int i = row; i < row + length; i++) {
                if (!isPositionValid(col, i))
                    return false;

                Tile tile = getTile(col, i);
                if (tile == null || tile.ship != null)
                    return false;

                for (Tile adjacent : getAdjacentTiles(col, i)) {
                    if (adjacent != null && adjacent.ship != null)
                        return false;
                }
            }
        }
        else {
            for (int i = col; i < col + length; i++) {
                if (!isPositionValid(i, row))
                    return false;

                Tile tile = getTile(i, row);
                if (tile == null || tile.ship != null)
                    return false;

                for (Tile adjacent : getAdjacentTiles(i, row)) {
                    if (adjacent != null && adjacent.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isPositionValid(Point2D position) {
        return isPositionValid(position.getX(), position.getY());
    }

    private boolean isPositionValid(double col, double row) {
        return col >= 0 && col < 10 && row >= 0 && row < 10;
    }

    public class Tile extends Rectangle {
        public int col, row;
        public Ship ship = null;
        public boolean isHit = false;

        private Grid grid;

        public Tile(int col, int row, Grid grid) {
            super(35, 35);
            this.col = col;
            this.row = row;
            this.grid = grid;
            setFill(Color.WHITE);
            setStroke(Color.BLACK);
        }

        public boolean fireAt() {
            isHit = true;
            setFill(Color.BLACK);

            if (ship != null) {
                ship.damage();
                setFill(Color.RED);
                if (!ship.isOperational()) {
                    grid.remainingShips--;
                }
                return true;
            }

            return false;
        }
    }
}
