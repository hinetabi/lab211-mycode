/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Person;

/**
 *
 * @author Legion
 */
public class PersonController {
    
    // get person list in file : path, and have money > money
    public ArrayList<Person> getPersonList(String path, int money) throws Exception {
        ArrayList<Person> list = new ArrayList<>();
        File f = new File(path);
        // if f not exist -> throw new Exception
        if (!f.exists()) {
            throw new Exception("Path doesn't exist");
        }
        Scanner sc = new Scanner(f);
        // if has next ==> continue read
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");
            try {
                Person p = new Person(line[0], line[1], Integer.parseInt(line[2].trim()));
                if (p.getMoney() > money) {
                    list.add(p);
                }
            } catch (Exception e) {
                Person p = new Person(line[0], line[1], 0);
            }
        }
        // sort list by money
        Collections.sort(list, (Person p1, Person p2) -> {
            return p1.getMoney() - p2.getMoney();
        });
        return list;
    }
    
    // copy text to new file, from Input Path to Output Path
    public void copyTextToNewFile(String inputPath, String outputPath) throws Exception {
        File f1 = new File(inputPath);
        File f2 = new File(outputPath);
        if (!f1.exists() || !f2.exists()) {
            throw new Exception("Path does not exist!");
        }
        
        // create new print writer
        PrintWriter pt = new PrintWriter(f2);
        Scanner sc = new Scanner(f1);
        while (sc.hasNext()) {
            String read = sc.nextLine();
            pt.println(read);
        }
        
        
        
        pt.close();
        
        
    }
    
}
