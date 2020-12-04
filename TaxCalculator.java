/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author aoife
 */
public class TaxCalculator {
    private Property a;
    private double tax = 100;
    public TaxCalculator(){
        
    }
    
    public TaxCalculator(Property a){
        this.a = a;
    }
    
    public double findTax(){
        double value = a.getMarketValue();
        if(value < 150000.00){
            tax = tax + 0;
        }
        if((value >= 150000) && (value <= 400000)){
            tax = tax + value*(0.01/100);
        }
        if((value >= 400001) && (value <= 650000)){
            tax = tax + value*(0.02/100);
        }
        if(value > 650000){
            tax = tax + value*(0.04/100);
        }

        String location = a.getLocation();
        if(location.equals("city")){
            tax = tax + 100;
        }
        if(location.equals("large town")){
            tax = tax + 80;
        }
        if(location.equals("small town")){
            tax = tax + 60;
        }
        if(location.equals("village")){
            tax = tax + 50;
        }
        if(location.equals("country")){
            tax = tax + 25;
        }
        
        if(a.getPpr() == false){
            tax = tax + 100; 
        }
        
        return tax;
    }
        
    public String toString(){
        return a.toString() + " " + tax;
    }
    
//    public static void main(String[] args){
//        Property a = new Property("Aoife", "35 Red Road", "V07T98", 200000.00, "small town", true);
//        TaxCalculator calculator = new TaxCalculator(a);
//        System.out.println(calculator.toString());
//    }
}
