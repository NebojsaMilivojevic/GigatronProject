package pages;

import helpers.BaseHelper;

public class Item implements Comparable<Item>
{
    private String name;
    private double price;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice ()
    {
        return price;
    }

    public int compareTo(Item item)
    {
        return (this.getName().compareTo(item.getName())); //this.getPrice()-item.getPrice();//this.getName().compareTo(item.getName());
    }
}

