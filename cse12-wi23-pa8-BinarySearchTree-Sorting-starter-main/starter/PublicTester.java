import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PublicTester {
    MyBST<Integer, Integer> tree;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
    }

    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getRight().getLeft(), treeRoot.successor());
    }

    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(10, 1);
        assertEquals(10, root.getRight().getRight().getKey().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals("size of tree", 7, tree.size);
    }

    @Test
    public void testSearch() {
        assertEquals(30, tree.search(3).intValue());
        assertNull(tree.search(10));
    }

    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(30, tree.remove(3).intValue());
        assertNull(root.getLeft().getRight());
        assertEquals(1, tree.remove(6).intValue());
        assertEquals(5, root.getRight().getKey().intValue());
        assertEquals("size of tree", 4, tree.size);
    }

    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        assertEquals(expectedRes, tree.inorder());
    }

    @Test
    public void TestRemoveRootWithTwoChildren(){
        MyBST<Integer, String> bst = new MyBST<>();
        bst.insert(8, "8");
        bst.insert(3, "3");
        bst.insert(10, "10");
        bst.insert(1, "1");
        bst.insert(6, "6");
        bst.insert(14, "14");
        bst.insert(4, "4");
        bst.insert(7, "7");
        bst.insert(13, "13");

        assertEquals("10", bst.root.successor().getValue());
        assertEquals("8", bst.remove(8));
        assertEquals("10", bst.root.getValue());
        assertEquals("3", bst.root.getLeft().getValue());
        assertEquals("14", bst.root.getRight().getValue());
        assertEquals("13", bst.root.getRight().getLeft().getValue());
        assertEquals(8, bst.size());
    }
}

