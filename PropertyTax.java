
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cnwar
 */
public class PropertyTax
{

    private ArrayList<Property> registeredProperties = new ArrayList<>();
    private double outStandingTax = 0;
   private ArrayList<Double> taxToPay = new ArrayList<>();
    
    
    public PropertyTax(Property p){
        
    }
   
    /*
    public ArrayList<Property> getRegisteredProperties()
    {
        return registeredProperties;
    }

    public void setRegisteredProperties(ArrayList<Property> registeredProperties)
    {
        this.registeredProperties = registeredProperties;
    }
*/

    public double getOutStandingTax()
    {
        return outStandingTax;
    }

    public void setOutStandingTax(double outStandingTax)
    {
        this.outStandingTax = outStandingTax;
    }
    
    

    public double calculateTax(Property p)
    {
        double value = p.getMarketValue();
        double tax = 100;
        if (registeredProperties.contains(p))
        {
            tax += mvt(p);
            tax += locationTax(p);
            if (p.isPrivateResidence() == false)
            {
                tax += 100;
            }
//Need to add compunded tax
        }

        return tax;

    }

    public double compoundedTax()
    {
        double tax = 0;
        //Tax paid in arrayList
        //Tax outstanding in arrayList
        //Tax paid by year in array List
        //Nuumber of years not pain in ArrayList
        return tax;
    }

    public double mvt(Property p)
    {
        double value = p.getMarketValue();
        double tax = 0;
        if ((value >= 150000) && (value <= 400000))
        {
            tax = 0.0001 * value;
        }
        else if ((400001 <= value) && (value <= 650000))
        {
            tax = 0.0002 * value;
        }
        else if (value > 650000)
        {
            tax = 0.0004 * value;
        }
        return tax;
    }

    public double locationTax(Property p)
    {
        String loc = p.getLocation();
        double tax = 0;

        switch (loc)
        {
            case "City":
                tax += 100;
                break;
            case "Large town":
                tax += 80;
                break;
            case "Small town":
                tax += 60;
                break;
            case "Village":
                tax += 50;
                break;
            case "Countryside":
                tax += 25;
                break;
            default:
                break;
        }
        return tax;
    }

}
