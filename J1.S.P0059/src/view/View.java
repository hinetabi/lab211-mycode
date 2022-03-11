/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PersonController;
import java.util.ArrayList;
import model.Person;

/**
 *
 * @author Legion
 */
public class View {

    final String[] ops;
    PersonController controller;

    public View() {
        this.ops = new String[]{"Find person info", "Copy text to new file", "Exit"};
        controller = new PersonController();
    }

    public int getchoice() {
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        System.out.println();
        return Inputter.inputIntegerInRange("Enter your choice(1-" + ops.length + "): ", 1, ops.length);
    }

    public void start() {
        int choice;
        do {
            System.out.println("-----File Processing-----");
            choice = getchoice();
            switch (choice) {
                case 1:
                    findPersonInfo();
                    break;
                case 2:
                    CopyToNewFile();
                    break;
            }

        } while (choice < ops.length);
    }

    // file person info
    private void findPersonInfo() {
        String path = Inputter.inputString("Enter path: ", false);
        int money = Inputter.inputInteger("Enter Money: ", false, false);
        try {
            ArrayList<Person> list = controller.getPersonList(path, money);
            display(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    // copy to new file
    private void CopyToNewFile() {
        String input = Inputter.inputString("Enter Source: ", false);
        String output = Inputter.inputString("Enter new File Name: ", false);
        try {
            controller.copyTextToNewFile(input, output);
            System.out.println("Copy done...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    
    public void display(ArrayList<Person> list) {
        System.out.println("------Result------");
        System.out.printf("%-15s%-15s%s\n", "Name", "Addess", "Money");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-15s%-15s%d\n", list.get(i).getName(), list.get(i).getAddress(), list.get(i).getMoney());
        }
        System.out.println("");
        System.out.println("Max: " + list.get(list.size() - 1).getName()) ;
        System.out.println("Min: " + list.get(0).getName());
    }

}
