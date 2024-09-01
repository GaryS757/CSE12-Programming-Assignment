/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA6 Write-up
   
  This file is for CSE 12 PA6 in Winter 2023,
  This file is to implement a Deque data structure with generic 
  type. It’s designed for deque to do some operations with 
  the generic type.
*/

/**
* This class is called MyDeque that implements the interface
* DequeInterface with generic type. That stores some methods,
* and do a Deque data structure. It has expandCapacity method,
* addFirst method, addLast method and so on.
*
*/
public class MyDeque<E> implements DequeInterface<E>{

    /**
     * There are the instance variables
     */
    Object[] data;
    int size;
    int rear;
    int front;

    /** 
     * Constructor to initialize the Object array data with
     * length of initialCapacity.
     * 
     * @param initialCapacity the initial capacity for the deque
     */
    public MyDeque(int initialCapacity){
        if(initialCapacity<0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    /** 
     * Returns the number of elements that exist in the deque.
     * 
     * @return the specific number of elements in deque
     */
    public int size(){
        return size;
    }

    /** 
     * Doubles the current capacity. If the capacity is 0, set 
     * the capacity to a default value of 10. This method should
     * preserve the current size and elements in the list.Elements
     * need to be contiguous after expanding. 
     */
    public void expandCapacity(){
        int zerocapacity = 10;
        int doubleNumber = 2;
        int currentCapacity = data.length;
        if (currentCapacity == 0){
            currentCapacity = zerocapacity;
        }else{
            currentCapacity = doubleNumber*data.length;
        }
        Object[] dataChange = new Object[currentCapacity];
        for (int i = 0; i<size;i++){
            dataChange[i] = data[(i+front)%data.length];
        }
        data = dataChange;
        front = 0;
        if(size==0){
            rear = 0;
        }else{
            rear = size-1;
        }
    }

    /** 
     * This method is to add the specified element to the
     * front of the deque and update front.
     * 
     * @param element the element we want to add first 
     */
    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        if (size!=0){
            if(front ==0){
                front = data.length-1;
            }else{
                front = front-1;
            }
        }
        data[front] = element;
        size++;
    }

    /** 
     * This method is to add the specified element to 
     * the rear of the deque and update rear.
     * 
     * @param element the element we want to add last
     */
    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        if(size != 0){
            if(rear == data.length-1){
                rear = 0;
            }else{
                rear = rear+1;
            }
        }
        data[rear] = element;
        size++;
    }

    /** 
     * This method is to removes and returns the element at 
     * the front of the deque if there is such an element. 
     * If there are no elements in the deque, return null.
     * 
     * @return the front element in the deque
     */
    public E removeFirst(){
        if(size==0){
            return null;
        }
        E returnElement = (E) data[front];
        data[front]= null;
        if(size==1){
            size--;
            return returnElement;
        }else if (front == data.length-1){
            front = 0;
            size--;
            return returnElement;
        }else{
            front = front+1;
            size--;
            return returnElement;
        }
    }

    /** 
     * Removes and returns the element at the rear of the 
     * deque if there is such an element. If there are no 
     * elements in the deque, return null.
     * 
     * @return the rear element in the deque
     */
    public E removeLast(){
        if(size==0){
            return null;
        }
        E returnElement = (E) data[rear];
        data[rear]= null;
        if (size ==1){
            size--;
            return returnElement;
        }else if (rear == 0){
            rear = data.length-1;
            size--;
            return returnElement;
        }else{
            rear = rear-1;
            size--;
            return returnElement;
        }
    }

    /** 
     * Returns the element at the front of the deque
     * if there is such an element. If there are no
     * elements in the deque, return null.
     * 
     * @return the front element in the deque
     */
    public E peekFirst(){
        if(size==0){
            return null;
        }
        return (E)data[front];
    }

    /** 
     * Returns the element at the rear of the deque 
     * if there is such an element. If there are no 
     * elements in the deque, return null.
     * 
     * @return the rear element in the deque
     */
    public E peekLast(){
        if(size==0){
            return null;
        }
        return (E)data[rear];
    }
}