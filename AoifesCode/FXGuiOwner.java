
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
    
    private String name;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean principalPrivateResidence;
    private PropertyOwner owner;
    private Property a;
       
    private Label ownerOrDeptL = new Label("Are you a property owner or Department of Environment?");
    private Button ownerBt = new Button("Owner");
    private Button deptBt = new Button("Department");
    private Button exit = new Button("Exit");
    
    private Label enterNameL = new Label("Enter name:");
    private TextField nameTf = new TextField();
    private Button enter = new Button("Enter");
    
    private Label optionsL = new Label("Do you want to:");
    private Button registerBt = new Button("Register a property");
    private Button payTaxDueBt = new Button("Pay tax due");
    private Button viewPropBt = new Button("View your properties");
    private Button statementBt = new Button("Get statement for a particular year");

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
        
    @Override
    public void start(Stage primaryStage) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(ownerOrDeptL, 0, 0);
        grid.add(ownerBt, 0, 1);
        grid.add(deptBt, 0, 2);
        grid.add(exit, 1, 3);

        ownerBt.setOnAction(e -> name());
        deptBt.setOnAction(new EventHandler<ActionEvent>(){
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

    //start of windows for owner
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

    public void ownerOptions() {
        newStage.close();
        grid.getChildren().clear();

        name = nameTf.getText();
        owner = new PropertyOwner(name); // need to check if owner exists already

        grid.add(optionsL, 0, 0);
        grid.add(registerBt, 0, 1);
        grid.add(payTaxDueBt, 0, 2);
        grid.add(viewPropBt, 0, 3);
        grid.add(statementBt, 0, 4);
        grid.add(exit, 0, 5);

        registerBt.setOnAction(e -> registerProp());
        payTaxBt.setOnAction(e -> payTax());
        viewPropBt.setOnAction(e -> viewProperties());
        statementBt.setOnAction(e -> viewPayments());

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }

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

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }
    
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
        System.out.println(owner.toString());
        
        grid.add(thanksRegisterL, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        grid.add(exit, 0, 2);

        backToMenuBt.setOnAction(e -> ownerOptions());

        newStage.setTitle("Property details");
        newStage.setScene(scene);
        newStage.show();
    }

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
                for (int i = 0; i < owner.getProperties().size(); i++) {
                    if (owner.getProperties().get(i).getAddress().equals(taxAddressTf)) {
                        owner.payTax(owner.getProperties().get(i));
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
        for (int i = 0; i < owner.getProperties().size(); i++) {
            if (owner.getProperties().get(i).getAddress().equals(taxAddressTf)) { //error reading file
                tax = owner.getProperties().get(i).taxDue();
            }
        }
        showTaxDueL.setText(Double.toString(tax));
    }
    
    public void viewProperties() {
        newStage.close();
        grid.getChildren().clear();

        owner.viewProperties();

        newStage.setTitle("Pay Tax");
        newStage.setScene(scene);
        newStage.show();

    }

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
    
    public void finishViewPayments(){
        newStage.close();
        grid.getChildren().clear();
        
        String s = null;
        for(int i=0; i<owner.getProperties().size(); i++){
            s = owner.getProperties().get(i).getAddress() + "\n" + 
                    owner.getProperties().get(i).getRecord(Integer.parseInt(yearTf.getText()));
        }
        
        viewPaymentsTa.setEditable(false);
        viewPaymentsTa.setText(s);
        
        grid.add(viewPaymentsTa, 0, 0);
        grid.add(exit, 0, 1);
        
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
