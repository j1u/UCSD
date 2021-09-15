import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    DoublyLinkedList<Integer> t1;
    DoublyLinkedList<String> t2;

    @Before
    public void setUp() throws Exception {
        t1 = new DoublyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            t1.add(i);
        }
    }

    @Test
    public void add() {
        t1 = new DoublyLinkedList<>();

        t1.add(1);
        assertEquals(1, (int)t1.get(0));

        t1.add(6);
        assertEquals(6, (int)t1.get(1));

        t1.add(10);
        assertEquals(10, (int)t1.get(2));
    }

    @Test
    public void testAdd() {
        t1.add(0, 13);
        assertEquals(13, (int)t1.get(0));

        t1.add(3, 11);
        assertEquals(11, (int)t1.get(3));

        t1.add(2,10);
        assertEquals(10, (int)t1.get(2));
    }

    @Test
    public void clear() {
        assertTrue(t1.size() > 0);
        t1.clear();
        assertEquals(0, t1.size());

        for (int i = 0; i < 3; i++) {
            t1.add(i);
        }

        assertTrue(t1.size() > 0);
        t1.clear();
        assertEquals(0, t1.size());

        for (int i = 0; i < 15; i++) {
            t1.add(i);
        }

        assertTrue(t1.size() > 0);
        t1.clear();
        assertEquals(0, t1.size());
    }

    @Test
    public void contains() {
        assertTrue(t1.contains(0));

        t1.remove(1);
        assertFalse(t1.contains(1));

        assertTrue(t1.contains(2));
    }

    @Test
    public void get() {
        t1.add(0, 13);
        assertEquals(13, (int)t1.get(0));

        t1.add(3, 11);
        assertEquals(11, (int)t1.get(3));

        t1.add(2,10);
        assertEquals(10, (int)t1.get(2));
    }

    @Test
    public void isEmpty() {
        assertFalse(t1.isEmpty());

        t1.clear();
        assertTrue(t1.isEmpty());

        t1 = new DoublyLinkedList<>();
        assertTrue(t1.isEmpty());
    }

    @Test
    public void remove() {
        t1.remove(0);
        assertEquals(1, (int)t1.get(0));

        t1.remove(2);
        assertEquals(4, (int)t1.get(2));

        t1.remove(0);
        assertEquals(2, (int)t1.get(0));
    }

    @Test
    public void set() {
        assertEquals(0, (int)t1.get(0));
        t1.set(0, 7);
        assertEquals(7, (int)t1.get(0));

        assertEquals(5, (int)t1.get(5));
        t1.set(5, 2);
        assertEquals(2, (int)t1.get(5));

        assertEquals(7, (int)t1.get(7));
        t1.set(7, 1);
        assertEquals(1, (int)t1.get(7));
    }

    @Test
    public void size() {
        assertEquals(10, t1.size());

        t1.clear();
        assertEquals(0, t1.size());

        for (int i = 0; i < 10; i++) {
            t1.add(i);
        }

        assertEquals(10, t1.size());
    }

    @Test
    public void testToString() {
        assertEquals("[(head) -> 0 -> 1 -> 2 -> 3 -> 4 -> " +
                "5 -> 6 -> 7 -> 8 -> 9 -> (tail)]", t1.toString());

        t1 = new DoublyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            t1.add(i);
        }

        assertEquals("[(head) -> 0 -> 1 -> 2 -> 3 -> 4 -> (tail)]", t1.toString());

        t1.clear();
        assertEquals("[(head) -> (tail)]", t1.toString());
    }

    @Test
    public void testRemoveMultiple(){
        t1.removeMultipleOf(2);
        assertEquals(1, (int)t1.get(0));
        assertEquals(3, (int)t1.get(1));

        t1.clear();
        for (int i = 0; i < 10; i++) {
            t1.add(i);
        }
        t1.removeMultipleOf(3);
        assertEquals(4, (int)t1.get(2));

        t1.clear();
        for (int i = 0; i < 15; i++) {
            t1.add(i);
        }
        t1.removeMultipleOf(4);
        assertEquals(5, (int)t1.get(3));
    }

    @Test
    public void testSwapSegment(){
        DoublyLinkedList<Integer> t3 = new DoublyLinkedList<>();
        for (int i = 10; i > 0; i--) {
            t3.add(i);
        }
        t1.swapSegment(t3, 2);
        assertEquals(10, (int)t1.get(0));
        assertEquals(0, (int)t3.get(0));

        t1.clear();
        t3.clear();
        for (int i = 0; i < 10; i++) {
            t1.add(i);
        }
        for (int i = 0; i > -10; i--) {
            t3.add(i);
        }
        t1.swapSegment(t3, 2);
        assertEquals(-1, (int)t1.get(1));
        assertEquals(1, (int)t3.get(1));

        t1.clear();
        t3.clear();
        for (int i = 0; i < 10; i++) {
            t1.add(i);
        }
        for (int i = 5; i > 0; i--) {
            t3.add(i);
        }
        t1.swapSegment(t3, 2);
        assertEquals(5, (int)t1.get(0));
        assertEquals(0, (int)t3.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointerInAdd(){
        t1.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointerInAdd2(){
        t1.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutInAdd(){
        t1.add(-1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutInAdd2(){
        t1.add(100, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutInGet(){
        t1.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutInRemove(){
        t1.get(100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIndexOutInSet(){
        t1.set(-1, 8);
    }

    @Test(expected = NullPointerException.class)
    public void TestNullPointInSet2(){
        t1.set(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalArgInRemoveMult(){
        t1.removeMultipleOf(-1);
    }
}