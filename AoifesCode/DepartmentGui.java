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
 *
 * @author Aoife Gleeson (19242395)
 */
public class DepartmentGui extends Application{
    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, 500, 500);
    private Stage newStage = new Stage();
    
    private DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    private PropertyManagement pm = new PropertyManagement();
    
    private ArrayList<Property> allProps = pm.getRegisteredProperties();
    
    private Label welcomeL = new Label("Welcome to the Department of Environment Managament System");
    private Button continueBt = new Button("Continue");
    
    private Label optionsL = new Label("Do you want to:");
    private RadioButton dataParticularPropRb = new RadioButton("Get property tax payment data for a particular property");
    private RadioButton dataParticularOwnerRb = new RadioButton("Get property tax payment data for a particular property owner");
    private RadioButton overdueTaxRb = new RadioButton("Get a list of all overdue property tax for a particular year");
    private RadioButton dataParticularAreaRb = new RadioButton("Get property tax statistics for a particular area");
    private RadioButton investigateRb = new RadioButton("Investigate the impact of possible changes to the rates and levies");
    private Button exit = new Button("Exit");
    
    private Button enter = new Button("Enter");
    private Label enterAddressL = new Label("Enter the address");
    private TextField addressTf = new TextField();
    
    private Label propDataL = new Label();
    private Button backToMenuBt = new Button("Back to menu");
    
    private Label enterOwnerL = new Label("Enter owner:");
    private TextField ownerTf = new TextField();
    
    private Label ownerDataL = new Label();
    
    private Label useEircodeL = new Label("Do you want to use an eircode routing key?");
    private RadioButton yesKeyRb = new RadioButton("Yes");
    private RadioButton noKeyRb = new RadioButton("No");
    
    private Label enterYearL = new Label("Enter year:");
    private TextField yearTf = new TextField();
    private Label enterEircodeRoutingKeyL = new Label("Enter eircode routing key:");
    private TextField eircodeRoutingKeyTf = new TextField();
    private Label getOverdueTaxL = new Label();
    
    private Label enterRoutingKeyL = new Label("Enter routing key");
    private TextField routingKeyTf = new TextField();
    
    private TextArea areaStatsTa = new TextArea();

    private Label investigateChoicesL = new Label("Do you want to");
    private RadioButton fixedCostRb = new RadioButton("Change the fixed cost");
    private RadioButton bracketValuesRb = new RadioButton("Change the brackets of the values for market value tax");
    private RadioButton bracketRatesRb = new RadioButton("Change the rates for market value tax");
    private RadioButton locationRb = new RadioButton("Change the rates for location category");
    private RadioButton pprRb = new RadioButton("Change the charge for the property "
            + "not being the \nprincipal private residence of the owner");
    private RadioButton penaltyRb = new RadioButton("Change the penalty for previous unpaid years");
    private RadioButton getStatsRb = new RadioButton("Get statistics");

    private Label newFixedCostL = new Label("Enter the new fixed cost");
    private TextField newFixedCostTf = new TextField();
    private Label thanksFixedCostL = new Label("Thank you for changing the fixed cost");

    private Label newFirstBracketValueL = new Label("Enter the first value");
    private TextField newFirstBracketValueTf = new TextField();
    private Label newSecondBracketValueL = new Label("Enter the second value");
    private TextField newSecondBracketValueTf = new TextField();
    private Label newThirdBracketValueL = new Label("Enter the third value");
    private TextField newThirdBracketValueTf = new TextField();
    private Label thanksValueBracketsL = new Label("Thank you for changing the bracket values");
    
    private Label newFirstBracketRateL = new Label("Enter the first value");
    private TextField newFirstBracketRateTf = new TextField();
    private Label newSecondBracketRateL = new Label("Enter the second value");
    private TextField newSecondBracketRateTf = new TextField();
    private Label newThirdBracketRateL = new Label("Enter the third value");
    private TextField newThirdBracketRateTf = new TextField();
    private Label newFourthBracketRateL = new Label("Enter the fourth value");
    private TextField newFourthBracketRateTf = new TextField();
    private Label thanksBracketRatesL = new Label("Thank you for changing the rates for market value tax");
    
    private Label newFirstLocationRateL = new Label("Enter the first value");
    private TextField newFirstLocationRateTf = new TextField();
    private Label newSecondLocationRateL = new Label("Enter the second value");
    private TextField newSecondLocationRateTf = new TextField();
    private Label newThirdLocationRateL = new Label("Enter the third value");
    private TextField newThirdLocationRateTf = new TextField();
    private Label newFourthLocationRateL = new Label("Enter the fourth value");
    private TextField newFourthLocationRateTf = new TextField();
    private Label newFifthLocationRateL = new Label("Enter the fifth value");
    private TextField newFifthLocationRateTf = new TextField();
    private Label thanksLocationRatesL = new Label("Thank you for changing the rates for location");
    
    private Label newPprL = new Label("Enter the new value");
    private TextField newPprTf = new TextField();
    private Label thanksPprL = new Label("Thank you for changing the charge");
    
    private Label newPenaltyL = new Label("Enter the new value");
    private TextField newPenaltyTf = new TextField();
    private Label thanksPenaltyL = new Label("Thank you for changing the penalty");
    
    private Label statsL = new Label();
    
    /**
     * A method which creates the first window that welcomes the user
     * @param primaryStage the first stage
     */
    @Override
    public void start(Stage primaryStage){
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
               
        grid.add(welcomeL, 0, 0);
        grid.add(continueBt, 0, 1);
        
        continueBt.setOnAction(e -> deptOptions());
        
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * A method which displays the options the user has and allows them to select one using radio buttons
     */
    public void deptOptions(){
        grid.getChildren().clear();
        
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
        investigateRb.setOnAction(e -> investigateChanges());
        
        newStage.setTitle("Department Options");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which allows the user to enter the address of a property to find data for that specific property
     */
    public void propData(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(enterAddressL, 0, 0);
        grid.add(addressTf, 1, 0);
        grid.add(enter, 1, 1);
        
        enter.setOnAction(e -> getPropData());

        newStage.setTitle("Property Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which displays the data for a particular property specified in propData()
     */
    public void getPropData(){
        newStage.close();
        grid.getChildren().clear();
        
        dmm.allProperties = pm.getRegisteredProperties();
        Property b = null;
        for(int i=0; i<dmm.allProperties.size(); i++){
            if(dmm.allProperties.get(i).getAddress().equals(addressTf.getText())){
                b = dmm.allProperties.get(i);
            }          
        }
               
        propDataL.setText("Owner address eircode value location principal residence\n" + dmm.getPropertyData(b));
        
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
    public void ownerData(){
        newStage.close();
        grid.getChildren().clear();
        
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
     * A method which displays data for a specific owner specified in ownerData()
     */
    public void getOwnerData(){
        newStage.close();
        grid.getChildren().clear();
        pm.getPropertyByOwner(ownerTf.getText());
        
        String s = "";
        ArrayList<Property> ownerProps = pm.getPropertyByOwner(ownerTf.getText());
        for(int i=0; i<ownerProps.size(); i++){
            s = s + ownerProps.get(i).toString() + "\n";
        }
        ownerDataL.setText(s);
        
        grid.add(ownerDataL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);
        
        backToMenuBt.setOnAction(e -> deptOptions());
               
        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which gives the user an option of using the routing key of an eircode 
     * to find data relating to overdue payments
     */
    public void overdueData(){
        newStage.close();
        grid.getChildren().clear();
        
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
     * A method which allows the user to enter the year and routing key for which they want overdue payments data on
     */
    public void setDetailsOverdueDataWithKey() {
        newStage.close();
        grid.getChildren().clear();

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
     * A method which displays the overdue payment data given the user selected to use the routing key and year
     */
    public void getOverdueDataWithKey() {
        newStage.close();
        grid.getChildren().clear();

        // copied some loops form DepartmentManagementMenu. couldnt use methods because of println
        String routekey = eircodeRoutingKeyTf.getText();
        int year = Integer.parseInt(yearTf.getText());
        ArrayList<Property> allProps = pm.getRegisteredProperties();
        ArrayList<Property> areaProps = new ArrayList<Property>();
        for(int i = 0; i<allProps.size(); i++){
            if(routekey.equals(allProps.get(i).getEircode().toUpperCase().substring(0, 3))){
                areaProps.add(allProps.get(i));
            }
        }
        String overdue = "";

        for(int i=0; i<areaProps.size(); i++){
            System.out.println(areaProps.get(i).getRecord(year).isWasPaid());
            if(!areaProps.get(i).getRecord(year).isWasPaid()){ //error here
                //System.out.println(areaProps.get(i).getOverdueRecords().toString());
                overdue+=areaProps.get(i).toString();
            } 
        }

        getOverdueTaxL.setText(overdue);

        grid.add(getOverdueTaxL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> deptOptions());

        newStage.setTitle("Overdue Tax Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
   /**
     * A method which allows the user to enter the year for which they want overdue 
     * payments data on and displays the data when the enter button is pressed
     */
    public void setDetailsOverdueDataWithoutKey() {
        newStage.close();
        grid.getChildren().clear();

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
                dmm.allProperties = pm.getRegisteredProperties();
                //ArrayList<Property> areaProperties = new ArrayList<Property>();
                for (int i = 0; i < dmm.allProperties.size(); i++) {
                    if (!dmm.allProperties.get(i).getRecord(year).getWasPaid()) { //error here
                        overdue += dmm.allProperties.get(i).toString() + "\n";
                    }
                }
                getOverdueTaxL.setText(overdue);
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
     * A method which allows the user to enter the routing key to find statistics for that area
     */
    public void areaStats(){
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
     * A method that displays the statistics for the area specified in areaStats()
     */
    public void getAreaStats(){
        newStage.close();
        grid.getChildren().clear();
        
        String routekey = routingKeyTf.getText();
        ArrayList<Property> allProps = pm.getRegisteredProperties();
        ArrayList<Property> areaProps = new ArrayList<Property>();
        for(int i = 0; i<allProps.size(); i++){
            if(routekey.equals(allProps.get(i).getEircode().toUpperCase().substring(0, 3))){
                areaProps.add(allProps.get(i));
            }
        }
        String s = "";
        for(int i=0; i<areaProps.size(); i++){
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
     * A method that gives the user options which allows them to change how tax is calculated
     */
    public void investigateChanges() {
        newStage.close();
        grid.getChildren().clear();

        grid.add(investigateChoicesL, 0, 0);
        grid.add(fixedCostRb, 0, 1);
        grid.add(bracketValuesRb, 0, 2);
        grid.add(bracketRatesRb, 0, 3);
        grid.add(locationRb, 0, 4);
        grid.add(pprRb, 0, 5);
        grid.add(penaltyRb, 0, 6);
        grid.add(getStatsRb, 0, 7);
        grid.add(exit, 0, 8);

        fixedCostRb.setOnAction(e -> changeFixedCost());
        bracketValuesRb.setOnAction(e -> changeBracketValues());
        bracketRatesRb.setOnAction(e -> changeBracketRates());
        locationRb.setOnAction(e -> changeLocationRates());
        pprRb.setOnAction(e -> changePpr());
        penaltyRb.setOnAction(e -> changePenalty());
        getStatsRb.setOnAction(e -> getStatistics());

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the fixed cost used to calculate property tax
     */
    public void changeFixedCost() {
        newStage.close();
        grid.getChildren().clear();

        grid.add(newFixedCostL, 0, 0);
        grid.add(newFixedCostTf, 1, 0);
        grid.add(exit, 0, 1);
        grid.add(enter, 1, 1);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();

                Property.setFixedCost(Double.parseDouble(newFixedCostTf.getText()));

                grid.add(thanksFixedCostL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);

                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to change the brackets of the values for market value tax
     */
    public void changeBracketValues() {
        newStage.close();
        grid.getChildren().clear();

        grid.add(newFirstBracketValueL, 0, 0);
        grid.add(newFirstBracketValueTf, 1, 0);
        grid.add(newSecondBracketValueL, 0, 1);
        grid.add(newSecondBracketValueTf, 1, 1);
        grid.add(newThirdBracketValueL, 0, 2);
        grid.add(newThirdBracketValueTf, 1, 2);
        grid.add(exit, 0, 3);
        grid.add(enter, 1, 3);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();
                
                double first = Double.parseDouble(newFirstBracketValueTf.getText());
                double second = Double.parseDouble(newSecondBracketValueTf.getText());
                double third = Double.parseDouble(newThirdBracketValueTf.getText());
                double[] newBrackets = {0, first, second, third};
                Property.setValueBrackets(newBrackets);
                
                grid.add(thanksValueBracketsL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);
                
                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });

        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which allows the user to change the rates for market value tax
     */
    public void changeBracketRates(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(newFirstBracketRateL, 0, 0);
        grid.add(newFirstBracketRateTf, 1, 0);
        grid.add(newSecondBracketRateL, 0, 1);
        grid.add(newSecondBracketRateTf, 1, 1);
        grid.add(newThirdBracketRateL, 0, 2);
        grid.add(newThirdBracketRateTf, 1, 2);
        grid.add(newFourthBracketRateL, 0, 3);
        grid.add(newFourthBracketRateTf, 1, 3);
        grid.add(exit, 0, 4);
        grid.add(enter, 1, 4);
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();
                
                double first = Double.parseDouble(newFirstBracketRateTf.getText());
                double second = Double.parseDouble(newSecondBracketRateTf.getText());
                double third = Double.parseDouble(newThirdBracketRateTf.getText());
                double fourth = Double.parseDouble(newFourthBracketRateTf.getText());
                double[] newRates = {first, second, third, fourth};
                Property.setValueBrackets(newRates);
                
                grid.add(thanksBracketRatesL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);
                
                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });
        
        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which allows the user to change the rates for location category
     */
    public void changeLocationRates(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(newFirstLocationRateL, 0, 0);
        grid.add(newFirstLocationRateTf, 1, 0);
        grid.add(newSecondLocationRateL, 0, 1);
        grid.add(newSecondLocationRateTf, 1, 1);
        grid.add(newThirdLocationRateL, 0, 2);
        grid.add(newThirdLocationRateTf, 1, 2);
        grid.add(newFourthLocationRateL, 0, 3);
        grid.add(newFourthLocationRateTf, 1, 3);
        grid.add(newFifthLocationRateL, 0, 4);
        grid.add(newFifthLocationRateTf, 1, 4);
        grid.add(exit, 0, 5);
        grid.add(enter, 1, 5);
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();
                
                double first = Double.parseDouble(newFirstLocationRateTf.getText());
                double second = Double.parseDouble(newSecondLocationRateTf.getText());
                double third = Double.parseDouble(newThirdLocationRateTf.getText());
                double fourth = Double.parseDouble(newFourthLocationRateTf.getText());
                double fifth = Double.parseDouble(newFifthLocationRateTf.getText());
                double[] newRates = {first, second, third, fourth, fifth};
                Property.setLocationCatRates(newRates);
                
                grid.add(thanksLocationRatesL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);
                
                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });
        
        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which allows the user to the charge for the property not being the 
     * principal private residence of the owner
     */
    public void changePpr(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(newPprL, 0, 0);
        grid.add(newPprTf, 1, 0);
        grid.add(exit, 0, 1);
        grid.add(enter, 1, 1);
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();

                Property.setPrincipalPrivateRate(Double.parseDouble(newPprTf.getText()));

                grid.add(thanksPprL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);

                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });
        
        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which allows the user to change the penalty for previous unpaid years
     */
    public void changePenalty(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(newPenaltyL, 0, 0);
        grid.add(newPenaltyTf, 1, 0);
        grid.add(exit, 0, 1);
        grid.add(enter, 1, 1);
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newStage.close();
                grid.getChildren().clear();

                Property.setUnpaidPenalty(Double.parseDouble(newPenaltyTf.getText()));

                grid.add(thanksPenaltyL, 0, 0);
                grid.add(backToMenuBt, 0, 1);
                grid.add(exit, 0, 2);

                backToMenuBt.setOnAction(e -> deptOptions());

                newStage.setTitle("Investigate Changes");
                newStage.setScene(scene);
                newStage.show();
            }
        });
        
        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
    
    /**
     * A method which displays a series of statistics on payment records
     */
    public void getStatistics(){
        newStage.close();
        grid.getChildren().clear();
        
        statsL.setText(dmm.statistics(allProps));
        
        grid.add(statsL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);
        
        backToMenuBt.setOnAction(e -> deptOptions());
        
        newStage.setTitle("Investigate Changes");
        newStage.setScene(scene);
        newStage.show();
    }
}
