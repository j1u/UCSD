/*
    Name: James Lu
 */

import java.util.EmptyStackException;

/**
 * Dynamic stack data structure that handles Strings, grows and shrinks according to specified
 * load and shrink factors
 * @author James Lu
 * @since  9 April 2021
 */

public class StringStack {

    //declare initial capacity, dynamic capacity, load factor, shrink factor, size, and stack
    private int initCapacity;
    private int capacity;
    private double loadF;
    private double shrinkF;
    private int size;
    private String[] stack;
    private final int capacityLimit = 5;
    private final double lFLimit = .67;
    private final double sFLimit = .33;
    private final int defaultCapacity = 10;
    private final double defaultLoadF = .75;
    private final double defaultShrinkF = .25;

    /**
     * Empty constructor that initializes instance variables to default values capacity = 10,
     * loadFactor = .75, shrinkFactor = .25
     */
    public StringStack(){
        initCapacity = defaultCapacity;
        capacity = defaultCapacity;
        loadF = defaultLoadF;
        shrinkF = defaultShrinkF;
        size = 0;
        stack = new String[capacity];
    }

    /**
     * Constructor that initializes instance variables capacity, load factor, and shrink factor
     * @param cap capacity for stack to be initialized with
     * @param lF load factor that determines when the stack should grow
     * @param sF shrink factor that determines when the stack should shrink
     *
     * @throws IllegalArgumentException if cap < 5, lF > 1, lF < .67, sF <= 0, sF > .33
     */
    public StringStack(int cap, double lF, double sF){
        if (cap < capacityLimit || lF > 1 || lF < lFLimit || sF <= 0 || sF > sFLimit){
            throw new IllegalArgumentException();
        }
        initCapacity = cap;
        capacity = cap;
        loadF = lF;
        shrinkF = sF;
        size = 0;
        stack = new String[capacity];
    }


    /**
     * Constructor that initializes instance variables capacity and load factor, shrink factor is
     * initialized to default value .25
     * @param cap capacity for stack to be initialized with
     * @param lF load factor that determines when the stack should grow
     *
     * @throws IllegalArgumentException if cap < 5, lF > 1, lF < .67
     */
    public StringStack(int cap, double lF){
        if (cap < capacityLimit || 1 < lF || lF < lFLimit){
            throw new IllegalArgumentException();
        }
        initCapacity = cap;
        capacity = cap;
        loadF = lF;
        shrinkF = defaultShrinkF;
        size = 0;
        stack = new String[capacity];
    }

    /**
     * Constructor that initializes instance variables capacity, load factor and shrink factor are
     * initialized to default values of .75 and .25 respectively
     * @param cap capacity for stack to be initialized with
     *
     * @throws IllegalArgumentException if cap < 5
     */
    public StringStack(int cap){
        if (cap < capacityLimit){
            throw new IllegalArgumentException();
        }
        initCapacity = cap;
        capacity = cap;
        loadF = defaultLoadF;
        shrinkF = defaultShrinkF;
        size = 0;
        stack = new String[capacity];
    }

    /**
     * Returns if the stack is empty
     * @return Whether or not stack is empty
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Clears stack
     */
    public void clear(){
        stack = new String[capacity];
        size = 0;
    }

    /**
     * Returns number of elements inside the stack
     * @return number of elements inside the stack
     */
    public int size(){
        return size;
    }

    /**
     * Returns the current capacity of the stack
     * @return current capacity of the stack
     */
    public int capacity(){
        return capacity;
    }

    /**
     * Returns the top element in the stack without removing it
     * @return top element in the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public String peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return stack[size-1];
    }

    /**
     * Pushes an element into the stack
     * @param element the element to be pushed into the stack
     */
    public void push(String element){
        int growthRate = 2;
        size += 1;
        if (((double)size / capacity) >= loadF){
            //increases capacity
            String[] tmp = new String[growthRate * capacity];
            //copies current stack to temp array
            System.arraycopy(stack, 0, tmp, 0, size);
            //sets stack to temp array
            capacity *= 2;
            stack = tmp;
        }
        stack[size-1] = element;
    }

    /**
     * Removes the top item in the stack and returns it
     * @return top item in the stack
     *
     * @throws EmptyStackException if stack is empty
     */
    public String pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        int shrinkRate = 2;
        size -= 1;

        //declare return object, retrieve it, and remove it from the stack
        String ret;
        if (size > 0) {
            ret = stack[size];
            stack[size] = null;
        }else {
            ret = stack[0];
            stack[0] = null;
        }

        if (((double)size / capacity) <= shrinkF ){
            int newCapacity = capacity / shrinkRate;

            if (newCapacity < this.initCapacity){
                newCapacity = this.initCapacity;
            }

            //temp array with halved capacity
            String[] tmp = new String[newCapacity];
            //copy stack to new temp array
            System.arraycopy(stack, 0, tmp, 0, size);
            //update capacity and stack
            capacity = newCapacity;
            stack = tmp;
        }
        return ret;
    }

    /**
     * Pushes multiple elements into the stack
     * @param elements array of elements to be pushed into the stack
     *
     * @throws IllegalArgumentException if elements == null
     */
    public void multiPush(String[] elements){
        if (elements == null){
            throw new IllegalArgumentException();
        }
        for (String i : elements) {
            push(i);
        }
    }

    /**
     * Pops a specified number of elements from the stack
     * @param amount amount to be popped from stack
     * @return array containing all the popped items
     *
     * @throws IllegalArgumentException if amount <= 0
     */
    public String[] multiPop(int amount){
        if (amount <= 0){
            throw new IllegalArgumentException();
        }
        String[] tmp;
        //if amount is greater than or equal to the size of the stack, pop all items
        // from the stack
        if (amount >= size){
            //capture size because it is updated each time pop is called
            int sz = size;
            tmp = new String[sz];
            for (int i = 0; i < sz; i++){
                tmp[i] = pop();
            }
            //if amount is less than size of stack then pop specified amount
        }else {
            tmp = new String[amount];
            for (int i = 0; i < amount; i++){
                tmp[i] = pop();
            }
        }
        return tmp;
    }
}
