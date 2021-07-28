import java.util.*;

/**
 * The RockPaperScissors program takes user input
 * from standard input and enables them to battle
 * the computer in a game of rock paper scissors.
 * The results are output to standard out.
 *
 * @author Kyle Walker
 * @version 1.0
 * @since 2021-07-27
 */
public class RockPaperScissors {

    public static void main(String[] args) {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        while (playGame());
        System.out.println("\nThank you for playing.");
    }

    /**
     * Main game method that configures the game
     * and loops through the rounds.
     *
     * @return boolean specifying game replay
     */
    public static boolean playGame() {

        List<String> options = new ArrayList<>();
        options.add("rock");
        options.add("paper");
        options.add("scissors");

        Random rand = new Random();

        // [Win, Loss, Tie]
        int[] playerScore = new int[3];
        int[] compScore = new int[3];

        Scanner input = new Scanner(System.in);

        // User round specification
        int rounds = 0;

        while (rounds <= 0 || rounds > 10) {
            try {
                System.out.print("How many rounds would you like to play(max 10)? ");
                rounds = input.nextInt();
                input.nextLine();

                if (rounds > 10)
                    System.out.println("Max rounds exceeded.");
                else if (rounds < 0)
                    System.out.println("Invalid number");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }

        for (int i = 0; i < rounds; i++) {
            // Query user choice (rock, paper, scissors)
            String userChoice = getInput(input, options);

            String compChoice = options.get(rand.nextInt(3));
            System.out.println("The computer chose " + compChoice + ".");

            // Display result of round (user win: 1, user loss: 0, tie: 2)
            int result = compare(userChoice, compChoice);

            switch (result) {
                // Loss
                case 0:
                    System.out.println("You lost this round.");
                    compScore[0] += 1;
                    playerScore[1] += 1;

                    break;
                // Win
                case 1:
                    System.out.println("You won this round!");
                    playerScore[0] += 1;
                    compScore[1] += 1;

                    break;
                // Tie
                default:
                    System.out.println("It is a tie!");
                    playerScore[2] += 1;
                    compScore[2] += 1;

                    break;
            }
        }

        // Tally the scores
        System.out.println("\nThe final scores are:");
        System.out.printf("%10s%10s%10s\n", "Wins", "Losses", "Ties");
        System.out.printf("User: %d%10d%10d\n", playerScore[0], playerScore[1], playerScore[2]);
        System.out.printf("Comp: %d%10d%10d\n", compScore[0], compScore[1], compScore[2]);

        // Ask if they want to play again
        System.out.print("\nWould you like to play again(y, n)? ");

        if (input.nextLine().equals("y")) {
            return true;
        } else {
            input.close();
            return false;
        }

    }

    /**
     * Queries the user for input until a valid move
     * is given.
     *
     * @param sc Scanner for user input
     * @param options List of valid moves
     * @return String containing user input
     */
    public static String getInput(Scanner sc, List<String> options) {
        System.out.print("Please enter a move (rock, paper, scissors): ");
        String input = sc.nextLine();

        while (!options.contains(input)) {
            System.out.println("Invalid input.");
            System.out.print("Please enter a move (rock, paper, scissors): ");
            input = sc.nextLine().toLowerCase();
        }

        return input;
    }

    /**
     * Takes the user's and computer's move choice
     * and compares them to reach a result.
     *
     * @param userChoice User's move
     * @param compChoice Computer's move
     * @return Result of round; 0=loss 1=win 2=tie
     */
    public static int compare(String userChoice, String compChoice) {
        if (userChoice.equals("rock")) {
            if (compChoice.equals("paper"))
                return 0;
            else if (compChoice.equals("scissors"))
                return 1;
            else
                return 2;

        } else if (userChoice.equals("paper")) {
            if (compChoice.equals("rock"))
                return 1;
            else if (compChoice.equals("scissors"))
                return 0;
            else
                return 2;

        } else {
            if (compChoice.equals("paper"))
                return 1;
            else if (compChoice.equals("rock"))
                return 0;
            else
                return 2;
        }
    }

}
