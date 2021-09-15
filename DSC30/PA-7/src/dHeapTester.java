import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {
    dHeap<Integer> t1;
    dHeap<Integer> t2;
    dHeap<String> t3;

    @Before
    public void setUp() throws Exception {
        t1 = new dHeap<>();
        assertNotNull(t1);
        t2 = new dHeap<>(2, 50, true);
        assertNotNull(t2);
        t3 = new dHeap<>(2, 6, false);
        assertNotNull(t3);

        Integer[] arr1 = new Integer[]{77, 49, 66, 85, 58, 17, 20, 54, 57, 63, 46, 95};
        Integer[] arr2 = new Integer[]{91, 89, 74, 81, 82, 61, 54, 72, 48, 29, 31};

        for (Integer i : arr1) {
            t1.add(i);
        }

        for (Integer i : arr2) {
            t2.add(i);
        }

        t3.add("g");
        t3.add("e");
        t3.add("r");
        t3.add("h");
        t3.add("s");
        t3.add("l");
        t3.add("n");
    }

    @org.junit.Test
    public void size() {
        int size1 = t1.size();
        assertEquals(12, size1);

        int size2 = t2.size();
        assertEquals(11, size2);

        int size3 = t3.size();
        assertEquals(7, size3);
    }

    //add is called in setup so we just assert here
    @org.junit.Test
    public void add() {
        Integer[] arr1 = new Integer[]{95,77,85,57,63,66,20,49,54,58,46,17};
        Integer[] arr2 = new Integer[]{91,89,74,81,82,61,54,72,48,29,31};
        String[] arr3 = new String[]{"e", "g", "l", "h", "s", "r", "n"};

        assertArrayEquals(arr1, t1.getHeap());
        assertArrayEquals(arr2, t2.getHeap());
        assertArrayEquals(arr3, t3.getHeap());
    }

    @org.junit.Test
    public void remove() {
        dHeap<Integer> t4 = new dHeap<>(2, 6, false);
        Integer[] arr4 = new Integer[]{11, 25, 13, 82, 64, 37, 20, 91, 84, 87, 92, 55};
        for (Integer i : arr4) {
            t4.add(i);
        }
        assertArrayEquals(arr4, t4.getHeap());

        Integer[] oneRemove = new Integer[]{13, 25, 20, 82, 64, 37, 55, 91, 84, 87, 92};
        t4.remove();
        assertArrayEquals(oneRemove, t4.getHeap());

        Integer[] twoRemove = new Integer[]{20, 25, 37, 82, 64, 92, 55, 91, 84, 87};
        t4.remove();
        assertArrayEquals(twoRemove, t4.getHeap());

        Integer[] fifthRemove = new Integer[]{55, 64, 84, 82, 87, 92, 91};
        t4.remove();
        t4.remove();
        t4.remove();
        assertArrayEquals(fifthRemove, t4.getHeap());

        dHeap<Integer> t5 = new dHeap<>(5, 6, true);
        Integer[] arr5 = new Integer[]{95,77,85,57,63,66,20,49,54,58,46,17};
        for (Integer i : arr5) {
            t5.add(i);
        }
        t5.remove();
        t5.remove();
    }

    @org.junit.Test
    public void clear() {
        assertEquals(12, t1.size());
        t1.clear();
        assertEquals(0, t1.size());

        assertEquals(11, t2.size());
        t2.clear();
        assertEquals(0, t2.size());

        assertEquals(7, t3.size());
        t3.clear();
        assertEquals(0, t3.size());
    }

    @org.junit.Test
    public void element() {
        Integer elem1 = t1.element();
        assertNotNull(elem1);
        assertEquals(95, (int) elem1);

        Integer elem2 = t2.element();
        assertNotNull(elem2);
        assertEquals(91, (int) elem2);

        String elem3 = t3.element();
        assertNotNull(elem3);
        assertEquals("e", elem3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsIAE() {
        t1 = new dHeap<>(-5, 6, true);
        t2 = new dHeap<>(0,5, true);
        t3 = new dHeap<>(-4, 8, false);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void addThrowsNPE() {
        t1.add(null);
        t2.add(null);
        t3.add(null);
    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void removeThrowNSEE() {
        t1.add(3);
        t1.add(76);
        t1.add(43);
        t1.clear();
        t1.remove();

        t2.remove();
        t2.remove();

        t3.add("fsdf");
        t3.clear();
        t3.remove();

        t3.add("htrss");
        t3.clear();
        t3.remove();
    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void elementThrowNSEE() {
        t1.element();
        t1.add(7);
        t1.clear();
        t1.element();

        t2.element();
        t2.element();

        t3.add("fdgerg");
        t3.clear();
        t3.element();

        t3.add("fsadfg");
        t3.clear();
        t3.element();
    }
}
