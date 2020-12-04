
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    private List<ArrayList<Object>> registeredProperties = new ArrayList<>();
    private ArrayList<PropertyOwner> registeredOwners = new ArrayList<>();

    public PropertyManagement()
    {

    }

    public void registerOwner(PropertyOwner p)
    {
        if (registeredOwners.contains(p))
        {
            System.out.println("Owner already registered.");
        }
        else
        {
            registeredOwners.add(p);
            initializing();
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
            registeredProperties.add(p.getThisProperty());
            initializing();
        }
    }

    public List<ArrayList<Object>> getRegisteredProperties()
    {
        return registeredProperties;
    }

    public void setRegisteredProperties(ArrayList<ArrayList<Object>> registeredProperties)
    {
        this.registeredProperties = registeredProperties;
    }

    public ArrayList<PropertyOwner> getRegisteredOwners()
    {
        return registeredOwners;
    }

    public void setRegisteredOwners(ArrayList<PropertyOwner> registeredOwners)
    {
        this.registeredOwners = registeredOwners;
    }

    public ArrayList<String[]> readPropertiesFile()
    {
        //Maybe we make a spreadsheet of ll properties and divide them up here.
        
        ArrayList<String[]> props = new ArrayList<>();
        try{
        BufferedReader csvReader = new BufferedReader(new FileReader("Properties.csv"));
            String row;
            
        while ((row = csvReader.readLine()) != null)
        {
            
            String[] propertyData = row.split(",");
            props.add(propertyData);
            
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

    public void initializing()
    {
        try
        {
            FileWriter csvWriter = new FileWriter("Properties.csv");
            csvWriter.append("Owner");
            csvWriter.append(",");
            csvWriter.append("Address");
            csvWriter.append(",");
            csvWriter.append("Eircode");
            csvWriter.append(",");
            csvWriter.append("Market Value");
            csvWriter.append(",");
            csvWriter.append("Location");
            csvWriter.append(",");
            csvWriter.append("Private Residence");
            csvWriter.append("\n");

            for (ArrayList<Object> rowData : registeredProperties)
            {
                csvWriter.append(String.join(",", rowData.toString()));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            for (PropertyOwner j : registeredOwners)
            {

                FileWriter csvWrite = new FileWriter(j.getName() + ".csv");

                csvWrite.append(j.getName() + " Properties");
                csvWrite.append("\n");
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

                for (Property k : j.getProperties())
                {
                    csvWriter.append(String.join(",", k.getThisProperty().toString()));
                    csvWriter.append("\n");
                }
            }

        }
        catch (IOException ex)
        {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
