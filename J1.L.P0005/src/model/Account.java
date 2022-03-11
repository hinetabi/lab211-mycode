/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Legion
 */
public class Account  implements Serializable{
    private String id;
    private String name;
    private double money;
    private String password;

    public Account() {
        id = "";
        name = "";
        money = 0;
    }

    public Account(String id, String name, double money) {
        this(id, name);
        this.money = money;
    }

    public Account(String id, String name, double money, String password) {
        this(id, name, money);
        this.password = password;
    }

    public Account(String id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
