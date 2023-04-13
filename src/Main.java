import exceptions.InvalidPositionException;
import exceptions.PositionException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Game game = new Game();
    private static Scanner input = new Scanner(System.in);

    private static void displayGameBoard() {
        for (var row : game.displayGameBoard()) System.out.println(Arrays.toString(row));
    }

    private static int getUserInput() {
        String playersName = game.getCurrentPlayersName();
        System.out.println(playersName + ", please select number between 1 and 9 to play in a position on the game board: ");
        int position = input.nextInt();
        return position;
    }

    public static void main(String[] args) throws PositionException {
        int noOfPlayers = 2;

        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name: ");
            String name = input.next();
            game.registerPlayers(name, i);
            game.giveSymbol();
        }

        displayGameBoard();

        do {
            int position = getUserInput();
            try {
                game.play(position);
            }catch (PositionException | InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            displayGameBoard();
        } while (!game.isWon() && !game.isTie());
        if (game.isWon()) System.out.println("Congratulations!, " + game.getCurrentPlayersName() + " you won!!");
        if (game.isTie()) System.out.println("It's a tie!");
    }
}
