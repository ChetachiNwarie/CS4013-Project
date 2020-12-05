
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class DepartmentManagementMenu {

    public ArrayList<Property> allProperties = new ArrayList<Property>();
    private Scanner in = new Scanner(System.in);
    
    public String getPropertyData(Property p){
        for(int i = 0; i<allProperties.size(); i++){
            if(p.equals(allProperties.get(i))){
                return p.toString();}
        }
        System.err.println("No such property is registered");
        return null;
    }
    
    public String getOwnerData(PropertyOwner own){
        return own.toString();
    }
    
    public String getOverdueTax(int year){
        String overdue = "";

        System.out.println("Do you want to use an eircode routing key? Y/N");
        String choice = in.nextLine().toUpperCase();
        
        if(choice.equals("Y")){
            System.out.println("Enter the routing key");
            String routekey = in.nextLine().toUpperCase();
            ArrayList<Property> areaProperties = areaRecords(routekey);
            
            for(int i = 0; i<areaProperties.size(); i++){
            if(!areaProperties.get(i).getRecord(year).getWasPaid()){
                overdue+=areaProperties.get(i).toString();
            }
        }
        }
        else{
            for(int i = 0; i<allProperties.size(); i++){
            if(!allProperties.get(i).getRecord(year).getWasPaid()){
                overdue+=allProperties.get(i).toString();
            }
        }
        }          
        
        return overdue;        
    }
    
    private ArrayList<Property> areaRecords(String routekey){
        ArrayList<Property> areaProperties = new ArrayList<Property>();
        
        for(int i = 0; i<allProperties.size(); i++){
            if(routekey.equals(allProperties.get(i).getEircode().toUpperCase().substring(0, 3))){
                areaProperties.add(allProperties.get(i));
            }
        }
        
        return areaProperties;
    }
    
    public String taxStatistics(){
        System.out.println("Enter the routing key");
        String routekey = in.nextLine().toUpperCase();
        ArrayList<Property> areaProperties = areaRecords(routekey);
        
        String stats = "";
        double totalVal = 0;
        int numRecords=0;
        int numPaidRecords=0;
        
        for(int i = 0; i<areaProperties.size(); i++){
            totalVal+=areaProperties.get(i).getMarketValue();
            ArrayList<PaymentRecord> payRecords = areaProperties.get(i).getAllRecords();
            numRecords += payRecords.size();
            for(int j =0; j < payRecords.size(); j++){
                if(payRecords.get(i).getWasPaid()){
                    numPaidRecords++;
                }
            }
        }
        
        
        return String.format("Total tax paid : %.2f\nAverage tax paid: %.2f\nNumber of property taxes paid : %d\nPercentage of taxes paid : %.2f\n",
                totalVal, (totalVal/areaProperties.size()), numRecords, (numPaidRecords/numRecords));
    }
    
    public String investigate(){
    
    }
}
