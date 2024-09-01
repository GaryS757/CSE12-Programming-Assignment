/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA5 Write-up
   
  This file is for CSE 12 PA5 in Winter 2023,
  This file is to implement a data structure similar to 
  HashSet. The file is to do the hashSet application. It’s 
  designed for students to register for classes and we need 
  to organize the students for the enrollment period.
*/
import java.util.Objects;

/**
* This class is called Student that stores some methods,
* and do a data structure similar to HashSet. It has
* equals method, hasCode method, compareTo method and so on.
*
*/
public class Student implements Comparable<Student>{

    /**
     * There are the instance variables
     */
    private final String firstName;
    private final String lastName;
    private final String PID;

    /** 
     * Constructor to Initialize the student’s information.
     * 
     * @param firstName the information of student's firstName
     * @param lastName  the information of student's lastName
     * @param PID  the information of student's PID
     */
    public Student(String firstName, String lastName, String PID){
        if (firstName == null||lastName == null||PID == null){
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /** 
     * This method is to return the student’s first name
     * 
     * @return the student's first name
     */
    public String getFirstName(){
        return firstName;
    }

    /** 
     * This method is to return the student’s last name 
     * 
     * @return the student's last name
     */
    public String getLastName(){
        return lastName;
    }

    /** 
     * This method is to return the student’s PID 
     * 
     * @return the student's PID
     */
    public String getPID(){
        return PID;
    }

    /** 
     * This method is to show whether it is a student
     * type.
     * 
     * @param o the object but should be student type
     * @return the boolean type(if student type, true)
     */
    @Override
    public boolean equals(Object o){
        if(o==null){
            return false;
        }else if(!(o instanceof Student)){
            return false;
        }else{
            Student student = (Student) o;
            if (Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(PID, student.PID)){
                return true;
            }
            return false;
        }
    }

    /** 
     * This method is to return the hash value generated 
     * using the built-in Objects.hash function. 
     * 
     * @return generate the hash value of the student's
     * firstName, lastName, and PID.
     */
    @Override
    public int hashCode(){
        return Objects.hash(firstName,lastName,PID);
    }

    /** 
     * This method is to compare this with another Student
     * in the order of lastName, firstName, and PID, using
     * String::compareTo.
     * 
     * @param o the student object
     * @return the integer to show which hashcode is larger
     */
    public int compareTo(Student o){
        if(o == null){
            throw new NullPointerException();
        }
        int compare_last =lastName.compareTo(o.lastName);
        if(compare_last!=0){
            return compare_last;
        }
        int compare_first = firstName.compareTo(o.firstName);
        if(compare_first!=0){
            return compare_first;
        }
        int compare_PID = PID.compareTo(o.PID);
        return compare_PID;
    }
}