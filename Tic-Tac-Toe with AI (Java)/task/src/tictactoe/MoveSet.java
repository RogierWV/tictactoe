package tictactoe;

public record MoveSet(int score, Cell cell) {
    public MoveSet invertScore() {
        return new MoveSet(-score, cell);
    }
}
