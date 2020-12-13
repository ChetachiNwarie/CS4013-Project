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
import javafx.scene.control.RadioButton;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * OwnerGui builds a GUI to allow the user to manage their properties and their property tax payments
 * @author Aoife Gleeson (19242395)
 */
public class OwnerGui extends Application {

    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, 500, 500);
    private Stage newStage = new Stage();

    private DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    private PropertyManagement pm = new PropertyManagement();

    private String name;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean principalPrivateResidence;
    private PropertyOwner owner;
    private Property a;

    private Button exit = new Button("Exit");

    private TextField nameTf = new TextField();
    private Button enter = new Button("Enter");

    private TextField addressTf = new TextField();
    private TextField eircodeTf = new TextField();
    private TextField marketValueTf = new TextField();
    private RadioButton cityRb = new RadioButton("City");
    private RadioButton largeTownRb = new RadioButton("Large Town");
    private RadioButton smallTownRb = new RadioButton("Small Town");
    private RadioButton villageRb = new RadioButton("Village");
    private RadioButton countrysideRb = new RadioButton("Countyside");
    private RadioButton yesPprRb = new RadioButton("Yes");
    private RadioButton noPprRb = new RadioButton("No");

    private Button backToMenuBt = new Button("Back to menu");

    private TextField taxAddressTf = new TextField();

    private Label showTaxDueL = new Label();
    private Label thanksTaxL = new Label();

    private TextField yearTf = new TextField();

    private TextArea viewPaymentsTa = new TextArea();

    private TextArea viewPropertiesTa = new TextArea();

    /**
     * A method which creates the first window that gives an option to the user
     * to select owner or Department of Environment
     *
     * @param primaryStage the first stage
     */
    @Override
    public void start(Stage primaryStage) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label ownerOrDeptL = new Label("Are you a property owner or Department of Environment?");
        RadioButton ownerRb = new RadioButton("Owner");
        RadioButton deptRb = new RadioButton("Department");

        grid.add(ownerOrDeptL, 0, 0);
        grid.add(ownerRb, 0, 1);
        grid.add(deptRb, 0, 2);
        grid.add(exit, 1, 3);

        ownerRb.setOnAction(e -> name());
        deptRb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DepartmentGui dept = new DepartmentGui();
                Stage newStage = new Stage();
                dept.start(newStage);
                newStage.show();
            }
        });

        ExitHandlerClass handler1 = new ExitHandlerClass();
        exit.setOnAction(handler1);

        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * A method which allows the user to enter the data needed to make a
     * PropertyOwner object
     */
    public void name() {
        grid.getChildren().clear();
        
        Label enterNameL = new Label("Enter name:");

        grid.add(enterNameL, 0, 0);
        grid.add(nameTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);

        enter.setOnAction(e -> ownerOptions());

        newStage.setTitle("Name");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that if the owner is already registered it assigns it to an
     * instance variable, else it registers a new owner
     */
    public void ownerOptions() {
        newStage.close();
        grid.getChildren().clear();

        name = nameTf.getText();

        for (int i = 0; i < pm.getRegisteredOwners().size(); i++) {
            if (name.equals(pm.getRegisteredOwners().get(i).getName())) {
                owner = pm.getRegisteredOwners().get(i);
            } else {
                owner = new PropertyOwner(name);
            }
        }
        pm.registerOwner(owner);
        
        Label optionsL = new Label("Do you want to:");
        RadioButton registerRb = new RadioButton("Register a property");
        RadioButton payTaxDueRb = new RadioButton("Pay tax due");
        RadioButton viewPropRb = new RadioButton("View your properties");
        RadioButton statementRb = new RadioButton("Get statement for a particular year");

        grid.add(optionsL, 0, 0);
        grid.add(registerRb, 0, 1);
        grid.add(payTaxDueRb, 0, 2);
        grid.add(viewPropRb, 0, 3);
        grid.add(statementRb, 0, 4);
        grid.add(exit, 1, 5);

        registerRb.setOnAction(e -> registerProp());
        payTaxDueRb.setOnAction(e -> payTax());
        viewPropRb.setOnAction(e -> viewProperties());
        statementRb.setOnAction(e -> setViewPayments());

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter details needed to register a new
     * property
     */
    public void registerProp() {
        newStage.close();
        grid.getChildren().clear();
        
        Label enterDetailsL = new Label("Enter your details");
        Label enterAddressL = new Label("Address");
        Label enterEircodeL = new Label("Eircode");
        Label enterValueL = new Label("Market value");
        Label enterLocationL = new Label("Location category");
        Label enterPprL = new Label("Principal private residence");

        grid.add(enterDetailsL, 0, 0);
        grid.add(enterAddressL, 0, 1);
        grid.add(enterEircodeL, 0, 2);
        grid.add(enterValueL, 0, 3);
        grid.add(enterLocationL, 0, 4);
        grid.add(enterPprL, 0, 9);
        grid.add(addressTf, 1, 1);
        grid.add(eircodeTf, 1, 2);
        grid.add(marketValueTf, 1, 3);
        grid.add(cityRb, 1, 4);
        grid.add(largeTownRb, 1, 5);
        grid.add(smallTownRb, 1, 6);
        grid.add(villageRb, 1, 7);
        grid.add(countrysideRb, 1, 8);
        grid.add(yesPprRb, 1, 9);
        grid.add(noPprRb, 1, 10);
        grid.add(enter, 1, 11);
        grid.add(exit, 0, 11);

        enter.setOnAction(e -> confirmRegisterProp());

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that registers a new property for the owner unless it is already
     * registered
     */
    public void confirmRegisterProp() {
        newStage.close();
        grid.getChildren().clear();
        
        Label thanksRegisterL = new Label("Thank you for registering your property");

        address = addressTf.getText().toUpperCase();
        eircode = eircodeTf.getText();
        marketValue = Double.parseDouble(marketValueTf.getText());
        if (cityRb.isSelected()) {
            location = "CITY";
        }
        if (largeTownRb.isSelected()) {
            location = "LARGE TOWN";
        }
        if (smallTownRb.isSelected()) {
            location = "SMALL TOWN";
        }
        if (villageRb.isSelected()) {
            location = "VILLAGE";
        }
        if (countrysideRb.isSelected()) {
            location = "COUNTRYSIDE";
        }
        if (yesPprRb.isSelected()) {
            principalPrivateResidence = true;
        } else if (noPprRb.isSelected()) {
            principalPrivateResidence = false;
        }

        owner.registerProperty(address, eircode, marketValue, location, principalPrivateResidence); //not adding to array list
        pm.registerProperty(new Property(name, address, eircode, marketValue, location, principalPrivateResidence));

        grid.add(thanksRegisterL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> ownerOptions());

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter their property address to compare
     * to one registered to them in order to pay tax due
     */
    public void payTax() {
        newStage.close();
        grid.getChildren().clear();
        
        Label enterTaxAddressL = new Label("Enter address");
        Button taxDueBt = new Button("Tax due");

        grid.add(enterTaxAddressL, 0, 0);
        grid.add(taxAddressTf, 1, 0);
        grid.add(taxDueBt, 1, 1);
        grid.add(exit, 0, 1);

        taxDueBt.setOnAction(e -> calculateTax());

        newStage.setTitle("Pay Tax");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that Uses the taxDue() method to find the tax due this year for
     * the property the owner entered
     */
    public void calculateTax() {
        newStage.close();
        grid.getChildren().clear();
        
        Label taxDueL = new Label("Property tax due");
        Button payTaxBt = new Button("Pay tax");
        
        double tax = 0.0;
        for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
            if (pm.getRegisteredProperties().get(i).getAddress().equals(taxAddressTf.getText())) {
                a = pm.getRegisteredProperties().get(i);
                tax = pm.getRegisteredProperties().get(i).taxDue();
            }
        }
        showTaxDueL.setText(Double.toString(tax));
        
        grid.add(taxDueL, 0, 0);
        grid.add(showTaxDueL, 1, 0);
        grid.add(exit, 0, 1);
        grid.add(payTaxBt, 1, 1);
        
        payTaxBt.setOnAction(e -> payTaxDue());
        
        newStage.setTitle("Pay Tax");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which uses the payTax() method to pay the tax for the property specified
     */
    public void payTaxDue() {
        newStage.close();
        grid.getChildren().clear();

        a.payTax();
        owner.payTax(a);
        a.getRecord(LocalDateTime.now().getYear()).setWasPaid(true);
        thanksTaxL.setText("Thank you for paying");
        
        grid.add(thanksTaxL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);
        
        backToMenuBt.setOnAction(e -> ownerOptions());
        
        newStage.setTitle("Pay Tax");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that displays a list of the owners registered properties in a
     * text area
     */
    public void viewProperties() {
        newStage.close();
        grid.getChildren().clear();

        String s = "";
        for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
            if (pm.getRegisteredProperties().get(i).getOwner().equals(name)) {
                s = s + pm.getRegisteredProperties().get(i).getAddress() + " " +
                        pm.getRegisteredProperties().get(i).getEircode() + "\n";
            }
        }
        viewPropertiesTa.setText("Address Eircode\n" + s);
        viewPropertiesTa.setEditable(false);

        grid.add(viewPropertiesTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> ownerOptions());

        newStage.setTitle("View Properties");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method which allows the user to enter the year for which they want to
     * view payment records
     */
    public void setViewPayments() {
        newStage.close();
        grid.getChildren().clear();
        
        Label yearL = new Label("Enter year");

        grid.add(yearL, 0, 0);
        grid.add(yearTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);

        enter.setOnAction(e -> getViewPayments());

        newStage.setTitle("Payment Records");
        newStage.setScene(scene);
        newStage.show();
    }

    /**
     * A method that displays payment records for the year specified in
     * setViewPayments()
     */
    public void getViewPayments() {
        newStage.close();
        grid.getChildren().clear();

        String s = "";
        for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
            if (pm.getRegisteredProperties().get(i).getOwner().equals(name)) {
                s = s + pm.getRegisteredProperties().get(i).getAddress() + "\n"
                        + pm.getRegisteredProperties().get(i).getRecord(Integer.parseInt(yearTf.getText())) + "\n";
            }
        }

        viewPaymentsTa.setEditable(false);
        viewPaymentsTa.setText(s);

        grid.add(viewPaymentsTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> ownerOptions());

        newStage.setTitle("Payment Records");
        newStage.setScene(scene);
        newStage.show();
    }
    
}


