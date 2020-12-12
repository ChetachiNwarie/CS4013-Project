

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel O'Mahony (19236719)
 */
public class PropertyOwner {

    private String name;
    private ArrayList<Property> properties = new ArrayList<Property>();
    
    
    /**
     * Constructor for class PropertyOwner.
     * @param name The name of the owner.
     */
    public PropertyOwner(String name){
        this.name=name.toUpperCase();
    }
    
    /**
     * Registers a new Property to this owner.
     * @param address The address of the Property to be registered.
     * @param eircode The eircode of the Property to be registered.
     * @param marketValue The market value of the Property to be registered.
     * @param locationCategory The location category of the Property to be registered.
     * @param principalPrivateResidence Whether or not the Property is the principal private residence of the owner or not.
     */
    public void registerProperty(String address, String eircode,  double marketValue, String locationCategory, boolean principalPrivateResidence){
        this.properties.add( new Property(name, address, eircode, marketValue, locationCategory, principalPrivateResidence));
    }
    
    /**
     * Uses the payTax method in the Property class for a given Property owned by
     * this owner.
     * @param prop The Property to pay the tax of. 
     */
    public void payTax(Property prop){
        for(int i=0;i<properties.size();i++){
            if(properties.get(i).equals(prop)){
                prop.payTax();
                return;
            }
        }
        System.err.println("That property does not belong to this owner");
    }
    
    /**
     * Prints the toString of each Property owned by this owner to the console.
     */
    public void viewProperties(){
        for(int i = 0; i<properties.size(); i++){
            System.out.println(properties.get(i).toString());
        }
    }
    
    /**
     * Prints the toString of each PaymentRecord of each Property owned for a given year
     * @param year The year of PaymentRecords to print.
     */
    public void queryYear(int year){
        for(int i = 0; i<properties.size(); i++){
            System.out.println(properties.get(i).getAddress());
            System.out.println(properties.get(i).getRecord(year));
        }
    }
    
    /**
     * Prints the toString of each PaymentRecord for a given Property owned by this owner.
     * @param prop The Property whose PaymentRecords toStrings are to be printed.
     */
    public void queryProperty(Property prop){
        ArrayList<PaymentRecord> allRecs = prop.getAllRecords();
        for(int i=0; i<allRecs.size();i++){
            System.out.println(allRecs.get(i).toString());
        }
    }
        
    //Aoife's code
    public Property getProperty(String address){
        Property a = null;
        for(int i=0; i<properties.size(); i++){
            if(properties.get(i).getAddress().equals(address)){
                a = properties.get(i);
            }
            else{
                a = null;
            }
        }
        return a;
    }
    
    //Chetachi's code
       public void initializing()
    {
        try
        {
            File f = new File(this.name.toUpperCase() + ".csv");
            if (!f.exists())
            {
                FileWriter csvWriter2 = new FileWriter(f, true);
                csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                csvWriter2.append(toString() + "\n");
                csvWriter2.flush();
                csvWriter2.close();
            }
            else
            {
                readPropertiesFile();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(PropertyOwner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        try
        {
            File f = new File(this.name.toUpperCase() + ".csv");
            File g = new File(name.toUpperCase() + ".csv");
            boolean a = f.exists();
            boolean b = g.exists();

            if (a == false)
            {
                FileWriter csvWriter2 = new FileWriter(g, true);
                if (b == false)
                {
                    csvWriter2.append("Owner,Address,Eircode,Market Value,Location,Private Residence\n");
                }
                this.name = name;
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
                for (Property p : getProperties())
                {
                    replaceLine(p, name, 0);//name wont be replaced in owner file as line doesnt exist
                }
                this.name = name;
                readPropertiesFile();
                for (Property p : properties)
                {
                    swapPropertyTo(p, f.toString(), g.toString());
                }

            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name = name;

    }

    public ArrayList<Property> getProperties()
    {
        ArrayList<Property> records = new ArrayList<>();
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(name.toUpperCase() + ".csv"));
            String line;
            int i = 0;

            while ((line = file.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] spl = line.split(",");
                    boolean privateRes = false;
                    if (spl[5].equals("yes"))
                    {
                        privateRes = true;
                    }

                    Property a = new Property(spl[0], spl[1], spl[2], Double.parseDouble(spl[3]), spl[4], privateRes);
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
        properties = records;
        return properties;
    }

    public void readPropertiesFile()
    {
        ArrayList<Property> props = new ArrayList<>();
        try
        {
            BufferedReader csvReader = new BufferedReader(new FileReader("Properties.csv"));
            String line;
            int i = 0;
            while ((line = csvReader.readLine()) != null)
            {
                String[] spl = line.split(",");
                if (spl[0] == name)
                {
                    boolean paid = false;
                    if (spl[5].equals("yes"))
                    {
                        paid = true;
                    }

                    Property a = new Property(spl[0], spl[1], spl[2], Double.parseDouble(spl[3]), spl[4], paid);
                    props.add(a);
                }

            }
            csvReader.close();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(PropertyManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PropertyManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        properties = props;

    }

    private void replaceLine(Property p, String change, int column)
    {
        String[] files =
        {
            "Properties.csv", this.name.toUpperCase() + " Payment Records.csv"
        };
        for (String filename : files)
        {
            try
            {
                BufferedReader file = new BufferedReader(new FileReader(filename));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                String toChange = p.toString();
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

    public String toString()
    {
        String propDetails = "";
        for (int i = 0; i < properties.size(); i++)
        {
            propDetails += properties.get(i).toString() + "\n";
        }
        return propDetails;

    }

    private void addPropertyTo(Property p, String filename)
    {

        try
        {
            FileWriter csvWriter1 = new FileWriter(filename, true);
            csvWriter1.append(p.toString() + "\n");
            csvWriter1.flush();
            csvWriter1.close();

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void swapPropertyTo(Property p, String from, String to)
    {
        removePropertyFrom(p, from);
        addPropertyTo(p, to);
    }

    private void removePropertyFrom(Property p, String filename)
    {
        //  String filename = "Properties.csv";

        try
        {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null)
            {
                if (!line.equals(p.toString()))
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

}