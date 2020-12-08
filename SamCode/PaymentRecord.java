/**
 * The PaymentRecord class represents the record of a year of property tax.
 * 
 * @author Samuel O'Mahony (19236719)
 */
public class PaymentRecord {

    private int year;
    private boolean wasPaid;
    private double amount;
    
    /**
     * Constructor for objects of class PaymentRecord
     * @param year The year associated with the record
     * @param wasPaid Has the tax for this year been paid? True if it has, false if not
     * @param amount The amount paid that year
     */
    public PaymentRecord(int year, boolean wasPaid, double amount){
        this.year=year;
        this.wasPaid=wasPaid;
        this.amount=amount;
    }
    
    /**
     * Returns the year associated with this record
     * @return The year associated with this record
     */
    public int getYear(){
        return year;
    }
    /**
     * Returns true if the tax for this year was paid, false if it was not.
     * @return True if the tax for this year was paid, false if it was not.
     */
    public boolean getWasPaid(){
        return wasPaid;
    }
    
    /**
     * Sets this PaymentRecord as paid(true) or unpaid(false)
     * @param wasPaid True if setting as paid, false if setting as unpaid
     */
    public void setWasPaid(boolean wasPaid){
        this.wasPaid=wasPaid;
    }
    /**
     * Sets the amount paid for the year corresponding to this PaymentRecord
     * @param amount The amount of tax paid for this year
     */
    public void setAmount(double amount){
        this.amount=amount;
    }
}