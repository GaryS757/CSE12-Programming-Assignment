/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA2 Write-up
   
  This file is for CSE 12 PA2 hidden tester in Winter 
  2023, This file is to test for a data structure 
  similar to Java’s ArrayLists with generic types
*/
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

/**
*This class is called MyArrayListHiddenTester that 
 tests some specific methods of MyArrayList.java

*/
public class MyArrayListHiddenTester {
	
	static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3 };
    Integer[] arrNonEmptyInts = {1, null, null}; // NOTE: LIST OF SIZE ONE

    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
            listCustomCapacity, listWithNull, listWithInt;
    
    public ExpectedException thrown= ExpectedException.none();

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() throws Exception {
    	listEmpty = new MyArrayList();
        listNonEmpty = new MyArrayList<>(arrNonEmptyInts);
        listNonEmpty.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(arr);
        listWithInt = new MyArrayList<Integer>(arrInts);
        
    }
    

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        listEmpty = new MyArrayList(-1);
        
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
    	listEmpty = new MyArrayList(null);
    	
    	assertArrayEquals("element is construct correctly",
                new Integer[]{null, null, null, null, null}, listEmpty.data);
    	assertEquals("size should be zero", 0, listEmpty.size);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        listWithNull= new MyArrayList(arrNonEmptyInts);

        assertEquals("size should be four", 3, listWithNull.size);

    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(1);

        assertEquals("capacity should be 9",6 , listWithInt.data.length);
        assertEquals("size should be 7",4, listWithInt.size);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listNonEmpty.append(null);

        assertEquals("capacity should be 9",3 , listNonEmpty.data.length);
        assertEquals("size should be 7",2, listNonEmpty.size);
        
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listWithInt.prepend(1);

        assertEquals("capacity should be 9",6 , listWithInt.data.length);
        assertEquals("size should be 7",4, listWithInt.size);
        
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listNonEmpty.prepend(null);

        assertEquals("capacity should be 3",3 , listNonEmpty.data.length);
        assertEquals("size should be 2",2, listNonEmpty.size);
        
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBound(){
    	listNonEmpty.insert(9, 2);
    	thrown.expect(IndexOutOfBoundsException.class);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for(int i=0;i<1000;i++){
            listNonEmpty.insert(0, 2);
        }
    	 assertEquals("capacity should be 1002",1002,listNonEmpty.data.length);
    	 assertEquals("size should be 1001",1001, listNonEmpty.size);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
    	listNonEmpty.get(-1);
    	thrown.expect(IndexOutOfBoundsException.class);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
    	listNonEmpty.set(6, 1);
    	thrown.expect(IndexOutOfBoundsException.class);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
    	listWithInt.remove(1);
    	listWithInt.remove(0);
    	assertArrayEquals("element is insert correctly",
                new Integer[]{ 3,null,null}, listWithInt.data);
   	 assertEquals("size should be 1",1, listWithInt.size);
    	
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
    	listDefaultCap.remove(12);
    	thrown.expect(IndexOutOfBoundsException.class);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){  	
       listDefaultCap.expandCapacity(2);
       thrown.expect(IllegalArgumentException.class);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
    	listDefaultCap.expandCapacity(11);
    	assertArrayEquals("element is expand correctly",
                new Integer[]{null, null, null, null, null, null, null, null, null,
                null, null}, listDefaultCap.data);
    }
    

}
