package tictactoe;

import java.util.Scanner;

public class User implements Player {
    Scanner scanner;
    Cell.val value;
    public User(Cell.val value, Scanner scanner) {
        this.value = value;
        this.scanner = scanner;
    }

    @Override
    public void move(Field field) {
        try {
            System.out.print("Enter the coordinates: ");
            field.put(scanner.nextLine().trim(), value);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            move(field);
        }
    }
}
