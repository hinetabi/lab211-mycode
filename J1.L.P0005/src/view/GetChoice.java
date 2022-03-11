package view;

import java.util.ArrayList;
import model.Account;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Legion
 */
public class GetChoice {

    /**
     * Get choice
     *
     * @param ops
     * @return
     */
    public static int getchoice(String[] ops) {
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        System.out.println();
        return validation.Inputter.inputIntegerInRange("Enter your choice(1-" + ops.length + "): ", 1, ops.length);
    }
    
    // get name from list of account
    public static String getAccountChoice(String msg, ArrayList<Account> accountList) {
        System.out.println(msg);
        for (int i = 0; i < accountList.size(); i++) {
            System.out.println((i+1) + ". " + accountList.get(i).getName()); 
        }
        return accountList.get(validation.Inputter.
                inputIntegerInRange("Enter your choice(1-" + accountList.size() + "): ", 1, accountList.size()) - 1)
                .getName();
    }

}
