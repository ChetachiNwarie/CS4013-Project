
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
 * @author Chetachi Nwarie (19244355)
 */
public class PropertyMain
{
    public static void main (String[]args){
          try{
        Property sam = new Property("Sam", "4 house", "V34He33", 2132322, "Large Town", true);
        sam.addToPaymentFile(new PaymentRecord(2010, false, 200));
        sam.addToPaymentFile(new PaymentRecord(2011, false, 200));
        sam.addToPaymentFile(new PaymentRecord(2012, false, 200));
        sam.addToPaymentFile(new PaymentRecord(2013, false, 200));
        sam.addToPaymentFile(new PaymentRecord(2014, false, 200));
        sam.addToPaymentFile(new PaymentRecord(2015, false, 200));
        Property aoife = new Property("Aoife", "34 Lenov", "V34Hr33", 21322422, "Small Town", false);
        aoife.addToPaymentFile(new PaymentRecord(2010, false, 200));
        aoife.addToPaymentFile(new PaymentRecord(2011, false, 200));
        aoife.addToPaymentFile(new PaymentRecord(2012, false, 200));
        aoife.addToPaymentFile(new PaymentRecord(2013, false, 200));
        aoife.addToPaymentFile(new PaymentRecord(2014, false, 200));
        aoife.addToPaymentFile(new PaymentRecord(2015, false, 200));
        
      
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
