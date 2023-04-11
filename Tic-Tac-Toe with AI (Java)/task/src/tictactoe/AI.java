package tictactoe;

import java.util.Random;

public abstract class AI implements Player {
    Random r;
    Cell.val value;

    public AI(Cell.val value) {
        this.r = new Random();
        this.value = value;
    }

    @Override
    public abstract void move(Field field);

    public boolean shouldPut(Field field, int x, int y, Cell.val c) {
        try {
            field.put(x,y,c);
            return false;
        } catch(Exception e) {
            return true;
        }
    }
}
