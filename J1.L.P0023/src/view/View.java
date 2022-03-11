/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.FruitList;
import controller.ShopManager;
import java.util.Enumeration;
import model.Fruit;
import validation.Inputter;

/**
 *
 * @author Legion
 */
public class View {

    private final String[] ops;
    private ShopManager shopManager;

    public View() {
        this.ops = new String[]{"Create Fruit", "View Orders", "Shopping (for buyer)", "Exit"};
        shopManager = new ShopManager();
    }

    public ShopManager getShopManager() {
        return shopManager;
    }

    public void setShopManager(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    //init data for shop
    public void init() {
        shopManager.createFruit(1, "Coconut", 2, 10, "Vietnam");
        shopManager.createFruit(2, "Orange", 3, 10, "US");
        shopManager.createFruit(3, "apple", 4, 10, "Thailand");
        shopManager.createFruit(4, "grape", 6, 10, "France");
        shopManager.createFruit(5, "Mango", 2, 10, "Vietnam");
    }

    /**
     * show list of fruit in shop manager except fruits which has quantity = 0
     */
    public void showListOfFruit() {
        FruitList fruits = shopManager.getFruitList();
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getQuantity() > 0) {
                System.out.printf("\t%-10s%-20s%-15s%-10.0f\n", fruits.get(i).getID(), fruits.get(i).getName(), fruits.get(i).getOrigin(), fruits.get(i).getPrice());
            }
        }
    }

    /**
     * show total of a fruit list
     *
     * @param fruitList
     */
    public void showTotalOfAFruitList(FruitList fruitList) {
        System.out.println(" Product | Quantity | Price | Amount ");
        double sum = 0;
        int dem = 0;
        for (Fruit fruit : fruitList) {
            dem++;
            double price = fruit.getQuantity() * fruit.getPrice();
            sum += price;
            System.out.println(String.format(dem + ".%-12s%-9s%s$    %s$", fruit.getName(), fruit.getQuantity(), fruit.getPrice(), price));
        }
        System.out.printf("Total: %.0f$\n", sum);
    }

    /**
     * create a new fruit
     */
    public void create() {
        int ID;

        //ID must be unique and positive
        do {
            ID = Inputter.inputInteger("Enter ID (integer): ", false, false);
            if (shopManager.getFruitList().isIdExistInList(ID)) {
                System.err.println("Please enter other ID!");
            } else {
                break;
            }
        } while (true);

        //Name can not contains digits
        String name = Inputter.inputStringInForm("Enter name: ", "[a-zA-z ]+");

        //Origin can not contains digits
        String origin = Inputter.inputStringInForm("Enter origin: ", "[a-zA-z ]+", true);

        //Price must positive
        double price = Inputter.inputDouble("Enter price: ", false);

        //quantity must positive
        int quantity = Inputter.inputInteger("Enter quantity: ", false, false);

        if (this.shopManager.createFruit(ID, name, price, quantity, origin)) {
            System.out.println("Created!");
        };
    }

    /**
     * view orders of all people
     */
    public void viewOrders() {
        if (shopManager.getOrderController().isEmpty()) {
            System.out.println("List order empty!");
            return;
        }
        
        Enumeration names;
        names = shopManager.getOrderController().getOrderTable().keys();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println("Customer: " + name.substring(0, name.indexOf("#")));
            showTotalOfAFruitList(shopManager.getOrderController().getOrderTable().get(name));
            System.out.println();
        }
        System.out.println("Show successfully!");
    }

    /**
     * After customer inputs id and quantity of fruit, the program shows
     * message: Do you want to order now (Y/N). If customer selects N, the
     * program returns to List of Fruit to continue ordering. If select Y, the
     * program displays by Quantity descending order.
     *
     * Last, Customer inputs his/her name to finish ordering.
     */
    public void shopping() {
        if (shopManager.isEmpty()) {
            System.out.println("Fruits list in shop is empty!");
        }

        //create a new list for order
        FruitList orderList = new FruitList();
        do {
            int choice;

            //choose a fruit from fruit shop list
            while (true) {
                choice = GetChoice.getFruitChoice(shopManager.getFruitList());
                if (shopManager.getFruitList().isIdExistInList(choice) && shopManager.getQuantity(choice) > 0) {
                    break;
                } else {
                    System.err.println("Please choose a fruit in list!");
                }
            }

            //get fruit
            Fruit fruit = shopManager.getFruitById(choice);

            System.out.println("You selected: " + fruit.getName());
            int quantity;

            //enter quantity (must > 0 and <= max quantity)
            do {
                quantity = Inputter.inputInteger("Enter quantity: ", false, true);
                if (quantity > fruit.getQuantity()) {
                    System.err.printf("Error! Current %s quantity in storage is: %d\n", fruit.getName(), fruit.getQuantity());
                    System.out.println("Leave blank if you do not want to order this!");
                } else {
                    break;
                }
            } while (true);

            //add fruit to list of customers
            if (quantity > 0) {
                fruit.setQuantity(fruit.getQuantity() - quantity);
                Fruit temp = new Fruit(fruit, quantity);
                orderList.add(temp);
            }
        } while (!Inputter.inputYesNo("Do you want to order now (Y/N)? "));

        //show total
        this.showTotalOfAFruitList(orderList);

        //input name
        String name = Inputter.inputStringInForm("Enter your name: ", "[a-zA-Z ]+");

        //add order
        shopManager.order(name, orderList);
        System.out.println("Order successfully!");
    }

    /**
     *
     */
    public void start() {

        int choice;
        init();
        do {
            System.out.println("\n=======FRUIT SHOP========");
            choice = GetChoice.getchoice(ops);
            switch (choice) {

                case 1: {
                    System.out.println("=======CREATE NEW=======");
                    create();
                    break;
                }

                case 2: {
                    System.out.println("=======VIEW ORDERS=======");
                    viewOrders();
                    break;
                }

                case 3: {
                    System.out.println("========SHOPPING=========");
                    shopping();
                    break;
                }

            }

        } while (choice < ops.length);
    }
}
