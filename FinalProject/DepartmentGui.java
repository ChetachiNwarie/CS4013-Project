import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

/**
 * DepartmentGui builds a GUI that allows the user to get data on property tax
 * payments
 *
 * @author Aoife Gleeson (19242395)
 */
public class DepartmentGui extends Application {

    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, 500, 500);
    private Stage newStage = new Stage();

    private DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    private PropertyManagement pm = new PropertyManagement();

    private ArrayList<Property> allProps = pm.getRegisteredProperties();

    private Button exit = new Button("Exit");

    private Button enter = new Button("Enter");
    private TextField addressTf = new TextField();

    private Label propDataL = new Label();
    private Button backToMenuBt = new Button("Back to menu");

    private TextField ownerTf = new TextField();

    private Label ownerDataL = new Label();

    private TextField yearTf = new TextField();
    private TextField eircodeRoutingKeyTf = new TextField();
    private Label getOverdueTaxL = new Label();

    private Label enterRoutingKeyL = new Label("Enter routing key");
    private TextField routingKeyTf = new TextField();

    private TextArea areaStatsTa = new TextArea();

    private TextField newFixedCostTf = new TextField();

    private TextField newFirstBracketValueTf = new TextField();
    private TextField newSecondBracketValueTf = new TextField();
    private TextField newThirdBracketValueTf = new TextField();

    private TextField newFirstBracketRateTf = new TextField();
    private TextField newSecondBracketRateTf = new TextField();
    private TextField newThirdBracketRateTf = new TextField();
    private TextField newFourthBracketRateTf = new TextField();

    private TextField newFirstLocationRateTf = new TextField();
    private TextField newSecondLocationRateTf = new TextField();
    private TextField newThirdLocationRateTf = new TextField();
    private TextField newFourthLocationRateTf = new TextField();
    private TextField newFifthLocationRateTf = new TextField();

    private TextField newPprTf = new TextField();
    
    private TextField newPenaltyTf = new TextField();
    
    private Label statsL = new Label();

    /**
     * A method which creates the first window that welcomes the user
     *
     * @param primaryStage the first stage
     */
    @Override
    public void start(Stage primaryStage) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label welcomeL = new Label("Welcome to the Department of Environment Managament System");
        Button continueBt = new Button("Continue");
        
        grid.add(welcomeL, 0, 0);
        grid.add(continueBt, 0, 1);

        continueBt.setOnAction(e -> deptOptions());

        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * A method which displays the options the user has and allows them to
     * select one using radio buttons
     */
    public void deptOptions() {
        grid.getChildren().clear();

        Label optionsL = new Label("Do you want to:");
        RadioButton dataParticularPropRb = new RadioButton("Get property tax payment data for a particular property");
        RadioButton dataParticularOwnerRb = new RadioButton("Get property tax payment data for a particular property owner");
        RadioButton overdueTaxRb = new RadioButton("Get a list of all overdue property tax for a particular year");
        RadioButton dataParticularAreaRb = new RadioButton("Get property tax statistics for a particular area");
        RadioButton investigateRb = new RadioButton("Investigate the impact of possible changes to the rates and levies");
        
        grid.add(optionsL, 0, 0);
        grid.add(dataParticularPropRb, 0, 1);
        grid.add(dataParticularOwnerRb, 0, 2);
        grid.add(overdueTaxRb, 0, 3);
        grid.add(dataParticularAreaRb, 0, 4);
        grid.add(investigateRb, 0, 5);
        grid.add(exit, 0, 6);

        ExitHandlerClass handle = new ExitHandlerClass();
        exit.setOnAction(handle);
        dataParticularPropRb.setOnAction(e -> propData());
        dataParticularOwnerRb.setOnAction(e -> ownerData());
        overdueTaxRb.setOnAction(e -> overdueData());
        dataParticularAreaRb.setOnAction(e -> areaStats());
        investigateRb.setOnAction(e -> changeFixedCost());

        newStage.setTitle("Department Options");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the address of a property to find
     * data for that specific property
     */
    public void propData() {
        newStage.close();
        grid.getChildren().clear();
        
        Label enterAddressL = new Label("Enter the address");

        grid.add(enterAddressL, 0, 0);
        grid.add(addressTf, 1, 0);
        grid.add(enter, 1, 1);

        enter.setOnAction(e -> getPropData());

        newStage.setTitle("Property Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which displays the data for a particular property specified in
     * propData()
     */
    public void getPropData() {
        newStage.close();
        grid.getChildren().clear();

        Property b = null;
        for (int i = 0; i < allProps.size(); i++) {
            if (allProps.get(i).getAddress().equals(addressTf.getText())) {
                b = allProps.get(i);
            }
        }

        propDataL.setText("Year Amount Paid\n" + dmm.getPropPaymentData(b));

        grid.add(propDataL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Property Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the name of an owner
     */
    public void ownerData() {
        newStage.close();
        grid.getChildren().clear();
        
        Label enterOwnerL = new Label("Enter owner:");

        grid.add(enterOwnerL, 0, 0);
        grid.add(ownerTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);

        enter.setOnAction(e -> getOwnerData());

        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which displays data for a specific owner specified in
     * ownerData()
     */
    public void getOwnerData() {
        newStage.close();
        grid.getChildren().clear();
        pm.getPropertyByOwner(ownerTf.getText()); 

        String s = "";
        ArrayList<Property> ownerProps = pm.getPropertyByOwner(ownerTf.getText());
        for (int i = 0; i < ownerProps.size(); i++) {
            
            s = s + ownerProps.get(i).getPaymentRecords().toString() + "\n";
        }
        ownerDataL.setText("Year Amount Paid\n" + s);

        grid.add(ownerDataL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which gives the user an option of using the routing key of an
     * eircode to find data relating to overdue payments
     */
    public void overdueData() {
        newStage.close();
        grid.getChildren().clear();
        
        Label useEircodeL = new Label("Do you want to use an eircode routing key?");
        RadioButton yesKeyRb = new RadioButton("Yes");
        RadioButton noKeyRb = new RadioButton("No");

        grid.add(useEircodeL, 0, 0);
        grid.add(yesKeyRb, 0, 1);
        grid.add(noKeyRb, 0, 2);

        yesKeyRb.setOnAction(e -> setDetailsOverdueDataWithKey());
        noKeyRb.setOnAction(e -> setDetailsOverdueDataWithoutKey());

        newStage.setTitle("Overdue Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the year and routing key for
     * which they want overdue payments data on
     */
    public void setDetailsOverdueDataWithKey() {
        newStage.close();
        grid.getChildren().clear();
        Label enterYearL = new Label("Enter year:");
        
        Label enterEircodeRoutingKeyL = new Label("Enter eircode routing key:");

        grid.add(enterYearL, 0, 1);
        grid.add(yearTf, 1, 1);
        grid.add(enterEircodeRoutingKeyL, 0, 2);
        grid.add(eircodeRoutingKeyTf, 1, 2);
        grid.add(enter, 1, 3);
        grid.add(exit, 0, 3);

        enter.setOnAction(e -> getOverdueDataWithKey());

        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which displays the overdue payment data given the user selected
     * to use the routing key and year
     */
    public void getOverdueDataWithKey() {
        newStage.close();
        grid.getChildren().clear();

        String routekey = eircodeRoutingKeyTf.getText();
        int year = Integer.parseInt(yearTf.getText());
        ArrayList<Property> areaProps = new ArrayList<Property>();
        for (int i = 0; i < allProps.size(); i++) {
            if (routekey.equals(allProps.get(i).getEircode().toUpperCase().substring(0, 3))) {
                areaProps.add(allProps.get(i));
            }
        }
        String overdue = "";
        for (int i = 0; i < areaProps.size(); i++) {
            for (int j = 0; j < areaProps.get(i).getOverdueRecords().size(); j++) {
                if (areaProps.get(i).getOverdueRecords().get(j).getYear() == year) {
                    overdue = overdue + areaProps.get(i).getOverdueRecords().toString() + "\n";
                }
            }
        }

        getOverdueTaxL.setText("Year Amount Paid\n" + overdue);

        grid.add(getOverdueTaxL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Overdue Tax Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the year for which they want
     * overdue payments data on and displays the data when the enter button is
     * pressed
     */
    public void setDetailsOverdueDataWithoutKey() {
        newStage.close();
        grid.getChildren().clear();
        Label enterYearL = new Label("Enter year:");

        grid.add(enterYearL, 0, 0);
        grid.add(yearTf, 1, 0);
        grid.add(enter, 1, 2);
        grid.add(exit, 0, 2);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();

                int year = Integer.parseInt(yearTf.getText());
                String overdue = "";
                for (int i = 0; i < allProps.size(); i++) {
                    for (int j = 0; j < allProps.get(i).getOverdueRecords().size(); j++) {
                        if (allProps.get(i).getOverdueRecords().get(j).getYear() == year) {
                            overdue = overdue + allProps.get(i).getOverdueRecords().toString() + "\n";
                        }
                    }
                }

                getOverdueTaxL.setText("Year Amount Paid\n" + overdue);
                grid.add(getOverdueTaxL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);

                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Overdue Tax Data");
                newStage.setScene(scene);
                newStage.show();

            }
        });

        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the routing key to find
     * statistics for that area
     */
    public void areaStats() {
        newStage.close();
        grid.getChildren().clear();

        grid.add(enterRoutingKeyL, 0, 0);
        grid.add(routingKeyTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);

        enter.setOnAction(e -> getAreaStats());

        newStage.setTitle("Area Statistics");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that displays the statistics for the area specified in
     * areaStats()
     */
    public void getAreaStats() {
        newStage.close();
        grid.getChildren().clear();

        String routekey = routingKeyTf.getText();
        ArrayList<Property> areaProps = new ArrayList<Property>();
        for (int i = 0; i < allProps.size(); i++) {
            if (routekey.equals(allProps.get(i).getEircode().toUpperCase().substring(0, 3))) {
                areaProps.add(allProps.get(i));
            }
        }
        String s = "";
        for (int i = 0; i < areaProps.size(); i++) {
            s = s + areaProps.get(i).toString() + "\n";
        }
        areaStatsTa.setText(s);
        areaStatsTa.setEditable(false);

        grid.add(areaStatsTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Area Statistics");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the fixed cost used to calculate
     * property tax
     */
    public void changeFixedCost() {
        newStage.close();
        grid.getChildren().clear();
        
        Label changeFixedCostL = new Label("Change fixed cost");
        Label newFixedCostL = new Label("Enter the new fixed cost");

        grid.add(changeFixedCostL, 0, 0);
        grid.add(newFixedCostL, 0, 1);
        grid.add(newFixedCostTf, 1, 1);
        grid.add(exit, 0, 2);
        grid.add(enter, 1, 2);

        enter.setOnAction(e -> changeBracketValues());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the brackets of the values for
     * market value tax
     */
    public void changeBracketValues() {
        newStage.close();
        grid.getChildren().clear();

        Property.setFixedCost(Double.parseDouble(newFixedCostTf.getText()));
        
        Label changeBracketValueL = new Label("Change bracket values");
        Label newFirstBracketValueL = new Label("Enter the first value");
        Label newSecondBracketValueL = new Label("Enter the second value");
        Label newThirdBracketValueL = new Label("Enter the third value");

        grid.add(changeBracketValueL, 0, 0);
        grid.add(newFirstBracketValueL, 0, 1);
        grid.add(newFirstBracketValueTf, 1, 1);
        grid.add(newSecondBracketValueL, 0, 2);
        grid.add(newSecondBracketValueTf, 1, 2);
        grid.add(newThirdBracketValueL, 0, 3);
        grid.add(newThirdBracketValueTf, 1, 3);
        grid.add(exit, 0, 4);
        grid.add(enter, 1, 4);

        enter.setOnAction(e -> changeBracketRates());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the rates for market value tax
     */
    public void changeBracketRates() {
        newStage.close();
        grid.getChildren().clear();
        
        Label changeBracketRateL = new Label("Change bracket rates");
        Label newFirstBracketRateL = new Label("Enter the first value");
        Label newSecondBracketRateL = new Label("Enter the second value");
        Label newThirdBracketRateL = new Label("Enter the third value");
        Label newFourthBracketRateL = new Label("Enter the fourth value");

        grid.add(changeBracketRateL, 0, 0);
        grid.add(newFirstBracketRateL, 0, 1);
        grid.add(newFirstBracketRateTf, 1, 1);
        grid.add(newSecondBracketRateL, 0, 2);
        grid.add(newSecondBracketRateTf, 1, 2);
        grid.add(newThirdBracketRateL, 0, 3);
        grid.add(newThirdBracketRateTf, 1, 3);
        grid.add(newFourthBracketRateL, 0, 4);
        grid.add(newFourthBracketRateTf, 1, 4);
        grid.add(exit, 0, 5);
        grid.add(enter, 1, 5);

        double first = Double.parseDouble(newFirstBracketValueTf.getText());
        double second = Double.parseDouble(newSecondBracketValueTf.getText());
        double third = Double.parseDouble(newThirdBracketValueTf.getText());
        double[] newBrackets = {0, first, second, third};
        Property.setValueBrackets(newBrackets);

        enter.setOnAction(e -> changeLocationRates());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the rates for location category
     */
    public void changeLocationRates() {
        newStage.close();
        grid.getChildren().clear();

        double first = Double.parseDouble(newFirstBracketRateTf.getText());
        double second = Double.parseDouble(newSecondBracketRateTf.getText());
        double third = Double.parseDouble(newThirdBracketRateTf.getText());
        double fourth = Double.parseDouble(newFourthBracketRateTf.getText());
        double[] newRates = {first, second, third, fourth};
        Property.setValueBrackets(newRates);
        
        Label changeLocationRateL = new Label("Change location rate");
        Label newFirstLocationRateL = new Label("Enter the first value");
        Label newSecondLocationRateL = new Label("Enter the second value");
        Label newThirdLocationRateL = new Label("Enter the third value");
        Label newFourthLocationRateL = new Label("Enter the fourth value");
        Label newFifthLocationRateL = new Label("Enter the fifth value");

        grid.add(changeLocationRateL, 0, 0);
        grid.add(newFirstLocationRateL, 0, 1);
        grid.add(newFirstLocationRateTf, 1, 1);
        grid.add(newSecondLocationRateL, 0, 2);
        grid.add(newSecondLocationRateTf, 1, 2);
        grid.add(newThirdLocationRateL, 0, 3);
        grid.add(newThirdLocationRateTf, 1, 3);
        grid.add(newFourthLocationRateL, 0, 4);
        grid.add(newFourthLocationRateTf, 1, 4);
        grid.add(newFifthLocationRateL, 0, 5);
        grid.add(newFifthLocationRateTf, 1, 5);
        grid.add(exit, 0, 6);
        grid.add(enter, 1, 6);

        enter.setOnAction(e -> changePpr());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to the charge for the property not being
     * the principal private residence of the owner
     */
    public void changePpr() {
        newStage.close();
        grid.getChildren().clear();

        double first = Double.parseDouble(newFirstLocationRateTf.getText());
        double second = Double.parseDouble(newSecondLocationRateTf.getText());
        double third = Double.parseDouble(newThirdLocationRateTf.getText());
        double fourth = Double.parseDouble(newFourthLocationRateTf.getText());
        double fifth = Double.parseDouble(newFifthLocationRateTf.getText());
        double[] newRates = {first, second, third, fourth, fifth};
        Property.setLocationCatRates(newRates);
        
        Label changePprL = new Label("Change charge for not \nbeing principal residence");
        Label newPprL = new Label("Enter the new value");

        grid.add(changePprL, 0, 0);
        grid.add(newPprL, 0, 1);
        grid.add(newPprTf, 1, 1);
        grid.add(exit, 0, 2);
        grid.add(enter, 1, 2);
        
        enter.setOnAction(e -> changePenalty());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the penalty for previous unpaid
     * years
     */
    public void changePenalty() {
        newStage.close();
        grid.getChildren().clear();
        
        Property.setPrincipalPrivateRate(Double.parseDouble(newPprTf.getText()));
        
        Label changePenaltyL = new Label("Change penalty");
        Label newPenaltyL = new Label("Enter the new value");

        grid.add(changePenaltyL, 0, 0);
        grid.add(newPenaltyL, 0, 1);
        grid.add(newPenaltyTf, 1, 1);
        grid.add(exit, 0, 2);
        grid.add(enter, 1, 2);
        
        enter.setOnAction(e -> getStatistics());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which displays a series of statistics on payment records
     */
    public void getStatistics() {
        newStage.close();
        grid.getChildren().clear();
        
        Property.setUnpaidPenalty(Double.parseDouble(newPenaltyTf.getText()));

        statsL.setText(dmm.statistics(allProps));

        grid.add(statsL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
        
        Property.setFixedCost(100);
        double[] originalValBrackets = {0, 150000, 400000, 650000};
        Property.setValueBrackets(originalValBrackets);
        double[] originalValBracketRates = {0, 0.0001, 0.0002, 0.0004};
        Property.setValueBracketRates(originalValBracketRates);
        double[] originalLocationCatRates = {100, 80, 60, 50, 25};
        Property.setLocationCatRates(originalLocationCatRates);
        Property.setPrincipalPrivateRate(100);
        Property.setUnpaidPenalty(0.07);
    }
}
