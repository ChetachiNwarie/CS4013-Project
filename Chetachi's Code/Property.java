
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cnwar
 */
public class Property
{

    private PropertyOwner owner;
    // private String owner = null;
    private String address = null;
    private String eircode = null;
    private double marketValue;
    private String location = null;
    private boolean privateResidence;
    private PropertyTax tax;
    private ArrayList<Object> thisProperty = new ArrayList<>();
    //private int yearsOwned;  //Day bought...
    //

    public ArrayList<Object> getThisProperty()
    {
        return thisProperty;
    }

    public void setThisProperty(ArrayList<Object> thisProperty)
    {
        this.thisProperty = thisProperty;
    }

    //Add to a csv file
    public Property()
    {

        thisProperty.add(owner);
        thisProperty.add(address);
        thisProperty.add(eircode);
        thisProperty.add(marketValue);
        thisProperty.add(location);
        thisProperty.add(privateResidence);

    }

    public Property(PropertyOwner owns)
    {
        owner = owns;
        thisProperty.add(owner);
        thisProperty.add(address);
        thisProperty.add(eircode);
        thisProperty.add(marketValue);
        thisProperty.add(location);
        thisProperty.add(privateResidence);
    }

    /*
    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
     */
    public PropertyOwner getOwner()
    {
        return owner;
    }

    public void setOwner(PropertyOwner o)
    {
        this.owner = o;
        //owner.addProperty(this);
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEircode()
    {
        return eircode;
    }

    public void setEircode(String eircode)
    {
        this.eircode = eircode;
    }

    public double getMarketValue()
    {
        return marketValue;
    }

    public void setMarketValue(double marketValue)
    {
        this.marketValue = marketValue;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public boolean isPrivateResidence()
    {
        return privateResidence;
    }

    public void setPrivateResidence(boolean privateResidence)
    {
        this.privateResidence = privateResidence;
    }

    /*
    public PropertyTax getTax()
    {
        tax = new
    
    }
     */
    public String toString()
    {
        String a = String.format("Property\nOwner: %s\nAddress: %s"
                + "\n%s\nPrivate Residence: %b\nLocation: %s",
                getOwner().getName(), getAddress(),
                getEircode(), isPrivateResidence(), getLocation());
        return a;
    }

}
