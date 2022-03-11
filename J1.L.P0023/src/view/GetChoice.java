package view;

import model.FruitList;

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

    /**
     * choose a fruit in list
     *
     * @param fruits
     * @return
     */
    public static int getFruitChoice(FruitList fruits) {
        int max = 0;
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getQuantity() > 0) {
                System.out.printf("\t%-10s%-20s%-15s%-10s\n", fruits.get(i).getID(), fruits.get(i).getName(), fruits.get(i).getOrigin(), fruits.get(i).getPrice());
                if (max < fruits.get(i).getID()) {
                    max = fruits.get(i).getID();
                }
            }
        }
        System.out.println();
        return validation.Inputter.inputIntegerInRange("Choose a fruit (item): ", 1, max, true);
    }

}
