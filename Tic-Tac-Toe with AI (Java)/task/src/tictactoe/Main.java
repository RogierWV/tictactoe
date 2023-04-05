package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Field field = new Field();
        Scanner s = new Scanner(System.in);
        field.parse(s.nextLine().trim());
        System.out.println(field);
    }
}
