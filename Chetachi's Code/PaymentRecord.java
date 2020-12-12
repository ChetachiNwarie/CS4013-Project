
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

    public PaymentRecord(int year, boolean wasPaid, double amount)
    {
        this.year = year;
        this.wasPaid = wasPaid;
        this.amount = amount;
    }

    public boolean isWasPaid()
    {
        return wasPaid;
    }

    public void setWasPaid(boolean wasPaid)
    {
        this.wasPaid = wasPaid;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public int getYear()
    {
        return year;
    }

    public boolean getWasPaid()
    {
        return wasPaid;
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
