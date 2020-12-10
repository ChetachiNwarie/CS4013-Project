
import java.util.ArrayList;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class PropertyOwner {

    private String name;
    private ArrayList<Property> properties = new ArrayList<Property>();
    
    public PropertyOwner(String name){
        this.name=name.toUpperCase();
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
    
    public void queryProperty(Property prop){
        ArrayList<PaymentRecord> allRecs = prop.getAllRecords();
        for(int i=0; i<allRecs.size();i++){
            System.out.println(allRecs.get(i).toString());
        }
    }
    
    public ArrayList<Property> getProperties(){
        return properties;
    }
        
    public String toString(){
        String propDetails="";
        for(int i = 0; i<properties.size(); i++){
            propDetails+=properties.get(i).toString();
        }
        return String.format(name+"\n"+propDetails);
    }
    
    //Aoife's code
    public Property getProperty(String address){
        Property a = null;
        for(int i=0; i<properties.size(); i++){
            if(properties.get(i).getAddress().equals(address)){
                a = properties.get(i);
            }
            else{
                a = null;
            }
        }
        return a;
    }
}