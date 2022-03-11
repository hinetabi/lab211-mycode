/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
    
    
}
