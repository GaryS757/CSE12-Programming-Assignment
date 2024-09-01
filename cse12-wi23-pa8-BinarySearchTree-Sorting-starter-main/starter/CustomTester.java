/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA8 Write-up
   
  This file is for CSE 12 PA8 in Winter 2023,
  This file is to test MyBST.java that implements Binary Search 
  tree data structure with generic type. 
*/
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
* This class is called CustomTester that tests MyBST
*/
public class CustomTester {
    MyBST<Integer, Integer> tree;
    
    /**
     * setup
     */   
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(6, 60, null);
        MyBST.MyBSTNode<Integer, Integer> four =
                new MyBST.MyBSTNode<>(4, 40, root);
        MyBST.MyBSTNode<Integer, Integer> eight =
                new MyBST.MyBSTNode<>(8, 80, root);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, four);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, three);
        MyBST.MyBSTNode<Integer, Integer> seven =
                new MyBST.MyBSTNode<>(7, 70, five);
        MyBST.MyBSTNode<Integer, Integer> ten =
                new MyBST.MyBSTNode<>(10, 100, eight);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(four);
        root.setRight(seven);
        four.setLeft(three);
        four.setRight(five);
        seven.setRight(eight);
        eight.setRight(ten);
        tree.size = 7;
    }

    /**
     * test successor
     */   
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getRight(), treeRoot.successor());
    }

    /**
     * test Insert
     */   
    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(11, 1);
        assertEquals(11, root.getRight().getRight().getRight().getRight().getKey().intValue());
        assertSame(root.getRight().getRight().getRight(), root.getRight().getRight().getRight().getRight().getParent());
        assertEquals("size of tree", 8, tree.size);

        tree.insert(4,39);
        assertEquals("size of tree", 8, tree.size);
    }

    /**
     * test search
     */   
    @Test
    public void testSearch() {
        assertEquals(100, tree.search(10).intValue());
        assertNull(tree.search(15));
        assertEquals("size of tree", 7, tree.size);
    }

    /**
     * test remove
     */   
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(80, tree.remove(8).intValue());
        assertEquals("size of tree", 6, tree.size);
        assertEquals(60, tree.remove(6).intValue());
        assertEquals("size of tree", 5, tree.size);
        assertNull(tree.remove(40));
    }

    /**
     * test In order
     */   
    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        tree.remove(10);
        tree.remove(8);
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        assertEquals(5, tree.size());
    }

}