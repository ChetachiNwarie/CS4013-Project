
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

/**
 *
 * @author Aoife Gleeson 19242395
 */
public class FXGuiOwner extends Application {
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
   
    private Label ownerOrDeptL = new Label("Are you a property owner or Department of Environment?");
    private RadioButton ownerRb = new RadioButton("Owner");
    private RadioButton deptRb = new RadioButton("Department");
    private Button exit = new Button("Exit");
    
    private Label enterNameL = new Label("Enter name:");
    private TextField nameTf = new TextField();
    private Button enter = new Button("Enter");
    
    private Label optionsL = new Label("Do you want to:");
    private RadioButton registerRb = new RadioButton("Register a property");
    private RadioButton payTaxDueRb = new RadioButton("Pay tax due");
    private RadioButton viewPropRb = new RadioButton("View your properties");
    private RadioButton statementRb = new RadioButton("Get statement for a particular year");

    private Label enterDetailsL = new Label("Enter your details");
    private Label enterAddressL = new Label("Address");
    private Label enterEircodeL = new Label("Eircode");
    private Label enterValueL = new Label("Market value");
    private Label enterLocationL = new Label("Location category");
    private Label enterPprL = new Label("Principal private residence");
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

    private Label thanksRegisterL = new Label("Thank you for registering your property");
    private Button backToMenuBt = new Button("Back to menu");

    private Label enterTaxAddressL = new Label("Enter address");
    private TextField taxAddressTf = new TextField();

    private Label taxDueL = new Label("Property tax due");
    private Label showTaxDueL = new Label();
    private Button payTaxBt = new Button("Pay tax");
    private Label thanksTaxL = new Label();
    private Button taxDueBt = new Button("Tax due");

    private Label yearL = new Label("Enter year");
    private TextField yearTf = new TextField();

    private TextArea viewPaymentsTa = new TextArea();
    
    private TextArea viewPropertiesTa = new TextArea();
        
    @Override
    public void start(Stage primaryStage) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(ownerOrDeptL, 0, 0);
        grid.add(ownerRb, 0, 1);
        grid.add(deptRb, 0, 2);
        grid.add(exit, 1, 3);

        ownerRb.setOnAction(e -> name());
        deptRb.setOnAction(new EventHandler<ActionEvent>(){
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
        
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    //works
    public void name() {
        grid.getChildren().clear();
        
        grid.add(enterNameL, 0, 0);
        grid.add(nameTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);

        enter.setOnAction(e -> ownerOptions());

        newStage.setTitle("Name");
        newStage.setScene(scene);
        newStage.show();
    }

    // works
    public void ownerOptions() {
        newStage.close();
        grid.getChildren().clear();

        name = nameTf.getText();
        
        for( int i=0; i < pm.getRegisteredOwners().size(); i++ )
        {
            if( name.equals(pm.getRegisteredOwners().get(i).getName()) )
                owner = pm.getRegisteredOwners().get(i);
            else{
                owner = new PropertyOwner(name);
            }
        }
        pm.registerOwner(owner);

        grid.add(optionsL, 0, 0);
        grid.add(registerRb, 0, 1);
        grid.add(payTaxDueRb, 0, 2);
        grid.add(viewPropRb, 0, 3);
        grid.add(statementRb, 0, 4);
        grid.add(exit, 0, 5);

        registerRb.setOnAction(e -> registerProp());
        payTaxRb.setOnAction(e -> payTax());
        viewPropRb.setOnAction(e -> viewProperties());
        statementRb.setOnAction(e -> viewPayments());

        newStage.setTitle("Owner Details");
        newStage.setScene(scene);
        newStage.show();
    }

    // works
    public void registerProp() {
        newStage.close();
        grid.getChildren().clear();

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
        grid.add(largeTownRb, 1 ,5);
        grid.add(smallTownRb, 1, 6);
        grid.add(villageRb, 1, 7);
        grid.add(countrysideRb, 1, 8);
        grid.add(yesPprRb, 1, 9);
        grid.add(noPprRb, 1, 10);
        grid.add(enter, 1, 11);
        grid.add(exit, 0, 11);

        enter.setOnAction(e -> confirmRegisterProp());

        newStage.setTitle("Register Property");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // works for csv file not array
    public void confirmRegisterProp() {
        newStage.close();
        grid.getChildren().clear();

        address = addressTf.getText().toUpperCase();
        eircode = eircodeTf.getText();
        marketValue = Double.parseDouble(marketValueTf.getText());
        if(cityRb.isSelected()){
            location = "city";
        }
        if(largeTownRb.isSelected()){
            location = "large town";
        }
        if(smallTownRb.isSelected()){
            location = "small town";
        }
        if(villageRb.isSelected()){
            location = "village";
        }
        if(countrysideRb.isSelected()){
            location = "countryside";
        }
        if(yesPprRb.isSelected()){
            principalPrivateResidence = true;
        }
        else if(noPprRb.isSelected()){
            principalPrivateResidence = false;
        }
        
        owner.registerProperty(address, eircode, marketValue, location, principalPrivateResidence); //not adding to array list
        pm.registerProperty(new Property(name, address, eircode, marketValue, location, principalPrivateResidence));
        
        grid.add(thanksRegisterL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> ownerOptions());

        newStage.setTitle("Register Property");
        newStage.setScene(scene);
        newStage.show();
    }

    // need to fix paid
    public void payTax() {
        newStage.close();
        grid.getChildren().clear();

        grid.add(enterTaxAddressL, 0, 0);
        grid.add(taxAddressTf, 1, 0);
        grid.add(taxDueL, 0, 1);
        grid.add(showTaxDueL, 1, 1);
        grid.add(payTaxBt, 1, 2);
        grid.add(taxDueBt, 0, 2);
        grid.add(thanksTaxL, 0, 3);
        grid.add(backToMenuBt, 0, 4);

        taxDueBt.setOnAction(e -> calculateTax());
        payTaxBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
                    if (pm.getRegisteredProperties().get(i).getAddress().equals(taxAddressTf)) {
                        //owner.payTax(pm.getRegisteredProperties().get(i)); //trying to get around error
                        pm.getRegisteredProperties().get(i).payTax(); //saying tax wasnt paid
                        pm.getRegisteredProperties().get(i).getRecord(LocalDateTime.now().getYear()).setWasPaid(true); //saying tax wasnt paid
                    }
                }
                thanksTaxL.setText("Thank you for paying");
                backToMenuBt.setOnAction(e -> ownerOptions());
            }
        });

        newStage.setTitle("Pay Tax");
        newStage.setScene(scene);
        newStage.show();
    }
    
    public void calculateTax() {
        double tax = 0.0;
        for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
            if (pm.getRegisteredProperties().get(i).getAddress().equals(taxAddressTf.getText())) {
                tax = pm.getRegisteredProperties().get(i).taxDue(); // only used to get around array error
            }
        }
        showTaxDueL.setText(Double.toString(tax));
    }
    
    // works but could neaten layout
    public void viewProperties() {
        newStage.close();
        grid.getChildren().clear();

        String s = "";
        for(int i=0; i<pm.getRegisteredProperties().size(); i++){
            if(pm.getRegisteredProperties().get(i).getOwner().equals(name)){
                s = s + pm.getRegisteredProperties().get(i).toString() + "\n";
            }
        }
        viewPropertiesTa.setText(s);
        viewPropertiesTa.setEditable(false);
        
        grid.add(viewPropertiesTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        newStage.setTitle("View Properties");
        newStage.setScene(scene);
        newStage.show();

    }

    // works
    public void viewPayments() {
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(yearL, 0, 0);
        grid.add(yearTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishViewPayments());

        newStage.setTitle("Payment Records");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // need to fix still
    public void finishViewPayments(){
        newStage.close();
        grid.getChildren().clear();
        
        String s = "";
        for (int i = 0; i < pm.getRegisteredProperties().size(); i++) {
            if(pm.getRegisteredProperties().get(i).getOwner().equals(name)){
                s = pm.getRegisteredProperties().get(i).getAddress() + "\n"     //wont show payments for multiple properties
                    + pm.getRegisteredProperties().get(i).getRecord(Integer.parseInt(yearTf.getText()));
            }
            
        }
        
        viewPaymentsTa.setEditable(false);
        viewPaymentsTa.setText(s);
        
        grid.add(viewPaymentsTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);
        
        newStage.setTitle("Payment Records");
        newStage.setScene(scene);
        newStage.show();
    }   
}

class ExitHandlerClass implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.exit(0);
    }
}
