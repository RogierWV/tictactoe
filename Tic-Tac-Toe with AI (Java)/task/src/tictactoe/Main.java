package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    public class Main {
    static Field field;
    static Scanner scanner;
    static Map<Cell.val, Player> players;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("Input command: ");
                String[] cmd = scanner.nextLine().split("\\s+");
                switch (cmd[0].trim().toLowerCase()) {
                    case "start" -> {
                        if (cmd.length != 3) throw new IllegalArgumentException("Bad parameters!");
                        startGame(cmd[1], cmd[2]);
                    }
                    case "exit" -> System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void startGame(String p1, String p2) {
        field = new Field();
        players = new HashMap<>();
        createPlayer(p1, Cell.val.X);
        createPlayer(p2, Cell.val.O);
        System.out.println(field);
        do {
            update();
        } while(shouldRun());
    }

    public static void createPlayer(String type, Cell.val value) {
        players.put(value, switch(type.trim().toLowerCase()) {
            case "user" -> new User(value, scanner);
            case "easy" -> new EasyAI(value);
            case "medium" -> new MediumAI(value);
            case "hard" -> new HardAI(value);
            default -> throw new IllegalArgumentException("Bad parameters!");
        });
    }
    public static void update() {
        players.get(field.getPlayer()).move(field);
        System.out.println(field);
    }

    public static boolean shouldRun() {
        boolean r = false;
        switch (field.checkGameState()) {
            case NOT_FINISHED -> r = true;
            case DRAW -> System.out.println("Draw");
            case X -> System.out.println("X wins");
            case O -> System.out.println("O wins");
        }
        return r;
    }
}
