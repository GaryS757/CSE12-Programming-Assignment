/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA7 Write-up
   
  This file is for CSE 12 PA7 in Winter 2023,
  This file is to implement min MyPriorityQueue data structure 
  with generic type. It’s designed for MyPriorityQueue to do 
  some operations with the generic type based on min Heap. 
  A priority queue is a queue where elements are sorted by 
  their priority.
*/

import java.util.Collection;

/**
* This class is called MyPriorityQueue. Our MyPriorityQueue 
* implementation will be using a MyMinHeap backend. Our 
* underlying data structure is a min-heap, so smaller elements
* have higher priorities. The root node of the min-heap is the
* one with the highest priority and is also the smallest element
* in the min-heap.
*/
public class MyPriorityQueue<E extends Comparable<E>>{

    //instance variable
    protected MyMinHeap<E> heap;

    //Constructors
    /** 
     * This no-argument constructor initializes heap to be an 
     * empty MyMinHeap.
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /** 
     * The constructor initializes heap to contain the elements
     * in collection. That will throw a NullPointerException if 
     * collection or any element in collection is null.
     * 
     * @param collection the generic type of data put in MyMinHeap
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        if(collection == null||collection.contains(null)){
            throw new NullPointerException();
        }
        heap = new MyMinHeap<>(collection);
    }

    //Methods
    /** 
     * This method is to add element to this priority queue.
     * The heap should be fixed accordingly. That will throw a 
     * NullPointerException and do not add to the priority queue
     * if element is null.
     * 
     * @param element the element that we want to add
     */
    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    /** 
     * This method is to return the element with the highest 
     * priority.The element in our min-heap is the smallest
     * element. If priority queue is empty, return null instead.
     * 
     * @return the smallest element in the heap
     */
    public E peek(){
        if(heap.size()==0){
            return null;
        }
        return heap.getMin();
    }

    /** 
     * This method is to remove and return the element with 
     * the highest priority.The element in our min-heap is 
     * the smallest element. If priority queue is empty, 
     * return null instead.
     * 
     * @return the smallest element in the heap
     */
    public E pop(){
        if(heap.size()==0){
            return null;
        }
        return heap.remove();
    }

    /** 
     * This method is to return the number of elements in 
     * the priority queue.
     * 
     * @return the number of the element in the heap
     */
    public int getLength(){
        return heap.size();
    }

    /** 
     * This method is to clear out the entire priority queue
     * (the priority queue should be empty after this call).
     */
    public void clear(){
        heap.clear();
    }
}