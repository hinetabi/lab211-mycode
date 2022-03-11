/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Manager.ManageEastAsiaCountries;

/**
 *
 * @author Legion
 */
public class View {

    private final String[] ops;
    private ManageEastAsiaCountries manager;
    public View() {
        manager = new ManageEastAsiaCountries();
        this.ops = new String[]{"1. Enter the information for 2 countries in Southeast Asia."
        , "2. Display already information."
                , "3. Search the country according to the entered country's name."
                , "4. Display the information increasing with the country name."
                , "5. Exit." };
    }
    
    private int getChoice(String[] ops) {
        for (String op : ops) {
            System.out.println(op);
        }
        return Validation.Inputter.inputIntegerInRange("Enter your choice (1-" + ops.length + "): ", 1, ops.length);
    }
    
    public static void mainScreen() {
        
    }

    public void start() {
        int choice = 0;
        do {
           choice = getChoice(ops);
           switch (choice) {
               case 1: {
                   manager.addCountryInformation();
                   break;
               }
               
               case 2: {
                   manager.getRecentlyEnteredInformation();
                   break;
               }
               
               case 3: {
                   String name = Validation.Inputter.inputString("Enter name: ", false);
                   manager.printData(manager.searchInformationByName(name));
                   break;
               }
               
               case 4: {
                   manager.sortInformationByAscendingOrder();
                   manager.printData(manager.getAsiaCountriesList());
                   break;
               }
           }
        } while (choice < ops.length);
        
    }
}
