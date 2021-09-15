import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLQueueTest {
    DLLQueue<Integer> tst;
    DLLQueue<Integer> tst2;
    DLLQueue<Integer> tst3;

    @Before
    public void setUp() throws Exception {
        this.tst = new DLLQueue<>();
        this.tst2 = new DLLQueue<>();
        this.tst3 = new DLLQueue<>();

        for (int i = 0; i < 10; i++) {
            this.tst.enqueue(i);
        }
    }

    @Test
    public void size() {
        assertEquals(0, tst2.size());
        assertEquals(10, tst.size());
        tst.dequeue();
        assertEquals(9,tst.size());
    }

    @Test
    public void isEmpty() {
        assertFalse(tst.isEmpty());
        assertTrue(tst2.isEmpty());
        tst2.enqueue(0);
        assertFalse(tst2.isEmpty());
    }

    @Test
    public void enqueue() {
        tst = new DLLQueue<>();
        assertTrue(tst.isEmpty());

        for (int i = 0; i < 10; i++) {
            this.tst.enqueue(i);
        }

        assertEquals(0, (int) tst.dequeue());
        assertEquals(1, (int) tst.dequeue());
        this.tst.enqueue(5);
        assertEquals(2, (int) tst.dequeue());
    }

    @Test
    public void dequeue() {
        assertEquals(0, (int) tst.dequeue());
        assertEquals(1, (int) tst.dequeue());
        assertEquals(2, (int) tst.dequeue());
    }

    @Test
    public void peek() {
        assertEquals(0, (int) tst.peek());
        for (int i = 5; i < 15; i++) {
            tst2.enqueue(i);
            tst3.enqueue(i+4);
        }
        assertEquals(5, (int) tst2.peek());
        assertEquals(9, (int) tst3.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalArgInEnqueue(){
        tst.enqueue(null);
    }

}