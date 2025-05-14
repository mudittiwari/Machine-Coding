import PlayingBoard.Board;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        Board board = new Board(8, 2, 9);
        // Add snakes
        board.addSnake(62, 5);
        board.addSnake(33, 6);
        board.addSnake(49, 9);
        board.addSnake(88, 16);
        board.addSnake(41, 20);
        board.addSnake(56, 53);
        board.addSnake(98, 64);
        board.addSnake(93, 73);
        board.addSnake(95, 75);

        // Add ladders
        board.addLadder(2, 37);
        board.addLadder(27, 46);
        board.addLadder(10, 32);
        board.addLadder(51, 68);
        board.addLadder(61, 79);
        board.addLadder(65, 84);
        board.addLadder(71, 91);
        board.addLadder(81, 100);

        // Add players
        board.addPlayer("Gaurav", 0);
        board.addPlayer("Sagar", 0);

        // Player turn order
        List<String> playerOrder = Arrays.asList("Gaurav", "Sagar");

        // Predefined dice rolls as per your example output
        List<Integer> diceRolls = Arrays.asList(
                6, 1, 6, 4, 4, 6, 5, 4, 1, 6, 6, 2, 6, 6, 5, 2,
                2, 5, 3, 5, 6, 3, 2, 3, 3, 5, 3, 4, 2, 5, 2, 6,
                3, 3, 5, 2, 5, 5, 5, 6, 5, 1, 4, 2, 5, 6, 3, 4,
                1, 1, 1, 5, 6, 3
        );

        int turn = 0;

        for (int i = 0; i < diceRolls.size(); i++) {
            String currentPlayer = playerOrder.get(turn % playerOrder.size());
            int dice = diceRolls.get(i);
            int oldPosition = getPlayerPosition(board, currentPlayer);
            boolean moved = board.updatePlayerLocation(currentPlayer, dice);
            int newPosition = getPlayerPosition(board, currentPlayer);
            if (moved) {
                System.out.println(currentPlayer + " rolled a " + dice + " and moved from " + oldPosition + " to " + newPosition);
                if(getPlayerResult(board, currentPlayer)) {
                    System.out.println(currentPlayer + " wins the game");
                    break;
                }
            }
            turn++;
        }
    }

    // Helper method to get player's current location
    private static Boolean getPlayerResult(Board board, String name) {
        for (String p : board.getPlayersWon()) {
            if (p.equals(name)) {
                return true;
            }
        }
        return false;
    }
    private static int getPlayerPosition(Board board, String name) {
        for (Player p : board.getPlayers()) {
            if (p.getPlayerName().equals(name)) {
                return p.getCurrentLocation();
            }
        }
        return -1;
    }
}
