package tictactoe;

public class HardAI extends AI {
    public HardAI(Cell.val value) {
        super(value);
    }

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"hard\"");
        MoveSet optimalMove = minimax(field.clone(), value);
        if (optimalMove.cell() == null) {
            throw new RuntimeException("Can't make an optimal move!");
        }
        field.put(optimalMove.cell().x, optimalMove.cell().y, optimalMove.cell().value);
    }

    private MoveSet minimax(Field f, Cell.val value) {
        Field.win win = f.checkGameState();
        if(win.name().equals(value.name())) return new MoveSet(1, null); //win
        if(win.name().equals(Cell.getOpposite(value).name())) return new MoveSet(-1, null); //loss
        if(win == Field.win.DRAW) return new MoveSet(0, null); //draw
        if(win == Field.win.NOT_FINISHED) {

        }
        return new MoveSet(0, null);
    }
}
