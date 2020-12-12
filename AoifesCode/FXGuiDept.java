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
        
        enter.setOnAction(e -> getOwnerData());
        
        newStage.setTitle("Owner Data");
        newStage.setScene(scene);
        newStage.show();
    }
    
    // works
    public void getOwnerData(){
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
        
        grid.add(useEircodeL, 0, 0);
        grid.add(yesKeyRb, 0, 1);
        grid.add(noKeyRb, 0, 2);

        yesKeyRb.setOnAction(e -> setDetailsOverdueDataWithKey());
        noKeyRb.setOnAction(e -> setDetailsOverdueDataWithoutKey());

        newStage.setTitle("Overdue Data");
        newStage.setScene(scene);
        newStage.show();        
    }
    
    // works
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
    
    // doesnt work still need to fix
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
    
    // not working yet
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
    
    // works but could neaten layout
    public void finishAreaStats(){
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
    
    public void investigateChanges(){
        //need to finish
    }
}
