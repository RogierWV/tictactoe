package tictactoe;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class HardAI extends AI {
    ForkJoinPool pool;
    public HardAI(Cell.val value) {
        super(value);
        pool = ForkJoinPool.commonPool();
    }

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"hard\"");
        MoveSet optimalMove = pool.invoke(new MinimaxTask(field.clone(), value));
        if (optimalMove.cell() == null) {
            throw new RuntimeException("Can't make an optimal move!");
        }
        field.put(optimalMove.cell().x, optimalMove.cell().y, optimalMove.cell().value);
    }

    private class MinimaxTask extends RecursiveTask<MoveSet> {
        private final Field f;
        private final Cell.val v;

        public MinimaxTask(Field f, Cell.val v) {
            this.f = f;
            this.v = v;
        }

        @Override
        protected MoveSet compute() {
            Field.win win = f.checkGameState();
            if(win.name().equals(value.name())) return new MoveSet(1, null); //win
            if(win.name().equals(Cell.getOpposite(value).name())) return new MoveSet(-1, null); //loss
            if(win == Field.win.DRAW) return new MoveSet(0, null); //draw
            if(win == Field.win.NOT_FINISHED) {
                //for each possible move call minimax with new state and get best result
                //TODO: figure out why it touches non empty cells
                return f.getEmptyCells().stream()
                        .map(c -> new MinimaxTask(f.clone().put(c.x, c.y, v), Cell.getOpposite(v)).fork())
                        .map(ForkJoinTask::join)
                        // opponent's move so invert the value
                        .map(MoveSet::invertScore)
                        .peek(System.out::println) //debug
                        .max(Comparator.comparingInt(MoveSet::score))
                        .get();
            }
            return new MoveSet(0, null);
        }
    }
}
