import java.io.*;
import java.util.ArrayList;

public class PasswordManager
{
        private static final String filePath = "resources/passwords.dat";

        public static ArrayList<PasswordEntry> loadPasswords()
        {
            try
            {
                File file = new File(filePath);

                if (!file.exists())
                {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    return new ArrayList<>();
                }

                if (file.length() == 0)
                {
                    return new ArrayList<>();
                }

                FileInputStream fileInputStream = new FileInputStream(filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<PasswordEntry> passwords = (ArrayList<PasswordEntry>) objectInputStream.readObject();
                return passwords;
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Password file not found: " + e.getMessage());
            }
            catch (IOException e)
            {
                System.out.println("Error reading password file: " + e.getMessage());
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Couldn't load passwords: " + e.getMessage());
            }

            return new ArrayList<>();
        }

        public static void savePasswords(ArrayList<PasswordEntry> passwords)
        {
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(passwords);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Unable to save passwords " + e.getMessage());
            }
            catch (IOException e)
            {
                System.out.println("Unable to create output stream: " + e.getMessage());
            }
        }
}
