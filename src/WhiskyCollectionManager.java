import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WhiskyCollectionManager
{
    private ArrayList<Whisky> whiskies;
    public WhiskyCollectionManager()
    {
        this.whiskies = loadWhiskies();
    }

    public ArrayList<Whisky> loadWhiskies()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("resources/whiskycollection.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Whisky> whiskies = (ArrayList<Whisky>) objectInputStream.readObject();
            return whiskies;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Error converting data to object: " + e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Can't find class representing saved object: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    public void saveWhiskies(ArrayList<Whisky> whiskies)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("resources/whiskycollection.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(whiskies);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to save: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Unable to create object output stream: " + e.getMessage());
        }
    }

    public void addWhisky(Whisky whisky)
    {
        whiskies.add(whisky);
        saveWhiskies(whiskies);
    }

    public void listWhiskies()
    {
        if (whiskies.isEmpty())
        {
            System.out.println("No whiskies in the collection");
        }
        else
        {
            for (Whisky whisky : whiskies)
            {
                System.out.println(whisky);
            }
        }
    }
}
