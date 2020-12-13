import java.util.Scanner;
/**
 * Command line interface for the system
 *
 * @author Owen Griffith 19276257
 */
public class CommandLineRun
{
   static Scanner in = new Scanner(System.in);
   private String[] locations =
    {
        "City", "Large town", "Small Town", "Village", "Countryside"
    };
   
   /**
     * Main method to choose to access as a property owner or as Department of Environment.
     */
    public static void main( String[] args )
    {
        System.out.println("Access system as P)roperty owner or D)epartment of Environment");
        String command = in.nextLine().toUpperCase();
        if( command.equals("P") )
            propOwn();

        else if( command.equals("D") )
            depManMenu();
    }
    
     /**
     * Command line interface for property owner with choice of possible operations.
     */
     public static void propOwn()
    {
        PropertyManagement pm = new PropertyManagement();

        System.out.println("Enter name: ");
        String n = in.nextLine().toUpperCase();
        PropertyOwner pOwner = new PropertyOwner(n);

        //check if owner already exists
        for( int i=0; i < pm.getRegisteredOwners().size(); i++ )
        {
            if( n.equals(pm.getRegisteredOwners().get(i).getName()) )
                pOwner = pm.getRegisteredOwners().get(i);
        }
        pm.registerOwner( pOwner );

        boolean more = true;
        while (more)
        { 
            System.out.println("A) Register a property\n" +
                "B) Pay property tax due for a particular property\n" +
                "C) View list of all your properties\n" +
                "D) Get a balancing statement for a particular year\n" +
                "E) Quit\n");
            String command = in.nextLine().toUpperCase();
            if (command.equals("A"))
            {
                System.out.println("Enter address: ");
                String add = in.nextLine().toUpperCase();
                System.out.println("Enter eircode: ");
                String eir = in.nextLine().toUpperCase();
                System.out.println("Enter market value: ");
                double mVal = in.nextDouble();
                System.out.println("Enter location category: ");
                String loc = (String) getChoice(locations);
                boolean ppr = false;
                System.out.println("Is this your principal private residence? Y/N");
                String ans = in.nextLine().toUpperCase();
                if( ans.equals("Y") )
                    ppr = true;

                pOwner.registerProperty(add,eir,mVal,lCat,ppr);
                pm.registerProperty( new Property(n,add,eir,mVal,lCat,ppr) );
            }
            else if (command.equals("B"))
            { 
                System.out.println("Enter the property's eircode: ");
                String p = in.nextLine().toUpperCase();
                for( int i=0; i < pm.getRegisteredProperties().size(); i++ )
                {
                    if( p.equals(pm.getRegisteredProperties().get(i).getEircode()) )
                        pOwner.payTax(pm.getRegisteredProperties().get(i));
                }
            }
            else if (command.equals("C"))
            { 
                pOwner.viewProperties();
            }
            else if (command.equals("D"))
            { 
                System.out.println("Enter year: ");
                int y = in.nextInt();
                pOwner.queryYear(y);
            }
            else if (command.equals("E"))
            { 
                more = false;
            }
        }
    }

    /**
     * Command line interface for Department Management with all possible operations.
     */
    public static void depManMenu()
    {
        DepartmentManagementMenu dmm = new DepartmentManagementMenu();
        PropertyManagement pm = new PropertyManagement();

        boolean more = true;
        while (more)
        { 
            System.out.println("A) Get property tax payment data for a particular property\n" +
                "B) Get property tax payment data for a particular property owner\n" +
                "C) Get a list of all overdue property tax for a particular year\n" +
                "D) Get property tax statistics for a particular area\n" +
                "E) Investigate the impact of possible changes to the rates and levies\n" +
                "F) Quit\n");
            String command = in.nextLine().toUpperCase();
            if (command.equals("A"))
            {
                System.out.println("Enter the property's eircode: ");
                String p = in.nextLine().toUpperCase();
                for( int i=0; i < pm.getRegisteredProperties().size(); i++ )
                {
                    if( p.equals(pm.getRegisteredProperties().get(i).getEircode()) )
                        dmm.getPropertyData(pm.getRegisteredProperties().get(i));
                }
            }
            else if (command.equals("B"))
            { 
                System.out.println("Enter property owner: ");
                String o = in.nextLine().toUpperCase();
                for( int i=0; i < pm.getRegisteredOwners().size(); i++ )
                {
                    if( o.equals(pm.getRegisteredOwners().get(i).getName()) )
                        dmm.getOwnerData(pm.getRegisteredOwners().get(i));
                }
            }
            else if (command.equals("C"))
            { 
                System.out.println("Enter year: ");
                int y = in.nextInt();
                dmm.getOverdueTax(y);
            }
            else if (command.equals("D"))
            { 
                dmm.areaStatistics();
            }
            else if (command.equals("E"))
            { 
                dmm.investigate();
            }
            else if (command.equals("F"))
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
