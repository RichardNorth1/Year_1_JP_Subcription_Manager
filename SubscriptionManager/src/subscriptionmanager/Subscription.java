/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author Richard North A0313108
 * @version 1.0
 */

public class Subscription {
    // Properties 
    private final String fullName;
    private final char packageType;
    private final int duration;
    private final DiscountCodes discountCode;
    private final char term;
    private int price;
    private final String date;

    /**
     * This is a constructor method for creating a subscription 
     * @param fullName accepted format of "R North"
     * @param packageType accepted values should be 'B', 'S', 'G'
     * @param duration accepted values should be '1', '3', '6', '12'
     * @param discountCode
     * @param term accepted values should be 'M', 'O'
     * @param price 
     * @param date accepted value should be in the format"dd-MMM-yyyy"
     */
    public Subscription(String fullName, char packageType, int duration, 
            DiscountCodes discountCode, char term, int price, String date) {
        this.fullName = fullName;
        this.packageType = packageType;
        this.duration = duration;
        this.discountCode = discountCode;
        this.term = term;
        this.price = price;
        this.date = date;
    }
    
    /**
     * This is a constructor method for creating a subscription where the price needs to be 
     * calculated and todays date needs to be applied to the subscription
     * @param fullName accepted format of "R North"
     * @param packageType accepted values should be 'B', 'S', 'G'
     * @param duration accepted values should be '1', '3', '6', '12'
     * @param discountCode
     * @param term accepted values should be 'M', 'O'
     */
    public Subscription(String fullName,  char packageType, int duration, 
            DiscountCodes discountCode, char term) {
        this.fullName = fullName;
        this.packageType = packageType;
        this.duration = duration;
        this.discountCode = discountCode;
        this.term = term;
        this.date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());       
    }
    
    /**
     * A simple method to get the subscriptions full name 
     * @return the subscription name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * A simple method to get the subscriptions package type
     * @return the subscriptions package type
     */
    public char getPackageType() {
        return packageType;
    }

    /**
     * A simple method that returns the package type as full string eg. returns 
     * "Bronze" if the subscriptions package type is a 'B'
     * @return the package type as a string 
     */
    public String getPackageInString(){
        String packageInString = "";
        switch(packageType){
            case'B':
                packageInString = "Bronze";
                break;
            case 'S':
                packageInString = "Silver";
                break;
            case'G':
                packageInString = "Gold";
                break;
        }
        return packageInString;
    }
    
    /**
     * A simple method to get the subscriptions duration
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * A simple method that returns the duration as full string eg. returns 
     * "Twelve" if the subscriptions duration is 12
     * @return the duration as a string
     */
    public String getDurationInString(){
        String durationInString = "";
        switch(this.duration){
            case 1:
                durationInString = "One";
                break;
            case 3:
                durationInString = "Three";
                break;
            case 6:
                durationInString = "Six";
                break;
            case 12:
                durationInString = "Twelve";
                break;
        }
        return durationInString;
    }
    
    /**
     * A simple method to get the subscriptions discount code
     * @return the discount code as a string
     */
    public String getDiscountCode() {
        return discountCode.getDiscountCode();
    }

    /**
     * A simple method to get the subscriptions term
     * @return the term as a character
     */
    public char getTerm() {
        return term;
    }
    
    /**
     * A simple method that returns the term as full string eg. returns 
     * "Monthly" if the subscriptions term is 'M'
     * @return 
     */
    public String getTermInString(){
        String termInString = "";
        if (this.term == 'M'){
            termInString = "Monthly";
        }
        else {
            termInString = "One-off";
        }
        return termInString;
    }
    
    /**
     * A simple method to get the subscriptions price
     * @return the price as an integer
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * A simple method to get the subscriptions date
     * @return the date as a string 
     */
    public String getDate() {
        return date;
    }

    /**
     * This method is used to calculate the subscriptions price (only to be used
     * when the price has not been given in the constructor method) this method works by 
     * storing the the base prices of the different packages and simply takes the corresponding
     * base price of the package type and minuses the durations position in the array 
     * multiplied by 100 (deducts 100 off the base price per position in the durations array)
     * once the price has been calculated the term is checked to see if the subscription is a
     * monthly or one off payment and if it is the price is adjusted so that a 5% discount 
     * is applied.
     */
    public void calculatePrice(){
        int[] durations = {1, 3, 6, 12};
        char[] packageTypes = {'B', 'S', 'G'};
        int[] basePrice = {600, 800, 999};
        int currentPrice = 0;
        
        for (int outter = 0; outter < packageTypes.length ; outter++){
            if (packageType == packageTypes[outter]){
                for (int inner = 0; inner < durations.length ; inner++){ 
                    if (duration == durations[inner]){
                        currentPrice = basePrice[outter] - (inner *100);
                    }
                }
            }
        }

        this.price = currentPrice -((currentPrice / 100) * discountCode.applicableDiscount());
        if (term == 'O'){
            this.price = (((this.price * 12)/100 )* 95);
        }
    }

    /**
     * This is a simple method to return the subscription details (mainly used to 
     * return the subscription details in the same format as that in the "current.txt" 
     * and "sample.txt" files) 
     * @return a formatted string of the subscription attribute separated by a tab character
     */
    @Override
    public String toString() {
        return date + "\t" + packageType + "\t" + duration + "\t" + discountCode.toString()
                + "\t" + term + "\t" + price + "\t" + fullName + "\n" ;
    }

    
    
}
