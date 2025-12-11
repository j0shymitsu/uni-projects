import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        WhiskyCollectionManager whiskyManager = new WhiskyCollectionManager();

        // main loop
        while (true)
        {
            System.out.println("EDIT YOUR COLLECTION:\n");
            System.out.println("1. Add new whisky");
            System.out.println("2. View current whiskies");
            System.out.println("3. Exit");
            System.out.println("\nEnter an option: ");

            String userInput = scanner.nextLine();

            switch (userInput)
            {
                case "1":
                    System.out.println("Enter distillery: ");
                    String distillery = scanner.nextLine();

                    int age;
                    while (true)
                    {
                        System.out.println("Enter age (enter '0' for NAS): ");
                        String ageInput = scanner.nextLine();
                        try
                        {
                            age = Integer.parseInt(ageInput);
                            if (age < 0)
                            {
                                System.out.println("Age cannot be negative.");
                            }
                            else
                            {
                                break;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid number. Please enter a whole number (or 0).");
                        }
                    }

                    System.out.println("Enter region: ");
                    String region = scanner.nextLine();

                    System.out.println("Enter expression: ");
                    String expression = scanner.nextLine();

                    whiskyManager.addWhisky(new Whisky(distillery, age, region, expression));
                    System.out.println("Whisky added!\n-------------\n\n");
                    break;

                case "2":
                    whiskyManager.listWhiskies();
                    break;

                case "3":
                    System.out.println("Terminated");
                    return;

                default:
                    System.out.println("Invalid input, try again!");
            }
        }
    }
}
