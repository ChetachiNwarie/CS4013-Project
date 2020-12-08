import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a Property in a system that calculates and records
 * the property tax on properties. 
 * 
 * @author Samuel O'Mahony (19236719)
 */
public class Property {

    //attributes a property has
    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    private ArrayList<PaymentRecord> paymentRecords;
    
    //constants to calculate tax
    private static double fixedCost = 100;
    private static double[] valueBrackets = {0, 150000, 400000, 650000};
    private static double[] valueBracketRates = {0, 0.0001, 0.0002, 0.0004};
    private static double[] locationCatRates = {25, 50, 60, 80, 100};
    private static double principalPrivateRate = 100;
    private static double unpaidPenalty = 0.07;
    private static int currentYear = LocalDate.now().getYear();

    /**
     * Constructor for objects of class Property 
     * @param owner The owner of the property
     * @param address The address of the property
     * @param eircode The eircode of the property
     * @param marketValue The market value of the property
     * @param locationCategory The location category e.g. city, small town, countryside
     * @param principalPrivateResidence Is the property the principal private residence of the owner? If yes then true, if no then false
     */
    public Property(String owner, String address, String eircode, double marketValue, String locationCategory, boolean principalPrivateResidence){
        this.owner=owner;
        this.address=address;
        this.eircode=eircode.toUpperCase();
        this.marketValue=marketValue;
        this.locationCategory=locationCategory.toLowerCase();
        this.principalPrivateResidence=principalPrivateResidence;
    }

    /**
     * Returns the PaymentRecord object corresponding to a given year for this property or 
     * null if no such record exists.
     * 
     * @param year The year of the record wanted
     * @return The PaymentRecord for the year given by the user or null if no such record exists. 
     */
    public PaymentRecord getRecord(int year){
        for(int i = 0; i<paymentRecords.size(); i++){
            if(paymentRecords.get(i).getYear()==year){
                return paymentRecords.get(i);
            }
        }
        System.err.println("No record exists for that year");
        return null;
    }
    
    /**
     * Returns an ArrayList containing all the PaymentRecords for this property
     * 
     * @return An ArrayList containing all the PaymentRecords for this property
     */
    public ArrayList<PaymentRecord> getAllRecords(){
        return paymentRecords;
    }
    
    private double taxDueThisYear(){
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

            case "countryside":
                taxDue+=locationCatRates[0];
                break;
            case "village":
                taxDue+=locationCatRates[1];
                break;
            case "small town":
                taxDue+=locationCatRates[2];
                break;
            case "large town":
                taxDue+=locationCatRates[3];
                break;
            case "city":
                taxDue+=locationCatRates[4];
                break;                   
            
        }

        //charge if not the principal private residence
        if(!principalPrivateResidence){
            taxDue+=100;
        }
        
        return taxDue;
    }

    /**
     *  Calculates and returns the tax due on this property based on the fixed rate,
     * market value, location, whether or not it is the principal private residence
     * of the owner, and how many previous years are unpaid plus the penalty for 
     * a year remaining unpaid.
     * @return The tax due on this property
     */
    public double taxDue(){    
        //overdue penalty
        double taxBeforePenalty=taxDueThisYear();
        double taxDue=taxBeforePenalty;
        ArrayList<PaymentRecord> yearsOverdue = this.getOverdueRecords();
        for(int i = 0; i<yearsOverdue.size(); i++){
            int pow = currentYear-yearsOverdue.get(i).getYear();//pow is the no. of years for which a penalty applies
            taxDue+=taxBeforePenalty*Math.pow(1+unpaidPenalty, pow);
        }        
        
        return taxDue;
    }

    /**
     * Checks each PaymentRecord associated with this property and returns an ArrayList
     * of all overdue/unpaid records.
     * @return An ArrayList of the unpaid records associated with this property
     */
    private ArrayList<PaymentRecord> getOverdueRecords(){
        ArrayList<PaymentRecord> overdueRecords = new ArrayList<>();
        for(int i = 0; i<paymentRecords.size(); i++){
            if(paymentRecords.get(i).getYear()!=currentYear){
            if(!paymentRecords.get(i).getWasPaid()){
                overdueRecords.add(paymentRecords.get(i));
            }
            }
        }
        return overdueRecords;
    }

    /**
     * Sets the PaymentRecord's wasPaid boolean to true for each year and sets
     * the amount paid for that year.
     */
    public void payTax(){
        ArrayList<PaymentRecord> overdueRecords = this.getOverdueRecords();
              
        for(int i = 0; i<overdueRecords.size();i++){
            overdueRecords.get(i).setWasPaid(true);
            int pow = currentYear-overdueRecords.get(i).getYear();
            double amount = taxDueThisYear()*Math.pow(1+unpaidPenalty, pow);
            overdueRecords.get(i).setAmount(amount);
        }
    }
    
    /**
     * Sets the fixed cost
     * @param newCost The new fixed cost
     */
    public static void setFixedCost(double newCost){
        fixedCost = newCost;
    }
    /**
     * Sets the brackets of market value that decide which category of market value
     * a property should be charged for.
     * @param newBrackets An array with the new market value brackets
     */
    public static void setValueBrackets(double[] newBrackets){
        Arrays.sort(newBrackets);
        valueBrackets=newBrackets;
    }
    /**
     * Sets the rates charged for properties in each market value bracket
     * @param newRates An array containing the new rates for each market value bracket
     */
    public static void setValueBracketRates(double[] newRates){
        Arrays.sort(newRates);
        valueBracketRates=newRates;
    }
    /**
     * Sets the rates charged for properties in each location category
     * @param newRates An array of the new rates for each location category
     */
    public static void setLocationCatRates(double[] newRates){
        Arrays.sort(newRates);
        locationCatRates=newRates;
    }
    /**
     * Sets the cost of the charge in place if a property is not the principal private
     * residence of the owner
     * @param rate The new cost of the charge
     */
    public static void setPrincipalPrivateRate(double rate){
        principalPrivateRate=rate;
    }
    /**
     * Sets the penalty applied for each year tax is unpaid
     * @param newPenalty The new penalty to be applied
     */
    public static void setUnpaidPenalty(double newPenalty){
        unpaidPenalty=newPenalty;
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