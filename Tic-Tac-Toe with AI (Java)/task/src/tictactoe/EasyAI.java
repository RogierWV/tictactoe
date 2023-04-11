package tictactoe;

public class EasyAI extends AI {

    public EasyAI(Cell.val value) {
        super(value);
    }

    public void move(Field field) {
        System.out.println("Making move level \"easy\"");
        while(shouldPut(field, r.nextInt(3)+1, r.nextInt(3)+1, value));
    }
}
