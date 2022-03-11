package model;


/**
 * Class order is all the order of an customer
 * @author Legion
 */
public class Order {
    private FruitList fruitList;
    private String name;

    public Order() {
    }
    
    public Order(String name) {
        this.name = name;
        fruitList = new FruitList();
    }

    public Order(String name, FruitList fruitList) {
        this.fruitList = fruitList;
        this.name = name;
    }

    public FruitList getFruitList() {
        return fruitList;
    }

    public void setFruitList(FruitList fruitList) {
        this.fruitList = fruitList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * calculate the total price
     * @return 
     */
    public int getTotalPrice() {
        int result = 0;
        for (Fruit fruit : fruitList) {
            result += fruit.getPrice() * fruit.getQuantity();
        }
        return result;
    }
    
    
}
