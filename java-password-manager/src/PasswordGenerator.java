import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordGenerator
{
    private static final SecureRandom random = new SecureRandom();

    public static String generate()
    {
        ArrayList<Character> lowercase = toArrayList("abcdefghijklmnopqrstuvwxyz");
        ArrayList<Character> uppercase = toArrayList("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        ArrayList<Character> digits = toArrayList("0123456789");
        ArrayList<Character> symbols = toArrayList("!@#$%^&*()_+-=[]{}|;:',.<>?/");

        StringBuilder password = new StringBuilder();

        while (password.length() < 18)
        {
            int listPicker = random.nextInt(4);

            switch (listPicker)
            {
                case 0:
                    if (!lowercase.isEmpty())
                    {
                        char nextChar = popRandomChar(lowercase);
                        password.append(nextChar);
                    }
                    break;
                case 1:
                    if (!uppercase.isEmpty())
                    {
                        char nextChar = popRandomChar(uppercase);
                        password.append(nextChar);
                    }
                    break;
                case 2:
                    if (!digits.isEmpty())
                    {
                        char nextChar = popRandomChar(digits);
                        password.append(nextChar);
                    }
                    break;
                case 3:
                    if (!symbols.isEmpty())
                    {
                        char nextChar = popRandomChar(symbols);
                        password.append(nextChar);
                    }
                    break;
            }
        }

        return password.toString();
    }

    private static ArrayList<Character> toArrayList(String chars)
    {
        ArrayList<Character> list = new ArrayList<>();

        for (char c : chars.toCharArray())
        {
            list.add(c);
        }

        return list;
    }

    private static char popRandomChar(ArrayList<Character> charList)
    {
        int index = random.nextInt(charList.size());
        return charList.remove(index);
    }
}
