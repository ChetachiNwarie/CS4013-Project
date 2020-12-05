/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class PaymentRecord {

    private int year;
    private boolean wasPaid;
    private double amount;
    
    public PaymentRecord(int year, boolean wasPaid, double amount){
        this.year=year;
        this.wasPaid=wasPaid;
        this.amount=amount;
    }
    
    public int getYear(){
        return year;
    }
    
    public boolean getWasPaid(){
        return wasPaid;
    }
}