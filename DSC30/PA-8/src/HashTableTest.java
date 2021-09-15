/*
 * Name: James Lu
 * PID: A16687580
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    HashTable t1;
    HashTable t2;
    HashTable t3;

    //Constructor tests
    @Before
    public void setUp() throws Exception {
        t1 = new HashTable();
        assertNotNull(t1);

        t2 = new HashTable(25);
        assertNotNull(t2);

        t3 = new HashTable(20);
        assertNotNull(t3);
    }

    @Test
    public void constructorThrowsIAE(){
        try {
            HashTable t = new HashTable(3);
            fail();
            HashTable t2 = new HashTable(1);
            fail();
            HashTable t3 = new HashTable(-1);
            fail();
        }catch (IllegalArgumentException e){

        }

    }

    @Test
    public void insertThrowsNPE(){
        try {
            t1.insert(null);
            fail();
            t2.insert(null);
            fail();
            t3.insert(null);
            fail();
        }catch (NullPointerException e){

        }

    }

    @Test
    public void deleteThrowsNPE(){
        try {
            t1.delete(null);
            fail();
            t2.delete(null);
            fail();
            t3.delete(null);
            fail();
        }catch (NullPointerException e){

        }

    }

    @Test
    public void lookupThrowsNPE(){
        try {
            t1.lookup(null);
            fail();
            t2.lookup(null);
            fail();
            t3.lookup(null);
            fail();
        }catch (NullPointerException e){

        }

    }

    @org.junit.Test
    public void insert() {
        t1.insert("A");
        assertTrue(t1.lookup("A"));

        t1.insert("sdfgh");
        assertTrue(t1.lookup("sdfgh"));

        t1.insert("Fsegse");
        assertTrue(t1.lookup("Fsegse"));
    }

    @org.junit.Test
    public void delete() {
        t1.insert("A");
        t1.insert("sdfgh");
        t1.insert("Fsegse");

        t1.delete("A");
        assertFalse(t1.lookup("A"));

        t1.delete("sdfgh");
        assertFalse(t1.lookup("sdfgh"));

        t1.delete("Fsegse");
        assertFalse(t1.lookup("Fsegse"));
    }

    @org.junit.Test
    public void lookup() {
        t1.insert("A");
        assertTrue(t1.lookup("A"));

        t1.insert("sdfgh");
        assertTrue(t1.lookup("sdfgh"));

        t1.insert("Fsegse");
        assertTrue(t1.lookup("Fsegse"));

        t1.delete("A");
        assertFalse(t1.lookup("A"));

        t1.delete("sdfgh");
        assertFalse(t1.lookup("sdfgh"));

        t1.delete("Fsegse");
        assertFalse(t1.lookup("Fsegse"));
    }

    @org.junit.Test
    public void size() {
        int s1 = t1.size();
        assertEquals(0, s1);

        t1.insert("A");
        int s2 = t1.size();
        assertEquals(1, s2);

        t1.insert("sdfgh");
        t1.insert("Fsegse");
        int s3 = t1.size();
        assertEquals(3, s3);
    }

    @org.junit.Test
    public void capacity() {
        int cap1 = t1.capacity();
        assertEquals(15, cap1);

        int cap2 = t2.capacity();
        assertEquals(25, cap2);

        int cap3 = t3.capacity();
        assertEquals(20, cap3);
    }

    @org.junit.Test
    public void getStatsLog() {
        t1.insert("a");
        t1.insert("asdf");
        t1.insert("fsdfa");
        t1.insert("aghds");

        assertEquals("", t1.getStatsLog());

        t1.insert("kflso");
        t1.insert("fghes");
        t1.insert("ryuid");
        t1.insert("fsasjju");
        t1.insert("fsdhgyuir");
        t1.insert("uoiepouy");

        assertEquals("Before rehash # 1: load factor 0.60, 7 collision(s).\n",
                t1.getStatsLog());

        HashTable t = new HashTable(5);
        t.insert("a");
        t.insert("asdf");
        t.insert("fsdfa");
        t.insert("ryuid");
        assertEquals("Before rehash # 1: load factor 0.60, 1 collision(s).\n",
                t.getStatsLog());
    }
}