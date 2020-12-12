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

/**
 *
 * @author Aoife Gleeson 19242395
 */
public class FXGuiDept extends Application{
    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, 500, 500);
    private Stage newStage = new Stage();
    
    private DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    private PropertyManagement pm = new PropertyManagement();
    
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
    
    private Label enterYearL = new Label("Enter year:");
    private TextField yearTf = new TextField();
    private Label enterEircodeRoutingKeyL = new Label("Enter eircode routing key:");
    private TextField eircodeRoutingKeyTf = new TextField();
    
    private Label enterRoutingKeyL = new Label("Enter routing key");
    private TextField routingKeyTf = new TextField();
    
    private TextArea areaStatsTa = new TextArea();

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
    
    // works
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
    
    // works
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
    
    // works but could neaten layout
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
    
    // works
    public void ownerData(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(enterOwnerL, 0, 0);
        grid.add(ownerTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishOwnerData());
        
        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // works
    public void finishOwnerData(){
        newStage.close();
        grid.getChildren().clear();
        pm.getPropertyByOwner(ownerTf.getText()); //only used to get around array error. reads owner csv file and returns array of properties
        
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
    
    // works
    public void overdueData(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(enterYearL, 0, 0);
        grid.add(yearTf, 1, 0);
        grid.add(enterEircodeRoutingKeyL, 0, 1);
        grid.add(eircodeRoutingKeyTf, 1, 1);
        grid.add(enter, 1, 2);
        grid.add(exit, 0, 2);
        
        enter.setOnAction(e -> finishOverdueData());
        
        newStage.setTitle("Overdue Data");
        newStage.setScene(scene);
        newStage.show();        
    }
    
    // need to fix still
    public void finishOverdueData(){
        newStage.close();
        grid.getChildren().clear();
        
        dmm.getOverdueTax(Integer.parseInt(yearTf.getText()), eircodeRoutingKeyTf.getText());
        
        newStage.setTitle("Overdue Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // works
    public void areaStats(){
        newStage.close();
        grid.getChildren().clear();
        
        grid.add(enterRoutingKeyL, 0, 0);
        grid.add(routingKeyTf, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishAreaStats());
        
        newStage.setTitle("Area Statistics");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // need to fix still
    public void finishAreaStats(){
        newStage.close();
        grid.getChildren().clear();
        
        ArrayList<Property> props = new ArrayList<Property>();
        props = dmm.sortByEircode(routingKeyTf.getText().toUpperCase());       
        areaStatsTa.setText(props.toString());
        
        grid.add(areaStatsTa, 0, 0);
        grid.add(backToMenuBt, 0, 1);
        
        backToMenuBt.setOnAction(e -> deptOptions());
        
        newStage.setTitle("Area Statistics");
        newStage.setScene(scene);
        newStage.show();
    }
    
    public void investigateChanges(){
        //need to finish
    }
}
