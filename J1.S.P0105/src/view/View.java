/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProductController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import validation.Inputter;

/**
 *
 * @author Legion
 */
public class View {

    ProductController manager;
    private final String[] ops;
    private final String[] searchOps;
    private final String[] sortOps;

    public View() {
        manager = new ProductController();
        ops = new String[]{"Add Storekeeper.",
            "Add product.",
            "Update product.",
            "Search product by Name, Category, Storekeeper, ReceiptDate.",
            "Sort product by Expiry date, Date of manufacture.",
            "Exit."};

        searchOps = new String[]{"Search by Name", "Search by Category",
            "Search by Store Keeper", "Search by Receipt Date"};

        sortOps = new String[]{"Sort by Expiry Date", "Sort By Date of Manufacture"};
    }

    public void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        manager.addStoreKeeper("Binh");
        manager.addStoreKeeper("Chien");
        manager.addStoreKeeper("Dang");

        try {
            manager.addANewProduct(1, "Milk", "Vinh Phuc", 10, simpleDateFormat.parse("12-02-2025"), simpleDateFormat.parse("10-02-2021"), "no1", "Binh",
                    simpleDateFormat.parse("12-02-2021"));
            manager.addANewProduct(2, "Gao", "Ha Noi", 12, simpleDateFormat.parse("12-04-2025"), simpleDateFormat.parse("12-03-2021"),
                    "no2", "Chien", simpleDateFormat.parse("14-03-2021"));
            manager.addANewProduct(3, "Che", "Thai Nguyen", 20, simpleDateFormat.parse("12-05-2025"), simpleDateFormat.parse("20-02-2020"),
                    "no1", "Chien", simpleDateFormat.parse("12-05-2020"));
            manager.addANewProduct(4, "Kem", "Ha Nam", 30, simpleDateFormat.parse("12-01-2025"), simpleDateFormat.parse("12-02-2022"),
                    "no2", "Dang", simpleDateFormat.parse("12-05-2022"));
        } catch (ParseException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // add a new store keeper
    public void addNewStoreKeeper() {
        //enter name of store keeper
        String name = Inputter.inputStringInForm("Enter Name: ", "[a-zA-Z ]+");
        if (manager.addStoreKeeper(name)) {
            System.out.println("Add successfully!");
        } else {
            System.out.println("Store Keeper already in list!");
        }
    }

    // add a new product
    public void addNewProduct() {
        int id = Inputter.inputInteger("Enter Id (not duplicate): ", false, false);
        String name = Inputter.inputStringInForm("Enter Name: ", "[a-zA-Z ]+");
        String location = Inputter.inputStringInForm("Enter Location: ", "[a-zA-Z ]+");
        double price = Inputter.inputDouble("Enter price: ", false);
        Date expiryDate = Inputter.getDate("Enter expiry date (dd-mm-yyyy): ", false);
        Date dateOfManufacture = Inputter.getDate("Enter date of manufacture (dd-mm-yyyy): ", false);
        String category = Inputter.inputStringInForm("Enter Category:: ", "[0-9a-zA-Z ]+");
        String storeKeeper = Inputter.inputStringInForm("Enter Store Keeper: ", "[a-zA-Z ]+");
        Date receiptDate = Inputter.getDate("Enter receipt date (dd-mm-yyyy): ", false);

        if (manager.addANewProduct(id, name, location, price, expiryDate,
                dateOfManufacture, category, storeKeeper, receiptDate)) {
            System.out.println("Add successfully!");
        } else {
            System.out.println("Can not add!");
        }
    }

    // print the product 
    public void showTheProduct(ArrayList arr) {
        if (arr.isEmpty()) {
            System.out.println("List empty!");
            return;
        }
        System.out.printf("%-2s|%-10s|%-13s|%-5s|%-10s|%-20s|%-15s|%-15s|%-10s\n", "ID", "Name", "Location", "Price",
                "Expiry Date", "Date Of Manufacture", "Category", "Store Keeper", "Receipt Date");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).toString());
        }
    }

    //update product
    public void updateProduct() {
        
        int id = Inputter.inputInteger("Enter Id (not duplicate): ", false, false);
        
        if (manager.isIdInList(id)) {
            System.out.println("Enter if not want to update!");
            String name = Inputter.inputString("Enter Name: ", true);
            String location = Inputter.inputString("Enter Location: ", true);
            double price = Inputter.inputDouble("Enter price: ", true);
            Date expiryDate = Inputter.getDate("Enter expiry date (dd-mm-yyyy): ", true);
            Date dateOfManufacture = Inputter.getDate("Enter date of manufacture (dd-mm-yyyy): ", true);
            String category = Inputter.inputString("Enter Category:: ", true);
            String storeKeeper = Inputter.inputString("Enter Store Keeper: ", true);
            Date receiptDate = Inputter.getDate("Enter receipt date (dd-mm-yyyy): ", true);

            if (manager.updateProductById(id, name, location, price, expiryDate,
                    dateOfManufacture, category, storeKeeper, receiptDate)) {
                System.out.println("Update successfully!");
            } else {
                System.out.println("Can not update!");
            }
        } else {
            System.out.println("ID not in list!");
        }

    }

    //search product
    public void searchProduct() {
        int choice = GetChoice.getchoice(searchOps);
        switch (choice) {
            case 1: {
                String name = Inputter.inputString("Enter name: ", false);
                ArrayList arr = manager.searchProductByName(name);
                showTheProduct(arr);
                break;
            }

            case 2: {
                String category = Inputter.inputString("Enter name: ", false);
                ArrayList arr = manager.searchProductByCategory(category);
                showTheProduct(arr);
                break;
            }

            case 3: {
                String storeKeeper = Inputter.inputString("Enter name: ", false);
                ArrayList arr = manager.searchProductByStoreKeeper(storeKeeper);
                showTheProduct(arr);
                break;
            }

            case 4: {
                Date receiptDate = Inputter.getDate("Enter reiptdate", false);
                ArrayList arr = manager.searchProductByReceiptDate(receiptDate);
                showTheProduct(arr);
                break;
            }
        }
    }

    //sort
    public void sortProduct() {
        int choice = GetChoice.getchoice(sortOps);
        switch (choice) {
            case 1: {
                manager.sortProductByExpiryDate();
                break;
            }

            case 2: {
                manager.sortProductByDateOfManufacture();
                break;
            }
        }
    }

    public void start() {
        initData();
        int choice;
        do {
            System.out.println("=======MAIN MENU=======");
            choice = GetChoice.getchoice(ops);
            switch (choice) {
                case 1: {
                    System.out.println("====ADD NEW STORE KEEPER====");
                    addNewStoreKeeper();
                    break;
                }

                case 2: {
                    System.out.println("=====ADD NEW PRODUCT=====");
                    addNewProduct();
                    break;
                }

                case 3: {
                    System.out.println("====UPDATE PRODUCT=====");
                    updateProduct();
                    break;
                }

                case 4: {
                    System.out.println("====SEARCH PRODUCT=====");
                    searchProduct();
                    break;
                }

                case 5: {
                    System.out.println("====SORT PRODUCT====");
                    sortProduct();
                    showTheProduct(manager.getProductList());
                    break;
                }
            }
        } while (choice < ops.length && choice > 0);
    }

}
