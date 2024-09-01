/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA5 Write-up
   
  This file is for CSE 12 PA5 in Winter 2023,
  This file is to implement a data structure similar to 
  HashSet. The file is to do the hashSet application. The course 
  designed for students to register for classes and we need 
  to organize the students for the enrollment period through courses.
*/

import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;

/**
* This class is called Course that stores some methods,
* and do a data structure similar to HashSet. Our Course class 
* will help us store the students registered for this particular 
* course in the form of a HashSet.
*
*/
public class Course{

    /**
     * There are the instance variables
     */
    HashSet<Student> enrolled;
    private final String department;
    private final String number;
    private final String description;
    private final int capacity;

    /** 
     * Constructor to initialize the course’s information with
     * an initial enrollment of 0 students.
     * 
     * @param department the information of course department
     * @param number  the information of course number
     * @param description  the information of course description
     * @param capacity the information of course capacity
     */
    public Course(String department, String number, 
                    String description, int capacity){
        if (department == null||number == null||description == null) {
            throw new IllegalArgumentException();
        }
        if (capacity<=0){
            throw new IllegalArgumentException();
        }
        enrolled = new HashSet<>();
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
    }

    /** 
     * This method is to return the course department
     * 
     * @return the course department
     */
    public String getDepartment(){
        return department;
    }

    /** 
     * This method is to return the course number
     * 
     * @return the course number
     */
    public String getNumber(){
        return number;
    }

    /** 
     * This method is to return the course description
     * 
     * @return the course description
     */
    public String getDescription(){
        return description;
    }

    /** 
     * This method is to return the course capacity
     * 
     * @return the course capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /** 
     * This method is to help the student object 
     * to enroll the course 
     * 
     * @param student the student object who wants to enroll
     * @return the boolean type(if enrolled, true)
     */
    public boolean enroll(Student student){
        if (student == null){
            throw new IllegalArgumentException();
        }
        if (!(enrolled.contains(student))&&!(enrolled.size()>=capacity)){
            enrolled.add(student);
            return true;
        }
        return false;
    }

    /** 
     * This method is to help the student object 
     * to drop the course 
     * 
     * @param student the student object who wants to drop
     * @return the boolean type(if dropped, true)
     */
    public boolean drop(Student student){
        if (student == null){
            throw new IllegalArgumentException();
        }
        return enrolled.remove(student);
    }

    /** 
     * This method is to cancel the whole class. If the course
     * is canceled, all of the students are dropped from course.
     * 
     */
    public void cancel(){
        enrolled.clear();
    }

    /** 
     * This method is to check whether the class is full of 
     * students 
     * 
     * @return the boolean type(if full, true)
     */
    public boolean isFull(){
        if (enrolled.size()>= capacity){
            return true;
        }else{
            return false;
        }
    }

    /** 
     * This method is to check the number of the students who
     * already enrolled
     * 
     * @return the number of the enrolled students.
     */
    public int getEnrolledCount(){
        return enrolled.size();
    }

    /** 
     * This method is to get the number of the available 
     * seats of that course
     * 
     * @return the number of the available seats 
     */
    public int getAvailableSeats(){
        return capacity-enrolled.size();
    }

    /** 
     * This method is to Return a shallow copy of all 
     * the students enrolled in this course. 
     * 
     * @return shallow copy of the students enrolled in course 
     */
    public HashSet<Student> getStudents(){
        return (HashSet<Student>)enrolled.clone();

    }

    /** 
     * This method is to make all the students enrolled in the
     * course should be listed in the increasing order 
     * 
     * @return the arraylist of students with increasing order
     */
    public ArrayList<Student> getRoster(){
        ArrayList<Student> arrlist = new ArrayList<Student>();
        
        for(Student a: this.enrolled){
            arrlist.add(a);
        }

        Collections.sort(arrlist);
        return arrlist;
    }

    /** 
     * This method is to return a string representation for
     * a Course object.
     * 
     * @return string format of course instance variables
     */
    public String toString(){
        return String.format("%s %s [%d] %s",
                        department,number,capacity,description);

    }
}