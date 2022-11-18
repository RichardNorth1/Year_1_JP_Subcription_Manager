/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;

import java.util.ArrayList;


/**
 * @author Richard North A0313108
 * @version 1.0
 */
public class MonthlySubscriptionSummary extends YearlySubcriptionSummary{
    private final int monthValue;
    /**
     * Constructor method for creating a  given month's subscription summary
     * @param yearsSubsriptions The complete list of all subscription read from a file  
     * @param monthValue valid integers would be any integer between 0-11 
     */
    public MonthlySubscriptionSummary(ArrayList<Subscription> yearsSubsriptions, int monthValue) {
        super(yearsSubsriptions);
        this.monthValue = monthValue;
    }

    /**
     * A method that calls multiple other methods in order to create a monthly 
     * subscription summary of a given month that will be output.
     */
    @Override
    public void createSummary() {
        String[] months = {"January", "Feburary", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
        createBlankSpace();
        createHeaderAndFooter();
        createEmptyLine();
        createCenteredLine(String.format("Total number of subscriptions for %s: "+23, months[monthValue]));
        createCenteredLine("Average subscription fee: £" + String.format("%,.2f", calculateAverageMonthly(monthValue)));
        createEmptyLine();
        createCenteredLine("Percentage of Subscriptions: ");
        createCenteredLine("Bronze: " + String.format("%,.2f", calculatePercentage('B', sortYearlySubscriptions().get(monthValue))));
        createCenteredLine("Silver: " + String.format("%,.2f", calculatePercentage('S', sortYearlySubscriptions().get(monthValue))));
        createCenteredLine("Gold: " + String.format("%,.2f", calculatePercentage('G', sortYearlySubscriptions().get(monthValue))));
        createEmptyLine();
        createHeaderAndFooter();
        createBlankSpace();
    }
    
    
}
