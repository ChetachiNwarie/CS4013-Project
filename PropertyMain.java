
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chetachi
 */
public class PropertyMain
{
    public static void main (String[]args){
        try
        {
            PropertyManagement prop = new PropertyManagement();
            PropertyChargeManagementSystem p = new PropertyChargeManagementSystem();
            p.run(prop);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PropertyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
