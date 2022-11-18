/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Richard North A0313108
 * @version 1.0
 */
public class Menu {

    /**
     * This method creates a menu and calls the method (InputValidator.getInt())
     * to get an input and once a valid input has been entered the corresponding
     * option method is called.
     */
    public void run() {
        ArrayList<Integer> validOptions = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        String userPrompt = "Please enter one of the following options \n"
                + "1. Enter new Subscription\n"
                + "2. Display Summary of subscriptions\n"
                + "3. Display Summary of subscription for Selected Month\n"
                + "4. Find and display subscription\n"
                + "0. Exit\n";

        boolean inUse = true;
        do {
            switch (InputValidator.getInt(validOptions, userPrompt)) {
                case 1:
                    newSubcription();
                    break;
                case 2:
                    summaryYear();
                    break;
                case 3:
                    summaryMonth();
                    break;
                case 4:
                    search();
                    break;
                case 0:
                    inUse = false;
                    break;
            }
        } while (inUse);
    }
    
    /**
     * This method is used to create new subscriptions this is done by prompting the 
     * user to enter values which are validated by the various methods in the InputValidator
     * class. Once all the subscription details have been obtained these details are used to create
     * a Subscription object and a summary of the subscription is displayed to the user. 
     * After the summary has been displayed the user then is prompted again to verify the
     * details in the summary are correct and if so the FileIO.saveCustomer() is called to save 
     * the subscription to the "current.txt" file.
     */
    private void newSubcription() {
        // Package options
        ArrayList<Character> packageOptions = new ArrayList<>(Arrays.asList('B', 'S', 'G'));
        String packageOptionPrompt = "Please enter your package type\n"
                + "Valid options are: B, S, G\n";
        // Duration options
        ArrayList<Integer> durationOptions = new ArrayList<>(Arrays.asList(1, 3, 6, 12));
        String durationPrompt = "Please enter the duration of package\n"
                + "Valid options are: 1, 3, 6, 12\n";
        // Term options
        ArrayList<Character> termOptions = new ArrayList<>(Arrays.asList('O', 'M'));
        String termPrompt = "Please enter the term of package\n"
                + "Valid options are: O, M\n";
        // discount code
        String discountPrompt = "Please enter a valid discount code or enter a value to skip\n";
        // Main options
        ArrayList<Integer> mainOptions = new ArrayList<>(Arrays.asList(0, 1));
        String optionPrompt = "Please enter one of the following options\n"
                + "1. Create new Subscription\n"
                + "0. Main menu\n";
        // save user
        ArrayList<Integer> saveOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        String savePrompt = "Please enter one of the following options\n"
                + "1. Save customer\n"
                + "2. Dont save\n";

        boolean inUse = true;
        do {
            switch (InputValidator.getInt(mainOptions, optionPrompt)) {
                case 0:
                    inUse = false;
                    break;
                case 1:
                    Subscription newSubscription = new Subscription(
                            InputValidator.getUserName(),
                            InputValidator.getChar(packageOptions, packageOptionPrompt),
                            InputValidator.getInt(durationOptions, durationPrompt),
                            new DiscountCodes(InputValidator.getString(discountPrompt)),
                            InputValidator.getChar(termOptions, termPrompt));

                    newSubscription.calculatePrice();

                    new CustomerSummary(newSubscription).createSummary();

                    if (InputValidator.getInt(saveOptions, savePrompt) == 1) {
                        FileIO.saveCustomer(newSubscription);
                    }

                    break;
            }
        } while (inUse);
    }
    
    /**
     * This method is used to prompt the user to select the file they would like to use
     * to create the yearly subscription, Once the selected file has been obtained 
     * a new YearlySubcriptionSummary is created and is output
     */
    private void summaryYear() {
        boolean inUse = true;

        ArrayList<Integer> menuOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        String optionPrompt = "Please enter one of the following options\n"
                + "1. View subscription summary for \"current.txt\"\n"
                + "2. View subscription summary for \"sample.txt\"\n"
                + "0. Main menu\n";
        do {
            ArrayList<Subscription> customers = new ArrayList<>();
            switch (InputValidator.getInt(menuOptions, optionPrompt)) {
                case 0:
                    inUse = false;
                    break;
                case 1:
                    customers = FileIO.readCustomer(1);
                    new YearlySubcriptionSummary(customers).createSummary();
                    break;
                case 2:
                    customers = FileIO.readCustomer(2);
                    new YearlySubcriptionSummary(customers).createSummary();
                    break;
            }
        } while (inUse);
    }

    /**
     * This method is used to prompt the user to indicate whether they would like to 
     * generate the monthly summary using the "current.txt" or "sample.txt", Once this is done
     * the user is prompted to enter a month value eg. 6. After the above has been executed  
     * a summary is generated for that given month by creating a new MonthlySubscriptionSummary
     */
    public void summaryMonth() {
        boolean inUse = true;
        // Month options
        ArrayList<Integer> monthOptions = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        String monthPrompt = "Please enter a month in a decimal format\n"
                + "Valid options are: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12\n";
        // menu options
        ArrayList<Integer> menuOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        String optionPrompt = "Please enter one of the following options\n"
                + "1. View subscription summary for \"current.txt\"\n"
                + "2. View subscription summary for \"sample.txt\"\n"
                + "0. Main menu\n";

        do {
            switch (InputValidator.getInt(menuOptions, optionPrompt)) {
                case 0:
                    inUse = false;
                    break;
                case 1:
                    // create new summary using the "current.txt"
                    new MonthlySubscriptionSummary(FileIO.readCustomer(1),
                            InputValidator.getInt(monthOptions, monthPrompt) - 1).createSummary();
                    break;
                case 2:
                    // create new summary using the "sample.txt"
                    new MonthlySubscriptionSummary(FileIO.readCustomer(2),
                            InputValidator.getInt(monthOptions, monthPrompt) - 1).createSummary();
                    break;
            }
        } while (inUse);
    }

    /**
     * This method is used to create a summary for any and all matching summaries 
     * the user searches for. This is done by prompting the user to enter a customer name 
     * or a partial name to search against the customers 
     */
    public void search() {
        boolean inUse = true;
        ArrayList<Integer> menuOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        String optionPrompt = "Please enter one of the following options\n"
                + "1. View subscription summary for \"current.txt\"\n"
                + "2. View subscription summary for \"sample.txt\"\n"
                + "0. Main menu\n";
        //Name search prompt
        String namePrompt = "Please enter the name you would like to search for: \n";
        do {
            ArrayList<Subscription> customers = new ArrayList<>();
            String customerName = "";

            switch (InputValidator.getInt(menuOptions, optionPrompt)) {
                case 0:
                    inUse = false;
                    break;

                case 1:
                    customers = FileIO.readCustomer(1);
                    customerName = InputValidator.getString(namePrompt);
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getFullName().toUpperCase().contains(customerName.toUpperCase())) {
                            new CustomerSummary(customers.get(i)).createSummary();
                        }
                    }
                    break;

                case 2:
                    customers = FileIO.readCustomer(2);
                    customerName = InputValidator.getString(namePrompt);
                    for (int i = 0; i < customers.size(); i++) {
                        if (customers.get(i).getFullName().toUpperCase().contains(customerName.toUpperCase())) {
                            new CustomerSummary(customers.get(i)).createSummary();
                        }
                    }
                    break;
            }
        } while (inUse);
    }

}
