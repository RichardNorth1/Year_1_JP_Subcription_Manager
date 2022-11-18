/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscriptionmanager;


/**
 * @author Richard North A0313108
 * @version 1.0
 */
public class CustomerSummary extends SubscriptionSummary {
    private final Subscription customer;
    /**
    * Constructor method for creating a customer summary
    * <p>
    * @param Subscription this method takes a subscription object
    */
    public CustomerSummary(Subscription customer) {
        super();
        this.customer = customer;
    }
    /**
     * A method that calls multiple other methods in order to create a subscription
     * summary that will be output.
    */
    @Override
    public void createSummary(){
        double subscriptionPrice = customer.getPrice() / 100;
        createBlankSpace();
        createHeaderAndFooter();
        createEmptyLine();
        createLeftLine("Customer: "+ customer.getFullName());
        createEmptyLine();
        createDateAndDiscountLine();
        createPackageAndDurationLine();
        createLeftLine("Term: "+ customer.getTermInString());
        createEmptyLine();      
        createCenteredLine(customer.getTermInString()+" Subscription: £" +String.format("%,.2f", subscriptionPrice));
        createEmptyLine(); 
        createHeaderAndFooter();
        createBlankSpace();
    }

    private void createDateAndDiscountLine(){
        // should merge this method with package line method?
        String output = "|  Date: " + customer.getDate();
        System.out.print(output);
        int firstHalf = output.length();
        for (int i=0; i<(summaryCharacterWidth/2)-firstHalf; i++)
            System.out.print(" ");
        
        output = "Discount code: " + customer.getDiscountCode();
        System.out.print(output);
        int secondHalf = output.length();
        for (int i=0; i<(summaryCharacterWidth/2)-secondHalf +1; i++)
            System.out.print(" ");
        System.out.println("|");       
    }
    
    private void createPackageAndDurationLine(){
        String output = "|  Package: " + customer.getPackageInString();
        System.out.print(output);
        int firstHalf = output.length();
        for (int i=0; i<(summaryCharacterWidth/2)-firstHalf; i++)
            System.out.print(" ");
        
        output = "Duration: " + customer.getDurationInString();
        System.out.print(output);
        int secondHalf = output.length();
        for (int i=0; i<(summaryCharacterWidth/2)-secondHalf+1; i++)
            System.out.print(" ");
        System.out.println("|");       
    }
    
}
