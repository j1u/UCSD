/*
 * NAME: James Lu
 */

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * TODO
 * @author James Lu
 * @since 4/26/2021
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            Node nxt = this.next;
            Node prv = this.prev;
            nxt.setPrev(prv);
            prv.setNext(nxt);
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.nelems = 0;
        this.head = new Node(null);
        this.tail = new Node(null);

        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null){
            throw new NullPointerException();
        }

        Node newNode = new Node(element, this.tail, this.tail.getPrev());
        this.tail.getPrev().setNext(newNode);
        this.tail.setPrev(newNode);
        this.nelems++;

        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     * @param index index to insert data
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if index < 0 or index > size of list
     * @throws NullPointerException if element == null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null){
            throw new NullPointerException();
        }
        if (index < 0 || index > this.nelems - 1){
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.getNth(index);
        Node newNode = new Node(element, curr, curr.getPrev());
        curr.getPrev().setNext(newNode);
        curr.setPrev(newNode);

        this.nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.nelems = 0;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element element to be checked for
     * @return if element was found in list
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node tmp = this.head.next;
        for (int i = 0; i < this.nelems; i++) {
            if (tmp.getElement().equals(data)){
                return true;
            }
            tmp = tmp.getNext();

        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index index to get element
     * @return element at index
     * @throws IndexOutOfBoundsException if index < 0 or index > size of list
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.nelems - 1){
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.getNth(index);

        return curr.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index index to get node
     * @return node at index
     * @throws IndexOutOfBoundsException if index < 0 or index > size of list
     */
    private Node getNth(int index) {
        if (index < 0 || index > this.nelems - 1){
            throw new IndexOutOfBoundsException();
        }
        Node tmp = this.head.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    /**
     * Determine if the list empty
     *
     * @return whether or not the list is empty
     */
    @Override
    public boolean isEmpty() {
        return this.nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index index to remove element
     * @return removed element
     * @throws IndexOutOfBoundsException if index < 0 or index > size of list
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.nelems){
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.getNth(index);
        T elem = curr.getElement();
        curr.remove();
        this.nelems--;

        return elem;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index index of element to change
     * @param element new element to set
     * @return old element
     * @throws IndexOutOfBoundsException if index < 0 or index > size of list
     * @throws NullPointerException if element == null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null){
            throw new NullPointerException();
        }
        if (index < 0 || index >= this.nelems){
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.getNth(index);
        T elem = curr.getElement();
        curr.setElement(element);

        return elem;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return size of list
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return string representation of list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[(head) -> ");
        for (int i = 0; i < this.nelems; i++) {
            T tmp = this.get(i);
            sb.append(tmp);
            sb.append(" -> ");
        }
        sb.append("(tail)]");
        return sb.toString();
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * @param base base to remove indices at
     * @throws IllegalArgumentException if base < 1
     */
    public void removeMultipleOf(int base) {
        if (base < 1){
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> indices = new ArrayList<>();
        int dec = 0;

        for (int i = 0; i < this.size(); i += base) {
            indices.add(i - dec);
            dec++;
        }

        for (int i: indices) {
            this.remove(i);
        }
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * @param other other list to swap with
     * @param splitIndex index to split at
     */
    public void swapSegment(DoublyLinkedList other, int splitIndex) {
        Node thisStart = this.head.getNext();
        Node thisEnd = this.getNth(splitIndex);
        Node otherStart = other.head.getNext();
        Node otherEnd = other.getNth(splitIndex);

        this.head.setNext(otherStart);
        otherStart.setPrev(this.head);

        other.head.setNext(thisStart);
        thisStart.setPrev(other.head);

        Node thisNext = thisEnd.getNext();
        Node otherNext = otherEnd.getNext();

        thisEnd.setNext(otherNext);
        otherNext.setPrev(thisEnd);

        otherEnd.setNext(thisNext);
        thisNext.setPrev(otherEnd);

    }

}
