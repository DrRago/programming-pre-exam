import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Leonhard Gahr
 */
class Game {
    private final Board board;
    private final Player[] players;
    private int numLegs;

    Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    /**
     * play the game
     */
    void start() {
        numLegs = 0;

        Scanner scanner = new Scanner(System.in);

        gameloop:
        while (!isFinished()) {
            // determine the current player
            Player currentPlayer = players[numLegs % players.length];

            System.out.printf("Player: %s, %d points remaining.%n", currentPlayer, currentPlayer.getRemainingPoints());

            if (currentPlayer.getRemainingPoints() <= 170) {
                System.out.printf("Possible checkout: %s", getHint(currentPlayer.getRemainingPoints()));
            }

            System.out.print("Enter visit: ");

            String[] input = scanner.nextLine().trim().split(" ");
            Field[] hitFields = new Field[input.length];

            for (int i = 0; i < hitFields.length; i++) {
                hitFields[i] = board.parseField(input[i]);
                if (hitFields[i] == null) {
                    System.out.println("invalid input, repeat");
                    continue gameloop;
                }
            }

            Visit currentVisit = new Visit(hitFields);
            if (!currentPlayer.addVisit(currentVisit)) {
                // not sure whether a message should be printed if the visit was invalid or not
                System.out.println("Invalid visit!");
            } else {
                System.out.printf("Scored: %d%n", currentVisit.getValue());
            }
            System.out.println("=".repeat(25));
            numLegs++;
        }

        if (numLegs >= 10) {
            System.out.println("You're too bad for this game!");
        } else {
            Player winner = players[(numLegs - 1) % players.length];
            System.out.printf("Game shot and the leg, %s!%n", winner);
            concludeGame(winner);
        }
    }

    /**
     * get a checkout way for num of points
     *
     * @param points the points
     * @return the checkout way
     */
    private String getHint(int points) {
        String hintFile = "checkouts.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(hintFile));

            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < points - 1) {
                count++;
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * write the winner to file
     *
     * @param winner the winner
     */
    private void concludeGame(Player winner) {
        String highscorePath = "highscore.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(highscorePath, true));
            bw.write(String.format("%s won with %d darts%n", winner, winner.getCountDartsThrown()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFinished() {
        return Arrays.stream(players).anyMatch(player -> player.getRemainingPoints() == 0) || numLegs >= 10;
    }
}
