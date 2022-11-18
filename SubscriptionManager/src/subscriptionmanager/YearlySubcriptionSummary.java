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

public class YearlySubcriptionSummary extends SubscriptionSummary{
    protected final ArrayList<Subscription> yearsSubsriptions;

    /**
     * This is a constructor method for creating a yearly subscription summary
     * @param yearsSubsriptions an arraylist containing multiple Subscriptions
     */
    public YearlySubcriptionSummary(ArrayList<Subscription> yearsSubsriptions) {
        super();
        this.yearsSubsriptions = yearsSubsriptions;
    }

    /**
     * A method that calls multiple other methods in order to create a yearly 
     * subscription summary that will be output.
     */
    @Override
    public void createSummary(){
        createBlankSpace();
        createHeaderAndFooter();
        createEmptyLine();
        createCenteredLine("Total number of Subscriptions: " + yearsSubsriptions.size());
        createCenteredLine("Average monthly Subscription: " + yearsSubsriptions.size()/12); 
        createCenteredLine("Average monthly Subscription fee: £" + String.format("%,.2f", calculateAverage()));
        createEmptyLine(); 
        createCenteredLine("Percentage of subscriptions:");
        createCenteredLine("Bronze: "+ String.format("%,.2f", calculatePercentage('B', yearsSubsriptions)));
        createCenteredLine("Silver: "+ String.format("%,.2f", calculatePercentage('S', yearsSubsriptions)));
        createCenteredLine("Gold: "+ String.format("%,.2f", calculatePercentage('G', yearsSubsriptions)));
        createEmptyLine(); 
        createCenteredLine("Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");
        createCenteredLine(CreateTotalsString());
        createEmptyLine();
        createHeaderAndFooter();
        createBlankSpace();
    }
    
    /**
     * This method is used to calculate the average monthly price of given month
     * @param listPosition integer value of a month
     * @return the average price of subscription in the given month
     */
    public double calculateAverageMonthly(int listPosition){
        ArrayList<ArrayList<Subscription>> sortedSubscriptions = sortYearlySubscriptions();
        double totalMonthlyPrice = 0;
        for (Subscription subscription : sortedSubscriptions.get(listPosition)) {
            totalMonthlyPrice += subscription.getPrice();
        }
        double AverageMonthlyPrice = totalMonthlyPrice / sortedSubscriptions.get(listPosition).size();
        return AverageMonthlyPrice /100;
    }
    
    /**
     * This method is used to calculate the average subscription price of each months 
     * average subscription price
     * @return The average monthly subscription price
     */
    public double calculateAverage(){
        double totalPrice = 0;
        for(int i=0; i<12; i++){
            totalPrice += calculateAverageMonthly(i);
        }
        double averagedPrice = totalPrice /12;
        
        return averagedPrice;
    }
    
    /**
     * This method is used to calculate the percentage of a given package type 
     * @param packageType the type of package needed to be calculated
     * @param subscriptionList an array list containing all subscriptions to be calculated
     * @return a double type representing the percentage the given package type makes up
     */
    public double calculatePercentage(char packageType, ArrayList<Subscription> subscriptionList){
        double amountOfPackage = 0;
        for (Subscription subscription: subscriptionList)
            if(subscription.getPackageType() == packageType){
                amountOfPackage ++;
            }
        
        return amountOfPackage / subscriptionList.size() *100;
    }
    
    /**
     * This method is used to create a formatted string containing the total number of 
     * subscriptions of each month
     * @return a formatted string of monthly totals
     */
    public String CreateTotalsString(){
        ArrayList<ArrayList<Subscription>> sortedSubscriptions = sortYearlySubscriptions();
        String TotalsString = "";
        for (ArrayList monthsSubscription : sortedSubscriptions) {
            TotalsString += String.format("%-3d ", monthsSubscription.size());
        }
        return TotalsString;
    }

    /**
     * This method is used to create an array list containing arraylists of subscriptions 
     * that have been sorted into each month meaning that all subscriptions that 
     * were created in January are added to a list and all subscriptions created in February 
     * are added to another and so on.
     * @return a sorted array list containing an arraylist of subscriptions
     */
    public ArrayList<ArrayList<Subscription>> sortYearlySubscriptions(){
        ArrayList<ArrayList<Subscription>> customersbyMonth = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>(Arrays.asList
        ("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        
        for (String month: months){
            ArrayList<Subscription> monthlySubscritions = new ArrayList<>();
            for (Subscription thisSubsription : yearsSubsriptions) {
                if (thisSubsription.getDate().substring(3, 6).equals(month)){
                    monthlySubscritions.add(thisSubsription);
                }
            }
            customersbyMonth.add(monthlySubscritions);
        }     
        return customersbyMonth;
    }
}
