
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cnwar
 */
public class PropertyManagement
{

    private ArrayList<Property> registeredProperties = new ArrayList<>();
    private ArrayList<PropertyOwner> registeredOwners = new ArrayList<>();

    public PropertyManagement()
    {
        readPropertiesFile();
    }

    //Check this is it needed?
    public ArrayList<Property> getPropertyByLocation(String loc)
    {
        ArrayList<Property> props = new ArrayList<>();
        try
        {
            BufferedReader csvReader = new BufferedReader(new FileReader("Properties.csv"));
            String line;
            while ((line = csvReader.readLine()) != null)
            {
                String[] spl = line.split(",");
                if (spl[4].toUpperCase() == loc.toUpperCase())
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
        return props;
    }

    public void registerOwner(PropertyOwner p)
    {

        try
        {
            File file = new File(p.getName().toUpperCase() + ".csv");
            if (file.exists())
            {

                System.out.println("Owner already registered.");

            }
            else

            {
                FileWriter csvWriter = new FileWriter(file, true);
                csvWriter.write("Address,Eircode,MarketValue,Location,Private Residence,");
                registeredOwners.add(p);
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(PropertyManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void registerProperty(Property p)
    {
        if (registeredProperties.contains(p))
        {
            System.out.println("Property already registered.");
        }
        else
        {
            registeredProperties.add(p);
            initializing();
        }
    }

    public ArrayList<Property> getRegisteredProperties()
    {
        readPropertiesFile();
        return registeredProperties;

    }

    public void setRegisteredProperties(ArrayList<Property> registeredProperties)
    {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("Properties.csv"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            int i = 0;

            while (((line = file.readLine()) != null) && (i < 1))
            {
                inputBuffer.append(line);
                inputBuffer.append('\n');
                i++;
            }

            for (Property p : registeredProperties)
            {
                inputBuffer.append(p.toString() + '\n');
            }

            file.close();
            FileOutputStream fileOut = new FileOutputStream("Properties", false);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
        catch (Exception e)
        {
            System.err.println("Problem reading file.");
        }

        readPropertiesFile();

    }

    public ArrayList<PropertyOwner> getRegisteredOwners()
    {
        readPropertiesFile();
        return registeredOwners;
    }

    public void setRegisteredOwners(ArrayList<PropertyOwner> registeredOwners)
    {
        this.registeredOwners = registeredOwners;
    }

    public void readPropertiesFile()
    {
        ArrayList<Property> props = new ArrayList<>();
        ArrayList<PropertyOwner> own = new ArrayList<>();
        try
        {
            BufferedReader csvReader = new BufferedReader(new FileReader("Properties.csv"));
            String line;
            int i = 0;
            while ((line = csvReader.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] spl = line.split(",");
                    boolean paid = false;
                    if (spl[5].equals("yes"))
                    {
                        paid = true;
                    }

                    Property a = new Property(spl[0], spl[1], spl[2], Double.parseDouble(spl[3]), spl[4], paid);
                    props.add(a);
                    if (!own.contains(spl[0]))
                    {
                        own.add(new PropertyOwner(spl[0]));
                    }
                }
                i++;
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
        registeredProperties = props;
        registeredOwners = own;

    }

    public void initializing()
    {
        try
        {

            for (PropertyOwner j : registeredOwners)
            {

                FileWriter csvWrite = new FileWriter(j.getName().toUpperCase() + ".csv");

                csvWrite.append("Owner");
                csvWrite.append(",");
                csvWrite.append("Address");
                csvWrite.append(",");
                csvWrite.append("Eircode");
                csvWrite.append(",");
                csvWrite.append("Market Value");
                csvWrite.append(",");
                csvWrite.append("Location");
                csvWrite.append(",");
                csvWrite.append("Private Residence");
                csvWrite.append("\n");

                csvWrite.flush();
                csvWrite.close();
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        

    public void addToPropertiesFile(Property a)
    {
        if (getRegisteredProperties().contains(a))
        {
            removeFromPropertiesFile(a);
        }
        if (!getRegisteredOwners().contains(a.getOwner()))
        {
            File f = new File(a.getOwner().toUpperCase() + ".csv");

        }

        String[] files =
        {
            "Properties.csv", a.getOwner().toUpperCase() + ".csv"
        };
        for (String filename : files)
        {
            try
            {
                FileWriter csvWriter1 = new FileWriter(filename, true);
                csvWriter1.append(a.toString() + "\n");
                csvWriter1.flush();
                csvWriter1.close();

            }
            catch (IOException ex)
            {
                Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void removeFromPropertiesFile(Property a)
    {

        a.deleteProperty();
        readPropertiesFile();

    }

}
