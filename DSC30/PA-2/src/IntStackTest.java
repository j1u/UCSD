import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {

    IntStack t1;
    IntStack t2;
    IntStack t3;

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample1() {
        t1 = new IntStack(4, .75, .25);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample2() {
        t1 = new IntStack(8, 3, .25);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample3() {
        t1 = new IntStack(8, .53, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample4() {
        t1 = new IntStack(7, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample5() {
        t1 = new IntStack(4, .75);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample6() {
        t1 = new IntStack(2);
    }

    @Test (expected = EmptyStackException.class)
    public void testEmptyStacksExample1() {
        t1 = new IntStack();
        t1.peek();
    }

    @Test (expected = EmptyStackException.class)
    public void testEmptyStacksExample2() {
        t1 = new IntStack();
        t1.pop();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample7() {
        t1 = new IntStack();
        t1.multiPush(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgumentExample8() {
        t1 = new IntStack();
        t1.multiPop(-5);
    }

    @Test
    public void emptyConstructorTest(){
        t1 = new IntStack();
        assertArrayEquals(new double[]{10, .75, .25},
                new double[]{t1.capacity(), t1.getLoadFactor(), t1.getShrinkFactor()}, .005);
    }

    @Test
    public void constructorTests(){
        t1 = new IntStack(10, .67, .22);
        assertArrayEquals(new double[]{10, .67, .22},
                new double[]{t1.capacity(), t1.getLoadFactor(), t1.getShrinkFactor()}, .005);
        t1 = new IntStack(15, .77, .18);
        assertArrayEquals(new double[]{15, .77, .18},
                new double[]{t1.capacity(), t1.getLoadFactor(), t1.getShrinkFactor()}, .005);
        t1 = new IntStack(20, .89, .25);
        assertArrayEquals(new double[]{20, .89, .25},
                new double[]{t1.capacity(), t1.getLoadFactor(), t1.getShrinkFactor()}, .005);

        t2 = new IntStack(10, .67);
        assertArrayEquals(new double[]{10, .67, .25},
                new double[]{t2.capacity(), t2.getLoadFactor(), t2.getShrinkFactor()}, .005);
        t2 = new IntStack(15, .77);
        assertArrayEquals(new double[]{15, .77, .25},
                new double[]{t2.capacity(), t2.getLoadFactor(), t2.getShrinkFactor()}, .005);
        t2 = new IntStack(20, .89);
        assertArrayEquals(new double[]{20, .89, .25},
                new double[]{t2.capacity(), t2.getLoadFactor(), t2.getShrinkFactor()}, .005);

        t3 = new IntStack(10);
        assertArrayEquals(new double[]{10, .75, .25},
                new double[]{t3.capacity(), t3.getLoadFactor(), t3.getShrinkFactor()}, .005);
        t3 = new IntStack(15);
        assertArrayEquals(new double[]{15, .75, .25},
                new double[]{t3.capacity(), t3.getLoadFactor(), t3.getShrinkFactor()}, .005);
        t3 = new IntStack(20);
        assertArrayEquals(new double[]{20, .75, .25},
                new double[]{t3.capacity(), t3.getLoadFactor(), t3.getShrinkFactor()}, .005);
    }

    @Test
    public void getLoadFactor() {
        t1 = new IntStack(10, .67, .22);
        assertEquals(.67, t1.getLoadFactor(), .005);
        t1 = new IntStack(10, .78, .22);
        assertEquals(.78, t1.getLoadFactor(), .005);
        t1 = new IntStack(10, .7, .22);
        assertEquals(.7, t1.getLoadFactor(), .005);
    }

    @Test
    public void getShrinkFactor() {
        t1 = new IntStack(10, .67, .25);
        assertEquals(.25, t1.getShrinkFactor(), .005);
        t1 = new IntStack(10, .78, .11);
        assertEquals(.11, t1.getShrinkFactor(), .005);
        t1 = new IntStack(10, .9, .15);
        assertEquals(.15, t1.getShrinkFactor(), .005);
    }

    @Test
    public void isEmptyTest() {
        t1 = new IntStack(10, .8, .11);
        assertTrue(t1.isEmpty());
        t1.push(2);
        assertFalse(t1.isEmpty());
        t1.pop();
        assertTrue(t1.isEmpty());

        t1.clear();
        for (int i = 0; i < 10; i++) {
            t1.push(i);
        }
        for (int i = 0; i < 10; i++) {
            t1.pop();
        }
        assertTrue(t1.isEmpty());
    }

    @Test
    public void clearTest() {
        t1 = new IntStack(10, .8, .11);
        t1.push(2);
        t1.push(3);
        t1.push(4);
        t1.clear();
        assertTrue(t1.isEmpty());

        t2 = new IntStack(15, .8);
        t2.push(2);
        t2.push(3);
        t2.push(4);
        t2.clear();
        assertTrue(t2.isEmpty());

        t3 = new IntStack(20);
        t3.push(2);
        t3.push(3);
        t3.push(4);
        t3.clear();
        assertTrue(t3.isEmpty());
    }

    @Test
    public void sizeTest() {
        t1 = new IntStack(10, .8, .11);
        assertEquals(t1.size(), 0);
        t1.push(2);
        t1.push(3);
        t1.push(4);
        assertEquals(t1.size(), 3);
        t1.pop();
        assertEquals(t1.size(), 2);
    }

    @Test
    public void capacityTest() {
        t1 = new IntStack(10, .8, .11);
        t2 = new IntStack(15, .8);
        t3 = new IntStack(20);
        assertEquals(t1.capacity(), 10);
        assertEquals(t2.capacity(), 15);
        assertEquals(t3.capacity(), 20);
    }

    @Test
    public void peekTest() {
        t1 = new IntStack(10, .8, .11);
        t1.push(2);
        assertEquals(t1.peek(), 2);
        t1.push(3);
        assertEquals(t1.peek(), 3);
        t1.push(4);
        assertEquals(t1.peek(), 4);
    }

    @Test
    public void pushTest() {
        t1 = new IntStack(5, .8, .11);
        for (int i = 0; i < 10; i++) {
            t1.push(i);
        }
        t1.push(2);
        assertEquals(t1.peek(), 2);
        t1.push(3);
        assertEquals(t1.peek(), 3);
        t1.push(4);
        assertEquals(t1.peek(), 4);
    }

    @Test
    public void popTest() {
        t1 = new IntStack(20, .9, .2);
        for (int i = 0; i < 6; i++) {
            t1.push(i);
        }
        t1.pop();
        t1.pop();
        t1.pop();
        t1.push(2);
        assertEquals(t1.pop(), 2);
        t1.push(3);
        assertEquals(t1.pop(), 3);
        t1.push(4);
        assertEquals(t1.pop(), 4);
    }

    @Test
    public void multiPushTest() {
        t1 = new IntStack(10, .8, .11);
        t1.multiPush(new int[] {2, 3, 4, 5});
        assertEquals(5, t1.pop());
        assertEquals(4, t1.pop());
        assertEquals(3, t1.pop());
    }

    @Test
    public void multiPopTest() {
        t1 = new IntStack(10, .8, .11);
        t1.multiPush(new int[] {2, 3, 4, 5});
        assertArrayEquals(new int[] {5, 4, 3, 2}, t1.multiPop(4));
        t1.clear();
        t1.multiPush(new int[] {2, 3, 4, 5, 7, 9});
        assertArrayEquals(new int[] {9, 7, 5, 4}, t1.multiPop(4));
        t1.clear();
        t1.multiPush(new int[] {2, 3, 4, 5});
        assertArrayEquals(new int[] {5, 4, 3}, t1.multiPop(3));
    }
}
