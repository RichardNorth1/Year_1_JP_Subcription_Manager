/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Richard North A0313108
 * @version 1.0
 */
public class InputValidator {
    
    /**
     * This method is used to prompt the user to enter their first and last name.
     * once the user has entered this the first character of their first name is 
     * retained and capitalised as well as capitalising the first character of their 
     * last name and concatenating their names to be used within the programme
     * @return The full name concatenated together e.g "R North"
     */
   public static String getUserName() {
       // TODO need to add validation to limit the customer name to 25 characters in length 
       String fullName = "";
       boolean invalid = true;
       do{
            Scanner input = new Scanner(System.in);
            System.out.print("\nPlease enter your first name\n");
            String firstName = input.next().toUpperCase().trim();

            System.out.print("\nPlease enter your last name\n");
            String lastName = input.next().trim();
            // formatting the last name so the first letter is capitalized
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            fullName = firstName.charAt(0) +  " " + lastName;
            if (fullName.length() <= 25){
                invalid = false;
            }
            else {
                System.out.print("\nSorry that name is to long please try again\n");
            }
       }while(invalid);
        return fullName;
    }
   
   /**
    * This method is used to prompt the user to enter a string and returns the users input
    * @param userPrompt is a a string that will be output to the explaining the input needed
    * @return The String requested by the userprompt
    */
   public static String getString(String userPrompt){
       Scanner input = new Scanner(System.in);
       System.out.print(userPrompt);
       String userString = input.nextLine().trim();
       
       return userString;
   }
   
   /**
    * This method is used to get a string from the user and validate whether it is a valid entry
    * @param userPrompt A string message to be displayed to the user 
    * @param validOptions A Arraylist that contains all the valid inputs
    * @return  The users validated string
    */
   public static String getValidString(String userPrompt,ArrayList<String> validOptions){
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        String userString = "";
 
        do{
            System.out.println(userPrompt);
            userString = input.next().trim().toLowerCase();
            userString = userString.substring(0, 1).toUpperCase() + userString.substring(1);
            if(validOptions.contains(userString)){
                invalid = false;
            }
            else{
                System.out.println("\nThat is invalid please try again\n");
            }
        }while(invalid);

        return userString;
    }
   
   /**
    * This method is used to get a character from the user and validate whether it is a valid entry
    * @param userPrompt A string message to be displayed to the user 
    * @param validOptions A Arraylist that contains all the valid inputs
    * @return  The users validated character 
    */
    public static char getChar(ArrayList<Character> validOptions, String userPrompt) {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        char userChar = ' ';

        do {
            System.out.print(userPrompt);
            userChar = input.next().toUpperCase().trim().charAt(0);
            if(validOptions.contains(userChar)){
                invalid = false;
            }
            else{
                System.out.print("\nThat is invalid please try again\n");
            }
        } while (invalid);

        return userChar;
    }
    
   /**
    * This method is used to get a integer from the user and validate whether it is a valid entry
    * @param userPrompt A string message to be displayed to the user 
    * @param validOptions A Arraylist that contains all the valid inputs
    * @return  The users validated integer
    */
    public static int getInt(ArrayList<Integer> validOptions, String userPrompt) {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        int userInt = 0;
        do {
            // print out options
            System.out.print(userPrompt);

            if (input.hasNextInt()){
                userInt = input.nextInt();
                if (validOptions.contains(userInt)){
                    invalid = false;
                }
                else{
                    System.out.println("\nThat is not a valid option\n");
                }
            }
            else {
                System.out.println("\nThat is not a Integer\n");
                
                input.next();
            }
            
        } while(invalid);

        return userInt;

    }

}
