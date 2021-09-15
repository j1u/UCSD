/*
 * Name: James Lu
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author James Lu
 * @since  5/10/2021
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.nelems = 0;
        root = new BSTNode(null, null, null);
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        BSTNode newNode = new BSTNode(null, null, key);
        if (this.getRoot().getKey() == null){
            this.root = newNode;
            this.nelems++;
            return true;
        }

        BSTNode curr = this.root;

        return insertHelper(curr, newNode);
    }

    /**
     * recursive helper method to insert into tree
     *
     * @param curr current node
     * @param newNode node to be added
     * @return whether or not the add was successful
     */
    private boolean insertHelper(BSTNode curr, BSTNode newNode){
        int comparedVal = curr.getKey().compareTo(newNode.getKey());
        if (comparedVal == 0){
            return false;
        }
        if ((curr.getLeft() == null) && (comparedVal > 0)){
            curr.setleft(newNode);
            this.nelems++;
            return true;
        }else if ((curr.getRight() == null) && (comparedVal < 0)){
            curr.setright(newNode);
            this.nelems++;
            return true;
        }
        if ((curr.getLeft() != null) && (comparedVal > 0)) {
            return insertHelper(curr.getLeft(), newNode);
        } else if ((curr.getRight() != null) && (comparedVal < 0)) {
            return insertHelper(curr.getRight(), newNode);
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null){
            throw new NullPointerException();
        }
        return recursiveFind(this.root, key);
    }

    private boolean recursiveFind(BSTNode curr, T key){
        if (curr == null || curr.getKey() == null){
            return false;
        }
        int compareVal = curr.getKey().compareTo(key);
        if (curr.getLeft() == null && curr.getRight() == null && compareVal != 0){
            return false;
        }
        if (compareVal == 0){
            return true;
        }else if (compareVal > 0){
            return recursiveFind(curr.getLeft(), key);
        }else {
            return recursiveFind(curr.getRight(), key);
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null){
            throw new NullPointerException();
        }
        if (!this.findKey(key)){
            throw new IllegalArgumentException();
        }
        BSTNode node = findNode(this.root, key);
        node.addNewInfo(data);
    }

    /**
     * recursive helper method that finds a node in the tree
     *
     * @param curr current node
     * @param key key top be found
     * @return node with the desired key
     */
    private BSTNode findNode(BSTNode curr, T key) {
        int compareVal = curr.getKey().compareTo(key);
        if (compareVal == 0){
            return curr;
        }else if (compareVal > 0){
            return findNode(curr.getLeft(), key);
        }else {
            return findNode(curr.getRight(), key);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null){
            throw new NullPointerException();
        }
        if (!this.findKey(key)){
            throw new IllegalArgumentException();
        }
        BSTNode node = findNode(this.root, key);
        return node.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (this.nelems == 0) {
            return -1;
        }

        return findHeightHelper(this.root) - 1;
    }

    /**
     * Helper for the findHeight method
     *
     * @param curr Current node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode curr) {
        if (curr == null) {
            return 0;
        }else {
            int leftHeight = findHeightHelper(curr.getLeft());
            int rightHeight = findHeightHelper(curr.getRight());

            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            }else {
                return (rightHeight + 1);
            }
        }
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> st;

        /**
         * Iterator constructor that begins at the far left leaf node
         */
        public BSTree_Iterator() {
            this.st = new Stack<>();
            BSTNode curr = getRoot();
            while (curr != null){
                this.st.push(curr);
                curr = curr.getLeft();
            }
        }

        /**
         * Checks if there is another element in the tree
         *
         * @return
         */
        public boolean hasNext() {
            return !this.st.empty();
        }

        /**
         * returns the next element in the tree
         *
         * @return
         */
        public T next() {
            if (!this.hasNext()){
                throw new NoSuchElementException();
            }
            BSTNode curr = this.st.pop();
            T val = curr.getKey();

            if (curr.getRight() != null){
                curr = curr.getRight();
                while (curr != null){
                    this.st.push(curr);
                    curr = curr.getLeft();
                }
            }
            return val;
        }
    }

    /**
     * Iterator wrapper
     *
     * @return new BST iterator
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    /**
     * Finds the intersection between two trees
     *
     * @param iter1 iterator of first tree
     * @param iter2 iterator of second tree
     * @return arraylist of intersecting values
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> tmp1 = new ArrayList<>();
        ArrayList<T> tmp2 = new ArrayList<>();
        while (iter1.hasNext()){
            tmp1.add(iter1.next());
        }
        while (iter2.hasNext()){
            tmp2.add(iter2.next());
        }
        tmp1.retainAll(tmp2);
        return tmp1;
    }

    /**
     * finds the max key at a particular level
     *
     * @param level level top be searched
     * @return the max key at the level
     */
    public T levelMax(int level) {
        if (level == 0){
            return this.getRoot().getKey();
        }
        if (level > this.findHeight()){
            return null;
        }

        return recursiveLevelMax(level, this.getRoot());
    }

    /**
     * Recursive helper for level max
     *
     * @param level level to be searched
     * @param curr current node
     * @return max node at the level
     */
    private T recursiveLevelMax(int level, BSTNode curr){
        if (level == 0) {
            return curr.getKey();
        }

        T left = recursiveLevelMax(level - 1, curr.getLeft());
        T right = recursiveLevelMax(level - 1, curr.getRight());

        int comparison = left.compareTo(right);
        if (comparison < 0) {
            return right;
        } else if (comparison > 0) {
            return left;
        } else {
            return left;
        }
    }
}
