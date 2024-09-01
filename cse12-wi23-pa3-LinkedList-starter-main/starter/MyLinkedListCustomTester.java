
/**
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of MyLinkedList. You will only receive points
 * if your test passes when run on a good implementation and fails for the
 * corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

public class MyLinkedListCustomTester {
	// Optional: add test variables here
	private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"CSE 12", "Paul Cao", "Christine Alvarado"};

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyIntegerList =new MyLinkedList<Integer>();
		threeStringList =new MyLinkedList<>();
		MyLinkedList<String>.Node node0 =  
		this.threeStringList.new Node(this.strData[0]);
		MyLinkedList<String>.Node node1 =  
		this.threeStringList.new Node(this.strData[1]);
		MyLinkedList<String>.Node node2 =  
		this.threeStringList.new Node(this.strData[2]);

		this.threeStringList.head.next = node0;
		node0.prev = this.threeStringList.head;
		node0.next = node1;
		node1.prev = node0;
		node1.next = node2;
		node2.prev = node1;
		node2.next = this.threeStringList.tail;
		this.threeStringList.tail.prev = node2;
		this.threeStringList.size = 3;
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		// TODO: add your test here
        this.threeStringList.add("Gary Gillespie");
		boolean yesno = false;
		try{
			threeStringList.add(null);
		}catch(NullPointerException e){
			yesno = true;
		}
		assertEquals(true, yesno);
		assertEquals(4, threeStringList.size());
		assertEquals("Gary Gillespie", 
			threeStringList.tail.prev.data);
		assertSame(threeStringList.tail, 
				threeStringList.tail.prev.next);
		assertEquals("Christine Alvarado", 
			threeStringList.tail.prev.prev.data);
		assertEquals(true, threeStringList.add("testtest"));



	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		// TODO: add your test here
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(0,3);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(2,3);
		this.emptyIntegerList.add(6,3);

		assertEquals("Size of the LinkedList should be updated", 
		7, this.emptyIntegerList.size);


	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		// TODO: add your test here
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(0,3);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(2,3);
		this.emptyIntegerList.add(6,3);
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomRemoveFromEmpty() {
		// TODO: add your test here
		this.emptyIntegerList.remove(1);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		MyLinkedList<String>.Node testNode =  
					threeStringList.new Node(threeStringList.remove(1));

		
		assertEquals(2, threeStringList.size);
		assertSame("Christine Alvarado", 
							threeStringList.tail.prev.data);
		assertSame( "CSE 12", threeStringList.tail.prev.prev.data);
		assertSame( "Christine Alvarado", threeStringList.head.next.next.data);
		assertSame( "CSE 12", threeStringList.head.next.data);
		assertSame( threeStringList.tail.prev.data, 
					threeStringList.head.next.next.data);
		assertSame( threeStringList.tail.prev.prev.data, 
				threeStringList.head.next.data);

	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomSetIdxOutOfBounds() {
		// TODO: add your test here
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.add(1);
		this.emptyIntegerList.set(100,1);

	}
}
