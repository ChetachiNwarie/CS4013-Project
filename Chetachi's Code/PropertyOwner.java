
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
public class PropertyOwner
{
    private String name = null;
    private ArrayList<Property> properties = new ArrayList<>();
    
    public PropertyOwner(String name, Property p){
        this.name = name.toUpperCase();
        properties.add(p);
    }
    
    public PropertyOwner(String name){
        this.name = name.toUpperCase();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name.toUpperCase();
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties)
    {
        this.properties = properties;
    }
    
    public void addProperty(Property p){
       // p.setOwner(this);
       // if(p.getOwner()==this){
        properties.add(p);
        //}
    }
    
    public void removeProperty(Property p){
        properties.remove(p);
    }
    
    public String toString(){
        String a = String.format("Property Owner: %s\n"
                + "Properties Owned:\n %s", getName(), getProperties());
        return a;
    }
    
    
    
    
    
}
