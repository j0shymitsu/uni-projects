package uk.ac.chester;

import java.util.ArrayList;
import java.util.Arrays;

public class Problems
{
    static int triple(int number){
        return number * 3;
    }



    static boolean heatingShouldBeOn(double roomTemperature)
    {
        return roomTemperature < 19;
    }



    static int averageOfThreeNumbers(int x, int y, int z)
    {
        return (x + y + z) / 3;
    }



    static String concatenatedWords(String a, String b, String c, String d)
    {
        return a + " " + b + " " + c + " " + d;
    }



    static int scrabbleTileValue(char scrabbleLetter)
    {
        char lowercaseLetter = Character.toLowerCase(scrabbleLetter);

        switch (lowercaseLetter)
        {
            case 'a', 'e', 'i', 'l', 'n', 'o', 'r', 's', 't', 'u':
                return 1;
            case 'd', 'g':
                return 2;
            case 'b', 'c', 'm', 'p':
                return 3;
            case 'f', 'h', 'v', 'w', 'y':
                return 4;
            case 'k':
                return 5;
            case 'j', 'x':
                return 8;
            case 'q', 'z':
                return 10;
        }

        return 0;
    }



    static double productOfNumbersInArray(double[] arrayOfDoubles)
    {
        double product = 1;

        if (arrayOfDoubles.length == 0)
        {
            return 0;
        }

        for (double digit : arrayOfDoubles)
        {
            product *= digit;
        }

        return product;
    }



    static int minValue(int[] intArray)
    {
        if (intArray.length == 0)
        {
            return 0;
        }
        else
        {
            Arrays.sort(intArray);
            return intArray[0];
        }
    }



    static int sumOfNumbersFromOneToN(int num)
    {
        int sum = 0;

        for (int i = 0; i <= num; i++)
        {
            sum += i;
        }

        return sum;
    }



    static String removeDoubleSpaces(String sentence)
    {
        sentence = sentence.replaceAll("\\.\\s+", ". ");
        return sentence;
    }



    static String apaFormatCitation(String[] authors, int year)
    {
        String citation = "";

        if (authors.length == 1)
        {
            citation += authors[0];
        }
        else if (authors.length == 2)
        {
            citation += authors[0] + " & " + authors[1];
        }
        else if (authors.length < 5)
        {
            for (int i = 0; i < authors.length - 1; i++)
            {
                citation += authors[i] + ", ";
            }
            citation += "& " + authors[authors.length - 1];
        }
        else
        {
            citation += authors[0] + " et al.";
        }

        citation += ", ";
        citation += year;

        return citation;
    }



    static boolean isValidBishopMove(char currentColumn, int currentRow, char destinationColumn, int destinationRow)
    {
        if ((currentColumn < 'a' || currentColumn > 'h') || (destinationColumn < 'a' || destinationColumn > 'h'))
        {
            return false;
        }
        else if ((currentRow < 1 || currentRow > 8) || (destinationRow < 1 || destinationRow > 8))
        {
            return false;
        }
        else if (currentColumn == destinationColumn && currentRow == destinationRow)
        {
            return false;
        }
        else
        {
            return ((currentColumn + currentRow) + (destinationColumn + destinationRow)) % 2 == 0;
        }
    }



    static String priceAsString(int currencyHundredths, char currencySymbol, boolean endDisplayed)
    {
        float decimalCurrency = (float) currencyHundredths / 100;

        if (endDisplayed)
        {
            return String.format("%.2f", decimalCurrency) + currencySymbol;
        }
        else
        {
            return currencySymbol + String.format("%.2f", decimalCurrency);
        }
    }



    static int lastPartialMatch(String subString, String[] superString)
    {
        String uppercaseString = subString.toUpperCase();    // wasn't sure how to do this whilst "ignoring" case
        int lastIndex = -1;

        for (int i = 0; i < superString.length; i++)
        {
            if (superString[i].contains(uppercaseString))
            {
                lastIndex = i;
            }
        }

        return lastIndex;
    }



    static String wordsFromVariableName(String variableName)
    {
        String[] splitWordsArray = variableName.split("(?=[A-Z])");
        String newString = "";

        for (String splitWord : splitWordsArray)
        {
            String output = splitWord.substring(0, 1).toUpperCase() + splitWord.substring(1);
            newString += output + " ";
        }

        return newString.trim();
    }



    static String formattedNameFromArray(String[] personsName)
    {
        String[] titleList = new String[]{"Dr", "Miss", "Ms", "Mr", "Mrs", "Rev"};
        String returnedName = "";

        for (int i = 0; i < personsName.length; i++)
        {
            // has title
            for (String title : titleList)
            {
                if (personsName[i].equals(title))
                {
                    returnedName += title + ". ";
                    i = 1;
                }
            }

            if (i == 0)
            {
                returnedName += personsName[i] + " ";
            }
            else if (i < personsName.length - 1)
            {
                returnedName += personsName[i].substring(0, 1).toUpperCase() + ". ";
            }
            else
            {
                returnedName += personsName[i];
            }
        }

        return returnedName.trim();
    }



    static ArrayList<String> textChunks (String sentence)
    {
        ArrayList<String> wordList = new ArrayList<String>();
        String[] splitWords = sentence.split(" ");
        String currentLine = "";

        for (int i = 0; i < splitWords.length; i++)
        {
            String currentWord = splitWords[i];

            if (currentLine.length() + currentWord.length() > 12)
            {
                wordList.add(currentLine.trim());
                currentLine = "";
            }

            currentLine += currentWord + " ";
        }

        if (!currentLine.isEmpty())
        {
            wordList.add(currentLine.trim());
        }

        return wordList;
    }
}