/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Legion
 */
public class Fruit {
    private int ID;
    private String name;
    private double price;
    private String origin;
    private int quantity;

    public Fruit() {
        ID = 0;
        name = "";
        price = 0;
        quantity = 0;
        origin = "";
    }

    public Fruit(int ID, String name, double price, String origin) {
        this();
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.origin = origin;
    }

    public Fruit(int ID, String name, double price, String origin, int quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.quantity = quantity;
    }
    
    public Fruit(Fruit f, int quantity) {
        this.ID = f.getID();
        this.name = f.getName();
        this.price = f.getPrice();
        this.origin = f.getOrigin();
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    
}
