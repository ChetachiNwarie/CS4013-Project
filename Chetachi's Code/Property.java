
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class Property
{
//Sam's Property Class

    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    private ArrayList<PaymentRecord> paymentRecords = new ArrayList<>();
    //constants to calculate tax from here on
    private double fixedCost = 100;
    private double[] valueBrackets =
    {
        0, 150000, 400000, 650000
    };
    private double[] valueBracketRates =
    {
        0, 0.0001, 0.0002, 0.0004
    };
    private double[] locationCatRates =
    {
        100, 80, 60, 50, 25
    };
    private double principalPrivateRate = 100;
    private double unpaidPenalty = 0.07;
    private int currentYear = LocalDate.now().getYear();

    public Property(String owner, String address, String eircode, double marketValue, String locationCategory, boolean principalPrivateResidence)
    {

        this.address = address;
        this.eircode = eircode.toUpperCase();
        this.marketValue = marketValue;
        this.locationCategory = locationCategory;
        this.principalPrivateResidence = principalPrivateResidence;
        this.owner = owner;
        initializingFiles();
        //Chetachi's Additions

    }

     private void initializingFiles()
    {
        try
        {
            String cont = this.toString();
            if (!fileContainsLine("Properties.csv", this.toString()))
            {
                FileWriter csvWriter = new FileWriter("Properties.csv", true);
                csvWriter.append(this.toString() + "\n");
                csvWriter.flush();
                csvWriter.close();
            }

            File pr = new File("Payment Records");
            pr.mkdir();
            File e = new File(pr, this.address.toUpperCase() + " Payment Records.csv");
            if (!e.exists())
            {
                FileWriter csvWriter1 = new FileWriter(e, false);
                csvWriter1.append("Year,Tax,Paid\n");
                csvWriter1.flush();
                csvWriter1.close();
            }

            File o = new File("Owners");
            o.mkdir();
            File f = new File(o, this.owner.toUpperCase() + ".csv");
            if (!f.exists())
            {
                FileWriter csvWriter2 = new FileWriter(f, true);
                csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                csvWriter2.append(toString() + "\n");
                csvWriter2.flush();
                csvWriter2.close();
            }
            else if (!fileContainsLine("Owners\\" + f.getName(), this.toString()))
            {

                FileWriter csvWriter2 = new FileWriter(f, true);
                csvWriter2.append(toString() + "\n");
                csvWriter2.flush();
                csvWriter2.close();
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public ArrayList<PaymentRecord> getPaymentRecords()
    {
        ArrayList<PaymentRecord> records = new ArrayList<>();
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("Payment Records\\" + address.toUpperCase() + " Payment Records.csv"));
            String line;
            int i = 0;

            while ((line = file.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] spl = line.split(",");
                    boolean paid = false;
                    if (spl[2].equals("yes"))
                    {
                        paid = true;
                    }

                    PaymentRecord a = new PaymentRecord(Integer.parseInt(spl[0]), paid, Double.parseDouble(spl[1]));
                    records.add(a);
                }
                i++;
            }
            file.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }
        paymentRecords = records;
        return paymentRecords;
    }

    //Done
     public void setPaymentRecords(ArrayList<PaymentRecord> paymentRecords)
    {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("Payment Records\\" + address.toUpperCase() + " Payment Records.csv"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int i = 0;

            while (((line = file.readLine()) != null) && (i < 1))
            {
                inputBuffer.append(line);
                inputBuffer.append('\n');
                i++;
            }

            for (PaymentRecord p : paymentRecords)
            {
                inputBuffer.append(p.toString() + '\n');
            }

            file.close();
            FileOutputStream fileOut = new FileOutputStream("Payment Records\\" + address.toUpperCase() + " Payment Records.csv", false);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }

        this.paymentRecords = paymentRecords;
    }
    public double getFixedCost()
    {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost)
    {
        this.fixedCost = fixedCost;
    }

    public double[] getLocationCatRates()
    {
        return locationCatRates;
    }

    public void setLocationCatRates(double[] locationCatRates)
    {
        this.locationCatRates = locationCatRates;
    }

    public double getPrincipalPrivateRate()
    {
        return principalPrivateRate;
    }

    public void setPrincipalPrivateRate(double principalPrivateRate)
    {
        this.principalPrivateRate = principalPrivateRate;
    }

    public double getUnpaidPenalty()
    {
        return unpaidPenalty;
    }

    public void setUnpaidPenalty(double unpaidPenalty)
    {
        this.unpaidPenalty = unpaidPenalty;
    }

    public int getCurrentYear()
    {
        return currentYear;
    }

    public void setCurrentYear(int currentYear)
    {
        this.currentYear = currentYear;
    }

    public PaymentRecord getRecord(int year)
    {

        for (int i = 0; i < getPaymentRecords().size(); i++)
        {
            if (paymentRecords.get(i).getYear() == year)
            {
                return paymentRecords.get(i);
            }
        }
        return null;
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
        addToPaymentFile(new PaymentRecord(currentYear, false, taxDue));
          
        return taxDue;
    }

    
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

    //Shouldnt take any input.
    public void deleteProperty()
    {
        String[] files =
        {
            "Properties.csv", "Owners\\" + this.owner.toUpperCase() + ".csv"
        };

        for (String f : files)
        {
            try
            {
                BufferedReader file = new BufferedReader(new FileReader(f));
                StringBuffer inputBuffer = new StringBuffer();
                String line;

                while ((line = file.readLine()) != null)
                {
                    if (!line.equals(this.toString()))
                    {
                        inputBuffer.append(line);
                        inputBuffer.append('\n');
                    }

                }
                file.close();
                FileOutputStream fileOut = new FileOutputStream(f, false);
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();

            }
            catch (Exception e)
            {
                System.err.println("Problem reading file.");
            }
        }

        File a = new File("Payment Records\\" + this.address.toUpperCase() + " Payment Records.csv");
        a.delete();
        paymentRecords.clear();

    }
    private void removePropertyFrom(String filename)
    {
        //  String filename = "Properties.csv";

        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null)
            {
                if (!line.equals(this.toString()))
                {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }

            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename, false);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }

        /*
        File a = new File(this.address.toUpperCase() + " Payment Records.csv");
        a.delete();
        paymentRecords.clear();
         */
    }

    //Done
     public void removeFromPaymentFile(PaymentRecord a)
    {
        paymentRecords.remove(a);
        String filename = "Payment Records\\" + address.toUpperCase() + " Payment Records.csv";
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null)
            {
                if (!line.contains(Integer.toString(a.getYear())))
                {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }

            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename, false);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }

    }

    //Done
     public void addToPaymentFile(PaymentRecord a)
    {

        for (PaymentRecord p : getPaymentRecords())
        {
            if (p.getYear() == a.getYear())
            {
                removeFromPaymentFile(a);
                break;
            }
        }

        paymentRecords.add(a);
        try
        {
            FileWriter csvWriter1 = new FileWriter("Payment Records\\" + this.address.toUpperCase() + " Payment Records.csv", true);
            csvWriter1.append(a.toString() + "\n");
            csvWriter1.flush();
            csvWriter1.close();

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Done
    public ArrayList<PaymentRecord> getOverdueRecords()
    {
        ArrayList<PaymentRecord> overdueRecords = new ArrayList<>();
        for (PaymentRecord r : getPaymentRecords())
        {
            if (!r.isWasPaid())
            {
                overdueRecords.add(r);
            }
        }

        return overdueRecords;

    }

    //Done
    public void payTax()
    {
        ArrayList<PaymentRecord> overdueRecords = getOverdueRecords();
        for (PaymentRecord r : overdueRecords)
        {
            r.replaceLine("Payment Records\\" + address.toUpperCase() + " Payment Records.csv", "yes", 2);
        }
        overdueRecords.clear();

    }

    @Override
    public String toString()
    {
        String p = "";
        if (isPrincipalPrivateResidence() == true)
        {
            p = "yes";
        }
        else
        {
            p = "no";
        }

        String a = String.format("%s,%s,%s,%.0f,%s,%s",
                getOwner(), getAddress(), getEircode(),
                getMarketValue(), getLocationCategory(), p);

        return a;
    }

     public void replaceLine(String change, int column)
    {
        String[] files =
        {
            "Properties.csv", "Owners\\" + this.owner.toUpperCase() + " Payment Records.csv"
        };
        for (String filename : files)
        {
            try
            {
                BufferedReader file = new BufferedReader(new FileReader(filename));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                String toChange = toString();
                while ((line = file.readLine()) != null)
                {

                    if (line.equals(toChange))
                    {
                        String[] split = line.split(",");
                        split[column] = change;

                        String a = Arrays.toString(split);
                        String[] split2 = a.split("(\\[)|(\\])");
                        line = split2[1];
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
                System.err.println("Problem reading file.");
            }
        }
    }
    public String getEircode()
    {
        return eircode;
    }

    public void setEircode(String eircode)
    {
        replaceLine(eircode, 2);

        this.eircode = eircode;
    }

    public String getOwner()
    {
        return owner;
    }

     public void setOwner(String owner)
    {
        try
        {
            File f = new File("Owners\\" + this.owner.toUpperCase() + ".csv");
            File g = new File("Owners\\" + owner.toUpperCase() + ".csv");
            boolean a = f.exists();
            boolean b = g.exists();

            if (a == false)
            {
                FileWriter csvWriter2 = new FileWriter(g, true);
                if (b == false)
                {
                    csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                }
                this.owner = owner;
                csvWriter2.append(toString() + "\n");
                csvWriter2.flush();
                csvWriter2.close();

            }
            else
            {
                if (b == false)
                {
                    FileWriter csvWriter2 = new FileWriter(g, true);
                    csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                    csvWriter2.flush();
                    csvWriter2.close();
                }
                replaceLine(owner, 0);//name wont be replaced in owner file as line doesnt exist
                this.owner = owner;
                swapPropertyTo(f.toString(), g.toString());
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.owner = owner;
    }

    public String getAddress()
    {
        return address;
    }

   public void setAddress(String address)
    {
        try
        {
            File f = new File("Payment Records\\" + this.address.toUpperCase() + " Payment Records.csv");
            File g = new File("Payment Records\\" + address.toUpperCase() + " Payment Records.csv");

            if (!f.exists())
            {
                if (!g.exists())
                {
                    FileWriter csvWriter1 = new FileWriter(g.toString(), false);
                    csvWriter1.append("Year,Tax,Paid\n");
                    csvWriter1.flush();
                    csvWriter1.close();
                }
            }

            replaceLine(address, 1);
            if (g.exists())
            {
                ArrayList<PaymentRecord> nr = getPaymentRecords();
                this.address = address;
                //add existing data to file and delete old file...
                for (PaymentRecord s : nr)
                {
                    addToPaymentFile(s);
                }
                f.delete();
            }
            else
            {

                f.renameTo(g);
                this.address = address;
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getMarketValue()
    {
        return marketValue;
    }

    public void setMarketValue(double marketValue)
    {
        replaceLine(Double.toString(marketValue), 3);
        this.marketValue = marketValue;
    }

    public String getLocationCategory()
    {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory)
    {
        replaceLine(locationCategory, 4);
        this.locationCategory = locationCategory;
    }

    public boolean isPrincipalPrivateResidence()
    {
        return principalPrivateResidence;
    }

    public void setPrincipalPrivateResidence(boolean principalPrivateResidence)
    {
        String p = "";
        if (principalPrivateResidence)
        {
            p = "yes";
        }
        else
        {
            p = "no";
        }

        replaceLine(p, 5);
        this.principalPrivateResidence = principalPrivateResidence;
    }

    private boolean fileContainsLine(String filename, String find)
    {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            String line;
            String toChange = find;
            while ((line = file.readLine()) != null)
            {

                if (line.equals(toChange))
                {
                    return true;
                }

            }
            file.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }

        return false;
    }

    private void addPropertyTo(String filename)
    {

        try
        {
            FileWriter csvWriter1 = new FileWriter(filename, true);
            csvWriter1.append(toString() + "\n");
            csvWriter1.flush();
            csvWriter1.close();

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void swapPropertyTo(String from, String to)
    {
        removePropertyFrom(from);
        addPropertyTo(to);
    }

}
