/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Richard North A0313108
 * @version 1.0
 */

public class FileIO {
    
    /**
     * This method is used to append a given customer to the current.txt file 
     * @param customer The subscription object that is going to be appended to the .txt file
     */
    public static void saveCustomer(Subscription customer){
        
        
        try(final PrintWriter output = new PrintWriter(new BufferedWriter
        (new FileWriter("src/subscriptionmanager/current.txt", true)))){
            
            output.print(customer.toString());
        }
        catch (IOException e){
            System.out.println("Im sorry that file can't be found");
        }
    }
    /**
     * This method is used to to read the customer details from  a file
     * <p>
     * This method takes an integer as a way to select the correct file, once  a file has been 
     * selected each line from the given file is read and the customers details are retrieved 
     * and a subscription is created with the customers details. Once the subscription is created 
     * it is then added to an arraylist this is repeated until all lines in the file have been 
     * read. 
     * @param fileOption 1 is for the "current.txt" and 2 for "sample.txt".
     * @return an arraylist containing all of the retrieved customer subscriptions.
     */
    public static ArrayList<Subscription> readCustomer(int fileOption){
        ArrayList<Subscription> customers = new ArrayList<>();
        String fileOne = "src/subscriptionmanager/current.txt";
        String fileTwo = "src/subscriptionmanager/sample.txt";
        String fileToRead = "";
        if (fileOption == 1){
            fileToRead = fileOne;
        }
        else {
            fileToRead = fileTwo;
            
        }
        try(final Scanner fileInput = new Scanner(new FileReader(fileToRead))){
            //int counter = 0;
            while (fileInput.hasNext()){
                //counter ++;
                
                String date = fileInput.next();
                char packageType = fileInput.next().charAt(0);
                int duration = Integer.parseInt(fileInput.next());
                String discountCode = fileInput.next();
                char packageTerm = fileInput.next().charAt(0);
                int price = Integer.parseInt(fileInput.next());
                String name = fileInput.nextLine().trim();
                
                // creating a customer
                Subscription customer = new Subscription(name, packageType, duration,
                        new DiscountCodes(discountCode), packageTerm, price, date);
                customers.add(customer);
                
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The file could not be found");
        }
        return customers;
    }
    
}
