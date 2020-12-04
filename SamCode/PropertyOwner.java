
import java.util.ArrayList;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class PropertyOwner {

    private String name;
    private ArrayList<Property> properties = new ArrayList<Property>();
    
    public PropertyOwner(String name){
        this.name=name;
    }
    
    public void registerProperty(String address, String eircode,  double marketValue, String locationCategory, boolean principalPrivateResidence){
        this.properties.add( new Property(name, address, eircode, marketValue, locationCategory, principalPrivateResidence));
    }
    
    public void payTax(Property prop){
        prop.payTax();
    }
    
    public void viewProperties(){
        for(int i = 0; i<properties.size(); i++){
            System.out.println(properties.get(i).toString());
        }
    }
    
    public void queryYear(int year){
        for(int i = 0; i<properties.size(); i++){
            System.out.println(properties.get(i).getAddress());
            System.out.println(properties.get(i).getRecord(year));
        }
    }
    
    public ArrayList<Property> getProperties(){
        return properties;
    }
        
}
