/*
 * Name: James Lu
 */

import java.util.Arrays;

/**
 * Hash Table class that uses simplified CRC function to hash values
 * 
 * @author James Lu
 * @since 5/24/21
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());
    private static final int DEFAULT_CAPACITY = 15;
    private static final int MIN_CAPACITY = 5;
    private static final double REHASH_FACTOR = .55;

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private double loadF; //load factor
    private int rehashCount;
    private int collisionCount;
    StringBuilder stats = new StringBuilder(); //statistics

    /**
     * Default Hashtable constructor using default capacity of 15
     */
    public HashTable() {
        this.size = 0;
        this.table = new String[DEFAULT_CAPACITY];
        this.loadF = ((double) this.size() / (double) this.capacity());
        this.rehashCount = 0;
        this.collisionCount = 0;
    }

    /**
     * Hashtable constructor with capacity parameter
     *
     * @param capacity desired capacity
     * @throws IllegalArgumentException if capacity < 5
     */
    public HashTable(int capacity) {
        if (capacity < MIN_CAPACITY){
            throw new IllegalArgumentException();
        }
        this.size = 0;
        this.table = new String[capacity];
        this.loadF = ((double)this.size() / (double)this.capacity());
        this.rehashCount = 0;
        this.collisionCount = 0;
    }

    /**
     * Insert value into hashtable, returns false if value is already in table
     *
     * @param value value to insert
     * @return whether or not insert was successful
     * @throws NullPointerException if value == null
     */
    @Override
    public boolean insert(String value) {
        if (value == null){
            throw new NullPointerException();
        }
        if (this.lookup(value)){
            return false;
        }else {
            if (this.loadF > REHASH_FACTOR){
                rehash();
            }
            int hash = hashString(value);
            //if value is at its hash location
            if (this.table[hash] == BRIDGE | this.table[hash] == null){
                this.table[hash] = value;
                this.size++;
                this.loadF = ((double)this.size() / (double)this.capacity());
                return true;
            }
            //if a collision occurs
            while (this.table[hash] != BRIDGE && this.table[hash] != null){
                this.collisionCount++;
                hash = (hash + 1) % this.capacity();
            }
            this.table[hash] = value;
            this.size++;
            this.loadF = ((double)this.size() / (double)this.capacity());
            return true;
        }
    }

    /**
     * Deletes a value from the hashtable, false if value not found
     *
     * @param value value to delete
     * @return whether or not the delete was successful
     * @throws NullPointerException if value == null
     */
    @Override
    public boolean delete(String value) {
        if (value == null){
            throw new NullPointerException();
        }
        if (!this.lookup(value)){
            return false;
        }else {
            int hash = hashString(value);
            //if value is at the hash
            if (this.table[hash].equals(value)){
                this.table[hash] = BRIDGE;
                this.size--;
                this.loadF = ((double)this.size() / (double)this.capacity());
                return true;
            }
            //if a collision occurs
            while (!this.table[hash].equals(value)){
                if (this.table[hash] == BRIDGE){
                    continue;
                }
                if (this.table[hash].equals(value)){
                    return true;
                }
                hash = (hash + 1) % this.capacity();
            }
            this.table[hash] = BRIDGE;
            this.size--;
            this.loadF = ((double)this.size() / (double)this.capacity());
            return true;
        }
    }

    /**
     * Finds a value in the hashtable
     *
     * @param value value to look up
     * @return whether or not the value is found
     * @throws NullPointerException if value == null
     */
    @Override
    public boolean lookup(String value) {
        if (value == null){
            throw new NullPointerException();
        }
        int hash = hashString(value);
        while (this.table[hash] != null){
            if (this.table[hash] == BRIDGE){
                hash = (hash + 1) % this.capacity();
                continue;
            }
            if (this.table[hash].equals(value)){
                return true;
            }
            hash = (hash + 1) % this.capacity();
        }
        return false;
    }

    /**
     * returns number of values in hashtable
     *
     * @return number of values in hashtable
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * returns capacity of hashtable
     *
     * @return capacity of hashtable
     */
    @Override
    public int capacity() {
        return this.table.length;
    }

    /**
     * Returns statistics of the hashtable
     *
     * @return string detailing the num of rehashes and collisions
     */
    public String getStatsLog() {
        return this.stats.toString();
    }

    /**
     * Helper function to rehash when load factor is > .55
     */
    private void rehash() {
        this.rehashCount++;
        this.stats.append("Before rehash # ").append(this.rehashCount).append(": load factor ")
                .append(String.format("%.2f", this.loadF)).append(", ").append(this.collisionCount)
                .append(" collision(s).\n");

        HashTable tmp = new HashTable(2 * this.capacity());
        for (String s : this.table) {
            if (s != BRIDGE && s != null) {
                tmp.insert(s);
            }
        }
        this.size = tmp.size;
        this.table = tmp.table;
    }

    /**
     * Helper hashing function
     *
     * @param value value to be hashed
     * @return hash value
     */
    private int hashString(String value) {
        int hashVal = 0;
        int lft = 5;
        int rght = 27;

        for (int i = 0; i < value.length(); i++) {
            int leftShift = hashVal << lft;
            int rightShift = hashVal >>> rght;

            hashVal = (leftShift | rightShift) ^ value.charAt(i);
        }
        return Math.abs(hashVal % this.capacity());
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
