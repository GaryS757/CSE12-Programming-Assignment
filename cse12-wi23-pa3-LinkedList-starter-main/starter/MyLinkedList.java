/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA3 Write-up
   
  This file is for CSE 12 PA3 in Winter 2023,
  This file is to implement a data structure similar to 
  Java's LinkedList and contains some basic methods of the
  LinkedList
*/
import java.util.AbstractList;

/**
*This class is called MyLinkedList that stores some methods,
*and do a data structure similar to Java's LinkedList. It has
*remove method, add method, clear method and so on.
*/
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

//  Implementation of the MyLinkedList Class
    /**
     * That is Constructor that creates an empty list 
     * and initializes all the necessary variables.
     *
     */
    public MyLinkedList() {
        /* Add your implementation here */
        // TODO
    	head = new Node(null);
    	tail = new Node(null);
    	head.setNext(tail);
    	tail.setPrev(head);
    	size = 0;
    }

    /**
     * That method is to return the number of the nodes
     * that stored in the list 
     *
     * @return the number of nodes in list
     */
    @Override
    public int size() {
        // need to implement the size method
        return size; // TODO
    }


    /**
     * That method is to get data of type E within
     * the node at position index.
     *
     * @param index the position of the node we need
     * @return the element of the current node
     */
    @Override
    public E get(int index) {
        if(index <0 || index >=size){
            throw new IndexOutOfBoundsException();
        }
        Node current_node = head.getNext();
        for(int i=0;i<=index-1;i++){
            current_node=current_node.getNext();
        }
        return current_node.getElement() ;  // TODO
    }

    /**
     * That method is add a node into this list by index. The
     * input index can be any integer in between zero and the
     * number of elements (inclusive on both ends).
     *
     * @param index the position of the node we need
     * @param data the element of the node that we want to add
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        // TODO
        if (data == null){
            throw new NullPointerException();
        }
        
        if(index <0 || index >size){
            throw new IndexOutOfBoundsException();
        }
        
        Node node_add = new Node(data);
        
        if(index == 0){
            node_add.setNext(head.getNext());
            node_add.setPrev(head);
            head.getNext().setPrev(node_add);
            head.setNext(node_add);
        }else if(index == size){
        	tail.getPrev().setNext(node_add);
        	node_add.setPrev(tail.getPrev());
        	node_add.setNext(tail);
        	tail.setPrev(node_add);
        }else{
            Node Middle_current = getNth(index);
            node_add.next = Middle_current;
            node_add.prev = Middle_current.prev;
            Middle_current.prev.next = node_add;
            Middle_current.prev = node_add;
        }
        size++;
    }        

    /**
     * That method is to add a node at the end into this list.
     *
     * @param data the element of the node that we want to add
     * @return  true due to method definition in AbstractList.
     */
    public boolean add(E data) {
        if (data == null){
            throw new NullPointerException();
        }

        Node node_add = new Node(data);
        
        tail.getPrev().setNext(node_add);
        node_add.setPrev(tail.getPrev());        
        node_add.setNext(tail);
        tail.setPrev(node_add);
        
        size++;
        
        return true; // TODO
    }

    /**
     * That method is to set the element for the node at the 
     * specified index to data and return the element that was
     *  previously stored in this node.
     * 
     * @param index the position of node that we want to set
     * @param data the element of the node that we want to add
     * @return the element that was previously stored in node
     */
    public E set(int index, E data) {
        if (data == null){
            throw new NullPointerException();
        }
        if(index <0 || index >=size){
            throw new IndexOutOfBoundsException();
        }
        
        Node indexNode = head.getNext();
        
        for(int i=0;i<=index-1;i++){
        	indexNode = indexNode.getNext();
        }
        
        E tmpData = indexNode.getElement();
        
        indexNode.setElement(data);
        
        return tmpData; // TODO
    }

    /**
     * That method is to remove the node from the specified 
     * index in this list and return the data from the 
     * removed node.
     * 
     * @param index the position of node that we want to remove
     * @return the element in node that we remove
     */
    public E remove(int index) {
        if(index <0 || index >=size){
            throw new IndexOutOfBoundsException();
        }
        
        Node tmpNode;
        
        if(index == 0){
        	tmpNode = head.getNext();
            head.setNext(tmpNode.getNext());
            tmpNode.getNext().setPrev(head);
        }else if(index == size -1){
        	tmpNode = tail.getPrev();
            tail.setPrev(tmpNode.getPrev());
            tmpNode.getPrev().setNext(tail);;
        }else{
            Node middle_current = head.getNext();
            for (int i=0; i<index; i++){
                middle_current = middle_current.next;
            }
            tmpNode = middle_current;
            middle_current.prev.next = middle_current.next;
            middle_current.next.prev = middle_current.prev;
        }
        
        size--;

        return tmpNode.getElement(); // TODO
    }

    /**
     * That method is to Remove all (non-sentinel) nodes 
     * from the list.
     */
    public void clear() {
    	head.setNext(tail);
    	tail.setPrev(head);
    	size = 0;
    }

    /**
     * That method is to determine if the list is empty.
     * 
     * @return boolean for if the list is empty
     */
    public boolean isEmpty() {
    	if (size == 0)
    		return true; 
    	else
    		return false;
    }

    /**
     * Tt is a A helper method that returns the Node at 
     * a specified index, not the Node data.
     * 
     * @param index the specific index that we want to get
     * @return the node at a specified index
     */
    protected Node getNth(int index) {
        if(index <0 || index >=size){
            throw new IndexOutOfBoundsException();
        }
        
        Node tmpNode = head.getNext();
        
        for (int i=0; i<index; i++){
        	tmpNode = tmpNode.next;
        }
        return tmpNode;  // TODO
    }
}
