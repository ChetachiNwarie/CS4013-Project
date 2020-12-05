import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class Property {

    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    private ArrayList<PaymentRecord> paymentRecords;
    //constants to calculate tax from here on
    private double fixedCost = 100;
    private double[] valueBrackets = {0, 150000, 400000, 650000};
    private double[] valueBracketRates = {0, 0.0001, 0.0002, 0.0004};
    private double[] locationCatRates = {100, 80, 60, 50, 25};
    private double principalPrivateRate = 100;
    private double unpaidPenalty = 0.07;
    private int currentYear = LocalDate.now().getYear();

    public Property(String owner, String address, String eircode, double marketValue, String locationCategory, boolean principalPrivateResidence){
        this.owner=owner;
        this.address=address;
        this.eircode=eircode.toUpperCase();
        this.marketValue=marketValue;
        this.locationCategory=locationCategory.toLowerCase();
        this.principalPrivateResidence=principalPrivateResidence;
    }


    public PaymentRecord getRecord(int year){
        for(int i = 0; i<paymentRecords.size(); i++){
            if(paymentRecords.get(i).getYear()==year){
                return paymentRecords.get(i);
            }
        }
        System.err.println("No record exists for that year");
        return null;
    }
    
    public ArrayList<PaymentRecord> getAllRecords(){
        return paymentRecords;
    }

    public double taxDue(){
        //fixed rate
        double taxDue = fixedCost;

        //rate based on market value
        for(int i=3;i>=0;i--){
            if(marketValue>valueBrackets[i]){
                taxDue+=marketValue*valueBracketRates[i];
                break;
            }
        }

        //charge based on location
        switch(locationCategory){

            case "city":
                taxDue+=100;
                break;
            case "large town":
                taxDue+=80;
                break;
            case "small town":
                taxDue+=60;
                break;
            case "village":
                taxDue+=50;
                break;
            case "countryside":
                taxDue+=25;
                break;
        }

        //charge if not the principal private residence
        if(!principalPrivateResidence){
            taxDue+=100;
        }

        //overdue penalty
        int yearsOverdue = this.getOverdueRecords().size();
        taxDue=taxDue*Math.pow(1+unpaidPenalty, yearsOverdue);

        return taxDue;
    }

    private ArrayList<PaymentRecord> getOverdueRecords(){
        ArrayList<PaymentRecord> overdueRecords = new ArrayList<>();
        for(int i = 0; i<paymentRecords.size(); i++){
            if(!paymentRecords.get(i).getWasPaid()){
                overdueRecords.add(paymentRecords.get(i));
            }
        }
        return overdueRecords;
    }

    public void payTax(){
        ArrayList<PaymentRecord> overdueRecords = this.getOverdueRecords();
        for(int i = 0; i<overdueRecords.size();i++){

        }
    }
    
    //Chetachi's code from here down
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
        return locationCategory;
    }

    public void setLocation(String location)
    {
        this.locationCategory = location;
    }

    public boolean isPrivateResidence()
    {
        return principalPrivateResidence;
    }

    public void setPrivateResidence(boolean privateResidence)
    {
        this.principalPrivateResidence = privateResidence;
    }
    
    public String getOwner()
    {
        return owner;
    }


    public String toString()
    {
        String a = String.format("Property\nOwner: %s\nAddress: %s"
                + "\n%s\nPrivate Residence: %b\nLocation: %s",
                getOwner(), getAddress(),
                getEircode(), isPrivateResidence(), getLocation());
        return a;
    }

}