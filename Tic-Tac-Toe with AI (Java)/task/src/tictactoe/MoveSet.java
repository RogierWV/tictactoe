package tictactoe;

public record MoveSet(int score, Cell cell) {
    public MoveSet invertScore() {
        return new MoveSet(-score, cell);
    }

    @Override
    public String toString() {
        return "MoveSet{" +
                "score=" + score +
                ", cell={" + cell.x +
                ", " + cell.y +
                ", " + cell.value +
                "}}";
    }
}
