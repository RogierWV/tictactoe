package tictactoe;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

public class HardAI extends AI {
    ForkJoinPool pool;
    public HardAI(Cell.val value) {
        super(value);
        pool = ForkJoinPool.commonPool();
    }

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"hard\"");
        MoveSet optimalMove = pool.invoke(new MinimaxTask(field.clone(), value, null, true));
        if (null == optimalMove.cell()) throw new RuntimeException("Can't make an optimal move!");
//        System.out.println(field);
        field.put(optimalMove.cell().x, optimalMove.cell().y, value);
    }

    private static class MinimaxTask extends RecursiveTask<MoveSet> {
        private final Field f;
        private final Cell.val v;
        private final Cell c;
        private final boolean max;

        public MinimaxTask(Field f, Cell.val v, Cell c, boolean max) {
            this.f = f;
            this.v = v;
            this.c = c;
            this.max = max;
        }

        @Override
        protected MoveSet compute() {
//            System.out.println();
            Field.win win = f.checkGameState();
            if(win.name().equals(v.name())) return new MoveSet(1, c); //win
            else if(win.name().equals(Cell.getOpposite(v).name())) return new MoveSet(-1, c); //loss
            else if(win == Field.win.DRAW) return new MoveSet(0, c); //draw
            else //if(win == Field.win.NOT_FINISHED) {
                //for each possible move call minimax with new state and get best result
                return f.getEmptyCells().stream()
//                        .peek(c -> System.out.printf("{%d,%d,%s}", c.x, c.y, c.value))
                        .map(c -> new MinimaxTask(f.clone().put(c.x, c.y, v), Cell.getOpposite(v), new Cell(c.x, c.y, v), !max).fork())
                        .map(ForkJoinTask::join)
                        // invert or no-op
                        .map((max)
                                ? MoveSet::invertScore // invert values
                                : Function.identity()) // no-op
//                        .peek(System.out::print) //debug
                        // min or max
                        .max((max)
                                ? Comparator.comparingInt(MoveSet::score)
                                : Comparator.comparingInt(MoveSet::score).reversed())
                        .orElse(new MoveSet(0, c));
        }
    }
}
