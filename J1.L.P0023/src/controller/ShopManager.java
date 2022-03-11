/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Fruit;
import model.FruitList;
import model.Order;

/**
 *
 * @author Legion
 */
public class ShopManager {

    private FruitList shopFruitList;
    private OrderController orderController;

    /**
     *
     */
    public ShopManager() {
        this.shopFruitList = new FruitList();
        orderController = new OrderController();
    }

    /**
     *
     * @param shopFruitList
     */
    public ShopManager(FruitList shopFruitList) {
        this();
        this.shopFruitList = shopFruitList;
    }

    /**
     *
     * @param shopFruitList
     * @param orderTable
     */
    public ShopManager(FruitList shopFruitList, OrderController orderTable) {
        this.shopFruitList = shopFruitList;
        this.orderController = orderTable;
    }

    /**
     *
     * @return
     */
    public FruitList getFruitList() {
        return shopFruitList;
    }

    /**
     *
     * @param shopFruitList
     */
    public void setShopFruitList(FruitList shopFruitList) {
        this.shopFruitList = shopFruitList;
    }

    /**
     *
     * @return
     */
    public OrderController getOrderController() {
        return orderController;
    }

    /**
     *
     * @param orderController
     */
    public void setOrderTable(OrderController orderController) {
        this.orderController = orderController;
    }

    /**
     * create a new fruit check id (must be unique)
     *
     * @param ID
     * @param name
     * @param price
     * @param quantity
     * @param origin
     * @return
     */
    public boolean createFruit(int ID, String name, double price, int quantity, String origin) {
        name = validation.Inputter.beautyName(name);
        origin = validation.Inputter.beautyName(origin);

        // nếu exist -> return false
        if (this.shopFruitList.isIdExistInList(ID)) {
            return false;
        }

        this.shopFruitList.add(new Fruit(ID, name, price, origin, quantity));
        return true;
    }

    /**
     * add order to database (check if same name, then they can continue order )
     *
     * @param name
     * @param fruits
     */
    public void order(String name, FruitList fruits) {
        // format name
        name = validation.Inputter.beautyName(name);
        orderController.addNewOrder(new Order(name, fruits));
        // if name has already existed
        
    }

    /**
     * get quantity of a fruit in shop by its ID
     *
     * @param id
     * @return quantity of fruit
     */
    public int getQuantity(int id) {
        return shopFruitList.get(shopFruitList.findFruitById(id)).getQuantity();
    }

    /**
     * check if the fruit list is empty
     *
     * @return true if shop list is empty
     */
    public boolean isEmpty() {
        for (Fruit fruit : shopFruitList) {
            if (fruit.getQuantity() > 0) {
                return false;
            }
        }
        return shopFruitList.isEmpty();
    }

    /**
     *
     * @param ID
     * @return
     */
    public Fruit getFruitById(int ID) {
        int index = shopFruitList.findFruitById(ID);
        if (index >= 0) {
            return shopFruitList.get(index);
        } else {
            return null;
        }

    }
}
