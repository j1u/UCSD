import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    MyPriorityQueue<Integer> t1;

    @Before
    public void setUp() throws Exception {
        t1 = new MyPriorityQueue<>(6);
        int[] arr1 = new int[]{77, 49, 66, 85, 58, 17, 20, 54, 57, 63, 46, 95};
        for (Integer i : arr1) {
            t1.offer(i);
        }
    }

    @Test
    public void offer() {

    }

    @Test
    public void poll() {
        Integer poll1 = t1.poll();
        assertEquals(95, (int) poll1);
    }

    @Test
    public void clear() {
    }

    @Test
    public void peek() {
    }

    @Test
    public void isEmpty() {
    }
}