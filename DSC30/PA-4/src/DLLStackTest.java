import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLStackTest {
    DLLStack<Integer> tst;
    DLLStack<Integer> tst2;
    DLLStack<Integer> tst3;

    @Before
    public void setUp() throws Exception {
        this.tst = new DLLStack<>();
        this.tst2 = new DLLStack<>();
        this.tst3 = new DLLStack<>();

        for (int i = 0; i < 10; i++) {
            this.tst.push(i);
        }
    }

    @Test
    public void size() {
        assertEquals(0, tst2.size());
        assertEquals(10, tst.size());
        tst.pop();
        assertEquals(9,tst.size());
    }

    @Test
    public void isEmpty() {
        assertFalse(tst.isEmpty());
        assertTrue(tst2.isEmpty());
        tst2.push(0);
        assertFalse(tst2.isEmpty());
    }

    @Test
    public void push() {
        tst = new DLLStack<>();
        assertTrue(tst.isEmpty());

        for (int i = 0; i < 10; i++) {
            this.tst.push(i);
        }

        assertEquals(9, (int) tst.pop());
        assertEquals(8, (int) tst.pop());
        this.tst.push(5);
        assertEquals(5, (int) tst.pop());
    }

    @Test
    public void pop() {
        assertEquals(9, (int) tst.pop());
        assertEquals(8, (int) tst.pop());
        this.tst.push(5);
        assertEquals(5, (int) tst.pop());
    }

    @Test
    public void peek() {
        assertEquals(9, (int) tst.peek());
        this.tst.push(8);
        assertEquals(8, (int) tst.peek());
        this.tst.push(5);
        assertEquals(5, (int) tst.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalArgInPush(){
        tst.push(null);
    }
}