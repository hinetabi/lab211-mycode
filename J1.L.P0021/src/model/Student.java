/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Legion
 */
public class Student {

    private String ID;
    private String studentName;
    private int semester;
    private String courseName;

    public Student() {
    }

    public Student(String ID, String studentName, int semester, String courseName) {
        this.ID = ID;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

//    @Override
//    public String toString() {
//        return String.format("|%-10s|%-18s|%-18s|%-18s|", ID, studentName, courseName, semester);
//    }
    @Override
    public String toString() {
        return String.format("|%-10s|%-18s|%-18s|", ID, studentName, courseName);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return this.toString().equalsIgnoreCase(o.toString());
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return this.toString().compareTo("");
    }

}
