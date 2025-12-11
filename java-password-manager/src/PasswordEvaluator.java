import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PasswordEvaluator
{
    private static final Set<String> commonPasswords = new HashSet<>();
    private static final Set<String> commonNames = new HashSet<>();
    private static final Set<String> dictionaryWords = new HashSet<>();

    static
    {
        loadList("resources/passwords.txt", commonPasswords);
        loadList("resources/names.txt", commonNames);
        loadList("resources/dictionarywords.txt", dictionaryWords);
    }

    private static void loadList(String filename, Set<String> set)
    {
        try
        {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine())
            {
                set.add(scanner.nextLine().trim().toLowerCase());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found : " + filename);
        }
    }

    // checks

    public static int evaluate(String password)
    {
        int score = 0;
        if (password.length() < 8)
        {
            return 0;
        }

        score += lengthCheck(password);
        score += diversityCheck(password);
        score += commonPasswordsCheck(password);
        score += commonNamesCheck(password);
        score += dictionaryWordsCheck(password);
        score += repeatCheck(password);
        score += entropyCheck(password);

        if (password.length() > 20)
        {
            score += 20;
        }

        return Math.max(1, Math.min(score, 100));
    }

    private static int lengthCheck(String password)
    {
        int length = password.length();

        if (length < 10) return 1;
        if (length < 12) return 11;
        if (length < 14) return 16;
        if (length < 16) return 20;
        if (length < 18) return 28;
        if (length < 20) return 31;
        return 33;
    }

    private static int diversityCheck(String password)
    {
        int totalCategories = 0;

        if (password.matches(".*[a-z].*")) totalCategories++;
        if (password.matches(".*[A-Z].*")) totalCategories++;
        if (password.matches(".*[0-9].*")) totalCategories++;
        if (password.matches(".*[!@#$%^&*()\\-_=+{}\\[\\]:;\"'<>,.?/].*")) totalCategories++;

        int score = totalCategories * 5;

        if (totalCategories == 4)
        {
            score += 17;
        }

        return score;
    }

    private static int commonPasswordsCheck(String password)
    {
        String lowercasePassword = password.toLowerCase();

        for (String word : commonPasswords)
        {
            if (word.equals(lowercasePassword))
            {
                return -20;
            }
            else if (lowercasePassword.contains(word) && word.length() > lowercasePassword.length() / 2)
            {
                return -10;
            }
        }

        if (lowercasePassword.length() >= 18)
        {
            return 10;
        }
        else
        {
            return 3;
        }
    }

    private static int commonNamesCheck(String password)
    {
        if (password.length() >= 18)
        {
            return 10;
        }

        String lowercasePassword = password.toLowerCase();

        for (String name : commonNames)
        {
            if (lowercasePassword.contains(name))
            {
                return -12;
            }
        }

        return 11;
    }

    private static int dictionaryWordsCheck(String password)
    {
        if (password.length() >= 18)
        {
            return 10;
        }

        for (String word : dictionaryWords)
        {
            if (word.length() >= 3 && password.toLowerCase().contains(word))
            {
                return -12;
            }
        }

        return 10;
    }

    private static int repeatCheck(String password)
    {
        String lowercasePassword = password.toLowerCase();

        String[] commonSequences = {
                "abc", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm", "lmn", "mno", "nop", "opq",
                "pqr", "qrs", "rst", "stu", "tuv", "uvw", "vwx", "wxy", "xyz", "zyx", "yxw", "xwv", "wvu", "vut", "uts",
                "123", "234", "345", "456", "567", "678", "789", "890", "987", "876", "765", "654", "543", "432", "321",
                "qwe", "wer", "ert", "rty", "tyu", "yui", "uio", "iop", "asd", "sdf", "dfg", "fgh", "ghj", "hjk", "jkl",
                "zxc", "xcv", "cvb", "vbn", "bnm", "aze", "zer", "qsd", "wxc", "!@#", "@#$", "#$%", "$%^", "%^&", "^&*",
                "&*(", "*()"
        };

        for (String sequence : commonSequences)
        {
            if (lowercasePassword.contains(sequence))
            {
                return -10;
            }
        }

        return 10;

    }

    private static int entropyCheck(String password)
    {
        Set<Character> unique = new HashSet<>();

        for (char c : password.toCharArray())
        {
            unique.add(c);
        }

        double entropy = password.length() * (Math.log(unique.size()) / Math.log(2));

        if (entropy < 5)
        {
            return -99;
        }
        if (entropy < 25)
        {
            return -50;
        }
        if (entropy < 45)
        {
            return 0;
        }
        if (entropy < 55)
        {
            return 15;
        }
        if (entropy < 85)
        {
            return 25;
        }

        return 60;
    }
}
