import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class BSTreeTester {

    BSTree<Integer> t1;
    BSTree<Integer> t2;
    BSTree<String> t3;

    @org.junit.Before
    public void setUp() throws Exception {
        t1 = new BSTree<>();
        t2 = new BSTree<>();
        t3 = new BSTree<>();

        t1.insert(6);
        t1.insert(3);
        t1.insert(9);
        t1.insert(7);
        t1.insert(14);
        t1.insert(1);

        t2.insert(45);
        t2.insert(56);
        t2.insert(24);
        t2.insert(25);
        t2.insert(78);
        t2.insert(12);
        t2.insert(36);

        t3.insert("j");
        t3.insert("b");
        t3.insert("a");
        t3.insert("d");
        t3.insert("k");
        t3.insert("r");
    }

    @Test
    public void constructorTests(){
        t1 = new BSTree<>();
        assertNotNull(t1);
        t2 = new BSTree<>();
        assertNotNull(t2);
        t3 = new BSTree<>();
        assertNotNull(t3);
    }

    @Test
    public void getRootTest(){
        BSTree<Integer>.BSTNode root1 = t1.getRoot();
        assertEquals(6, (int) root1.getKey());

        BSTree<Integer>.BSTNode root2 = t2.getRoot();
        assertEquals(45, (int) root2.getKey());

        BSTree<String>.BSTNode root3 = t3.getRoot();
        assertEquals("j", root3.getKey());
    }

    @Test
    public void getSizeTest(){
        int size1 = t1.getSize();
        assertEquals(6, size1);

        int size2 = t2.getSize();
        assertEquals(7, size2);

        int size3 = t3.getSize();
        assertEquals(6, size3);
    }

    @Test
    public void insertTest(){
        t1 = new BSTree<>();
        assertEquals(0, t1.getSize());

        t1.insert(6);
        assertEquals(1, t1.getSize());

        t1.insert(3);
        assertEquals(2, t1.getSize());

        t1.insert(9);
        assertEquals(3, t1.getSize());
    }

    @Test
    public void findKeyTest(){
        boolean find1 = t1.findKey(6);
        assertTrue(find1);

        boolean find2 = t1.findKey(500);
        assertFalse(find2);

        boolean find3 = t3.findKey("a");
        assertTrue(find3);
    }

    //tests both datainsert and datafind
    @Test
    public void dataListTest(){
        t1.insertData(6, 23);
        Integer data1 = t1.findDataList(6).get(0);
        assertEquals(23, (int) data1);

        t1.insertData(9, 56);
        Integer data2 = t1.findDataList(9).get(0);
        assertEquals(56, (int) data2);

        t3.insertData("a", "huh");
        String data3 = t3.findDataList("a").get(0);
        assertEquals("huh", data3);
    }

    @Test
    public void getHeightTest(){
        int h1 = t1.findHeight();
        assertEquals(2, h1);

        int h2 = t2.findHeight();
        assertEquals(3, h2);

        int h3 = t3.findHeight();
        assertEquals(2, h3);
    }

    //tests all iterator methods, constructor, hasnext, next
    @Test
    public void iteratorTest(){
        Iterator<Integer> i1 = t1.iterator();
        boolean hasnxt1 = i1.hasNext();
        assertTrue(hasnxt1);

        Iterator<Integer> i2 = t2.iterator();
        boolean hasnxt2 = i2.hasNext();
        assertTrue(hasnxt2);

        Iterator<String> i3 = t3.iterator();
        boolean hasnxt3 = i3.hasNext();
        assertTrue(hasnxt3);

        Integer tst1 = i1.next();
        Integer tst2 = i1.next();
        Integer tst3 = i1.next();
        Integer tst4 = i1.next();
        Integer tst5 = i1.next();

        assertEquals(1, (int) tst1);
        assertEquals(3, (int) tst2);
        assertEquals(6, (int) tst3);
        assertEquals(7, (int) tst4);
        assertEquals(9, (int) tst5);
    }

    @Test
    public void intersectionTest(){
        t1 = new BSTree<>();
        t2 = new BSTree<>();

        t1.insert(6);
        t1.insert(3);
        t1.insert(9);
        t1.insert(7);
        t1.insert(14);
        t1.insert(1);

        t2.insert(7);
        t2.insert(56);
        t2.insert(24);
        t2.insert(25);
        t2.insert(78);
        t2.insert(9);
        t2.insert(14);

        Iterator<Integer> iter1 = t1.iterator();
        Iterator<Integer> iter2 = t2.iterator();

        ArrayList<Integer> inter1 = t1.intersection(iter1, iter2);
        ArrayList<Integer> correct1 = new ArrayList<>();
        correct1.add(7);
        correct1.add(9);
        correct1.add(14);

        assertArrayEquals(correct1.toArray(), inter1.toArray());

        t1 = new BSTree<>();
        t2 = new BSTree<>();

        t1.insert(6);
        t1.insert(3);
        t1.insert(15);
        t1.insert(7);
        t1.insert(14);
        t1.insert(1);

        t2.insert(7);
        t2.insert(56);
        t2.insert(24);
        t2.insert(15);
        t2.insert(78);
        t2.insert(9);
        t2.insert(3);

        iter1 = t1.iterator();
        iter2 = t2.iterator();

        ArrayList<Integer> inter2 = t1.intersection(iter1, iter2);
        ArrayList<Integer> correct2 = new ArrayList<>();
        correct2.add(3);
        correct2.add(7);
        correct2.add(15);

        assertArrayEquals(correct2.toArray(), inter2.toArray());

        BSTree<String> t4 = new BSTree<>();

        t4.insert("u");
        t4.insert("b");
        t4.insert("q");
        t4.insert("d");
        t4.insert("s");
        t4.insert("w");

        Iterator<String> iter3 = t3.iterator();
        Iterator<String> iter4 = t4.iterator();

        ArrayList<String> inter3 = t3.intersection(iter3, iter4);
        ArrayList<String> correct3 = new ArrayList<>();
        correct3.add("b");
        correct3.add("d");

        assertArrayEquals(correct2.toArray(), inter2.toArray());
    }

    @Test
    public void levelMax(){
        BSTree<Integer> tmp = new BSTree<>();
        tmp.insert(4);
        tmp.insert(2);
        tmp.insert(5);
        tmp.insert(1);
        tmp.insert(3);

        int test1 = tmp.levelMax(0);
        assertEquals(4, test1);

        int test2 = tmp.levelMax(1);
        assertEquals(5, test2);

//        int test3 = tmp.levelMax(2);
//        assertEquals(3, test3);

        assertNull(tmp.levelMax(3));
    }
}