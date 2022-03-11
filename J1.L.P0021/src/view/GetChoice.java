package view;

import java.util.ArrayList;
import model.Student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Legion
 */
public class GetChoice {

    /**
     * Get choice
     *
     * @param ops
     * @return
     */
    public static int getchoice(String[] ops) {
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        System.out.println();
        return validation.Inputter.inputIntegerInRange("Enter your choice(1-" + ops.length + "): ", 1, ops.length);
    }

    /**
     * choose 1 course from course list
     *
     * @param msg
     * @param courseList
     * @return
     */
    public static String getCourseChoice(String msg, String[] courseList) {
        System.out.println(msg);
        for (int i = 0; i < courseList.length; i++) {
            System.out.println((i + 1) + ". " + courseList[i]);
        }
        System.out.println();
        int index = validation.Inputter.inputIntegerInRange("Enter your choice(1-" + courseList.length + "): ", 1, courseList.length, true) - 1;
        if (index < 0) {
            return "";
        }
        return courseList[index];
    }

    /**
     * choose 1 student from student list index
     *
     * @param msg
     * @param students
     * @param indexList
     * @return index of student from list
     */
    public static int getStudentChoice(String msg, ArrayList<Student> students, ArrayList<Integer> indexList) {
        System.out.println(msg);
        System.out.println(String.format("   |%-10s|%-18s|%-18s|%-18s|", "ID", "Student Name", "Course Name", "Semester"));
        for (int i = 0; i < indexList.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(indexList.get(i)).toString()
                    + String.format("%-18s|", students.get(indexList.get(i)).getSemester()));
        }

        System.out.println("\nLeave blank if do not want to update!");
        int index = validation.Inputter.inputIntegerInRange("Choose records to update (1-" + indexList.size() + "): ", 1, indexList.size(), true) - 1;
        if (index < 0) {
            return -1;
        }
        return indexList.get(index);
    }
}
