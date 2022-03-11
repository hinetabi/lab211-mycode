/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class Product {
    private int Id;
    private String name;
    private String location;
    private double price;
    private Date expiryDate;
    private Date dateOfManufacture;
    private String category;
    private StoreKeeper storeKeeper;
    private Date receiptDate;

    public Product() {
    }

    public Product(int Id, String name, String location, double price, Date expiryDate, Date dateOfManufacture, String category, StoreKeeper storeKeeper, Date receiptDate) {
        this.Id = Id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.expiryDate = expiryDate;
        this.dateOfManufacture = dateOfManufacture;
        this.category = category;
        this.storeKeeper = storeKeeper;
        this.receiptDate = receiptDate;
    }
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StoreKeeper getStoreKeeper() {
        return storeKeeper;
    }

    public void setStoreKeeper(StoreKeeper storeKeeper) {
        this.storeKeeper = storeKeeper;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%-2d|%-10s|%-13s|%-5.0f|%-10s|%-20s|%-15s|%-15s|%-10s", 
                Id, name, location, price, simpleDateFormat.format(expiryDate), simpleDateFormat.format(dateOfManufacture), 
                category, storeKeeper.getName(), simpleDateFormat.format(receiptDate));
        //return "Product{" + "" + Id + ", name=" + name + ", location=" + location + ", price=" + price + ", expiryDate=" + expiryDate + ", dateOfManufacture=" + dateOfManufacture + ", category=" + category + ", storeKeeper=" + storeKeeper + ", receiptDate=" + receiptDate + '}';
    }
    
    
}
