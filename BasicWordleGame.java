/**
 * this is a wordle game, here a random word will be selected and u have to guess the word in 6 attempts
 * @author Gauresh Rekdo
 * @version 1.0
 * @since 14/08/2024
 * @lastmodified 21/08/2024
 */

import java.util.*;

public class BasicWordleGame {
    private static final String SECRET_WORD = "APPLE";  // Replace with your chosen word
    private static final int MAX_ATTEMPTS = 6;
    private static final int WORD_LENGTH = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> guesses = new ArrayList<>();
        boolean isGuessed = false;

        System.out.println("Welcome to Wordle!");
        System.out.println("Try to guess the 5-letter word. You have " + MAX_ATTEMPTS + " attempts.");

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            System.out.print("Enter your guess (attempt " + (attempt + 1) + "): ");
            String guess = scanner.nextLine().toUpperCase();

            if (guess.length() != WORD_LENGTH || !guess.matches("[A-Z]*")) {
                System.out.println("Invalid input. Please enter a " + WORD_LENGTH + "-letter word.");
                attempt--;  // Do not count this as an attempt
                continue;
            }

            guesses.add(guess);
            if (guess.equals(SECRET_WORD)) {
                isGuessed = true;
                break;
            }

            displayHint(guess);
        }

        if (isGuessed) {
            System.out.println("Congratulations! You've guessed the word correctly!");
        } else {
            System.out.println("Sorry, you've used all attempts. The word was: " + SECRET_WORD);
        }

        scanner.close();
    }

    private static void displayHint(String guess) {
        StringBuilder hint = new StringBuilder();
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guess.charAt(i) == SECRET_WORD.charAt(i)) {
                hint.append(guess.charAt(i));
            } else if (SECRET_WORD.contains(String.valueOf(guess.charAt(i)))) {
                hint.append('.');
            } else {
                hint.append('_');
            }
        }
        System.out.println("Hint: " + hint);
    }
}