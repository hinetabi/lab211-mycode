/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.BankController;
import controller.IOHelper;
import java.io.IOException;

/**
 *
 * @author Legion
 */
public class View {

    private final String[] ops;
    private final String[] createOps;
    private final BankController controller;

    public View() {
        controller = new BankController();
        this.ops = new String[]{"Create new account", "Login function",
            "Withdrawal function",
            "Deposit function",
            "Transfer money",
            "Remove account",
            "Quit"};
        this.createOps = new String[]{"Create a fully new account.",
            "Create a new username base on existed id"};
    }

    public void loadData() {
        try {
            controller.loadAuth("src\\database\\user.dat");
            controller.loadInfo("src\\database\\bank.dat");
        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Load fail!");
            e.printStackTrace();
            System.err.println("File error!");
        }
    }

    // get password from keyboard
    public String enterPassword() {
        // enter password (must accept condition of a password)
        String password = validation.Inputter.inputPassword("Enter password: ");
        boolean isReenterPassword;

        // check confirm password
        String confirmPassword;
        while (true) {
            // enter confirm pass word
            confirmPassword = validation.Inputter.inputString("Enter confirm password: ", false);
            // return if equals
            if (confirmPassword.equals(password)) {
                return password;
            } else {
                System.err.println("Confirm password wrong!");
                // if want to change password (or re-enter password) => true
                isReenterPassword = validation.Inputter.
                        inputYesNo("Do you want to re-enter password? (Y/N)", "Y", "N");
            }
            // if user want to re-enter password
            if (isReenterPassword) {
                return enterPassword();
            } else {
                System.out.println("Please enter confirm password again!");
            }
        }
    }

    // create a new account;
    public void createANewAccount() {
        if (controller.isLogin()) {
            System.err.println("You need to log out!");
            return;
        }

        String id;
        // enter id (must unique)

        id = validation.Inputter.inputStringInForm("Enter Id (must unique): ", "[0-9]{6}");
        if (controller.isIdInList(id)) {
            System.err.println("ID has already existed in list!");
            return;
        }

        // enter name
        String name = validation.Inputter.inputString("Enter account name: ", false);

        // enter password
        String password = enterPassword();

        // create into database
        if (controller.createNewAccount(id, name, password)) {
            System.out.println("Add Successfully!");
        } else {
            System.out.println("Can n");
        }

        //oke thi luu vao cả 2 file user.dat và bank.dat
        try {
            //user = id + pass
            this.controller.saveAuth("src\\database\\user.dat");
            //bank = id + name + money
            this.controller.saveInfo("src\\database\\bank.dat");
            System.out.println("Saved!");
        } catch (IOException e) {
            System.err.println("File error!");
        }
    }

    // login
    public boolean login() {
        if (controller.isLogin()) {
            System.err.println("You need to log out before login!");
            return false;
        }
        String id = validation.Inputter.inputString("Enter id: ", false);
        String password = validation.Inputter.inputString("Enter password: ", false);
        if (IOHelper.SHA256(password).equals(controller.getPassword(id))) {
            controller.login(id);
            return true;
        } else {
            System.err.println("Password wrong!");
            return false;
        }
    }

    // widthdraw money (thêm tính năng rút được tiền ra từ nhiều tài khoản khác nhau!)
    public void widthdrawMoney() {
        if (!controller.isLogin()) {
            System.err.println("You need to login to continue!");
            return;
        }
        
        // enter money to withdraw
        double money = validation.Inputter.inputDouble("Enter money to widthdraw: ", false);
        try {
            if (controller.withdraw(money)) {
                System.out.println("Successfully!");
            }
            // save info
            controller.saveInfo("src\\database\\bank.dat");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // deposit into an account
    public void deposit() {
        if (!controller.isLogin()) {
            System.err.println("You need to login");
            return;
        }
        
        // enter money to deposit
        double money = validation.Inputter.inputDouble("Enter money to deposit: ", false);
        // confirm message
        boolean confirm = validation.Inputter.inputYesNo("You want to deposit " + money + "! (Yes/No) ", "Yes", "No");

        if (!confirm) {
            return;
        }

        try {
            // deposit
            if (controller.deposit(money)) {
                System.out.println("Deposit successfully!");
            }

            // save info
            controller.saveInfo("src\\database\\bank.dat");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // transfer money
    public void transfer() {
        if (!controller.isLogin()) {
            System.err.println("You need to login");
            return;
        }
        
        // enter id:
        double money = validation.Inputter.inputDouble("Enter money to transfer: ", false);

        // enter id
        String id = validation.Inputter.inputString("Enter recipient account id: ", false);
        System.out.println("Your recipient name: " + controller.getAccountById(id).getName());
        boolean confirm = validation.Inputter.inputYesNo("You want to transfer " + money + " to: " + controller.getAccountById(id).getName(), "Yes", "No");
        // check if id in list
        if (controller.isIdInList(id) && confirm) {
            try {
                //transfer money
                controller.transfer(id, money);
                // save info
                controller.saveInfo("src\\database\\bank.dat");
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.err.println("Recipient account not exist!");
        }

    }

    public void remove() {
        if (!controller.isLogin()) {
            System.err.println("You need to login");
            return;
        }

        // choose account name
        boolean confirm = validation.Inputter.inputYesNo("Do you want to remove this account? (Yes/No) ", "Yes", "No");
        if (confirm) {
            try {
                // remove account
                if (controller.removeAccount()) {
                    System.out.println("Remove successfully!");
                    controller.logout();
                }

                // save info
                controller.saveInfo("src\\database\\bank.dat");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }

    }

    public void start() {
        int choice;
        loadData();

        do {
            System.out.println("=====THE SECRET BANK=====");
            choice = GetChoice.getchoice(ops);
            switch (choice) {

                case 1: {
                    System.out.println("======CREATE=======");
                    createANewAccount();
                    break;
                }

                case 2: {
                    System.out.println("======LOGIN======");
                    if (login()) {
                        System.out.println("Login successfully");
                    }
                    break;
                }

                case 3: {
                    System.out.println("======WITHDRAW======");
                    widthdrawMoney();
                    break;
                }

                case 4: {
                    System.out.println("======DEPOSIT======");
                    deposit();
                    break;
                }

                case 5: {
                    System.out.println("======TRANSFER======");
                    transfer();
                    break;
                }

                case 6: {
                    System.out.println("======REMOVE======");
                    remove();
                    break;
                }
            }
            System.out.println("=============================");
        } while (choice < ops.length);
    }

}
