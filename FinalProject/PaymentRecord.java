
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
//Sam's payment record class
public class PaymentRecord
{

    private int year;
    private boolean wasPaid;
    private double amount;

    /**
     * Constructor for objects of class PaymentRecord
     * @param year The year associated with the record
     * @param wasPaid Has the tax for this year been paid? True if it has, false if not
     * @param amount The amount paid that year
     */
    public PaymentRecord(int year, boolean wasPaid, double amount)
    {
        this.year = year;
        this.wasPaid = wasPaid;
        this.amount = amount;
    }

    /**
     * Returns true if the tax for this year was paid, false if it was not.
     * @return True if the tax for this year was paid, false if it was not.
     */
    public boolean getWasPaid()
    {
        return wasPaid;
    }

     /**
     * Sets this PaymentRecord as paid(true) or unpaid(false)
     * @param wasPaid True if setting as paid, false if setting as unpaid
     */
    public void setWasPaid(boolean wasPaid)
    {
        this.wasPaid = wasPaid;
    }

    /**
     * Returns the amount paid for this year's record.
     * @return The amount paid for this year's record.
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Sets the amount paid for the year corresponding to this PaymentRecord
     * @param amount The amount of tax paid for this year
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    /**
     * Returns the year associated with this record
     * @return The year associated with this record
     */
    public int getYear()
    {
        return year;
    }
    
    
   // Chetachi's code from here
     public void replaceLine(String filename, String change, int column)
    {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            String find = toString();
            while ((line = file.readLine()) != null)
            {
                if (line.equals(find))
                {
                    String[] split = line.split(",");
                    split[column] = change;

                    String a = Arrays.toString(split);
                    String[] split2 = a.split("(\\[)|(\\])");
                    line = split2[1].replaceAll("\\s","");
                }

                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename, false);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
        catch (Exception e)
        {
            System.out.println("Problem reading file.");
        }
    }


    @Override
    public String toString()
    {
        String p = "";
        if (getWasPaid())
        {
            p = "yes";
        }
        else
        {
            p = "no";
        }
        String b = String.format("%d,%.0f,%s", getYear(), getAmount(), p);
        return b;
    }
}
