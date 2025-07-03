import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class WhiskyCollection
{
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        ArrayList<Whisky> whiskies = new ArrayList<>();

        while (true)
        {
            System.out.println("Would you like to enter a new whisky into your collection? " +
                    "\n[Type 'exit' to quit; hit enter or type anything else to continue.]");

            String exit = inputScanner.nextLine();
            exit = exit.toUpperCase();

            if (!exit.equals("EXIT"))
            {
                System.out.println("\nRegion: ");
                String region = inputScanner.nextLine();

                System.out.println("Distillery: ");
                String distillery = inputScanner.nextLine();

                System.out.println("Expression (optional):");
                String expression = inputScanner.nextLine();

                    if (expression.isBlank())
                    {
                        expression = null;
                    }
                    else
                    {
                        expression = expression;
                    }

                System.out.println("Age: ");
                int age = inputScanner.nextInt();

                Whisky nextWhisky = new Whisky(region, distillery, expression, age);
                whiskies.add(nextWhisky);
                System.out.println("Added: " + nextWhisky);

                System.out.println("\nWhiskies now in database: ");

                for (Whisky whiskey : whiskies)
                {
                    System.out.println(whiskey);
                }
            }

            // logic for editing/deleting from collection
        }
    }

    private static void writeToFile(ArrayList<String> whiskies)
    {
        Path location = Paths.get("resources/whiskycollection.txt");
        Charset utf8 = StandardCharsets.UTF_8;

        try
        {
            Files.write(location, whiskies, utf8, StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
            System.out.println("File could not be saved!");
        }
    }
}
