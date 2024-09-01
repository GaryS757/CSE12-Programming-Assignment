/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA8 Write-up
   
  This file is for CSE 12 PA8 in Winter 2023,
  This file is to implement Binary Search tree data structure 
  with generic type. It’s designed for to do some operations
  with the generic type based on the connection between nodes.
*/
import java.util.ArrayList;

/**
 * This class is called MyBST that MyBST has the property that
 * all nodes in the left subtree of a node will have a key smaller
 * than node.key, and all nodes in the right subtree of node will
 * have a key greater than node.key. It has insert method, search
 * method, removed method, and so on.
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    /** 
     * This method is to return the size of the binary search
     * tree that is the number of node in it
     * 
     * @return the number of node in the binary serach tree
     */
    public int size() {
        return size;
    }

    /** 
     * This method is to insert a new node containing the arguments
     * key and value into the binary search tree according to the 
     * binary search tree properties. Update the tree size accordingly.
     * 
     * @param key the key of the node that we want to insert
     * @param value the value of the node that we want to insert
     * @return if exist in tree, return old value; if not, return null 
     */
    public V insert(K key, V value) {
        if(key == null){
            throw new NullPointerException();
        }
        if(root == null){
            root = new MyBSTNode<K,V>(key, value, null);
            size++;
            return null;
        }
        MyBSTNode<K, V> current_node = root;
        MyBSTNode<K, V> add_node = 
                                new MyBSTNode<K, V>(key, value, current_node);
        while(true){
            if(key.compareTo(current_node.getKey())==0){
                V old_value =current_node.getValue(); 
                current_node.setValue(value);
                return old_value;
            }else if(key.compareTo(current_node.getKey())<0){
                if(current_node.getLeft()==null){
                    current_node.setLeft(add_node);
                    add_node.setParent(current_node);
                    size++;
                    return null;
                }else{
                    current_node = current_node.getLeft();
                }
            }else{
                if(current_node.getRight()==null){
                    current_node.setRight(add_node);
                    add_node.setParent(current_node);
                    size++;
                    return null;
                }else{
                    current_node = current_node.getRight();
                }
            } 
        }
    }

    /** 
     * This method is to search for a node with key equal to key and
     * return the value associated with that node. The tree structure
     * should not be affected by this.
     * 
     * @param key the key that we want to search
     * @return if exist in tree, return value; if not, return null 
     */
    public V search(K key) {
        if(key ==null){
            return null;
        }
        MyBSTNode<K, V> current_node = root;
        while(current_node != null){
            if(key.compareTo(current_node.getKey())==0){
                return current_node.getValue();
            }else if(key.compareTo(current_node.getKey())<0){
                current_node = current_node.getLeft();
            }else{
                current_node = current_node.getRight();
            }
        }
        return null;
    }

    /** 
     * This method is to search for a node with key equal to key 
     * and return the value associated with that node. The node 
     * should be removed (disconnected) from the tree and all 
     * references should be fixed, if needed. Update the tree size
     * accordingly.
     * 
     * @param key the key that we want to remove
     * @return if exist in tree,return removed value;if not,return null 
     */
    public V remove(K key) {
        if(key == null){
            return null;
        }

        MyBSTNode<K, V> current_node = root;
        MyBSTNode<K, V> removed_node_parent = current_node;
        while(current_node != null){
            if(key.compareTo(current_node.getKey())<0){
                if(current_node.getLeft()==null){
                    return null;
                }else{
                    current_node = current_node.getLeft();
                }
            }else if(key.compareTo(current_node.getKey())>0){
                if(current_node.getRight()==null){
                    return null;
                }else{
                    current_node = current_node.getRight();
                }
            }else{
                V return_value = current_node.getValue();
                if(current_node.getLeft()==null && 
                                                current_node.getRight()==null){
                    if(removed_node_parent==null){
                        root = null;
                    }else if(current_node.getParent().getLeft()==current_node){
                        current_node.getParent().setLeft(null);
                    }else{
                        current_node.getParent().setRight(null);
                    }
                }else if (current_node.getLeft() != null &&
                             current_node.getRight() != null){
                    MyBSTNode<K,V> successor = current_node.successor();
                    remove(successor.getKey());
                    size++;
                    current_node.setKey(successor.getKey());
                    current_node.setValue(successor.getValue());
                }else if(current_node.getLeft()==null){
                    if(removed_node_parent==null){
                        root = current_node.getRight();
                    }else if(current_node.getParent().getLeft()==current_node){
                        current_node.getParent().setLeft(
                            current_node.getRight());
                    }else{
                        current_node.getParent().setRight(
                            current_node.getRight());
                    }
                }else if(current_node.getRight()==null){
                    if(removed_node_parent==null){
                        root = current_node.getLeft();
                    }else if(current_node.getParent().getLeft()==current_node){
                        current_node.getParent().setLeft(
                            current_node.getLeft());
                    }else{
                        current_node.getParent().setRight(
                            current_node.getLeft());
                    }
                }
                size--;
                return return_value;
            }
        }
        return null;
    }

    /** 
     * This method is to do an in-order traversal of the tree, 
     * adding each node to the end of an ArrayList, which will
     * be returned.
     * 
     * @return the Arraylist of node in order
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K,V>> resultlist = new ArrayList<>();
        if(size==0){
            return resultlist;
        }
        MyBSTNode<K, V> current_node = root;
        while(current_node.getLeft()!=null){
            current_node = current_node.getLeft();
        }

        while(current_node!=null){
            resultlist.add(current_node);
            current_node = current_node.successor();
        }

        return resultlist;
    }

    /**
     * This class is a static nested class of the MyBST class. it
     * contains some methods, like successor method, tostring method
     * and so on.
     */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        /** 
         * instance variables 
         */
        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /** 
         * This method is to return the node with the smallest 
         * key that is still larger than the key of the current node.
         * If there is no larger key, return null.
         * 
         * @return the bigger a little bit node
         */
        public MyBSTNode<K, V> successor() {
            if(this.getRight()!=null){
                MyBSTNode<K, V> result = this.getRight();
                while(result.getLeft()!=null){
                    result = result.getLeft();
                }
                return result;
            }else{
                MyBSTNode<K, V> result = this;
                while(result.getParent()!=null && 
                        result.getParent().getRight()==result){
                    result = result.getParent();
                }
                return result.getParent();
            }
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
