import java.io.Serializable;

public class Whisky implements Serializable
{
    private String region;
    private String distillery;
    private String expression;
    private int age;

//    private static int nextID = 1;
//    private int ID;

    public Whisky(String distillery, int age, String region, String expression)
    {
        this.region = region;
        this.distillery = distillery;
        this.expression = expression;
        this.age = age;
    }

    public String getRegion()
    {
        return region;
    }

    public String getDistillery()
    {
        return distillery;
    }

    public String getExpression()
    {
        return expression;
    }

    public int getAge()
    {
        return age;
    }

    //

    public void setRegion(String region)
    {
        this.region = region;
    }

    public void setDistillery(String distillery)
    {
        this.distillery = distillery;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    //

    @Override
    public String toString()
    {
        String displayAge;
        String displayExpression;

            if (age == 0)
            {
                displayAge = "NAS";
            }
            else displayAge = age + " Year";

            if (expression == null || expression.isBlank())
            {
                displayExpression = "";
            }
            else
            {
                displayExpression = capitalise(expression) + ". ";
            }

        return capitalise(distillery) + " " + displayAge + ". " + displayExpression + "Region: " + capitalise(region);
    }

    private String capitalise(String input)
    {
        if (input == null || input.isEmpty())
        {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
