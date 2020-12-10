
package projectc;

import java.util.ArrayList;
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
public class FXGuiOwner extends Application {
    public Stage secondWindow;
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
    //grid17
    private Label l21 = new Label("Enter year:");
    private TextField t11 = new TextField();
    private Label l22 = new Label("Enter eircode routing key:");
    private TextField t12 = new TextField();
    
    
    private Label l23 = new Label();    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(l1, 0, 0);
        grid.add(b1, 0, 1);
        grid.add(b2, 0, 2);
        grid.add(exit, 1, 3);

        b1.setOnAction(e -> name());
        b2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                FXGuiDept dept = new FXGuiDept();
                Stage newStage = new Stage();
                dept.start(newStage);
                newStage.show();
            }
        });

        ExitHandlerClass handler1 = new ExitHandlerClass();
        exit.setOnAction(handler1);
        
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    //start of windows for owner
    public void name() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);       

        grid.add(l2, 0, 0);
        grid.add(t1, 1, 0);
        grid.add(enter, 0, 1);
        grid.add(exit, 1, 1);

        enter.setOnAction(e -> ownerOptions());

        Scene secondScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Name");
        secondWindow.setScene(secondScene);
        secondWindow.show();
    }

    public void ownerOptions() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        name = t1.getText();
        owner = new PropertyOwner(name); // need to check if owner exists already

        grid.add(l3, 0, 0);
        grid.add(b3, 0, 1);
        grid.add(b4, 0, 2);
        grid.add(b5, 0, 3);
        grid.add(b6, 0, 4);
        grid.add(exit, 0, 5);

        b3.setOnAction(e -> registerProp());
        b4.setOnAction(e -> payTax());
        b5.setOnAction(e -> viewProperties());
        b6.setOnAction(e -> viewPayments());

        Scene thirdScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Property details");
        secondWindow.setScene(thirdScene);
        secondWindow.show();
    }

    public void registerProp() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(l4, 0, 0);
        grid.add(l5, 0, 1);
        grid.add(l6, 0, 2);
        grid.add(l7, 0, 3);
        grid.add(l8, 0, 4);
        grid.add(l9, 0, 5);
        grid.add(t2, 1, 1);
        grid.add(t3, 1, 2);
        grid.add(t4, 1, 3);
        grid.add(t5, 1, 4);
        grid.add(t6, 1, 5);
        grid.add(enter, 0, 6);
        grid.add(exit, 1, 6);

        enter.setOnAction(e -> finishRegisterProp());

        Scene fourthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Property details");
        secondWindow.setScene(fourthScene);
        secondWindow.show();
    }

    public void payTax() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(l11, 0, 0);
        grid.add(t7, 0, 1);
        grid.add(enter, 0, 2);
        grid.add(exit, 0, 3);

        enter.setOnAction(e -> continuePayTax());

        Scene sixthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Pay Tax");
        secondWindow.setScene(sixthScene);
        secondWindow.show();
    }

    public void viewProperties() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        owner.viewProperties();

        Scene ninthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Pay Tax");
        secondWindow.setScene(ninthScene);
        secondWindow.show();

    }

    public void viewPayments() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l15, 0, 0);
        grid.add(t8, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishViewPayments());

        Scene tenthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Payment Records");
        secondWindow.setScene(tenthScene);
        secondWindow.show();
    }
    
    public void finishViewPayments(){
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        String s = null;
        for(int i=0; i<owner.getProperties().size(); i++){
            s = owner.getProperties().get(i).getAddress() + "\n" + 
                    owner.getProperties().get(i).getRecord(Integer.parseInt(t8.getText()));
        }
        
        ta1.setEditable(false);
        ta1.setText(s);
        
        grid.add(ta1, 0, 0);
        
        Scene eleventhScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Payment Records");
        secondWindow.setScene(eleventhScene);
        secondWindow.show();
    }

    public void finishRegisterProp() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

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
        
        owner.registerProperty(address, eircode, marketValue, location, principalPrivateResidence); //not adding to array list
        
        //l23.setText(owner.toString());
        
        System.out.println(owner.toString());
        
        grid.add(l10, 0, 0);
        grid.add(b7, 0, 1);
        grid.add(exit, 0, 2);

        b7.setOnAction(e -> ownerOptions());

        Scene fifthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Property details");
        secondWindow.setScene(fifthScene);
        secondWindow.show();
    }
    

    public void continuePayTax() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //need to check if address matches property in owners property array
        double tax = owner.getProperty(t7.getText()).taxDue(); //not working null pointer exception
        
        l13.setText(Double.toString(tax));

        grid.add(l12, 0, 0);
        grid.add(l13, 1, 0);
        grid.add(b8, 2, 0);
        grid.add(exit, 2, 1);

        b8.setOnAction(e -> finishPayTax());

        Scene seventhScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Pay Tax");
        secondWindow.setScene(seventhScene);
        secondWindow.show();
    }

    public void finishPayTax() {
        secondWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(l14, 0, 0);
        grid.add(b7, 0, 1);
        grid.add(exit, 0, 2);

        owner.payTax(owner.getProperty(t7.getText()));

        b7.setOnAction(e -> ownerOptions());

        Scene eighthScene = new Scene(grid, 500, 500);
        secondWindow = new Stage();
        secondWindow.setTitle("Pay Tax");
        secondWindow.setScene(eighthScene);
        secondWindow.show();
    }
    
}
class ExitHandlerClass implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        System.exit(0);
    }
}
