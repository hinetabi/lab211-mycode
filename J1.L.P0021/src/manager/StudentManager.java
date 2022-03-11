package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import model.Student;

/**
 *
 * @author Legion
 */
public class StudentManager {
    
    private ArrayList<Student> studentList;

    //constructor
    public StudentManager() {
        this.studentList = new ArrayList<>();
    }
    
    public StudentManager(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    //getter, setter
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * add a new student
     *
     * @param ID
     * @param studentName
     * @param semester
     * @param courseName
     * @return
     */
    public boolean addNewStudent(String ID, String studentName, int semester, String courseName) {
        studentName = validation.Inputter.beautyName(studentName);
        Student student = new Student(ID, studentName, semester, courseName);
        
        //if records is exist in list => not add
        if (isStudentInList(student)) {
            return false;
        } else {
            String name = findNameById(ID);
            //check if student with this ID have exist
            if (name == null || name.equalsIgnoreCase(studentName)) {
                this.studentList.add(student);
                return true;
            } else {
                return false;
            }
            
        }
    }

    /**
     * delete a student by index
     *
     * @param index
     */
    public void deleteStudent(int index) {
        this.studentList.remove(index);
    }

    /**
     * find all students if his/her name contains input string
     *
     * @param name
     * @return
     */
    public ArrayList findStudentsByName(String name) {
        ArrayList<Student> ls = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                ls.add(student);
            }
        }

        //sort students found by name
        Collections.sort(ls, new Comparator<Student>() {
            @Override
            public int compare(Student t, Student t1) {
                String[] nameT = t.getStudentName().split(" ");
                String[] nameT1 = t1.getStudentName().split(" ");
                if (t.getStudentName().isEmpty()) {
                    return -1;
                } else if (t1.getStudentName().isEmpty()) {
                    return 1;
                }
                int lenT = nameT.length;
                int lenT1 = nameT1.length;
                while (lenT > 0 && lenT1 > 0) {
                    if (nameT[lenT - 1].equalsIgnoreCase(nameT1[lenT1 - 1])) {
                        lenT--;
                        lenT1--;
                    } else {
                        return nameT[lenT - 1].compareToIgnoreCase(nameT1[lenT1 - 1]);
                    }
                }
                return (lenT - lenT1);
            }
        });
        
        return ls;
    }

    /**
     * find students by ID
     *
     * @param ID
     * @return list index of students, return list empty if not exist
     */
    public ArrayList<Integer> findStudentById(String ID) {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getID().equals(ID)) {
                indexList.add(i);
            }
        }
        
        return indexList;
    }

    /**
     *
     * @param ID
     * @return name of student with ID
     */
    public String findNameById(String ID) {
        for (Student student : studentList) {
            if (student.getID().equalsIgnoreCase(ID)) {
                return student.getStudentName();
            }
        }
        return null;
    }

    /**
     *
     * @param index
     * @param studentName
     * @param semester
     * @param courseName
     * @return 
     */
    public boolean updateStudent(int index, String studentName, int semester, String courseName) {
        studentName = validation.Inputter.beautyName(studentName);

        Student student = studentList.get(index);
       
        //back-up data, using if not update
        Student student1 = new Student(student.getID(), student.getStudentName(), student.getSemester(), student.getCourseName()); 
        
        //update semester
        if (semester > 0) {
            student.setSemester(semester);
        }
        
        //update course name
        if (!courseName.isEmpty()) {
            student.setCourseName(courseName);
        }
        
        //if records has exist in list => return false
        if (isStudentInList(student)) {
            studentList.set(index, student1);
            return false;
        }

        //update name of all students that have the same ID
        if (!studentName.isEmpty()) {
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getID().equalsIgnoreCase(studentList.get(index).getID())) {
                    studentList.get(i).setStudentName(studentName);
                }
            }
        }

        
        return true;
    }

    /**
     * get report from list of student
     *
     * @return
     */
    public HashMap<String, Integer> getReport() {
        HashMap<String, Integer> records = new HashMap<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (records.containsKey(studentList.get(i).toString())) {
                records.put(studentList.get(i).toString(),records.get(studentList.get(i).toString())+1 );
            } else {
                records.put(studentList.get(i).toString(), 1 );
            }
            
        }
        return records;
    }

    /**
     *
     * @param e
     * @return if the student exists in list
     */
    public boolean isStudentInList(Student e) {
        for (Student student : studentList) {
            if (e.toString().equalsIgnoreCase(student.toString())) {
                // toString = ID + Name + CourseName
                // if (e.semester == student.semester => exist)
                if (e.getSemester() == student.getSemester()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
