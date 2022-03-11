/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collections;
import model.FruitList;
import java.util.Hashtable;
import model.Fruit;
import model.Order;

/**
 *
 * @author Legion
 */
public class OrderController {

    Hashtable<String, FruitList> orderTable;

    public OrderController() {
        orderTable = new Hashtable<>();
    }

    public OrderController(Hashtable<String, FruitList> orderTable) {
        this.orderTable = orderTable;
    }

    public Hashtable<String, FruitList> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(Hashtable<String, FruitList> orderTable) {
        this.orderTable = orderTable;
    }

    //add a new order (if name is exist, and if name is not exist)
    public void addNewOrder(Order order) {
        //name = name of order
        String name = order.getName() + "#" + orderTable.size();

        //fruits = fruit list of order
        FruitList fruits = order.getFruitList();
        // put new
        orderTable.put(name, fruits);

    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return orderTable.isEmpty();
    }

}
