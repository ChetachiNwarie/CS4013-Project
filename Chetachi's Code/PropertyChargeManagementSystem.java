

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

                for (Property r : prop.getRegisteredProperties())
                {
                    System.out.println(r.toString());
                }
            
        }
        else if (command.equals("O"))
            {
                for (String a : prop.getRegisteredOwners())
                {
                    System.out.println(a);
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
               
                System.out.println("Owner:");
                String name = in.nextLine();  
                System.out.println("Address:");
                String address =in.nextLine();
                System.out.println("Eircode:");
                String code = in.nextLine();
                System.out.println("Market Value:");
               double mv = in.nextDouble();
                System.out.println("Location: ");
                String loc = (String) getChoice(locations);
                System.out.println("Private residence?:");
                String bo = (String) getChoice(privateResidence);
                boolean priv = false;
                if (bo.equals("Yes"))
                {
                    priv = true;
                }
                in.nextLine(); // read the new-line character
                Property a = new Property(name, address, code, mv, loc, priv);
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

