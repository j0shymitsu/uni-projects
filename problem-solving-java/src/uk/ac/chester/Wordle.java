package uk.ac.chester;

import java.util.Arrays;
import java.util.Scanner;

public class Wordle
{
    public static void main(String[] args)
    {
        // game
        char[] targetWord = {'t', 'a', 'p', 'e', 'r'};
        char[] currentGuess = new char[5];
        char[] correctLetters = {'-', '-', '-', '-', '-'};
        int numOfGuesses = 0;
        String currentGuessString = "";

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("----------- WORDLE! ----------\n\n");
        System.out.println("There is a HIDDEN 5 letter word. You have 5 guesses to reveal the hidden word.\n" +
                "If you guess a correct LETTER in the WRONG place, the letter will remain in LOWERCASE.\n" +
                "If you guess a correct LETTER in the CORRECT place, the letter will remain in UPPERCASE\n" +
                "DASHES indicate that this letter has not yet been guessed correctly anywhere.\n" +
                "You have FIVE guesses. Good luck!\n");

        while (numOfGuesses < 6)
        {
            System.out.println("\nNumber of guesses: " + numOfGuesses);
            System.out.println("Enter your next guess: ");
            currentGuessString = inputScanner.nextLine();

            if (currentGuessString.length() != 5)
            {
                System.out.println("Guess must be 5 letters long!\n");
                continue;
            }
            else
            {
                currentGuess = inputConverter(currentGuessString);

                if (isWordMatching(targetWord, currentGuess))
                {
                    System.out.println("CORRECT! The word was: " + Arrays.toString(targetWord));
                    System.exit(0);
                }
                else
                {
                    updateCorrectLetters(correctLetters, targetWord, currentGuess);
                    System.out.println(correctLetters);
                    numOfGuesses += 1;
                }
            }
        }

        System.out.println("\nGAME OVER! The correct word was: " + Arrays.toString(targetWord));
    }


    public static boolean isWordMatching(char[] targetWord, char[] guessWord)
    {
        for (int i = 0; i < targetWord.length; i++)
        {
            if (targetWord[i] != guessWord[i])
            {
                return false;
            }
        }

        return true;
    }


    public static char[] inputConverter(String userInput)
    {
        return userInput.toLowerCase().toCharArray();
    }


    public static void updateCorrectLetters(char[] correctLetters, char[] targetWord, char[] currentGuess)
    {
        boolean[] matches = new boolean[5];

        // correct letter, correct position
        for (int i = 0; i < currentGuess.length; i++)
        {
            if (currentGuess[i] == targetWord[i])
            {
                correctLetters[i] = Character.toUpperCase(currentGuess[i]);
                matches[i] = true;
            }
        }

        // correct letter, wrong pos
        for (int i = 0; i < currentGuess.length; i++)
        {
            if (correctLetters[i] == '-')
            {
                for (int j = 0; j < targetWord.length; j++)
                {
                    if (!matches[j] && currentGuess[i] == targetWord[j])
                    {
                        correctLetters[i] = currentGuess[i];
                        matches[j] = true;
                        break;
                    }
                }
            }
        }
    }


}
