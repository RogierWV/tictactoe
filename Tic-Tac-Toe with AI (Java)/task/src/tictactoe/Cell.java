package tictactoe;

public class Cell {
    int x;
    int y;
    val value;
    public enum val {
        X, O, DEFAULT
    }
    public Cell(int x, int y, val value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Cell(int x, int y) {
        this(x, y, val.DEFAULT);
    }

    public static val getOpposite(val v) {
        return switch (v) {
            case O -> val.X;
            case X -> val.O;
            case DEFAULT -> val.DEFAULT;
        };
    }

    public String toString() {
        return String.valueOf((value == val.DEFAULT)?' ':value);
    }
}
