import PlayingBoard.Board;
import Player.Player;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        boolean simulationMode = true; // Toggle this to switch modes

        System.out.println("🎮 Welcome to Snake and Ladder Game! 🎮");

        int numPlayers;
        int numSnakes;
        int numLadders;
        Board board;

        List<String> playerNames = new ArrayList<>();

        if (simulationMode) {
            // --- SIMULATION MODE CONFIGURATION ---
            numPlayers = 2;
            numSnakes = 9;
            numLadders = 8;
            board = new Board(numLadders, numPlayers, numSnakes);

            // Predefined Snakes
            board.addSnake(62, 5);
            board.addSnake(33, 6);
            board.addSnake(49, 9);
            board.addSnake(88, 16);
            board.addSnake(41, 20);
            board.addSnake(56, 53);
            board.addSnake(98, 64);
            board.addSnake(93, 73);
            board.addSnake(95, 75);

            // Predefined Ladders
            board.addLadder(2, 37);
            board.addLadder(27, 46);
            board.addLadder(10, 32);
            board.addLadder(51, 68);
            board.addLadder(61, 79);
            board.addLadder(65, 84);
            board.addLadder(71, 91);
            board.addLadder(81, 100);

            // Players
            playerNames.add("Gaurav");
            playerNames.add("Sagar");
            for (String name : playerNames) board.addPlayer(name, 0);

            System.out.println("\n🎬 Running Simulation Mode...\n");
        } else {
            // --- USER INPUT MODE CONFIGURATION ---
            System.out.print("Enter number of players: ");
            numPlayers = sc.nextInt();
            System.out.print("Enter number of snakes: ");
            numSnakes = sc.nextInt();
            System.out.print("Enter number of ladders: ");
            numLadders = sc.nextInt();

            board = new Board(numLadders, numPlayers, numSnakes);

            // Snakes
            System.out.println("\n🐍 Enter snakes (start > end):");
            for (int i = 0; i < numSnakes; i++) {
                System.out.print("Snake " + (i + 1) + " Start: ");
                int start = sc.nextInt();
                System.out.print("Snake " + (i + 1) + " End: ");
                int end = sc.nextInt();
                board.addSnake(start, end);
            }

            // Ladders
            System.out.println("\n🪜 Enter ladders (start < end):");
            for (int i = 0; i < numLadders; i++) {
                System.out.print("Ladder " + (i + 1) + " Start: ");
                int start = sc.nextInt();
                System.out.print("Ladder " + (i + 1) + " End: ");
                int end = sc.nextInt();
                board.addLadder(start, end);
            }

            // Players
            sc.nextLine(); // consume newline
            System.out.println("\n🧍 Enter player names:");
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Player " + (i + 1) + " Name: ");
                String name = sc.nextLine();
                board.addPlayer(name, 0);
                playerNames.add(name);
            }
        }

        System.out.println("\n🎲 Game Start! Let's play!\n");

        // Predefined rolls for simulation
        List<Integer> simRolls = Arrays.asList(
                6, 1, 6, 4, 4, 6, 5, 4, 1, 6, 6, 2, 6, 6, 5, 2,
                2, 5, 3, 5, 6, 3, 2, 3, 3, 5, 3, 4, 2, 5, 2, 6,
                3, 3, 5, 2, 5, 5, 5, 6, 5, 1, 4, 2, 5, 6, 3, 4,
                1, 1, 1, 5, 6, 3
        );
        int rollIndex = 0;

        int turn = 0;
        while (board.getPlayersWon().size() < numPlayers) {
            String currentPlayer = playerNames.get(turn % playerNames.size());

            if (board.getPlayersWon().contains(currentPlayer)) {
                System.out.println("✅ " + currentPlayer + " already won! Skipping...");
                turn++;
                continue;
            }

            System.out.println(currentPlayer + "'s turn...");
            CLIAnimations.rollingDiceAnimation();

            int diceRoll;
            if (simulationMode) {
                if (rollIndex >= simRolls.size()) break;
                diceRoll = simRolls.get(rollIndex++);
            } else {
                System.out.print("Press enter to roll...");
                sc.nextLine();
                diceRoll = random.nextInt(6) + 1;
            }

            // Get old position
            int oldPos = board.getPlayers().stream()
                    .filter(p -> p.getPlayerName().equals(currentPlayer))
                    .findFirst().get().getCurrentLocation();

            System.out.println("🎲 " + currentPlayer + " rolled a " + diceRoll);

            CLIAnimations.loadingDots("Checking for snakes 🐍 or ladders 🪜", 3, 300);

            boolean moved = board.updatePlayerLocation(currentPlayer, diceRoll);

            // Get new position
            int newPos = board.getPlayers().stream()
                    .filter(p -> p.getPlayerName().equals(currentPlayer))
                    .findFirst().get().getCurrentLocation();

            if (moved) {
                System.out.println(currentPlayer + " moved from " + oldPos + " to " + newPos);
            } else {
                System.out.println("❌ Move invalid (start condition or over 100)");
            }

            if (newPos == 100) {
                CLIAnimations.showWinBanner(currentPlayer);
                break;
            }

            if (!simulationMode) {
                System.out.print("➡️  Continue to next turn? (y/n): ");
                String choice = sc.nextLine();
                if (!choice.equalsIgnoreCase("y")) {
                    System.out.println("👋 Exiting game...");
                    System.exit(0);
                }
            } else {
                Thread.sleep(1000); // Delay for animation effect
            }

            turn++;
        }

        System.out.println("\n🏁 Game Over!");
        System.out.println("🏆 Players who won in order:");
        for (String winner : board.getPlayersWon()) {
            System.out.println("🥇 " + winner);
        }
    }
}
