package uk.ac.chester;

import java.io.Serializable;

public class Whisky implements Serializable
{
    private String region;
    private String distillery;
    private String expression;
    private int age;

    public Whisky(String region, String distillery, String expression, int age)
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
}
