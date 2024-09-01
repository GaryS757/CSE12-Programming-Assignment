/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA4 Write-up
   
 * This file contains all the custom tests for verifying the  
 * MyLinkedList and Iterator implementation .
 * Iterator values were set manually to decouple its dependency 
 * on next() during testing.
*/

// DO NOT CHANGE THE METHOD NAMES
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

/**
 * This class contains custokm test cases for MyListIterator. listLen1 is a
 * linkedlist of length 1 and listLen2 is a linkedlist of length 2.
 */
public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;
    
    public ExpectedException thrown= ExpectedException.none();
    
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextEnd() {
    	listLen1Iter.next();
    	listLen1Iter.next();
    	thrown.expect(NoSuchElementException.class);
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousStart() {
        listLen1Iter.previous();
        thrown.expect(NoSuchElementException.class);
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test(expected = NullPointerException.class)
    public void testAddInvalid() {
    	String str = null;
    	listLen1Iter.add(str);
    	thrown.expect(NullPointerException.class);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantSet() {
    	String str = "abc";
    	listLen1Iter.canRemoveOrSet = false;
    	listLen1Iter.set(str);
    	thrown.expect(IllegalStateException.class);
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test(expected = NullPointerException.class)
    public void testSetInvalid() {
    	String str = null;
    	listLen1Iter.set(str);
    	thrown.expect(NullPointerException.class);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantRemove() {
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.remove();
        thrown.expect(IllegalStateException.class);
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.next();
    	assertFalse(listLen1Iter.hasNext());

    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {

        assertFalse(listLen1Iter.hasPrevious());

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        int a = -1;
        int c = listLen1Iter.previousIndex();
        assertEquals(a,c);
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.next();
        int b = 1;
        int d = listLen1Iter.nextIndex();
        assertEquals(b,d);

    }
}
