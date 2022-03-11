/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Legion
 */
public class FruitList extends ArrayList<Fruit> {

    public FruitList() {
        super();
    }

    /**
     * find fruit in list by its ID
     *
     * @param ID
     * @return index of fruit with id = ID
     */
    public int findFruitById(int ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID() == ID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * check if ID is in list?
     *
     * @param ID
     * @return true if id in list, false otherwise
     */
    public boolean isIdExistInList(int ID) {
        return (findFruitById(ID) >= 0);
    }

    /**
     * add/subtract quantity of a fruit in list (using in shopping)
     * add if isAdding = true, subtract if otherwise
     * @param quantity
     * @param ID
     * @param isAdding
     * @return true if ID exist in List, false otherwise
     */
    public boolean modifyQuantity(int ID, int quantity, boolean isAdding) {
        if (!isIdExistInList(ID)) {
            return false;
        } else {
            int index = findFruitById(ID);
            if (isAdding) {
                this.get(index).setQuantity(this.get(index).getQuantity() + quantity);
            } else {
                this.get(index).setQuantity(this.get(index).getQuantity() - quantity);
            }
        }
        return true;
    }
    
    /**
     * add a new fruit in list
     * @param fruit
     * @return 
     */
    @Override
    public boolean add(Fruit fruit) {
        int index = findFruitById(fruit.getID());
        if (index < 0) {
            return super.add(fruit);
        } else {
            this.get(index).setQuantity(this.get(index).getQuantity() + fruit.getQuantity());
            return true;
        }
    }

}
