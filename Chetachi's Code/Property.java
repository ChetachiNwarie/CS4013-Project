
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
 * @author Chetachi Nwarie (19244355)
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

    }

    
        //Chetachi's Additions
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

            File e = new File(this.address.toUpperCase() + " Payment Records.csv");
            if (!e.exists())
            {

                FileWriter csvWriter1 = new FileWriter(e, false);
                csvWriter1.append("Year,Tax,Paid\n");
                csvWriter1.flush();
                csvWriter1.close();
            }

            File f = new File(this.owner.toUpperCase() + ".csv");
            if (!f.exists())
            {
                FileWriter csvWriter2 = new FileWriter(f, true);
                csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                csvWriter2.append(toString() + "\n");
                csvWriter2.flush();
                csvWriter2.close();
            }
            else if (!fileContainsLine(f.getName(), this.toString()))
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
            BufferedReader file = new BufferedReader(new FileReader(address.toUpperCase() + " Payment Records.csv"));
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
            BufferedReader file = new BufferedReader(new FileReader(address.toUpperCase() + " Payment Records.csv"));
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
            FileOutputStream fileOut = new FileOutputStream(address.toUpperCase() + " Payment Records.csv", false);
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

    public double taxDue()
    {
        //fixed rate
        double taxDue = fixedCost;

        //rate based on market value
        for (int i = 3; i >= 0; i--)
        {
            if (marketValue > valueBrackets[i])
            {
                taxDue += marketValue * valueBracketRates[i];
                break;
            }
        }

        //charge based on location
        switch (locationCategory)
        {

            case "city":
                taxDue += 100;
                break;
            case "large town":
                taxDue += 80;
                break;
            case "small town":
                taxDue += 60;
                break;
            case "village":
                taxDue += 50;
                break;
            case "countryside":
                taxDue += 25;
                break;
        }

        //charge if not the principal private residence
        if (!principalPrivateResidence)
        {
            taxDue += 100;
        }

        //overdue penalty
        int yearsOverdue = this.getOverdueRecords().size();
        taxDue = taxDue * Math.pow(1 + unpaidPenalty, yearsOverdue);
        PaymentRecord a = new PaymentRecord(currentYear, false, taxDue);
        addToPaymentFile(a);

        return taxDue;
    }

    public void deleteProperty()
    {
        String[] files =
        {
            "Properties.csv", this.owner.toUpperCase() + ".csv"
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

        File a = new File(this.address.toUpperCase() + " Payment Records.csv");
        a.delete();
        paymentRecords.clear();

    }

    private void removePropertyFrom(String filename)
    {

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

    }

    public void removeFromPaymentFile(PaymentRecord a)
    {
        paymentRecords.remove(a);
        String filename = address.toUpperCase() + " Payment Records.csv";
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null)
            {
                if (!line.equals(a.toString()))
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

        for (PaymentRecord p : paymentRecords)
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
            FileWriter csvWriter1 = new FileWriter(this.address.toUpperCase() + " Payment Records.csv", true);
            csvWriter1.append(a.toString() + "\n");
            csvWriter1.flush();
            csvWriter1.close();

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public void payTax()
    {
        ArrayList<PaymentRecord> overdueRecords = getOverdueRecords();
        for (int i = 0; i < overdueRecords.size(); i++)
        {
            overdueRecords.get(i).replaceLine(address.toUpperCase() + " Payment Records.csv", "yes", 2);
            overdueRecords.remove(i);
        }

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
            "Properties.csv", this.owner.toUpperCase() + " Payment Records.csv"
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
            File f = new File(this.owner.toUpperCase() + ".csv");
            File g = new File(owner.toUpperCase() + ".csv");
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
            File f = new File(this.address.toUpperCase() + " Payment Records.csv");
            File g = new File(address.toUpperCase() + " Payment Records.csv");

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


