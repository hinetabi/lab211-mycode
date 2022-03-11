/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import model.Product;
import model.StoreKeeper;
import validation.Inputter;

/**
 *
 * @author Legion
 */
public class ProductController {

    private ArrayList<StoreKeeper> storeKeeperList;
    private ArrayList<Product> productList;

    public ProductController() {
        productList = new ArrayList<>();
        storeKeeperList = new ArrayList<>();
    }

    public ProductController(ArrayList<StoreKeeper> storeKeeperList, ArrayList<Product> productList) {
        this.storeKeeperList = storeKeeperList;
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<StoreKeeper> getStoreKeeperList() {
        return storeKeeperList;
    }

    public void setStoreKeeperList(ArrayList<StoreKeeper> storeKeeperList) {
        this.storeKeeperList = storeKeeperList;
    }

    // add a new store keeper
    public boolean addStoreKeeper(String name) {
        if (isStoreKeeperInList(name)) {
            return false;
        }
        storeKeeperList.add(new StoreKeeper(validation.Inputter.beautyName(name)));
        return true;
    }

    // add a new Product
    public boolean addANewProduct(int Id, String name, String location, double price,
            Date expiryDate,
            Date dateOfManufacture, String category, String storeKeeperName, Date receiptDate) {

        // check if Id is in list!
        if (isIdInList(Id)) {
            return false;
        }

        // date of manufacture must be before receipt date, else return false
        if (dateOfManufacture.compareTo(receiptDate) > 0) {
            return false;
        }

        // date of expiry date must > manufacture date
        if (expiryDate.compareTo(dateOfManufacture) < 0) {
            return false;
        }

        // store keeper must be in list
        if (!isStoreKeeperInList(storeKeeperName)) {
            return false;
        }

        // format name
        name = Inputter.beautyName(name);
        location = Inputter.beautyName(location);
        category = Inputter.beautyName(category);

        // add a new product
        this.productList.add(new Product(Id, name, location, price, expiryDate,
                dateOfManufacture, category, new StoreKeeper(storeKeeperName), receiptDate));

        return true;
    }

    /**
     * check if store keeper in list!
     *
     * @param name
     * @return true/false
     */
    public boolean isStoreKeeperInList(String name) {
        for (StoreKeeper storeKeeper : storeKeeperList) {
            if (storeKeeper.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if Id is in list!
     *
     * @param id
     * @return true/false
     */
    public boolean isIdInList(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * get index of product with ID = id
     *
     * @param id
     * @return
     */
    public int indexOfId(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // update a product by Id (store keeper name is update -> update in the store keeper list)
    public boolean updateProductById(int Id, String name, String location, double price,
            Date expiryDate, Date dateOfManufacture, String category,
            String storeKeeperName, Date receiptDate) {
        // check if Id is in list!
        int index = indexOfId(Id);
        if (index < 0) {
            return false;
        }

        if (!name.isEmpty()) {
            this.productList.get(index).setName(name);
        }

        if (!location.isEmpty()) {
            this.productList.get(index).setLocation(location);
        }

        if (!category.isEmpty()) {
            this.productList.get(index).setCategory(category);
        }

        if (!storeKeeperName.isEmpty()) {
            String stkeeperName = this.productList.get(index).getStoreKeeper().getName();
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getStoreKeeper().getName().equalsIgnoreCase(stkeeperName)) {
                    this.productList.get(i).setStoreKeeper(new StoreKeeper(storeKeeperName));
                }
            }
            this.productList.get(index).setStoreKeeper(new StoreKeeper(storeKeeperName));
        }

        if (price != 0) {
            this.productList.get(index).setPrice(price);
        }

        if (expiryDate != null) {
            this.productList.get(index).setExpiryDate(expiryDate);
        }

        // recript date must > date of manufactor, date of manufactor
        if (dateOfManufacture != null && dateOfManufacture.before(this.productList.get(index).getExpiryDate())
                && dateOfManufacture.before(this.productList.get(index).getReceiptDate())) {
            this.productList.get(index).setDateOfManufacture(dateOfManufacture);
        }

        // recript date must > date of manufactor
        if (receiptDate != null && receiptDate.after(dateOfManufacture)) {
            this.productList.get(index).setReceiptDate(receiptDate);
        }

        return true;
    }

    // search product by name
    public ArrayList<Product> searchProductByName(String name) {
        ArrayList<Product> arrayList = new ArrayList<>();

        // if product has Name = name, add to array list
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                arrayList.add(product);
            }
        }
        return arrayList;
    }

    // search product by category
    public ArrayList<Product> searchProductByCategory(String category) {
        ArrayList<Product> arrayList = new ArrayList<>();

        // if product has Category = name, add to array list
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                arrayList.add(product);
            }
        }
        return arrayList;
    }

    // search product by store keeper
    public ArrayList<Product> searchProductByStoreKeeper(String storeKeeperName) {
        ArrayList<Product> arrayList = new ArrayList<>();

        // if product has Name = name, add to array list
        for (Product product : productList) {
            if (product.getStoreKeeper().getName().toLowerCase()
                    .contains(storeKeeperName.toLowerCase())) {
                arrayList.add(product);
            }
        }
        return arrayList;
    }

    // search product by receipt date
    public ArrayList<Product> searchProductByReceiptDate(Date receiptDate) {
        ArrayList<Product> arrayList = new ArrayList<>();

        // if product has Name = name, add to array list
        for (Product product : productList) {
            if (product.getReceiptDate().equals(receiptDate)) {
                arrayList.add(product);
            }
        }
        return arrayList;
    }

    // sort product
    public void sortProductByExpiryDate() {
        Collections.sort(productList,
                (Product t, Product t1) -> t.getExpiryDate().compareTo(t1.getExpiryDate()));
    }

    // sort product
    public void sortProductByDateOfManufacture() {
        Collections.sort(productList,
                (Product t, Product t1) -> t.getDateOfManufacture().compareTo(t1.getDateOfManufacture()));
    }
}
