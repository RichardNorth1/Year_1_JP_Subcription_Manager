/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


/**
 * @author Richard North A0313108
 * @version 1.0
 */
public class DiscountCodes {
    // creating the object properties
    private  String discountCode;
    private boolean isValidCode;
    private final String date;
    /**
    * Constructor method for creating a discount code object
    * <p>
    * This constructor method takes a discount code as a string and also generates
    * the current date in the format of "dd-MMM-yyyy".
    * <p>
    * @param  discount code as a String to be checked.
    */
    public DiscountCodes(String discountCode) {
        this.discountCode = discountCode.toUpperCase();
        this.date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
    }
    
    /**
    * Constructor method for creating a discount code object
    * <p>
    * This constructor method takes a discount code as a string and also takes the date 
    * in the format of "dd-MMM-yyyy".
    * <p>
    * @param  discount code as a String to be checked.
    */
    public DiscountCodes(String discountCode, String date) {
        this.discountCode = discountCode;
        this.date = date;
    }
    
    /**
     * A simple getter method to retrieve the discount code
     * @return The discount code as a string
     */
    public String getDiscountCode() {
        return discountCode;
    }
    
    /**
     * This method is used to determine whether the discount code provided is the 
     * right character length.
     * @return A boolean value to state if the length is valid
     */
    private boolean validateCodeLength(){
        boolean validCodeLength = false;
        int allowedCodeLength = 6;
        
        if (discountCode.length() == allowedCodeLength){
            validCodeLength = true;
        }
        return validCodeLength;
    } 
            
    /**
     * This method is used to determine whether the discount code provided is in the
     * correct format such as The code format would be two letters, two digits, 
     * a letter and a digit eg."JF19L5".
     * @return A boolean value to state if the length is valid
     */           
    private boolean validateCodeFormat(){
        boolean validFormat = false;
        
        if(Character.isLetter(discountCode.charAt(0))){
            if(Character.isLetter(discountCode.charAt(1))){
                if(Character.isDigit(discountCode.charAt(2))){
                    if(Character.isDigit(discountCode.charAt(3))){
                        if(Character.isLetter(discountCode.charAt(4))){
                            if(Character.isDigit(discountCode.charAt(5))){
                                validFormat = true;
                            
                            }
                        }
                    }
                }
            }
        }   
        return validFormat;
    }
   
    /**
     * This method is used to determine whether the discount code provided has 
     * the correct expiry date and year this is determined by checking if the 
     * third and forth digits are match the year that has been given in the constructor.
     * if the code has the correct expiry year it is then checked to see if the discount codes 
     * fifth character contains a 'E' for months January - June or a 'L' for months July - December 
     * if the code contains one of the characters it also checks the given date to see if the month 
     * is valid
     * @return A boolean value to state if the expiry date is valid
     */       
    private boolean validateCodeExpiry(){
        ArrayList<String> firstSixMonths = new ArrayList<>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun"));
        ArrayList<String> SecondSixMonths = new ArrayList<>(Arrays.asList("Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        boolean validExpiry = false;
        int codeExpiryYear = Integer.parseInt(discountCode.substring(2, 4));
        char codeExpiryMonth = discountCode.charAt(4);
        int year = Integer.parseInt(date.substring(date.length() - 2));
        String month = date.substring(3, 6);

        
        if (codeExpiryYear == year){
            if (codeExpiryMonth == 'E' && firstSixMonths.contains(month)){
                validExpiry = true;
            }
            else if (codeExpiryMonth == 'L' && SecondSixMonths.contains(month)){
                validExpiry = true;
            }
        }     
        return validExpiry;
    }
    
    /**
     * This method is used to call the relevant methods (validateCodeExpiry(), 
     * validateCodeFormat(), validateCodeLength()) needed to validate the given 
     * discount code if all methods return true the isValidCode attribute of this class 
     * is set to true
     */
    public void validateCode(){
        if (validateCodeLength()){
            if (validateCodeFormat()){
                if (validateCodeExpiry()){
                    isValidCode = true;          
                }
            }
        }
        if (! isValidCode){
            discountCode = "-";
        }
    }

    /**
     * This method is used to determine the percentage the discount code. This method 
     * calls (validateCode()) and if the code is valid the value at the end of the 
     * discount code is returned (1-9%)
     * @return The discount percentage of discount as a integer
     */
    public int applicableDiscount(){
        validateCode();
        int discountAmount = 0;
        if (isValidCode){
            discountAmount = Character.getNumericValue(discountCode.charAt(5));
        }
        
        return discountAmount;
    }
    // TODO is this needed?
    @Override
    public String toString() {
        return discountCode;
    }

}
