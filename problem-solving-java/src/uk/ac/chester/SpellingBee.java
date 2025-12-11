package uk.ac.chester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellingBee
{
    public static void main(String[] args)
    {
        ArrayList<Character> allowedLetters = new ArrayList<>();
        ArrayList<String> correctGuesses = new ArrayList<>();

        Scanner inputScanner = new Scanner(System.in);

        // game loop
        System.out.println("SPELLING BEE\n" +
                "Create as many valid 4+ letter words using the given letters as you can.\n" +
                "Words must ALWAYS contain the middle letter.\n" +
                "To exit the game, type 'EXIT1' in all caps\n\n");

        // middle letter
        while (true)
        {
            System.out.println("Enter the middle letter:");
            String middleLetter = inputScanner.nextLine();

            if (middleLetter.equals("EXIT1"))
            {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }

            if (middleLetterValid(middleLetter))
            {
                char middle = middleLetter.toUpperCase().charAt(0);
                allowedLetters.add(middle);
                break;
            }
        }

        // other letters
        while (allowedLetters.size() < 7)
        {
            System.out.println("\nEnter the other letters:");
            String otherLetters = inputScanner.nextLine();

            if (otherLetters.equals("EXIT1"))
            {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }

            appendOtherLetters(otherLetters, allowedLetters);
        }

        // guessing
        while (true)
        {
            System.out.println("\nPlease enter a guess: ");
            String currentGuess = inputScanner.nextLine();
            currentGuess = currentGuess.toUpperCase();

            if (currentGuess.equals("EXIT1"))
            {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }

            if (currentGuess.equals("CH34TC0DE"))
            {
                System.out.println("\nCHEAT ACTIVATED! Here are all the possible words from your chosen letters: ");
                cheatCode(allowedLetters);
            }

            else if (guessIsLongEnough(currentGuess)
                    && containsMiddleLetter(currentGuess, allowedLetters.get(0))
                    && containsOnlyAllowedLetters(currentGuess, allowedLetters)
                    && isDictionaryWord(currentGuess)
                    && notAlreadyGuessed(currentGuess, correctGuesses))
            {
                correctGuesses.add(currentGuess);
                System.out.println("\nNice! " + currentGuess + " is valid. You now have " +
                        correctGuesses.size() + " correct word(s).");
            }
        }
    }


    private static boolean middleLetterValid(String letter)
    {
        if (letter.isEmpty())
        {
            System.out.println("Middle letter cannot be empty!\n");
            return false;
        }

        if (letter.length() > 1)
        {
            System.out.println("Middle letter must only be one character!\n");
            return false;
        }

        char c = letter.charAt(0);

        if (!Character.isLetter(c))
        {
            System.out.println("Middle letter must be a letter! (Not a number or symbol...)\n");
            return false;
        }

        return true;
    }


    private static void appendOtherLetters(String letters, ArrayList<Character> lettersArray)
    {
        if (letters.isEmpty())
        {
            System.out.println("Please enter some letters!");
            return;
        }

        if (letters.length() != 6)
        {
            System.out.println("Number of surrounding letters must equal 6!");
            return;
        }


        for (char c : letters.toCharArray())
        {
            if (!Character.isLetter(c))
            {
                System.out.println("Only valid letters allowed!");
                return;
            }
        }

        for (char c : letters.toUpperCase().toCharArray())
        {
            lettersArray.add(c);
        }
    }


    private static boolean guessIsLongEnough(String word)
    {
        if (word.length() < 4)
        {
            System.out.println("Word must be at least four letters long!");
            return false;
        }

        return true;
    }


    private static boolean containsMiddleLetter(String word, char middleLetter)
    {
        if (!word.contains(String.valueOf(middleLetter)))
        {
            System.out.println("Word must contain middle letter! - " + middleLetter);
            return false;
        }

        return true;
    }


    private static boolean containsOnlyAllowedLetters(String word, ArrayList<Character> allowedLetters)
    {
        char[] wordAsArray = word.toCharArray();

        for (char c : wordAsArray)
        {
            if (!allowedLetters.contains(c))
            {
                System.out.println("Letter " + c + "is not allowed! Allowed letters are: ");

                for (Character allowedLetter : allowedLetters)
                {
                    System.out.print(allowedLetter + " ");
                }
                System.out.println();
                return false;
            }
        }
        return true;
    }

    private static boolean isDictionaryWord(String word)
    {
        ArrayList<String> dictionaryWords = new ArrayList<>();
        File dictionaryFile = new File("resources/wordlist.txt");

        try
        {
            Scanner fileScanner = new Scanner(dictionaryFile);

            while (fileScanner.hasNext())
            {
                String validWord = fileScanner.nextLine();
                dictionaryWords.add(validWord);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("(Dictionary file could not be read)");
            System.out.println(e.getMessage());
        }

        if (dictionaryWords.contains(word.toLowerCase()))
        {
            return true;
        }
        else
        {
            System.out.println(word + " is not a valid dictionary word.");
            return false;
        }
    }


    private static boolean notAlreadyGuessed(String word, ArrayList<String> guesses)
    {
        if (guesses.contains(word))
        {
            System.out.println(word + " has already been guessed!");
            return false;
        }

        return true;
    }


    private static void cheatCode(ArrayList<Character> validLetters)
    {
        ArrayList<String> cheatWords = new ArrayList<>();
        File dictionaryFile = new File("resources/wordlist.txt");
        PrintStream unsilenced = System.out;
        PrintStream silenced = new PrintStream(OutputStream.nullOutputStream());

        try
        {
            Scanner fileScanner = new Scanner(dictionaryFile);
            System.setOut(silenced);

            while (fileScanner.hasNextLine())
            {
                String dictionaryWord = fileScanner.nextLine().toUpperCase();

                if (guessIsLongEnough(dictionaryWord)
                    && containsMiddleLetter(dictionaryWord, validLetters.get(0))
                    && containsOnlyAllowedLetters(dictionaryWord, validLetters))
                {
                    cheatWords.add(dictionaryWord);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.setOut(unsilenced);
            System.out.println("(Dictionary file could not be read)");
            System.out.println(e.getMessage());
        }
        finally
        {
            System.setOut(unsilenced);
        }

        for (String word : cheatWords)
        {
            System.out.println(word);
        }
    }
}
