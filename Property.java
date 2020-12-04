/*
 * To change this license header, choose License Headers in Property Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
//import java.util.ArrayList;
/**
 *
 * @author aoife
 */
public class Property {

    private String owner;
    private String address;
    private String eircode;
    private double marketValue;
    private String location;
    private boolean ppr; // true if ppr, false if not
    
    
    public Property(){
        owner = null;
        address = null;
        eircode = null;
        marketValue = 0.0;
        location = null;
        ppr = false;
    }
    
    public Property(String owner, String address, String eircode, double marketValue, String location, boolean ppr){
        this.owner = owner;
        this.address = address;
        this.eircode = eircode;
        this.marketValue = marketValue;
        this.location = location;
        this.ppr = ppr;
    }
    
    public String getOwner(){
        return owner;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getEircode(){
        return eircode;
    }
    
    public double getMarketValue(){
        return marketValue;
    }
    
    public void setMarketValue(double marketValue){
        this.marketValue = marketValue;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public boolean getPpr(){
        return ppr;
    }
    
    public void setPpr(boolean ppr){
        this.ppr = ppr;
    }
    
    public String toString(){
        return owner + " " + address + " " + eircode + " " + marketValue + " " + location + " " + ppr;
    }
    
}
