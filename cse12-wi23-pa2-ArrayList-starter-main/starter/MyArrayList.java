/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA2 Write-up
   
  This file is for CSE 12 PA2 in Winter 2023,
  This file is to implement a data structure similar 
  to Java’s ArrayLists with generic types
*/

/**
*This class is called MyArrayList that stores some methods,
*and do a data structure similar to Java's ArrayList. It has
*insert method, append method, preappend method and so on.
*/
public class MyArrayList<E> implements MyList<E>{
    Object[] data;
    int size;

    public MyArrayList(){
    	data = new Object[5];
    	size = 0;
    	//for()
    }

    public MyArrayList(int initialCapacity){
        if(initialCapacity<0){
            throw new IllegalArgumentException();
        }
        else{
            data = new Object[initialCapacity];
            size = 0;
        }
    }

    public MyArrayList (E[] arr){
        if(arr == null){
        	data = new Object[5];
        	size = 0;
        }
        else{
            data = new Object[arr.length];
            size = arr.length;
            for (int i = 0; i < arr.length; i++) {
            	data[i] = arr[i];
            }
        }
    }

    
    /**
     * That method is to, according to the required capacity,
     * expand the current capacity with some specfic rules.
     *
     * @param requiredCapacity the require Capacity we need
     */
    public void expandCapacity (int requiredCapacity){
        if(data.length > requiredCapacity){
            throw new IllegalArgumentException();
        }
        
        Object[] tmpData;
    	
    	if(data.length != 0){
            tmpData = new Object[data.length+3];
        }
    	else{
        	tmpData = new Object[5];
        }
    	
        if(tmpData.length < requiredCapacity){
            tmpData = new Object[requiredCapacity];
        }
        
        for(int i = 0; i <size; i++) {
        	tmpData[i] = data[i];
        }
        
        data = tmpData;
        
        return;
    }

    /**
     * That method is to get the current capacity
     *
     * @return now the current capacity
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * That method is to insert a specific element into the 
     * specific position that is index.
     *
     * @param index the specfic position we need to insert
     * @param element the specfic element we need to insert
     */
    public void insert(int index, E element){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException();
        }
        else if(size == data.length){
            expandCapacity(data.length+1);
            insert(index, element);
        }
        else {
        	int i = 0;
        	for(i = size; i>index; i--) {
        		data[i] = data[i-1];
        	}
        	data[i] = element;
        	size++;
        }
        
        
    }

    /**
     * That method is to add an element in the end of
     * the list.
     *
     * @param element the specfic element we need to add
     */
    public void append(E element){
        if(size == data.length){
            expandCapacity(data.length+1);
        }
        data[size] = element;
        size++;
    }

    /**
     * That method is to add an element in the beginning 
     * of the list.
     *
     * @param element the specfic element we need to add
     */
    public void prepend(E element){
    	insert(0,element);
    }

    /**
     * That method is to get the specific element in
     * the index that we want to get.
     *
     * @param index the specific position we need
     * @return the specific number we want to get
     */
    public E get(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        return (E)data[index];
    }

    /**
     * That method is to change the specific position
     * into the number that we want to put 
     *
     * @param index the specific position 
     * @param element the element we want to put
     * @return the overwritten element.
     */
    public E set(int index, E element){
        if(index<0 || index>=size){
        	throw new IndexOutOfBoundsException();
        }
        
        E tmpData = (E)data[index];
        data[index] = element; 
      
        return (E)tmpData;
    }

    /**
     * That method is to remove the element in the
     * specific index
     *
     * @param index the specific position that remove
     * @return the element at the specified index.
     */
    public E remove(int index){
        if(index<0 || index>=size){
        	throw new IndexOutOfBoundsException();
        }
        E tmpData = (E)data[index];
        
        for  (int i = index; i < size-1; i++) {
        	data[i] = data[i+1];
        }
        size--;
        data[size] = null;
        
        return tmpData;
    }

    /**
     * That method is to return the current size
     * of the arraylist
     *
     * @return the number of exsit elements in ArrayList
     */
    public int size(){
    	return size;
    }

}