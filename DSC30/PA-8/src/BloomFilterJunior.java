/*
 * Name: James Lu
 */

/**
 * Naive implementation of a bloom filter
 *
 * @author James Lu
 * @since 5/24/21
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;

    /* Instance variables */
    private boolean[] table;
    private int capacity;

    /**
     * Bloom filter constructor that initializes table and capacity
     *
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity < 50
     */
    public BloomFilterJunior(int capacity) {
        if (capacity < MIN_INIT_CAPACITY){
            throw new IllegalArgumentException();
        }
        this.table = new boolean[capacity];
        this.capacity = capacity;
    }

    /**
     * Insert an element into the bloom filter
     *
     * @param value value to be inserted
     * @throws NullPointerException if value == null
     */
    public void insert(String value) {
        if (value == null){
            throw new NullPointerException();
        }
        int h1 = hashBase256(value);
        int h2 = hashCRC(value);
        int h3 = hashHorners(value);

        this.table[h1] = true;
        this.table[h2] = true;
        this.table[h3] = true;
    }

    /**
     * Lookup an element in the bloom filter
     *
     * @param value value to be searched
     * @throws NullPointerException if value == null
     */
    public boolean lookup(String value) {
        if (value == null){
            throw new NullPointerException();
        }
        int h1 = hashBase256(value);
        int h2 = hashCRC(value);
        int h3 = hashHorners(value);

        if (this.table[h1] && this.table[h2] && this.table[h3]){
            return true;
        }
        return false;
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
        int hashVal = 0;
        int lft = 5;
        int rght = 27;

        for (int i = 0; i < value.length(); i++) {
            int leftShift = hashVal << lft;
            int rightShift = hashVal >>> rght;

            hashVal = (leftShift | rightShift) ^ value.charAt(i);
        }
        return Math.abs(hashVal % this.capacity);
    }

    /**
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }
}
