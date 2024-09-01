/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA5 Write-up
   
  This file is a tester. This is an example of the custom tester
   for CSE 12 PA5 in Winter 2023.
*/
import org.junit.Test;
import java.util.HashSet;
import java.util.Objects;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {

    /**
     * Test constructor with valid argument
     */
    Student student1 = new Student("Test", "Student", "A12345678");
    Student student2 = new Student("Test", "Student", "B12345678");
    String abc = "abcdefg";
    public ExpectedException thrown= ExpectedException.none();

    /**
     * Test equals when the argument is a non-Student object.
     */
    @Test
    public void testStudentEquals() {
        assertFalse(student1.equals(abc));
    }

    /**
     * Test compareTo to compare two Students that have the 
     * same last name and same first name.
     */
    @Test
    public void testStudentCompareTo() {
        int yesno = student1.compareTo(student2);
        assertTrue(yesno<=0);

    }

    /**
     * Test attempt to drop a non-existing student with a 
     * non-empty course roster. 
     */
    @Test
    public void testCourseDrop() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        Student stu = new Student("Whales", "Ocean", "A123");
    
        course.enrolled = new HashSet<>();
        course.enroll(stu);
        boolean dropstu= course.drop(student1);
        int sizedrop = course.getEnrolledCount();
        assertFalse(dropstu);
        assertEquals(false,dropstu);
        assertEquals(1,sizedrop);
    }

    /**
     * Test Attempt to enroll a valid student into a 
     * course that is already at maximum capacity.
     */
    @Test
    public void testCourseEnroll() {
        Course course1 = new Course("CSE", "12", "CSE12", 2);
        Student stu1 = new Student("Whales", "Ocean", "A1234");
        Student stu2 = new Student("Whales", "ABC", "A12345");
        Student stu3 = new Student("Whales", "AB", "A123456");

        course1.enrolled = new HashSet<>();
        course1.enroll(stu1);
        course1.enroll(stu2);
        boolean enrollThree = course1.enroll(stu3);
        assertFalse(enrollThree);
        assertEquals(false,enrollThree);
        assertEquals(2,course1.getEnrolledCount());
    }

    /**
     * Test getRoster on a course with a large number of
     * enrolled students. 
     */
    @Test
    public void testCourseGetRoster() {
        Course course100 = new Course("CSE", "12", "CSE12", 150);
        Student[] arrayEqualRoster = new Student[100];
        Student[] arrayEqual = new Student[100];

        for(int i=199; i>=100;i--){
            String pid ="A"+i;
            Student stu100 = new Student("Song","Song",pid);
            course100.enroll(stu100);
        }
        course100.getRoster();

        for (int i=0;i<100;i++){
            arrayEqualRoster[i] = course100.getRoster().get(i);
        }

        for (int i=100;i<=199;i++){
            String pid = "A"+i;
            Student stu100 = new Student("Song","Song",pid);
            arrayEqual[i-100]=stu100;
        }
        assertEquals(100,course100.getRoster().size());
        assertEquals("A199",course100.getRoster().get(99).getPID());
        assertArrayEquals(arrayEqual,arrayEqualRoster);
    }

    /**
     * Test the Sanctuary constructor with a negative argument for maxAnimals.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSanctConstructor() {
        Sanctuary sanct = new Sanctuary(-500, 50);
        thrown.expect(IllegalArgumentException.class);

    }

    /**
     * Test rescue animals from an existing species, where rescuing num 
     * animals will cause the number of animals to exceed the sanctuary's
     * max capacity. 
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary sanct = new Sanctuary(100, 2);
        sanct.sanctuary.put("Giraffe", 20);
        sanct.sanctuary.put("Meerkat", 65);
        assertEquals(35,sanct.rescue("Giraffe",50));
    }

    /**
     * Test rescue a new non-null species when the sanctuary 
     * is already at the max capacity for species.
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(100, 2);
        sanct.sanctuary.put("Giraffe", 20);
        sanct.sanctuary.put("Meerkat", 65);
        assertEquals(10,sanct.rescue("ABC",10));
        sanct.sanctuary.put("LLLL",15);
        assertEquals(10,sanct.rescue("ABC",10));
    }


    /**
     * Test release some (not all) of the animals of an existing
     * species in the sanctuary.
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Capybara", 40);
        sanct.sanctuary.put("Horse", 5);

        sanct.release("Capybara", 10);
        assertEquals(30,sanct.countForSpecies("Capybara"));

    }

    /**
     * Test Attempt to release more animals than exists for 
     * a specific animal species in the sanctuary.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSanctReleaseTooMany() {
        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Capybara", 40);
        sanct.sanctuary.put("Horse", 5);

        sanct.release("Capybara", 45);
        thrown.expect(IllegalArgumentException.class);
    }
}