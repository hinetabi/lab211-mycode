/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import manager.StudentManager;
import model.Student;

/**
 *
 * @author Legion
 */
public class View {

    private final String[] courseList;
    private final String[] ops;
    private final String IdRegex;
    StudentManager studentManager;

    //constructor
    public View() {
        this.studentManager = new StudentManager();
        this.courseList = new String[]{"Java", ".Net", "C/C++"};
        this.ops = new String[]{"Create a New Student",
            "Find and Sort By Name",
            "Update / Delete",
            "Report",
            "Display all data",
            "Exit"};
        this.IdRegex = "^[Hh][EeSs]\\d{6}$";
    }

    /**
     * display student in list
     *
     * @param studentList
     */
    public void displayStudent(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("Can not find students!");
        } else {
            System.out.printf("|%-10s|%-18s|%-18s|%-18s|\n", "ID", "Name", "CourseName", "Semester");
            for (Student student : studentList) {
                System.out.printf("%s%-18s|\n", student.toString(), student.getSemester());
            }
        }
    }

    /**
     * load default data
     */
    public void initData() {
        this.studentManager.addNewStudent("HE987654", "phAm bAng aN", 2, "Java");
        this.studentManager.addNewStudent("HS163537", "Le Thanh Lam", 1, "C/C++");
        this.studentManager.addNewStudent("HE123456", "vU Thi HA", 2, "Java");
        this.studentManager.addNewStudent("HE123456", "vU Thi HA", 3, ".Net");
        this.studentManager.addNewStudent("HE123456", "vu thi ha", 3, ".Net");
        this.studentManager.addNewStudent("HE123456", "vu thi ha", 2, ".Net");
        this.studentManager.addNewStudent("HE123486", "An ThaNh LonG", 5, ".Net");
    }

    /**
     * create a new student
     */
    public void create() {
        String ID = validation.Inputter.inputStringInForm("Enter ID of student: (HE...)", IdRegex).toUpperCase();
        String studentName = validation.Inputter.inputString("Enter student name: ", false);
        int semester = validation.Inputter.inputInteger("Enter semester: ", false, false);
        String courseName = GetChoice.getCourseChoice("Choose your course: ", courseList);

        if (this.studentManager.addNewStudent(ID, studentName, semester, courseName)) {
            System.out.println("Added!");
        } else {
            System.out.println("Can not add!");
        }
    }

    /**
     * find student by name
     */
    public void findStudent() {
        String name = validation.Inputter.inputString("Enter name (part of name): ", false);
        this.displayStudent(studentManager.findStudentsByName(name));
    }

    /**
     * update / delete students
     */
    public void modifyStudent() {

        //user enter ID
        String ID = validation.Inputter.inputStringInForm("Enter student ID (HE...): ", IdRegex).toUpperCase();

        //find students by id
        ArrayList<Integer> indexList = studentManager.findStudentById(ID);

        if (indexList.isEmpty()) {
            System.out.println("Can not find student!");
        } else {
            //user choose from list of records
            int index = GetChoice.getStudentChoice("", studentManager.getStudentList(), indexList);
            if (index >= 0) {

                do {
                    String choice = validation.Inputter.inputString("Do you want to update (U) or delete (D) student? ", false);
                    //if choice == u  => update
                    if (choice.equalsIgnoreCase("u")) {
                        System.out.println("Leave blank what you do not want to update.");
                        String studentName = validation.Inputter.inputString("Enter student name: ", true);
                        int semester = validation.Inputter.inputInteger("Enter semester: ", false, true);
                        String courseName = GetChoice.getCourseChoice("Choose your course: ", courseList);
                        if (studentManager.updateStudent(index, studentName, semester, courseName)) {
                            System.out.println("Updated!");
                        } else {
                            System.out.println("Can not update!");
                        }
                        break;
                    } else //if choice == d => delete
                    if (choice.equalsIgnoreCase("d")) {
                        studentManager.deleteStudent(index);
                        System.out.println("Deleted!");
                        break;
                    } else {
                        System.err.println("Enter U (update) / D (delete)");
                    }
                } while (true);
            }
        }
    }

    /**
     * report the name, courses + number of courses of a student 2 records that
     * have same ID is belongs to 1 student
     */
    private void report() {
        HashMap<String, Integer> records = studentManager.getReport();
//        Iterator it = records
        System.out.println(String.format("|%-10s|%-18s|%-18s|%-18s|",
                "ID", "Student Name", "Course Name", "Number of Courses"));
        for (String key : records.keySet()) {
            System.out.println(String.format("%s%-18s|", key, records.get(key)));
        }
    }

    /**
     * main screen
     */
    public void start() {
        int choice;
        initData();
        do {
            System.out.println("=====STUDENT MANAGER SYSTEM=====");
            choice = GetChoice.getchoice(ops);
            switch (choice) {

                case 1: {
                    System.out.println("======ADDING=======");
                    do {
                        create();
                    } while (validation.Inputter.inputYesNo("Do you want to continue (Y/N)? "));
                    break;
                }

                case 2: {
                    System.out.println("======FINDING======");
                    findStudent();
                    break;
                }

                case 3: {
                    System.out.println("======MODIFYING======");
                    modifyStudent();
                    break;
                }

                case 4: {
                    System.out.println("======REPORT======");
                    report();
                    break;
                }

                case 5: {
                    this.displayStudent(studentManager.getStudentList());
                }
            }
            System.out.println("=============================");
        } while (choice < ops.length);

    }

}
