/*
 * Name: James Lu
 */

import java.util.*;

/**
 * TODO
 * 
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private static int DEFAULT_SIZE = 6;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[DEFAULT_SIZE];
        this.d = 2;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.d = 2;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1){
            throw new IllegalArgumentException();
        }
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Returns number of elements in the heap
     *
     * @return number of elements in the heap
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Adds a value to the heap
     *
     * @param data data to be added
     * @throws NullPointerException if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if (data == null){
            throw new NullPointerException();
        }
        if (nelems + this.d > this.heap.length - 1){
            this.resize();
        }
        if (this.nelems == 0){
            this.heap[0] = data;
            this.nelems++;
        }else {
            int newIdx = this.size();
            this.heap[newIdx] = data;
            this.nelems++;
            this.bubbleUp(newIdx);
        }
    }

    /**
     * Removes an element from the top of the heap
     *
     * @return Max / min value from heap
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.nelems == 0){
            throw new NoSuchElementException();
        }
        T elem = this.heap[0];
        this.heap[0] = this.heap[this.size() - 1];
        this.heap[this.size() - 1] = null;
        this.nelems--;
        this.trickleDown(0);

        return elem;
    }

    /**
     * Clears the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[DEFAULT_SIZE];
        this.nelems = 0;
    }

    public T element() throws NoSuchElementException {
        if (this.nelems == 0){
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }

    /**
     * Helper method to trickle down value after removal
     *
     * @param index index of array to trickle down
     */
    private void trickleDown(int index) {
        int lIdx = 0;
        int rIdx = 0;

        if (this.size() == 2){
            lIdx = this.d * index;
            rIdx = this.d * index + 1;
        }else {
            lIdx = this.d * index + 1;
            rIdx = this.d * index + this.d;
        }

        int smallestOrBiggestChild = lIdx;

        //is leaf
        if (lIdx > this.size() - 1){
            return;
        }

        for (int i = lIdx + 1; i < rIdx + 1; i++) {
            if (this.heap[i] == null){
                continue;
            }
            if((this.isMaxHeap && (comp(i, smallestOrBiggestChild) > 0)) ||
                    (!this.isMaxHeap && (comp(i, smallestOrBiggestChild) < 0))){
                smallestOrBiggestChild = i;
            }
        }

        //no children smaller or bigger than
        if ((this.isMaxHeap && (comp(index, smallestOrBiggestChild) >= 0)) ||
                (!this.isMaxHeap && (comp(index, smallestOrBiggestChild) <= 0))){
            return;
        }
        //swap
        T tmp = this.heap[index];
        this.heap[index] = this.heap[smallestOrBiggestChild];
        this.heap[smallestOrBiggestChild] = tmp;

        trickleDown(smallestOrBiggestChild);
//        while(true){
//            int lIdx = 0;
//            int rIdx = 0;
//
//            if (this.size() == 2){
//                lIdx = this.d * index;
//                rIdx = this.d * index + 1;
//            }else {
//                lIdx = this.d * index + 1;
//                rIdx = this.d * index + this.d;
//            }
//
//            int smallestOrBiggestChild = lIdx;
//
//            //is leaf
//            if (lIdx > this.size() - 1) {
//                break;
//            }
//
//            for (int i = lIdx + 1; i < rIdx + 1; i++) {
//                if (this.heap[i] == null){
//                    continue;
//                }
//                if ((this.isMaxHeap && (comp(i, smallestOrBiggestChild) > 0)) ||
//                        (!this.isMaxHeap && (comp(i, smallestOrBiggestChild) < 0))) {
//                    smallestOrBiggestChild = i;
//                }
//            }
//
//            //no children smaller or bigger than
//            if ((this.isMaxHeap && (comp(index, smallestOrBiggestChild) >= 0)) ||
//                    (!this.isMaxHeap && (comp(index, smallestOrBiggestChild) <= 0))) {
//                break;
//            }
//
//            //swap
//            T tmp = this.heap[index];
//            this.heap[index] = this.heap[smallestOrBiggestChild];
//            this.heap[smallestOrBiggestChild] = tmp;
//
//            index = smallestOrBiggestChild;
//        }
    }

    /**
     * Helper method to bubble up after adding a value
     *
     * @param index index to bubble up from
     */
    private void bubbleUp(int index) {
        if ((this.isMaxHeap && (comp(index, this.parent(index)) <= 0)) ||
                ((!this.isMaxHeap && (comp(index, this.parent(index)) >= 0)))){
            return;
        }
        T tmp = this.heap[index];
        this.heap[index] = this.heap[this.parent(index)];
        this.heap[this.parent(index)] = tmp;
        if (this.parent(index) == 0){
            return;
        }
        bubbleUp(this.parent(index));
    }

    /**
     * Comparison to account for max / min heap
     *
     * @param index this index
     * @param other other index
     * @return > 0 if this > that, < 0 if this < that, == 0 if this == that
     */
    private int comp(int index, int other){
        return this.heap[index].compareTo(this.heap[other]);
    }

    /**
     * Resizes the underlying array if the heap is too large
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
    }

    /**
     * get parent of index
     *
     * @param index index to find parent of
     * @return the parent index
     */
    private int parent(int index) {
        return (index - 1) / this.d;
    }

    /**
     * helper method to aid with testing
     *
     * @return heap array
     */
    public T[] getHeap(){
        return Arrays.copyOf(this.heap, this.nelems);
    }

}
