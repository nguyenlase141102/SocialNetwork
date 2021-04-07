/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.checkvalid;

/**
 *
 * @author ANH NGUYEN
 */
public class Validation {
 
    public static boolean checkCharacters (String value){
        boolean result ;
        result  = !value.matches("^[a-zA-Z0-9 ]{0,100}$") ? false : true;
        return result;
    }
}
