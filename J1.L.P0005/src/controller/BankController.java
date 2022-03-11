/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Account;

/**
 *
 * @author Legion
 */
public class BankController {

    private ArrayList<Account> accountInfo;  // bank.dat : account (id, name, money) (using list because an id may have many account)
    private HashMap<String, String> accountAuth;   // user.dat : id, password 
    private boolean login;
    private String currentIdLogin;

    public BankController() {
        this.accountInfo = new ArrayList<>();
        this.accountAuth = new HashMap<>();
        this.login = false;
        this.currentIdLogin = null;
    }

    public BankController(ArrayList<Account> info, HashMap<String, String> auth, boolean login, String currentIdLogin) {
        this.accountInfo = info;
        this.accountAuth = auth;
        this.login = login;
        this.currentIdLogin = currentIdLogin;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getCurrentLogin() {
        return currentIdLogin;
    }

    public void setCurrentLogin(String currentLogin) {
        this.currentIdLogin = currentLogin;
    }

    // load file to auth
    public void loadAuth(String fileName) throws IOException, FileNotFoundException, ClassNotFoundException {
        accountAuth = (HashMap<String, String>) IOHelper.loadObject(fileName);
    }

    // load file to info
    public void loadInfo(String fileName) throws IOException, FileNotFoundException, ClassNotFoundException {
        accountInfo = (ArrayList<Account>) IOHelper.loadObject(fileName);
    }

    // save auth to file
    public void saveAuth(String fileName) throws IOException {
        IOHelper.saveObject(fileName, accountAuth);
    }

    // save info to file
    public void saveInfo(String fileName) throws IOException {
        IOHelper.saveObject(fileName, accountInfo);
    }

    //create fully new account
    public boolean createNewAccount(String id, String name, String password) {
        //doc file xem co id nao nhu the chua, neu co roi thi return false
        if (isIdInList(id)) {
            return false;
        }

        // add new account
        this.accountInfo.add(new Account(id, validation.Inputter.beautyName(name)));
        this.accountAuth.put(id, IOHelper.SHA256(password));

        return true;
    }

    // create another account for an id
    public boolean createNewAccountForExistedID(String id, String name) {

        if (!isIdInList(id)) {
            return false;
        }

        // name must be unique between 2 account with same id, otherwise return false
        for (int i = 0; i < accountInfo.size(); i++) {
            if (accountInfo.get(i).getId().equals(id)
                    && accountInfo.get(i).getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        this.accountInfo.add(new Account(id, validation.Inputter.beautyName(name)));
        return true;
    }

    //login
    public boolean login(String id) {
        if (isLogin()) {
            return false;
        }
        //set login = true
        login = true;

        // current id login = account id is logined
        this.currentIdLogin = id;

        return true;
    }

    //withdraw money of account at name = name
    public boolean withdraw(double money) throws Exception {
        if (!isLogin()) {
            throw new Exception("You need login!");
        }
        // find account with id and name!
        int index = getAccountOfCurrentId();
        if (index < 0) {
            throw new Exception("Can not find account!");
        }
        if (accountInfo.get(index).getMoney() > money) {
            accountInfo.get(index).setMoney(accountInfo.get(index).getMoney() - money);
            return true;
        } else {
            throw new Exception("You don't have enough money!");
        }
    }

    //deposit
    public boolean deposit(double money) throws Exception {
        if (!isLogin()) {
            throw new Exception("You need login!");
        }

        // find account with id and name!
        int index = getAccountOfCurrentId();
        if (index < 0) {
            throw new Exception("Can not find account!");
        }
        
        // update deposit
        this.accountInfo.get(index).setMoney(this.accountInfo.get(index).getMoney() + money);
        
        //deposit
        return true;
    }

    //tranfer money
    public boolean transfer(String recipentId, double money) throws Exception {
        if (!isLogin()) {
            throw new Exception("You need login!");
        }
        
        int index = getAccountOfCurrentId();
        if (index < 0) {
            throw new Exception("Can not find account!"); 
        }
        
        // if not have enough money
        if (accountInfo.get(index).getMoney() < money) {
            throw new Exception("Can not enough money!");
        }
        
        // find recipent index 
        int recipentIndex = -1;
        for (int i = 0; i < accountInfo.size(); i++) {
            if (accountInfo.get(i).getId().equals(recipentId)) {
                recipentIndex = i;
                break;
            }
        }
        
        // if not have recipent
        if (recipentIndex < 0) {
            throw new Exception("Can not find recipent account!"); 
        }
        
        // update
        accountInfo.get(index).setMoney(accountInfo.get(index).getMoney() - money);
        accountInfo.get(recipentIndex).setMoney(accountInfo.get(recipentIndex).getMoney() + money);
        
        return true;
    }

    // check if id valid in list
    public boolean isIdInList(String id) {
        return accountAuth.containsKey(id);
    }

    // get password from id
    public String getPassword(String id) {
        if (!isIdInList(id)) {
            return "";
        }
        return accountAuth.get(id);
    }

    // get index of account
    public int getAccountOfCurrentId() {
        for (int i = 0; i < accountInfo.size(); i++) {
            if (accountInfo.get(i).getId().equals(currentIdLogin)) {
                return i;
            }
        }
        return -1;
    }

    // remove account
    public boolean removeAccount() throws Exception {
        if (!isLogin()) {
            throw new Exception("You need login!");
        }
        // get index of account to remove
        int index = getAccountOfCurrentId();
        if (index < 0) {
            throw new Exception("Can not find account!");
        } else {
            this.accountInfo.remove(index);
            this.accountAuth.remove(currentIdLogin);
            return true;
        }
    }

    // logout
    public void logout() {
        login = false;
        currentIdLogin = null;
    }

    // get list of account
    public Account getAccountById(String id) {
        for (int i = 0; i < accountInfo.size(); i++) {
            if (accountInfo.get(i).getId().equals(id)) {
                return accountInfo.get(i);
            }
        }
        return null;
    }
}
