
import java.util.Scanner;
import java.io.IOException;

/**
 * A menu from the vending machine.
 *
 *
 *
 *
 */
public class PropertyChargeManagementSystem
{

    private Scanner in;
    private String[] locations =
    {
        "City", "Large town", "Small Town", "Village", "Countryside"
    };

    private String[] privateResidence =
    {
        "Yes", "No"
    };

    /**
     * Constructs a VendingMachineMenu object
     */
    public PropertyChargeManagementSystem()
    {
        in = new Scanner(System.in);
    }

    /**
     * Runs the vending machine system.
     *
     * @param prop the vending machine
     */
    public void run(PropertyManagement prop)
            throws IOException
    {
        boolean more = true;

        while (more)
        {
            System.out.println("S)how all properties  O)Show all owners  B)uy  A)dd property  R)emove coins  Q)uit");
            String command = in.nextLine().toUpperCase();

            if (command.equals("S"))
            {

                for (String[] r : prop.readPropertiesFile())
                {
                    System.out.println(r.toString());
                }

                /*
                if (prop.getRegisteredProperties().size() == 0)
                {
                    System.out.println("No properties to show.");
                }
                else
                {
               
                    
                    for (int i = 0; i < prop.getRegisteredProperties().size(); i++)
                    {
                        System.out.println(prop.getRegisteredProperties().get(i).toString());
                    }
                 */
            
        }
        else if (command.equals("O"))
            {
                for (int i = 0; i < prop.getRegisteredOwners().size(); i++)
                {
                    System.out.println(prop.getRegisteredOwners().get(i));
                }
            }
            else if (command.equals("I")) //allows one coin be inserted at a time
            {
                // prop.addCoin((Coin) getChoice(coins));
            }
            else if (command.equals("R"))
            {
                //System.out.println("Removed: " + prop.removeMoney());
            }
            else if (command.equals("B"))
            {
                /*
                try
                {
                    Product p = (Product) getChoice(prop.getProductTypes());//Get product types has to be an array
                    prop.buyProduct(p);
                }
                catch (VendingException ex)
                {
                    System.out.println(ex.getMessage());
                }
                 */
            }
            else if (command.equals("A"))
            {
                Property a = new Property();
                
                System.out.println("Owner:");
                PropertyOwner b = new PropertyOwner(in.nextLine());
                if(!prop.getRegisteredOwners().contains(b)){
                    prop.registerOwner(b);
                }
                
                a.setOwner(b);
                System.out.println("Address:");
                a.setAddress(in.nextLine());
                System.out.println("Eircode:");
                a.setEircode(in.nextLine());
                System.out.println("Market Value:");
                a.setMarketValue(in.nextDouble());
                System.out.println("Location: ");
                a.setLocation((String) getChoice(locations));
                System.out.println("Private residence?:");
                String bo = (String) getChoice(privateResidence);
                if (bo.equals("Yes"))
                {
                    a.setPrivateResidence(true);
                }
                else
                {
                    a.setPrivateResidence(false);
                }
                in.nextLine(); // read the new-line character
                prop.registerProperty(a);
            }
            else if (command.equals("Q"))
            {
                more = false;
            }
    }
}

private String getChoice(String[] choices)
    {
        if (choices.length == 0)
        {
            return null;
        }
        while (true)
        {
            char c = 'A';
            for (String choice : choices)
            {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.next();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
            {
                return choices[n];
            }
        }
    }

}
