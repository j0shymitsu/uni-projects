import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<PasswordEntry> savedPasswords = PasswordManager.loadPasswords();

        while (true)
        {
            System.out.println("\nPASSWORD MANGER");
            System.out.println("---------------\n");

            System.out.println("1. Generate a secure password");
            System.out.println("2. Evaluate a passwords strength");
            System.out.println("3. Store a password");
            System.out.println("4. View your saved passwords");
            System.out.println("5. Exit\n");

            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    String generatedPassword = PasswordGenerator.generate();
                    System.out.println("Generated password: " + generatedPassword);
                    break;

                case "2":
                    System.out.println("Enter a password to evaluate it's strength: ");

                    String userPassword = scanner.nextLine();
                    int score = PasswordEvaluator.evaluate(userPassword);

                    System.out.println("Password strength: " + score + "/100.");
                    break;

                case "3":
                    System.out.println("Enter name (e.g 'UoC Login', 'Facebook'): ");
                    String name = scanner.nextLine();

                    System.out.println("Enter related password: ");
                    String password = scanner.nextLine();

                    savedPasswords.add(new PasswordEntry(name, password));
                    PasswordManager.savePasswords(savedPasswords);

                    System.out.println("Password saved.");
                    break;

                case "4":
                    if (savedPasswords.isEmpty())
                    {
                        System.out.println("No saved passwords on file.");
                    }
                    else
                    {
                        System.out.println("Saved passwords: ");
                        for (PasswordEntry entry : savedPasswords)
                        {
                            System.out.println("~ " + entry.getName() + ": " + entry.getPassword());
                        }
                    }
                    break;

                case "5":
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
