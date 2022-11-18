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

public abstract class SubscriptionSummary {
    protected final int summaryCharacterWidth;

    /**
     * This is a constructor method for creating a subscription summary
     */
    protected SubscriptionSummary() {
        this.summaryCharacterWidth = 56;
    }

    protected abstract void createSummary();
            
    /**
     * A simple method that is used to print out 3 blank lines 
     */
    protected void createBlankSpace(){
        for (int i=0; i<3; i++){
            System.out.println("");
        }
    }
    
    /**
     * A simple method to outputs the summary header and footer 
     */
    protected void createHeaderAndFooter(){
        System.out.print("+");
        for (int i=0; i<this.summaryCharacterWidth; i++)
            System.out.print("=");
        System.out.println("+");
    }
    
    /**
     * A simple method that outputs "|" followed by white space to create a blank line 
     * within the summary
     */
    protected void createEmptyLine(){
        System.out.print("|");
        for (int i=0; i<this.summaryCharacterWidth; i++)
            System.out.print(" ");
        System.out.println("|");
    }
    
    /**
     * A method that takes a string and outputs that string as a centred line within the summary
     * @param outputMessage the string to be output centrally in the summary
     */
    protected void createCenteredLine(String outputMessage){
        System.out.print("|");
        int spacesAdded = 0;
        for (int i=0; i<(this.summaryCharacterWidth- outputMessage.length())/2; i++){
            System.out.print(" ");
            spacesAdded++;
        }
        System.out.print(outputMessage);
        for (int i=0; i<this.summaryCharacterWidth - (spacesAdded + outputMessage.length()); i++){
            System.out.print(" ");
        }
        System.out.println("|");
    }
    
    /**
     * A method that takes a string and outputs that string aligned on the left 
     * within the summary
     * @param outputMessage the string to be output in the summary
     */
    protected void createLeftLine(String outputMessage){
        System.out.print("|  " + outputMessage);
        for (int i=0; i<this.summaryCharacterWidth-outputMessage.length()-2; i++)
            System.out.print(" ");
        System.out.println("|");
    }

}
