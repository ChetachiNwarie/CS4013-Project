
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
    private static double fixedCost = 100;
    private static double[] valueBrackets =
    {
        0, 150000, 400000, 650000
    };
    private static double[] valueBracketRates =
    {
        0, 0.0001, 0.0002, 0.0004
    };
    private static double[] locationCatRates =
    {
        100, 80, 60, 50, 25
    };
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
    
    //back to Sam
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

    /**
     * Returns the current year which comes from the LocalDate class.
     * @return The current year.
     */
    public static int getCurrentYear()
    {
        return currentYear;
    }

    /**
     * Sets the current year manually.
     * @param currentYear The year to set as the current year.
     */
    public static void setCurrentYear(int currentYear)
    {
        Property.currentYear = currentYear;
    }

    /**
     * Returns the PaymentRecord associated with a given year.
     * @param year The year associated with the PaymentRecord to be returned.
     * @return The PaymentRecord associated with the given year.
     */
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
    
    /**
     * Returns an ArrayList containing all the PaymentRecords for this property
     * 
     * @return An ArrayList containing all the PaymentRecords for this property
     */
    public ArrayList<PaymentRecord> getAllRecords(){
        return paymentRecords;
    }

      /**
     *  Calculates and returns the tax due on this property based on the fixed rate,
     * market value, location and whether or not it is the principal private residence
     * of the owner.
     * @return The tax due on this property for just this year.
     */
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

    /**
     *  Calculates and returns the tax due on this property based on the fixed rate,
     * market value, location, whether or not it is the principal private residence
     * of the owner, and how many previous years are unpaid plus the penalty for 
     * a year remaining unpaid.
     * @return The tax due on this property.
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
    
    //Back to Chetachi
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

    //Back to Sam
    
    /**
     * Returns an ArrayList containing each unpaid record for this property.
     * @return an ArrayList containing each unpaid record for this property.
     */
    public ArrayList<PaymentRecord> getOverdueRecords()
    {
        ArrayList<PaymentRecord> overdueRecords = new ArrayList<>();
        for (PaymentRecord r : getPaymentRecords())
        {
            if (!r.getWasPaid())
            {
                overdueRecords.add(r);
            }
        }

        return overdueRecords;

    }

    //Written by Sam and Chetachi
    
    /**
     * Sets the wasPaid attribute of the unpaid PaymentRecords to true, sets the amount paid
     * and changes the csv file to represent this
     */
    public void payTax()
    {
        ArrayList<PaymentRecord> overdueRecords = getOverdueRecords();
        for (PaymentRecord r : overdueRecords)
        {
            r.replaceLine("Payment Records\\" + address.toUpperCase() + " Payment Records.csv", "yes", 2);
        }
        overdueRecords.clear();

    }


    //Written by Chetachi
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
                        line = split2[1].replaceAll(",\\s",",");
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

