import java.io.Serializable;

public class PasswordEntry implements Serializable
{
    private final String name;
    private final String password;

    public PasswordEntry(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        return name + ": " + password;
    }
}
