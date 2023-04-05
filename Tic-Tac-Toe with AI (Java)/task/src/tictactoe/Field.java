package tictactoe;

public class Field {
    Cell[] cells;

    public Field() {
        cells = new Cell[9];
        int count = 0;
        for(int x = 1; x <= 3; x++)
            for(int y = 1; y <= 3; y++)
                cells[count++] = new Cell(x,y);
    }

    public void parse(String input) {
        for(int i = 0; i < 9; i++) {
            cells[i].value = input.charAt(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("---------\n");
        int count = 0;
        for(int x = 1; x <= 3; x++) {
            sb.append("| ");
            for (int y = 1; y <= 3; y++) {
                sb.append(cells[count++].toString());
                sb.append(' ');
            }
            sb.append("|\n");
        }
        sb.append("---------\n");
        return sb.toString();
    }
}
