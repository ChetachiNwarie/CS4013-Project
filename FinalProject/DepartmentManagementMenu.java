
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Samuel O'Mahony (19236719)
 * @author Aoife Gleeson
 */
public class DepartmentManagementMenu {

    //Sam's code
    public ArrayList<Property> allProperties = new ArrayList<Property>();
    private Scanner in = new Scanner(System.in);

    /**
     * Returns the toString for a given property p
     *
     * @param p The Property which data is to be returned.
     * @return The toString for the property p.
     */
    public String getPropertyData(Property p) {
        for (int i = 0; i < allProperties.size(); i++) {
            if (p.equals(allProperties.get(i))) {
                return p.toString();
            }
        }
        System.err.println("No such property is registered");
        return null;
    }
    
    //aoife's method
    public String getPropPaymentData(Property p){
        return p.getPaymentRecords().toString();
    }

    /**
     * Returns the toString for a given PropertyOwner.
     *
     * @param own The owner whose data is to be returned.
     * @return The toString for the owner own.
     */
    public String getOwnerData(PropertyOwner own) {
        return own.toString();
    }

    /**
     * Returns a String composed of the toString of every Property that has
     * overdue property tax for a given year optionally just in a given
     * region(using Eircode routing key).
     *
     * @param year The year that is being checked for overdue tax.
     * @return The toString of every relevant Property that has overdue for a
     * given year.
     */
    public String getOverdueTax(int year) {
        String overdue = "";

        System.out.println("Do you want to use an eircode routing key? Y/N");
        String choice = in.nextLine().toUpperCase();

        if (choice.equals("Y")) {
            System.out.println("Enter the routing key");
            String routekey = in.nextLine().toUpperCase();
            ArrayList<Property> areaProperties = areaRecords(routekey);

            for (int i = 0; i < areaProperties.size(); i++) {
                if (!areaProperties.get(i).getRecord(year).getWasPaid()) {
                    overdue += areaProperties.get(i).toString();
                }
            }
        } else {
            for (int i = 0; i < allProperties.size(); i++) {
                if (!allProperties.get(i).getRecord(year).getWasPaid()) {
                    overdue += allProperties.get(i).toString();
                }
            }
        }

        return overdue;
    }

    /**
     * Returns an ArrayList containing every Property in a given region by using
     * that region's Eircode routing key.
     *
     * @param routekey The Eircode routing key to be used to select a region of
     * properties.
     * @return an ArrayList containing every Property in a given region.
     */
    private ArrayList<Property> areaRecords(String routekey) {
        ArrayList<Property> areaProperties = new ArrayList<Property>();

        for (int i = 0; i < allProperties.size(); i++) {
            if (routekey.equals(allProperties.get(i).getEircode().toUpperCase().substring(0, 3))) {
                areaProperties.add(allProperties.get(i));
            }
        }

        return areaProperties;
    }

    /**
     * Returns a String containing the total tax paid, average tax paid, number
     * of property taxes paid and percentage of taxes paid for a given region
     * using an Eircode routing key.
     *
     * @return A String containing the total tax paid, average tax paid, number
     * of property taxes paid and percentage of taxes paid for a given region.
     */
    public String areaStatistics() {
        System.out.println("Enter the routing key");
        String routekey = in.nextLine().toUpperCase();
        ArrayList<Property> areaProperties = areaRecords(routekey);

        return statistics(areaProperties);
    }

    /**
     * Does the calculations for the statistics of a given ArrayList of
     * properties and returns a String containing the calculated information.
     *
     * @param props The ArrayList of properties to calculate the statistics of.
     * @return A String containing the calculated statistics of the properties.
     * (the total tax paid, average tax paid, number of property taxes paid and
     * percentage of taxes paid)
     */
    public String statistics(ArrayList<Property> props) {
        String stats = "";
        double totalAmount = 0;
        int numRecords = 0;
        int numPaidRecords = 0;

        for (int i = 0; i < props.size(); i++) {
            ArrayList<PaymentRecord> payRecords = props.get(i).getAllRecords();

            for (int j = 0; i < payRecords.size(); j++) {
                totalAmount += payRecords.get(j).getAmount();
            }
            numRecords += payRecords.size();
            for (int k = 0; k < payRecords.size(); k++) {
                if (payRecords.get(k).getWasPaid()) {
                    numPaidRecords++;
                }
            }
        }

        return String.format("Total tax paid : %.2f\nAverage tax paid: %.2f\nNumber of property taxes paid : %d\nPercentage of taxes paid : %.2f\n",
                totalAmount, (totalAmount / props.size()), numRecords, (numPaidRecords / numRecords));
    }

    /**
     * Prompts the user to make a number of changes to the variables used in
     * calculating property tax and then informs them how the changes would
     * affect the statistics about the tax collected.
     *
     * @return A String informing the user about how the changes made would
     * affect the tax collected.
     */
    public String investigate() {
        String choice = "";

        do {
            System.out.println("Would you like to change the fixed cost? Y/N (Default=100)");
            choice = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double newCost = -1;
            do {
                System.out.println("Enter the new fixed cost: ");
                newCost = in.nextInt();
            } while (newCost < 0);
            Property.setFixedCost(newCost);
        }

        do {
            System.out.println("Would you like to change the brackets of the values for market value tax? Y/N (Default=150000, 400000, 650000)");
            String choice2 = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double firstBracket = -1;
            do {
                System.out.println("Enter the new first bracket: ");
                firstBracket = in.nextDouble();
            } while (firstBracket < 0);

            double secondBracket = -1;
            do {
                System.out.println("Enter the new second bracket: ");
                secondBracket = in.nextDouble();
            } while (secondBracket < firstBracket);

            double thirdBracket = -1;
            do {
                System.out.println("Enter the new third bracket: ");
                thirdBracket = in.nextDouble();
            } while (thirdBracket < secondBracket);

            double[] newBrackets = {0, firstBracket, secondBracket, thirdBracket};
            Property.setValueBrackets(newBrackets);

        }

        do {
            System.out.println("Would you like to change the rates for market value tax? Y/N (Default=0, 0.0001, 0.0002, 0.0004)");
            choice = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double firstRate = -1;
            do {
                System.out.println("Enter the new first rate: ");
                firstRate = in.nextDouble();
            } while (firstRate < 0 || firstRate > 1);

            double secondRate = -1;
            do {
                System.out.println("Enter the new second rate: ");
                secondRate = in.nextDouble();
            } while (secondRate < firstRate || secondRate > 1);

            double thirdRate = -1;
            do {
                System.out.println("Enter the new third rate: ");
                thirdRate = in.nextDouble();
            } while (thirdRate < secondRate || secondRate > 1);

            double fourthRate = -1;
            do {
                System.out.println("Enter the new fourth rate: ");
                fourthRate = in.nextDouble();
            } while (fourthRate < thirdRate || secondRate > 1);

            double[] newRates = {firstRate, secondRate, thirdRate, fourthRate};
            Property.setValueBracketRates(newRates);
        }

        do {
            System.out.println("Would you like to change the rates for location category? Y/N (Default=25, 50, 60, 80, 100)");
            choice = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double firstRate = -1;
            do {
                System.out.println("Enter the new first rate: ");
                firstRate = in.nextDouble();
            } while (firstRate < 0);

            double secondRate = -1;
            do {
                System.out.println("Enter the new second rate: ");
                secondRate = in.nextDouble();
            } while (secondRate < firstRate);

            double thirdRate = -1;
            do {
                System.out.println("Enter the new third rate: ");
                thirdRate = in.nextDouble();
            } while (thirdRate < secondRate);

            double fourthRate = -1;
            do {
                System.out.println("Enter the new fourth rate: ");
                fourthRate = in.nextDouble();
            } while (fourthRate < thirdRate);

            double fifthRate = -1;
            do {
                System.out.println("Enter the new fifth rate: ");
                fifthRate = in.nextDouble();
            } while (fifthRate < fourthRate);

            double[] newRates = {firstRate, secondRate, thirdRate, fourthRate, fifthRate};
            Property.setLocationCatRates(newRates);
        }

        do {
            System.out.println("Would you like to change the charge for the property "
                    + "not being the principal private residence of the owner? Y/N (Default=100)");
            choice = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double newCharge = -1;
            do {
                System.out.println("Enter the new charge: ");
                newCharge = in.nextDouble();
            } while (newCharge < 0);
            Property.setPrincipalPrivateRate(newCharge);
        }

        do {
            System.out.println("Would you like to change the penalty for previous unpaid years? Y/N (Default=0.07)");
            choice = in.nextLine().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        if (choice.equals("Y")) {
            double newPenalty = -1;
            do {
                System.out.println("Enter the new penalty: ");
                newPenalty = in.nextDouble();
            } while (newPenalty < 0 || newPenalty > 1);
            Property.setUnpaidPenalty(newPenalty);
        }

        String stats = statistics(allProperties);

        Property.setFixedCost(100);
        double[] originalValBrackets = {0, 150000, 400000, 650000};
        Property.setValueBrackets(originalValBrackets);
        double[] originalValBracketRates = {0, 0.0001, 0.0002, 0.0004};
        Property.setValueBracketRates(originalValBrackets);
        double[] originalLocationCatRates = {100, 80, 60, 50, 25};
        Property.setLocationCatRates(originalLocationCatRates);
        Property.setPrincipalPrivateRate(100);
        Property.setUnpaidPenalty(0.07);

        return stats;
    }

    //aoife's code
    public ArrayList<Property> sortByEircode(String routekey) {
        routekey = routekey.toUpperCase();
        ArrayList<Property> routeKeyProps = areaRecords(routekey);
        return routeKeyProps;
    }
}
