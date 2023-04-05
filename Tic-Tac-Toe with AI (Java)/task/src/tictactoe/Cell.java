package tictactoe;

public class Cell {
    int x;
    int y;
    char value;
    public static final char DEFAULT = '_';

    public Cell(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Cell(int x, int y) {
        this(x, y, DEFAULT);
    }

    public String toString() {
        return String.valueOf((value == '_')?' ':value);
    }
}
