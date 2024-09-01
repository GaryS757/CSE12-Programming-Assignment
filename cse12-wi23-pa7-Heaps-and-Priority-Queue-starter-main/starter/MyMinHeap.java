/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA7 Write-up
   
  This file is for CSE 12 PA7 in Winter 2023,
  This file is to implement min heap data structure with generic 
  type. It’s designed for min heap to do some operations with 
  the generic type based on the Arraylist.
*/

import java.util.ArrayList;
import java.util.Collection;

/**
* This class is called MyMinHeap that implements have a constraint
* on generic parameter E such that E implements Comparable, and
* implements MinHeapInterface interface. That stores some methods,
* and do a min heap data structure. It has percolateDown method, 
* percolateUp method, and so on.
*/
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E>{

    //instance variable
    protected ArrayList<E> data;

    //constructors:
    /** 
     * No-argument constructor that initializes data to an 
     * empty ArrayList
     */
    public MyMinHeap(){
        data = new ArrayList<>();
    }

    
    /** 
     * Constructor to initialize a min-heap using the elements
     * in collection
     * 
     * @param collection the generic type of data put in arraylist
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null||collection.contains(null)){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for(int i=0; i<data.size();i++){
            percolateDown(i);
        }
    }

    //help methods:
    /** 
     * This method is to swap the elements at from and to indices
     * in data.
     * 
     * @param from the index we want to switch index 
     * @param to the index we want to switch index 
     */
    protected void swap(int from, int to){
        E subsitude = data.get(to);
        data.set(to,data.get(from));
        data.set(from,subsitude);
    }

    /** 
     * This method is to calculate and return the parent index
     * of the parameter index.
     * 
     * @param index the index sothat we can get its parent index 
     * @return its parent index 
     */
    protected static int getParentIdx(int index){
        return (index-1)/2;
    }


    /** 
     * This method is to calculate and return the left child 
     * index of the parameter index.
     * 
     * @param index the index sothat we can get its left child index 
     * @return its left child index
     */
    protected static int getLeftChildIdx(int index){
        return index*2+1;
    }

    /** 
     * This method is to calculate and return the right child 
     * index of the parameter index.
     * 
     * @param index the index sothat we can get its right child index 
     * @return its right child index
     */
    protected static int getRightChildIdx(int index){
        return index*2+2;
    }

    /** 
     * This method is to return the index of the smaller child of 
     * the element at index. If the two children are equal, return
     * the index of the left child. If the node at index is a leaf
     * (has no children), return -1.
     * 
     * @param index the index sothat we can get its smaller child index
     * @return its smaller child index 
     */
    protected int getMinChildIdx(int index){
        if(getLeftChildIdx(index)>data.size()-1){
            return -1;
        }else if(getLeftChildIdx(index)==data.size()-1){
            return getLeftChildIdx(index);
        }else if(data.get(getLeftChildIdx(index)).compareTo(
                                data.get(getRightChildIdx(index)))<=0){
            return getLeftChildIdx(index);
        }else{
            return getRightChildIdx(index);
        }
    }

    /** 
     * This method is to Percolate the element at index up until no
     * heap properties are violated by this element (the heap 
     * properties will not be violated once this element's parent
     * is not greater than it). 
     * 
     * @param index the index that we need to percolateUp
     */
    protected void percolateUp(int index){
        while(index>0){
            if(data.get(index).compareTo(data.get(getParentIdx(index)))<0){
                swap(index,getParentIdx(index));
            }
            index = getParentIdx(index);
        }
    }

    /** 
     * This method is to Percolate the element at index down until
     * no heap properties are violated by this element (the heap
     * properties will not be violated once this element is not
     * greater than its children). If swapping is needed, always 
     * swap places with the smaller child. If both children are equal
     * and swapping is needed, swap with the left child.
     * 
     * @param index the index that we need to percolateDown
     */
    protected void percolateDown(int index){
        int smallerIdx = getMinChildIdx(index);
        while(smallerIdx>0){
            if(data.get(index).compareTo(data.get(smallerIdx))>0){
                swap(index,smallerIdx);
            }
            index = smallerIdx;
            smallerIdx = getMinChildIdx(index);
        }

    }

    /** 
     * This method is to Remove the element at index from data
     * and return it. In addition, this method needs to percolate 
     * this element down or percolate this element up as necessary
     * until no heap properties are violated by this element.
     * 
     * @param index the index that we want to delete
     * @return the generic object of that index
     */
    protected E deleteIndex(int index){
        E removed = data.get(index);
        if(index == data.size()-1){
            data.remove(index);
            return removed;
        }else{
            E fixElement = data.remove(data.size()-1);
            data.set(index,fixElement);
            percolateDown(index);
            percolateUp(index);
            return removed;
        }
    }

    //core methods:

    /** 
     * This method is to add element to the end of the heap, and
     * percolate only the inserted element up until no heap properties
     *  are violated.
     * 
     * @param element the element that we want to add
     */
    public void insert(E element){
        if(element == null){
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size()-1);
    }

    /** 
     * This method is to return the root (this will be the smallest)
     * element of the heap. If the heap is empty, return null instead.
     * 
     * @return the smallest element in the arraylist
     */
    public E getMin(){
        if(data.size()==0){
            return null;
        }
        return data.get(0);
    }

    /** 
     * This method is to remove and return the root (this will be the
     * smallest) element in the heap. Use deleteIndex() helper method
     * here. If the heap is empty, return null instead.
     * 
     * @return the smallest element in the arraylist
     */
    public E remove(){
        if(data.size()==0){
            return null;
        }
        E result = getMin();
        deleteIndex(0);
        return result;
    }

    /** 
     * This method is to return the number of elements in this
     * min-heap.
     * 
     * @return the number of the elements 
     */
    public int size(){
        return data.size();
    }

    /** 
     * This method is to clear out the entire heap (the heap
     * should be empty after this call).
     */
    public void clear(){
        data.clear();
    }
}