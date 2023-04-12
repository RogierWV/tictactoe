package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Field implements Cloneable {
    Cell[][] cells;

    @Override
    public Field clone() {
//            Field clone = (Field) super.clone();
//            clone.cells = cells.clone();
//            for (int x = 0; x < 3; x++) {
//                for (int y = 0; y < 3; y++) {
//                    clone.cells[x][y] = cells[x][y].clone();
//                }
//            }
//            return clone;
        Field clone = new Field();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                clone.cells[i][j] = new Cell(cells[i][j].x, cells[i][j].y, cells[i][j].value);
        return clone;
    }

    public List<Cell> getEmptyCells() {
        List<Cell> ret = new ArrayList<>();
        for (int x = 0; x < 3; x++)
            for(int y = 0; y< 3; y++)
                if(cells[x][y].value == Cell.val.DEFAULT) ret.add(cells[x][y]);
        return ret;
    }

    enum win {
        X, O, DRAW, NOT_FINISHED
    }
    public Field() {
        cells = new Cell[3][3];
        for(int x = 1; x <= 3; x++)
            for(int y = 1; y <= 3; y++)
                cells[x-1][y-1] = new Cell(x,y);
    }

    public void parse(String input) {
        int i = 0;
        for(int x = 1; x <= 3; x++)
            for(int y = 1; y <= 3; y++)
                cells[x-1][y-1].value = switch(input.charAt(i++)) {
                    case 'X' -> Cell.val.X;
                    case 'O' -> Cell.val.O;
                    default -> Cell.val.DEFAULT;
                };
    }

    public Field put(String coords, Cell.val value) {
        if(!coords.trim().matches("\\d\\s\\d")) throw new IllegalArgumentException("You should enter numbers!");
        int x = Integer.parseInt(coords, 0, 1, 10);
        int y = Integer.parseInt(coords, 2, 3, 10);
        return put(x, y, value);
    }

    public Field put(int x, int y, Cell.val value) {
        if (x > 3 || y > 3) throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        if (cells[x-1][y-1].value != Cell.val.DEFAULT) throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        cells[x-1][y-1].value = value;
        return this;
    }

    public Cell.val getPlayer() {
        int xs = 0;
        int os = 0;
        for(int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++) {
                if (cells[x][y].value == Cell.val.X) xs++;
                else if (cells[x][y].value == Cell.val.O) os++;
            }
        if(xs > os) return Cell.val.O;
        return Cell.val.X;
    }

    public win checkGameState() {
        for(int x = 0; x < 3; x++)
            if(cells[x][0].value != Cell.val.DEFAULT &&
                    cells[x][0].value == cells[x][1].value &&
                    cells[x][1].value == cells[x][2].value)
                return win.valueOf(String.valueOf(cells[x][0].value));
        for(int y = 0; y < 3; y++)
            if(cells[0][y].value != Cell.val.DEFAULT &&
                    cells[0][y].value == cells[1][y].value &&
                    cells[1][y].value == cells[2][y].value)
                return win.valueOf(String.valueOf(cells[0][y].value));
        if(cells[1][1].value != Cell.val.DEFAULT &&
                cells[0][0].value == cells[1][1].value &&
                cells[1][1].value == cells[2][2].value)
            return win.valueOf(String.valueOf(cells[1][1].value));
        if(cells[1][1].value != Cell.val.DEFAULT &&
                cells[0][2].value == cells[1][1].value &&
                cells[1][1].value == cells[2][0].value)
            return win.valueOf(String.valueOf(cells[1][1].value));
        for(int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (cells[x][y].value == Cell.val.DEFAULT) return win.NOT_FINISHED;
        return win.DRAW;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("---------\n");
        for(int x = 1; x <= 3; x++) {
            sb.append("| ");
            for (int y = 1; y <= 3; y++) {
                sb.append(cells[x-1][y-1].toString());
                sb.append(' ');
            }
            sb.append("|\n");
        }
        sb.append("---------\n");
        return sb.toString();
    }
}
