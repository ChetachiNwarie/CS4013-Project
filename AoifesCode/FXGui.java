
package projectc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;

/**
 *
 * @author Aoife Gleeson 19242395
 */
public class FXGui extends Application {
    private DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    //property details
    private String name;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean principalPrivateResidence;
    private PropertyOwner owner;
    private Property a;
    //grid1 instance variables
    private Label l1 = new Label("Are you a property owner or Department of Environment?");
    private Button b1 = new Button("Owner");
    private Button b2 = new Button("Department");
    private Button exit = new Button("Exit");
    //grid2
    private Label l2 = new Label("Enter name:");
    private TextField t1 = new TextField();
    private Button enter = new Button("Enter");
    //grid3
    private Label l3 = new Label("Do you want to:");
    private Button b3 = new Button("Register a property");
    private Button b4 = new Button("Pay tax due");
    private Button b5 = new Button("View your properties");
    private Button b6 = new Button("Get statement for a particular year");
    //grid4
    private Label l4 = new Label("Enter your details");
    private Label l5 = new Label("Address");
    private Label l6 = new Label("Eircode");
    private Label l7 = new Label("Market value");
    private Label l8 = new Label("Location category");
    private Label l9 = new Label("Principal private residence");
    private TextField t2 = new TextField();
    private TextField t3 = new TextField();
    private TextField t4 = new TextField();
    private TextField t5 = new TextField();
    private TextField t6 = new TextField();
    //grid5
    private Label l10 = new Label("Thank you for registering your property");
    private Button b7 = new Button("Back to menu");
    //grid6
    private Label l11 = new Label("Enter address");
    private TextField t7 = new TextField();
    //grid7
    private Label l12 = new Label("Property tax due");
    private Label l13 = new Label();
    private Button b8 = new Button("Pay tax");
    //grid8
    private Label l14 = new Label("Thank you for paying your property tax");
    
    //grid10
    private Label l15 = new Label("Enter year");
    private TextField t8 = new TextField();
    //grid11
    private TextArea ta1 = new TextArea();
    //grid12
    private Label l16 = new Label("Do you want to:");
    private Button b9 = new Button("Get property tax payment data for a particular property");
    private Button b10 = new Button("Get property tax payment data for a particular property owner");
    private Button b11 = new Button("Get a list of all overdue property tax for a particular year");
    private Button b12 = new Button("Get property tax statistics for a particular area");
    private Button b13 = new Button("Investigate the impact of possible changes to the rates and levies");
    //grid13
    private Label l17 = new Label("Enter the address");
    private TextField t9 = new TextField();
    //grid14
    private Label l18 = new Label();
    //grid15
    private Label l19 = new Label("Enter owner:");
    private TextField t10 = new TextField();
    //grid16
    private Label l20 = new Label();
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);

        grid1.add(l1, 0, 0);
        grid1.add(b1, 0, 1);
        grid1.add(b2, 0, 2);
        grid1.add(exit, 1, 3);

        b1.setOnAction(e -> name());
        b2.setOnAction(e -> deptOptions());

        ExitHandlerClass handler1 = new ExitHandlerClass();
        exit.setOnAction(handler1);

        Scene scene = new Scene(grid1, 500, 500);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //start of windows for owner
    public void name() {
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);       
        grid2.getChildren();

        grid2.add(l2, 0, 0);
        grid2.add(t1, 1, 0);
        grid2.add(enter, 0, 1);
        grid2.add(exit, 1, 1);

        enter.setOnAction(e -> ownerOptions());

        Scene secondScene = new Scene(grid2, 500, 500);
        Stage secondWindow = new Stage();
        secondWindow.setTitle("Name");
        secondWindow.setScene(secondScene);
        secondWindow.show();
    }

    public void ownerOptions() {
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.getChildren();

        name = t1.getText();
        owner = new PropertyOwner(name); // need to check if owner exists already

        grid3.add(l3, 0, 0);
        grid3.add(b3, 0, 1);
        grid3.add(b4, 0, 2);
        grid3.add(b5, 0, 3);
        grid3.add(b6, 0, 4);
        grid3.add(exit, 0, 5);

        b3.setOnAction(e -> registerProp());
        b4.setOnAction(e -> payTax());
        b5.setOnAction(e -> viewProperties());
        b6.setOnAction(e -> viewPayments());

        Scene thirdScene = new Scene(grid3, 500, 500);
        Stage thirdWindow = new Stage();
        thirdWindow.setTitle("Property details");
        thirdWindow.setScene(thirdScene);
        thirdWindow.show();
    }

    public void registerProp() {
        GridPane grid4 = new GridPane();
        grid4.setAlignment(Pos.CENTER);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.getChildren();

        grid4.add(l4, 0, 0);
        grid4.add(l5, 0, 1);
        grid4.add(l6, 0, 2);
        grid4.add(l7, 0, 3);
        grid4.add(l8, 0, 4);
        grid4.add(l9, 0, 5);
        grid4.add(t2, 1, 1);
        grid4.add(t3, 1, 2);
        grid4.add(t4, 1, 3);
        grid4.add(t5, 1, 4);
        grid4.add(t6, 1, 5);
        grid4.add(enter, 0, 6);
        grid4.add(exit, 1, 6);

        enter.setOnAction(e -> finishRegisterProp());

        Scene fourthScene = new Scene(grid4, 500, 500);
        Stage fourthWindow = new Stage();
        fourthWindow.setTitle("Property details");
        fourthWindow.setScene(fourthScene);
        fourthWindow.show();
    }

    public void payTax() {
        GridPane grid6 = new GridPane();
        grid6.setAlignment(Pos.CENTER);
        grid6.setHgap(10);
        grid6.setVgap(10);
        grid6.getChildren();

        grid6.add(l11, 0, 0);
        grid6.add(t7, 0, 1);
        grid6.add(enter, 0, 2);
        grid6.add(exit, 0, 3);

        enter.setOnAction(e -> continuePayTax());

        Scene sixthScene = new Scene(grid6, 500, 500);
        Stage sixthWindow = new Stage();
        sixthWindow.setTitle("Pay Tax");
        sixthWindow.setScene(sixthScene);
        sixthWindow.show();
    }

    public void viewProperties() {
        GridPane grid9 = new GridPane();
        grid9.setAlignment(Pos.CENTER);
        grid9.setHgap(10);
        grid9.setVgap(10);
        grid9.getChildren();

        owner.viewProperties();
        //System.out.println(owner.toString());

        Scene ninthScene = new Scene(grid9, 500, 500);
        Stage ninthWindow = new Stage();
        ninthWindow.setTitle("Pay Tax");
        ninthWindow.setScene(ninthScene);
        ninthWindow.show();

    }

    public void viewPayments() {
        GridPane grid10 = new GridPane();
        grid10.setAlignment(Pos.CENTER);
        grid10.setHgap(10);
        grid10.setVgap(10);
        grid10.getChildren();
        
        grid10.add(l15, 0, 0);
        grid10.add(t8, 1, 0);
        grid10.add(enter, 1, 1);
        grid10.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishViewPayments());

        Scene tenthScene = new Scene(grid10, 500, 500);
        Stage tenthWindow = new Stage();
        tenthWindow.setTitle("Payment Records");
        tenthWindow.setScene(tenthScene);
        tenthWindow.show();
    }
    
    public void finishViewPayments(){
        GridPane grid11 = new GridPane();
        grid11.setAlignment(Pos.CENTER);
        grid11.setHgap(10);
        grid11.setVgap(10);
        grid11.getChildren();
        
        String s = null;
        for(int i=0; i<owner.getProperties().size(); i++){
            s = owner.getProperties().get(i).getAddress() + "\n" + 
                    owner.getProperties().get(i).getRecord(Integer.parseInt(t8.getText()));
        }
        
        ta1.setEditable(false);
        ta1.setText(s);
        
        grid11.add(ta1, 0, 0);
        
        Scene eleventhScene = new Scene(grid11, 500, 500);
        Stage eleventhWindow = new Stage();
        eleventhWindow.setTitle("Payment Records");
        eleventhWindow.setScene(eleventhScene);
        eleventhWindow.show();
    }

    public void finishRegisterProp() {
        GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.CENTER);
        grid5.setHgap(10);
        grid5.setVgap(10);
        grid5.getChildren();

        address = t2.getText();
        eircode = t3.getText();
        marketValue = Double.parseDouble(t4.getText());
        location = t5.getText();
        String ppr = t6.getText().toUpperCase();
        if (ppr.equals("YES")) {
            principalPrivateResidence = true;
        } else {
            principalPrivateResidence = false;
        }
        System.out.println(address);
        
        //a = new Property(name, address, eircode, marketValue, location, principalPrivateResidence);
        owner.registerProperty(address, eircode, marketValue, location, principalPrivateResidence); //not adding to array list
        //owner.addProperty(a);
        
        System.out.println(owner.toString());
        
        grid5.add(l10, 0, 0);
        grid5.add(b7, 0, 1);
        grid5.add(exit, 0, 2);

        b7.setOnAction(e -> ownerOptions());

        Scene fifthScene = new Scene(grid5, 500, 500);
        Stage fifthWindow = new Stage();
        fifthWindow.setTitle("Property details");
        fifthWindow.setScene(fifthScene);
        fifthWindow.show();
    }
    

    public void continuePayTax() {
        GridPane grid7 = new GridPane();
        grid7.setAlignment(Pos.CENTER);
        grid7.setHgap(10);
        grid7.setVgap(10);
        grid7.getChildren();

        //need to check if address matches property in owners property array
        double tax = owner.getProperty(t7.getText()).taxDue(); //not working
  
        //double tax = a.taxDue();
        
        l13.setText(Double.toString(tax));

        grid7.add(l12, 0, 0);
        grid7.add(l13, 1, 0);
        grid7.add(b8, 2, 0);
        grid7.add(exit, 2, 1);

        b8.setOnAction(e -> finishPayTax());

        Scene seventhScene = new Scene(grid7, 500, 500);
        Stage seventhWindow = new Stage();
        seventhWindow.setTitle("Pay Tax");
        seventhWindow.setScene(seventhScene);
        seventhWindow.show();
    }

    public void finishPayTax() {
        GridPane grid8 = new GridPane();
        grid8.setAlignment(Pos.CENTER);
        grid8.setHgap(10);
        grid8.setVgap(10);
        grid8.getChildren();

        grid8.add(l14, 0, 0);
        grid8.add(b7, 0, 1);
        grid8.add(exit, 0, 2);

        owner.payTax(owner.getProperty(t7.getText()));

        //owner.payTax(a);
        
        b7.setOnAction(e -> ownerOptions());

        Scene eighthScene = new Scene(grid8, 500, 500);
        Stage eighthWindow = new Stage();
        eighthWindow.setTitle("Pay Tax");
        eighthWindow.setScene(eighthScene);
        eighthWindow.show();
    }
    //end of windows for owner
    
    //start of windows for dept
    public void deptOptions(){
        GridPane grid12 = new GridPane();
        grid12.setAlignment(Pos.CENTER);
        grid12.setHgap(10);
        grid12.setVgap(10);
        grid12.getChildren();
        
        grid12.add(l16, 0, 0);
        grid12.add(b9, 0, 1);
        grid12.add(b10, 0, 2);
        grid12.add(b11, 0, 3);
        grid12.add(b12, 0, 4);
        grid12.add(b13, 0, 5);
        grid12.add(exit, 0, 6);
        
        b9.setOnAction(e -> propData());
        b10.setOnAction(e -> ownerData());
        b11.setOnAction(e -> overdueData());
        b12.setOnAction(e -> areaStats());
        b13.setOnAction(e -> investigateChanges());
        
        Scene twelfthScene = new Scene(grid12, 500, 500);
        Stage twelfthWindow = new Stage();
        twelfthWindow.setTitle("Department Options");
        twelfthWindow.setScene(twelfthScene);
        twelfthWindow.show();
    }
    
    public void propData(){
        GridPane grid13 = new GridPane();
        grid13.setAlignment(Pos.CENTER);
        grid13.setHgap(10);
        grid13.setVgap(10);
        grid13.getChildren();
        
        grid13.add(l17, 0, 0);
        grid13.add(t9, 1, 0);
        grid13.add(enter, 1, 1);
        
        enter.setOnAction(e -> finishPropData());
        
        Scene thirteenthScene = new Scene(grid13, 500, 500);
        Stage thirteenthWindow = new Stage();
        thirteenthWindow.setTitle("Property Data");
        thirteenthWindow.setScene(thirteenthScene);
        thirteenthWindow.show();
    }
    
    public void finishPropData(){
        GridPane grid14 = new GridPane();
        grid14.setAlignment(Pos.CENTER);
        grid14.setHgap(10);
        grid14.setVgap(10);
        grid14.getChildren();
        
        Property b = null;
        for(int i=0; i<dmm.allProperties.size(); i++){
            if(dmm.allProperties.get(i).getAddress().equals(t9.getText())){
                b = dmm.allProperties.get(i);
            }
            else{
                System.err.println("property is not registered");
            }           
        }
               
        l18.setText(dmm.getPropertyData(b));
        
        grid14.add(l18, 0, 0);
        grid14.add(b7, 0, 1);
        grid14.add(exit, 0, 2);
        
        b7.setOnAction(e -> deptOptions());
        
        Scene thirteenthScene = new Scene(grid14, 500, 500);
        Stage thirteenthWindow = new Stage();
        thirteenthWindow.setTitle("Property Data");
        thirteenthWindow.setScene(thirteenthScene);
        thirteenthWindow.show();
    }
    
    public void ownerData(){
        GridPane grid15 = new GridPane();
        grid15.setAlignment(Pos.CENTER);
        grid15.setHgap(10);
        grid15.setVgap(10);
        grid15.getChildren();
        
        grid15.add(l19, 0, 0);
        grid15.add(t10, 1, 0);
        grid15.add(enter, 1, 1);
        grid15.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishOwnerData());
        
        Scene thirteenthScene = new Scene(grid15, 500, 500);
        Stage thirteenthWindow = new Stage();
        thirteenthWindow.setTitle("Owner Data");
        thirteenthWindow.setScene(thirteenthScene);
        thirteenthWindow.show();
    }
    
    public void finishOwnerData(){
        GridPane grid16 = new GridPane();
        grid16.setAlignment(Pos.CENTER);
        grid16.setHgap(10);
        grid16.setVgap(10);
        grid16.getChildren();
        
        PropertyOwner own = new PropertyOwner(t10.getText());
        l20.setText(dmm.getOwnerData(own));
        
        grid16.add(l20, 0, 0);
        grid16.add(b7, 0, 1);
        grid16.add(exit, 0, 2);
        
        b7.setOnAction(e -> deptOptions());
               
        Scene thirteenthScene = new Scene(grid16, 500, 500);
        Stage thirteenthWindow = new Stage();
        thirteenthWindow.setTitle("Owner Data");
        thirteenthWindow.setScene(thirteenthScene);
        thirteenthWindow.show();
    }
    
    public void overdueData(){
        GridPane grid16 = new GridPane();
        grid16.setAlignment(Pos.CENTER);
        grid16.setHgap(10);
        grid16.setVgap(10);
        grid16.getChildren();
        
        //need to finish
        
        Scene thirteenthScene = new Scene(grid16, 500, 500);
        Stage thirteenthWindow = new Stage();
        thirteenthWindow.setTitle("Owner Data");
        thirteenthWindow.setScene(thirteenthScene);
        thirteenthWindow.show();
        
    }
    
    public void areaStats(){
        //need to finish
    }
    
    public void investigateChanges(){
        //need to finish
    }

}

class ExitHandlerClass implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        System.exit(0);
    }
}
