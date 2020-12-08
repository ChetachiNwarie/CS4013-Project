/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectb;

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
public class FXGui extends Application {
    //grid instance variables
    private Label l1 = new Label("Are you a property owner or management?");
    private Button b1 = new Button("Owner");
    private Button b2 = new Button("Management");
    private Button exit = new Button("Exit");
    private Button enter = new Button("Enter");
    //grid3 and grid4 instance variables
    private TextField t1 = new TextField();
    private TextField t2 = new TextField();
    private TextField t3 = new TextField();
    private TextField t4 = new TextField();
    private TextField t5 = new TextField();
    private TextField t6 = new TextField();
    private Label l2 = new Label("Enter your property details");
    private Label l3 = new Label("Owner");
    private Label l4 = new Label("Address");
    private Label l5 = new Label("Eircode");
    private Label l6 = new Label("Market Value");
    private Label l7 = new Label("Location");
    private Label l8 = new Label("Principal private residence");
    private PropertyOwner name;
    //grid2 instance variables
    private Label l9 = new Label("Is your property registered?");
    private Button b3 = new Button("Yes");
    private Button b4 = new Button("No");
    //grid5 instance variables
    private Button b5 = new Button("View payment records");
    private Label l10 = new Label("Tax due this year:");
    private Label l11 = new Label();
    private Button b6 = new Button("Pay tax");
    private double tax;
    private Property a;
    //grid6 instance variables
    private Label l12 = new Label("Thank you for paying your property tax");
    private PaymentRecord b;
    //grid7 instance variables
    private TextArea ta1 = new TextArea();

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

        ExitHandlerClass handler1 = new ExitHandlerClass();
        exit.setOnAction(handler1);

        b1.setOnAction(e -> Registration());
        //b1.setOnAction(e -> Registered());
        b2.setOnAction(e -> management());

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Registration() {
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);

        grid2.add(l9, 1, 0);
        grid2.add(b3, 1, 1);
        grid2.add(b4, 1, 2);
        grid2.add(exit, 4, 3);

        b3.setOnAction(e -> Registered());
        b4.setOnAction(e -> NotRegistered());

        Scene secondScene = new Scene(grid2, 300, 275);
        Stage secondWindow = new Stage();
        secondWindow.setTitle("Registration");
        secondWindow.setScene(secondScene);
        secondWindow.show();

    }

    public void NotRegistered() {

        GridPane grid3 = new GridPane();

        grid3.setHgap(10);
        grid3.setVgap(10);
        Scene thirdScene = new Scene(grid3, 450, 340);
        Stage thirdWindow = new Stage();
        thirdWindow.setTitle("Property details");
        thirdWindow.setScene(thirdScene);
        thirdWindow.show();
        
        

        grid3.add(l2, 1, 0);
        grid3.add(l3, 1, 1);
        grid3.add(l4, 1, 2);
        grid3.add(l5, 1, 3);
        grid3.add(l6, 1, 4);
        grid3.add(l7, 1, 5);
        grid3.add(l8, 1, 6);
        grid3.add(t1, 2, 1);
        grid3.add(t2, 2, 2);
        grid3.add(t3, 2, 3);
        grid3.add(t4, 2, 4);
        grid3.add(t5, 2, 5);
        grid3.add(t6, 2, 6);
        grid3.add(enter, 3, 7);
        grid3.add(exit, 4, 7);

        enter.setOnAction(e -> taxNotRegistered());

    }

    public void Registered() {
        GridPane grid4 = new GridPane();
        grid4.setHgap(10);
        grid4.setVgap(10);
        Scene thirdScene = new Scene(grid4, 450, 340);
        Stage thirdWindow = new Stage();
        thirdWindow.setTitle("Property details");
        thirdWindow.setScene(thirdScene);
        thirdWindow.show();

        grid4.add(l2, 1, 0);
        grid4.add(l3, 1, 1);
        grid4.add(l4, 1, 2);
        grid4.add(l5, 1, 3);
        grid4.add(l6, 1, 4);
        grid4.add(l7, 1, 5);
        grid4.add(l8, 1, 6);
        grid4.add(t1, 2, 1);
        grid4.add(t2, 2, 2);
        grid4.add(t3, 2, 3);
        grid4.add(t4, 2, 4);
        grid4.add(t5, 2, 5);
        grid4.add(t6, 2, 6);
        grid4.add(enter, 3, 7);
        grid4.add(exit, 4, 7);

        enter.setOnAction(e -> taxRegistered());
        
    }

    public void taxNotRegistered() {
        GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.CENTER);
        String owner = t1.getText();
        String address = t2.getText();
        String eircode = t3.getText();
        double marketValue = Double.parseDouble(t4.getText());
        String location = t5.getText();
        boolean principalPrivateResidence;
        if (t6.getText().toLowerCase().equals("no")) {
            principalPrivateResidence = false;
        } else {
            principalPrivateResidence = true;
        }
        
        a = new Property(owner, address, eircode, marketValue, location, principalPrivateResidence);
        tax = a.taxDue();
        name = new PropertyOwner(t1.getText());
        
        grid5.setHgap(10);
        grid5.setVgap(10);
        Scene fourthScene = new Scene(grid5, 450, 340);
        Stage fourthWindow = new Stage();
        fourthWindow.setTitle("Property details");
        fourthWindow.setScene(fourthScene);
        fourthWindow.show();
        
        l11.setText(Double.toString(tax));
        grid5.add(l10, 1, 0);
        grid5.add(l11, 1, 1);
        grid5.add(exit, 4, 7);
        grid5.add(b6, 4, 6);
        grid5.add(b5, 4, 5);
        
        b5.setOnAction(e -> viewRecords());
        b6.setOnAction(e -> taxPayment());
    }
    
    public void taxPayment(){
        GridPane grid6 = new GridPane();
        grid6.setAlignment(Pos.CENTER);
        grid6.setHgap(10);
        grid6.setVgap(10);
        Scene fifthScene = new Scene(grid6, 450, 340);
        Stage fifthWindow = new Stage();
        fifthWindow.setTitle("Tax payment");
        fifthWindow.setScene(fifthScene);
        fifthWindow.show();
        
        grid6.add(l12, 1, 0);
        grid6.add(exit, 4, 7);
        grid6.add(b5, 3, 7);
        
        name.payTax(a);
        //a.addToPaymentFile(new PaymentRecord(LocalDate.now().getYear(), true, tax));

        b5.setOnAction(e -> viewRecords());
    }
    
    public void viewRecords(){
        GridPane grid7 = new GridPane();
        grid7.setAlignment(Pos.CENTER);
        grid7.setHgap(10);
        grid7.setVgap(10);
        Scene sixthScene = new Scene(grid7, 450, 340);
        Stage sixthWindow = new Stage();
        sixthWindow.setTitle("Payment Records");
        sixthWindow.setScene(sixthScene);
        sixthWindow.show();
        
       
        String s = null, paid;
        for(int i=0; i<a.getPaymentRecords().size(); i++){
            if(a.getPaymentRecords().get(i).isWasPaid() != false){
                paid = "not paid";
            }
            else{
                paid = "paid";
            }
            s=Integer.toString(a.getPaymentRecords().get(i).getYear())+" "+ paid +" "+ Double.toString(a.getPaymentRecords().get(i).getAmount())+"\n";
        }

        ta1.setText(s);
        ta1.setEditable(false);
        grid7.add(ta1, 0, 0);
        grid7.add(exit, 0, 1);
        
        
    }
    
    public void management(){
        GridPane grid8 = new GridPane();
        grid8.setAlignment(Pos.CENTER);
        grid8.setHgap(10);
        grid8.setVgap(10);
        Scene seventhScene = new Scene(grid8, 450, 340);
        Stage seventhWindow = new Stage();
        seventhWindow.setTitle("Management");
        seventhWindow.setScene(seventhScene);
        seventhWindow.show();
    }
    
    public void taxRegistered(){
        GridPane grid9 = new GridPane();
        grid9.setAlignment(Pos.CENTER);
        grid9.setHgap(10);
        grid9.setVgap(10);
        Scene eighthScene = new Scene(grid9, 450, 340);
        Stage eighthWindow = new Stage();
        eighthWindow.setTitle("Tax due");
        eighthWindow.setScene(eighthScene);
        eighthWindow.show();
        
        String owner1 = t1.getText();
        String address = t2.getText();
        String eircode = t3.getText();
        double marketValue = Double.parseDouble(t4.getText());
        String location = t5.getText();
        boolean principalPrivateResidence;
        if (t6.getText().toLowerCase().equals("no")) {
            principalPrivateResidence = false;
        } else {
            principalPrivateResidence = true;
        }
        //not finished
    }
}

class ExitHandlerClass implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        System.exit(0);
    }
}
