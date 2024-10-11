/**
 * this one is just modification of game (basicWordleGame) here word is selected randomly from predefined words from csv file
 * this is a wordle game, here a random word will be selected and u have to guess the word in 6 attempts
 * @author Gauresh Rekdo
 * @version 1.0
 * @since 14/08/2024
 * @lastmodified 21/08/2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordleGame {
    private static final int MAX_ATTEMPTS = 6;
    private static final int WORD_LENGTH = 5;
    private static String SECRET_WORD;

    public static void main(String[] args) {
        // Load words from CSV file and select a random word
        List<String> words = loadWordsFromCSV("words.csv");
        if (words.isEmpty()) {
            System.out.println("No words found in the CSV file.");
            return;
        }
        SECRET_WORD = selectRandomWord(words);
        
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

    private static List<String> loadWordsFromCSV(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordArray = line.split(",");
                for (String word : wordArray) {
                    words.add(word.trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
        return words;
    }

    private static String selectRandomWord(List<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
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
