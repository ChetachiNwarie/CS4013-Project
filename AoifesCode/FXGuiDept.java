/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author aoife
 */
public class FXGuiDept extends Application{
    DepartmentManagementMenu dmm = new DepartmentManagementMenu();
    private Stage newWindow;
    //first pane
    private Label l1 = new Label("Welcome to the Department of Environment Managament System");
    private Button b1 = new Button("Continue");
    //second pane
    private Label l2 = new Label("Do you want to:");
    private Button b2 = new Button("Get property tax payment data for a particular property");
    private Button b3 = new Button("Get property tax payment data for a particular property owner");
    private Button b4 = new Button("Get a list of all overdue property tax for a particular year");
    private Button b5 = new Button("Get property tax statistics for a particular area");
    private Button b6 = new Button("Investigate the impact of possible changes to the rates and levies");
    private Button exit = new Button("Exit");
    //third pane
    private Button enter = new Button("Enter");
    private Label l3 = new Label("Enter the address");
    private TextField t1 = new TextField();
    //fourth pane
    private Label l4 = new Label();
    private Button b7 = new Button("Back to menu");
    //fifth pane
    private Label l5 = new Label("Enter owner:");
    private TextField t2 = new TextField();
    //sixth pane
    private Label l6 = new Label();
    //seventh pane
    private Label l7 = new Label("Enter year:");
    private TextField t3 = new TextField();
    private Label l8 = new Label("Enter eircode routing key:");
    private TextField t4 = new TextField();
    
    private Label l9 = new Label("Enter routing key");
    private TextField t5 = new TextField();
    
    private TextArea ta1 = new TextArea();

    @Override
    public void start(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
               
        grid.add(l1, 0, 0);
        grid.add(b1, 0, 1);
        
        b1.setOnAction(e -> deptOptions());
        
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void deptOptions(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l2, 0, 0);
        grid.add(b2, 0, 1);
        grid.add(b3, 0, 2);
        grid.add(b4, 0, 3);
        grid.add(b5, 0, 4);
        grid.add(b6, 0, 5);
        grid.add(exit, 0, 6);
        
        ExitHandlerClass handle = new ExitHandlerClass();
        exit.setOnAction(handle);
        b2.setOnAction(e -> propData());
        b3.setOnAction(e -> ownerData());
        b4.setOnAction(e -> overdueData());
        b5.setOnAction(e -> areaStats());
        b6.setOnAction(e -> investigateChanges());
        
        Scene twelfthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Department Options");
        newWindow.setScene(twelfthScene);
        newWindow.show();
    }
    
    public void propData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l3, 0, 0);
        grid.add(t1, 1, 0);
        grid.add(enter, 1, 1);
        
        enter.setOnAction(e -> finishPropData());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Property Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void finishPropData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Property b = null;
        for(int i=0; i<dmm.allProperties.size(); i++){
            if(dmm.allProperties.get(i).getAddress().equals(t1.getText())){
                b = dmm.allProperties.get(i);
            }
            else{
                System.err.println("property is not registered");
            }           
        }
               
        l4.setText(dmm.getPropertyData(b));
        
        grid.add(l4, 0, 0);
        grid.add(b7, 0, 1);
        grid.add(exit, 0, 2);
        
        b7.setOnAction(e -> deptOptions());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Property Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void ownerData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l5, 0, 0);
        grid.add(t2, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishOwnerData());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Owner Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void finishOwnerData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        PropertyOwner own = new PropertyOwner(t2.getText());
        l6.setText(dmm.getOwnerData(own));
        
        grid.add(l6, 0, 0);
        grid.add(b7, 0, 1);
        grid.add(exit, 0, 2);
        
        b7.setOnAction(e -> deptOptions());
               
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Owner Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void overdueData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l7, 0, 0);
        grid.add(t3, 1, 0);
        grid.add(l8, 0, 1);
        grid.add(t4, 1, 1);
        grid.add(enter, 1, 2);
        grid.add(exit, 0, 2);
        
        enter.setOnAction(e -> finishOverdueData());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Owner Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();        
    }
    
    public void finishOverdueData(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        //need to finish
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Owner Data");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void areaStats(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(l9, 0, 0);
        grid.add(t5, 1, 0);
        grid.add(enter, 1, 1);
        grid.add(exit, 0, 1);
        
        enter.setOnAction(e -> finishAreaStats());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Area Statistics");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
//       ArrayList<Property> props = new ArrayList<Property>();
//       props = dmm.sortEircode("A");
    }
    
    public void finishAreaStats(){
        newWindow.close();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        ArrayList<Property> props = new ArrayList<Property>();
        props = dmm.sortEircode(t5.getText().toUpperCase());       
        ta1.setText(props.toString());
        
        grid.add(ta1, 0, 0);
        grid.add(b7, 0, 1);
        
        b7.setOnAction(e -> deptOptions());
        
        Scene thirteenthScene = new Scene(grid, 500, 500);
        newWindow = new Stage();
        newWindow.setTitle("Area Statistics");
        newWindow.setScene(thirteenthScene);
        newWindow.show();
    }
    
    public void investigateChanges(){
        //need to finish
    }


}
