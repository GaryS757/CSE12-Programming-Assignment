/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA6 Write-up
/**
 * This file contains some public tests (visible on Gradescope)
 * Use this as a guide to write tests to verify your MyDeque, MyStack, and
 * MyQueue implementation.
 */
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * This class contains public test cases for MyDeque, MyStack, and MyQueue
 */
public class CustomTester{
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    public ExpectedException thrown= ExpectedException.none();

    /** Test the Deque constructor with the invalid parameter */
    @Test(expected= IllegalArgumentException.class)
    public void testDequeConstructorWithWrongCapa() {
        int wrongCapacity = -10;
        MyDeque<Integer> deque = new MyDeque<>(wrongCapacity);
        thrown.expect(IllegalArgumentException.class);
    }


    /** Test expandCapacity with all null in deque and null inside of deque */
    @Test
    public void testExpandCapacityWithZeroAndNullInside() {

        MyDeque<Integer> zeroDeque =new MyDeque<>(0);
        Integer[] zeroOrig = {};
        Integer[] zerofinal = {null,null,null,null,null,null,
                               null,null,null,null};
        initDeque(zeroDeque,zeroOrig,0,0,0);
        zeroDeque.expandCapacity();
        assertEquals(10,zeroDeque.data.length);
        assertEquals(0,zeroDeque.size);
        assertEquals(0,zeroDeque.front);
        assertEquals(0,zeroDeque.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    zerofinal[i], zeroDeque.data[i]);
        }

        MyDeque<Integer> DequeL = new MyDeque<>(3);
        Integer[] origL = {4,null,1,2,3};
        Integer[] finalL = {1,2,3,4,null,null,null,null,null,null};
        initDeque(DequeL,origL,4,2,0);
        DequeL.expandCapacity();
        assertEquals(10,DequeL.data.length);
        assertEquals(4,DequeL.size);
        assertEquals(0,DequeL.front);
        assertEquals(3,DequeL.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    finalL[i], DequeL.data[i]);
        }
    }
    /** Test expandCapacity with the deque is at full capacity*/
    @Test
    public void testExpandCapacityAtFullCapacity() {
        MyDeque<Integer> Capadeque = new MyDeque<>(10);
        Integer[] Capaorig = {3,4,1,2};
        Integer[] CapafinalOrdering = {1,2,3,4,null,null,null,null};
        initDeque(Capadeque, Capaorig, 4, 2, 1);
        Capadeque.expandCapacity();
        assertEquals(8, Capadeque.data.length);
        assertEquals(4, Capadeque.size);
        assertEquals(0, Capadeque.front);
        assertEquals(3, Capadeque.rear);
        for (int i = 0; i < 8; i++) {
            assertEquals("Deque structure should be maintained",
                    CapafinalOrdering[i], Capadeque.data[i]);
        }
    }

    /**
     * Test addFirst with all add null
     */
    @Test(expected = NullPointerException.class)
    public void testAddFirstAddNull() {
        MyDeque<Integer> dequel = new MyDeque<>(10);
        Integer[] origl = {5, 6, 1, null, null, null, null, null, null, null};
        initDeque(dequel, origl, 3, 0, 2);
        dequel.addFirst(null);
        thrown.expect(NullPointerException.class);
    }

    /**
     * Test addFirst with add at full capacity
     */
    @Test
    public void testAddFirstAddAtFullCapacity(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {3,4,1,2};
        Integer[] finalOrdering = {1,2,3,4,null,null,null,100};
        initDeque(deque, orig, 4, 2, 1);
        deque.addFirst(100);
        assertEquals(8,deque.data.length);
        assertEquals(5, deque.size);
        assertEquals(7, deque.front);
        assertEquals(3,deque.rear);
        assertEquals(1, deque.data[0]);
        assertEquals(2,deque.data[1]);
        assertEquals(3, deque.data[2]);
        assertEquals(null,deque.data[4]);
    }

    /**
     * Test addLast with all add null
     */
    @Test(expected = NullPointerException.class)
    public void testAddLastAddNull() {
        MyDeque<Integer> dequel = new MyDeque<>(10);
        Integer[] origl = {5, 6, 1, null, null, null, null, null, null, null};
        initDeque(dequel, origl, 3, 0, 2);
        dequel.addLast(null);
        thrown.expect(NullPointerException.class);
    }

    /**
     * Test addLast with add at full capacity
     */
    @Test
    public void testAddLastAtFullCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {3,4,1,2};
        Integer[] finalOrdering = {1,2,3,4,100,null,null,null};
        initDeque(deque, orig, 4, 2, 1);
        deque.addLast(100);
        assertEquals(8,deque.data.length);
        assertEquals(5, deque.size);
        assertEquals(0,deque.front);
        assertEquals(4, deque.rear);
        assertEquals(100, deque.data[4]);
        assertEquals(1,deque.data[0]);
        assertEquals(3,deque.data[2]);
        assertEquals(null,deque.data[5]);
    }

    /**
     * Test removeFirst from all kinds of siuations like at beginning
     * of the deque, end of the deque, and so on. 
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {7, 3, 4, 1, 5, null, null, null, 8, 9 };
        initDeque(deque, orig, 7, 8, 4);
        deque.removeFirst();
        assertEquals(10,deque.data.length);
        assertEquals(6, deque.size);
        assertEquals(9, deque.front);
        assertEquals(4, deque.rear);
        deque.removeFirst();
        assertEquals(10,deque.data.length);
        assertEquals(5, deque.size);
        assertEquals(0, deque.front);
        assertEquals(4, deque.rear);
        deque.removeFirst();
        assertEquals(10,deque.data.length);
        assertEquals(4, deque.size);
        assertEquals(1, deque.front);
        assertEquals(4, deque.rear);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        assertEquals(10,deque.data.length);
        assertEquals(1, deque.size);
        assertEquals(4, deque.front);
        assertEquals(4, deque.rear);
        deque.removeFirst();
        assertEquals(10,deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(4, deque.front);
        assertEquals(4, deque.rear);
    }

    /**
     * Test removeLast from all kinds of siuations like at beginning
     * of the deque, end of the deque, and so on. 
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {7, 3, 4, 1, 5, null, null, null, 8, 9 };
        initDeque(deque, orig, 7, 8, 4);
        deque.removeLast();
        assertEquals(10,deque.data.length);
        assertEquals(6, deque.size);
        assertEquals(8, deque.front);
        assertEquals(3, deque.rear);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        assertEquals(10,deque.data.length);
        assertEquals(3, deque.size);
        assertEquals(8, deque.front);
        assertEquals(0, deque.rear);
        deque.removeLast();
        assertEquals(10,deque.data.length);
        assertEquals(2, deque.size);
        assertEquals(8, deque.front);
        assertEquals(9, deque.rear);
        deque.removeLast();
        assertEquals(10,deque.data.length);
        assertEquals(1, deque.size);
        assertEquals(8, deque.front);
        assertEquals(8, deque.rear);
        deque.removeLast();
        assertEquals(10,deque.data.length);
        assertEquals(0, deque.size);
        assertEquals(8, deque.front);
        assertEquals(8, deque.rear);
    }

    /**
     * Test peekFirst with all null deque and null inside 
     */
    @Test
    public void testpeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {7, 3, 4, 1, 5, null, null, null, 8, 9 };
        initDeque(deque, orig, 7, 8, 4);
        assertEquals(Integer.valueOf(8),deque.peekFirst());
        assertEquals(10, deque.data.length);
        assertEquals(7, deque.size);
        assertEquals(8, deque.front);
        assertEquals(4, deque.rear);

        MyDeque<Integer> dequeL = new MyDeque<>(10);
        Integer[] origL = {null,null,null,null,null };
        initDeque(dequeL, origL, 0, 0, 0);
        assertNull(dequeL.peekFirst());
        assertEquals(null,dequeL.peekFirst());
    }

    /**
     * Test peekLast with all null deque and null inside 
     */
    @Test
    public void testpeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {7, 3, 4, 1, 5, null, null, null, 8, 9 };
        initDeque(deque, orig, 7, 8, 4);
        assertEquals(Integer.valueOf(5),deque.peekLast());
        assertEquals(10, deque.data.length);
        assertEquals(7, deque.size);
        assertEquals(8, deque.front);
        assertEquals(4, deque.rear);

        MyDeque<Integer> dequeL = new MyDeque<>(10);
        Integer[] origL = {null,null,null,null,null };
        initDeque(dequeL, origL, 0, 0, 0);
        assertNull(dequeL.peekLast());
        assertEquals(null,dequeL.peekLast());
    }

    /**
     * Test Mystack with all null siuation and at full capacity 
     */
    @Test
    public void testMystack(){
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = {1, 2, 3, 4, 5};
        initDeque(stack.theStack, orig, 5, 0, 4);
        assertEquals(false,stack.empty());
        assertFalse(stack.empty());
        assertEquals(5,stack.size());
        stack.pop();
        assertEquals(null,stack.peek().intValue());
        assertEquals(4,stack.size());
        stack.push(9);
        assertEquals(5,stack.size());
        assertEquals(9,stack.peek().intValue());

        

        MyStack<Integer> stacknull = new MyStack<>(5);
        Integer[] orignull = {null, null, null, null, null};
        initDeque(stacknull.theStack, orignull, 0, 0, 0);
        assertNull(stacknull.pop());
        assertNull(stacknull.peek());
        assertTrue(stacknull.empty());
        assertEquals(null,stacknull.pop());
        assertEquals(null,stacknull.peek());
        assertEquals(true,stacknull.empty());
        assertEquals(0,stacknull.size());
        stacknull.push(1);
        assertEquals(1,stacknull.size());
        assertEquals(false,stacknull.empty());
        assertEquals(1,stacknull.peek().intValue());
    }

    /**
     * Test MyQueue with all null siuation and at full capacity 
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {1, 2, 3, 4, 5};
        initDeque(queue.theQueue, orig, 5, 0, 4);
        assertEquals(false,queue.empty());
        assertFalse(queue.empty());
        assertEquals(5,queue.size());
        queue.dequeue();
        assertEquals(4,queue.size());

        MyQueue<Integer> queuenull = new MyQueue<>(5);
        Integer[] orignull = {null, null, null, null, null};
        initDeque(queuenull.theQueue, orignull, 0, 0, 0);
        assertNull(queuenull.dequeue());
        assertNull(queuenull.peek());
        assertTrue(queuenull.empty());
        assertEquals(null,queuenull.dequeue());
        assertEquals(null,queuenull.peek());
        assertEquals(true,queuenull.empty());
        assertEquals(0,queuenull.size());
        queuenull.enqueue(1);
        assertEquals(1,queuenull.size());
        assertEquals(false,queuenull.empty());
    }
}
