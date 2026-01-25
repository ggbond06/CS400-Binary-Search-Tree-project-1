/**
 * Author: Ruijia Hu
 * Course: COMP SCI 400
 * Email: rhu87@wisc.edu
 * Project Name: BinarySearchTree
*/

/**
 * This class is for a Binary search tree that stores values in a sorted order. 
 * It allows duplicate data to be stored 
 * It also contains methods such as insert, contains, size, 
 * isEmpty, and clear to manage the values stored in the tree.
 * Three testing methods are also included to check if the 
 * Binary search tree methods are correctly implemented.
*/
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    
    protected BinaryNode<T> root;

    /** 
     * constructs an empty binary search tree with the root set to null
    */
    public BinarySearchTree() {
        this.root = null;
    }

    /** 
     * inserts a given data to the tree, if root is null, set data to be new root
     * otherwise send it to the insertHelper method and insert the given data
     * to the correct position
     * @param data the given data to be inserted
     * @throws NullPointerExceptio if data is null
    */
    @Override 
    public void insert(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Cannot insert null value into SortedCollection.");
        }

        BinaryNode<T> newNode = new BinaryNode<>(data);
        if (root == null) {
            root = newNode;
            return;
        }

        insertHelper(newNode, root);
    }

     /**
     * Performs the naive binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree. When the provided subtree
     * is null, this method does nothing. 
     */
    protected void insertHelper(BinaryNode<T> newNode, BinaryNode<T> subtree) {
        // TODO: define and make use of this method in your BinarySearchTree class
        if (subtree == null) {
            return;
        }

        if (newNode.getData().compareTo(subtree.getData()) <= 0) {
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getLeft());
            }
        } else {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                newNode.setUp(subtree);
            } else {
                insertHelper(newNode, subtree.getRight());
            }
        }
    }

    
    /** 
     * checks if a given value is in the binary search tree 
     * @param find the given value to search for
     * @return true if the value is found and false otherwise
     * @throws NullPointerException if the given value is null
    */
    @Override 
    public boolean contains(Comparable<T> find) {
        if (find == null) {
            throw new NullPointerException("cannot be null");
        }

        BinaryNode<T> currNode = root;

        while (currNode != null) {
            int compareData = find.compareTo(currNode.getData());
            if (compareData == 0) {
                return true;
            } else if (compareData < 0) {
                currNode = currNode.getLeft();
            } else {
                currNode = currNode.getRight();
            }
        }

        return false;
    }

    /** 
     * count the number of values stored in the tree
     * duplicated values are counted as well
     * @return total number of values in the tree
    */
    @Override
    public int size() {
       return nodeCountHelper(root);
    }

    /** 
     * counts the number of nodes in the subtree using recursion
     * @param node the subtree root
     * @return number of nodes in the subtree
    */
    private int nodeCountHelper(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + nodeCountHelper(node.getLeft()) + nodeCountHelper(node.getRight());
    }

    /** 
     * checks if the binary search tree is empty
     * @return true if the tree is empty and false otherwise
    */
    @Override
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    /** 
     * delete all the values stores in the tree
    */
    @Override
    public void clear() {
        root = null;
    }

    /** 
     * this test builds an integer BST with left and right insertions
     * this also checks for the size after insertion
     * if it contains root, interior values and leaf nodes
     * also test if the find method can detect a nonexisting value in the tree
     * @return true if all tests passed and false otherwise
    */
    public boolean test1() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(20);
        tree.insert(10);
        tree.insert(5);
        tree.insert(30);
        tree.insert(25);
        tree.insert(1);
        tree.insert(40);

        if (tree.size() != 7) {
            return false;
        }

        if (!tree.contains(20)) {
            return false;
        }
        if (!tree.contains(1)) {
            return false;
        }
        if (!tree.contains(40)) {
            return false;
        }
        if (!tree.contains(10)) {
            return false;
        }
        if (!tree.contains(25)) {
            return false;
        }

        if (tree.contains(100)) {
            return false;
        }

        return true;
    }

    /** 
     * this test builds a string BST with a duplicated value and 
     * checks the size after insertion of values. 
     * checks if all the inserted values can be found
     * and the structure like root, left, right nodes and their parent nodes
     * @return true if all tests passed and false otherwise
    */
    public boolean test2() {

        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("b");
        tree.insert("a");
        tree.insert("c");
        tree.insert("c");

        if (tree.size() != 4) {
            return false;
        }

        if (!tree.contains("b")) {
            return false;
        }
        if (!tree.contains("a")) {
            return false;
        }
        if(!tree.contains("c")) {
            return false;
        }

        if (!tree.root.getData().equals("b")) {
            return false;
        }
        if (tree.root.getLeft() == null || !tree.root.getLeft().getData().equals("a")) {
            return false;
        }
        if (tree.root.getLeft().getUp() != tree.root) {
            return false;
        }
        if (tree.root.getRight() == null || !tree.root.getRight().getData().equals("c")) {
            return false;
        }
        if (tree.root.getRight().getUp() != tree.root) {
            return false;
        }

        return true;
    }

    /** 
     * this test builds a BST and 
     * checks the size and clears it 
     * checks if the tree is empty after clearing it and the size
     * builds a second tree and checks the size and if it is empty 
     * and if a given value exists in the new BST.
     * @return true if all tests are passed and false otherwise.
    */
    public boolean test3() {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(20);
        tree.insert(10);
        tree.insert(5);
        tree.insert(30);
        tree.insert(25);
        tree.insert(1);
        tree.insert(40);

        if (tree.size() != 7) {
            return false;
        }
        tree.clear();
        if (!tree.isEmpty()) {
            return false;
        }
        if (tree.size() != 0) {
            return false;
        }

        tree.insert(3);
        tree.insert(2);
        tree.insert(5);
        tree.insert(4);
        tree.insert(1);

        if (tree.size() != 5) {
            return false;
        }
        if (tree.isEmpty()) {
            return false;
        }
        if (!tree.contains(1)) {
            return false;
        }

        return true;
    }

    /**
     * runs test1, test2 and test3 and print their results if they were passed or not
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        System.out.println("Test 1 : " + tree.test1());
        System.out.println("Test 2 : " + tree.test2());
        System.out.println("Test 3 : " + tree.test3());
    }
}