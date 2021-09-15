import org.junit.Before;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;

public class SortsTester {
    Sorts<Integer> intSort;
    Sorts<String> stringSort;
    Random rand;

    @Before
    public void setUp() throws Exception {
        intSort = new Sorts<>();
        stringSort = new Sorts<>();
        rand = new Random();
    }

    @org.junit.Test
    public void insertionSort() {
        ArrayList<Integer> randInt = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt.contains(r)){
                r = rand.nextInt(25);
            }
            randInt.add(r);
        }

        Integer[] sorted1 = new Integer[15];
        sorted1 = randInt.toArray(sorted1);
        Arrays.sort(sorted1);

        assertFalse(Arrays.equals(randInt.toArray(), sorted1));

        intSort.InsertionSort(randInt, 0, randInt.size() - 1);
        assertArrayEquals(randInt.toArray(), sorted1);

        randInt = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt.contains(r)){
                r = rand.nextInt(25);
            }
            randInt.add(r);
        }

        Integer[] sorted2 = new Integer[15];
        sorted2 = randInt.toArray(sorted2);
        Arrays.sort(sorted2);

        assertFalse(Arrays.equals(randInt.toArray(), sorted2));

        intSort.InsertionSort(randInt, 0, randInt.size() - 1);
        assertArrayEquals(randInt.toArray(), sorted2);


        ArrayList<String> randString = new ArrayList<>();
        randString.add("csadierjg");
        randString.add("bseysas");
        randString.add("jkyuknf");
        randString.add("sadfghds");
        randString.add("sdfgds");
        randString.add("aaasdf");

        String[] sorted3 = new String[6];
        sorted3 = randString.toArray(sorted3);
        Arrays.sort(sorted3);

        assertFalse(Arrays.equals(randString.toArray(), sorted3));

        stringSort.InsertionSort(randString, 0, randString.size() - 1);
        assertArrayEquals(randString.toArray(), sorted3);
    }

    @org.junit.Test
    public void quickSort() {
        ArrayList<Integer> randInt1 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt1.contains(r)){
                r = rand.nextInt(25);
            }
            randInt1.add(r);
        }

        Integer[] sorted1 = new Integer[15];
        sorted1 = randInt1.toArray(sorted1);
        Arrays.sort(sorted1);

        assertFalse(Arrays.equals(randInt1.toArray(), sorted1));

        intSort.QuickSort(randInt1, 0, randInt1.size() - 1);

        assertArrayEquals(randInt1.toArray(), sorted1);

        ArrayList<Integer> randInt2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt2.contains(r)){
                r = rand.nextInt(25);
            }
            randInt2.add(r);
        }

        Integer[] sorted2 = new Integer[15];
        sorted2 = randInt2.toArray(sorted2);
        Arrays.sort(sorted2);

        assertFalse(Arrays.equals(randInt2.toArray(), sorted2));

        intSort.QuickSort(randInt2, 0, randInt2.size() - 1);

        assertArrayEquals(randInt2.toArray(), sorted2);

        ArrayList<String> randString = new ArrayList<>();
        randString.add("csadierjg");
        randString.add("bseysas");
        randString.add("jkyuknf");
        randString.add("sadfghds");
        randString.add("sdfgds");
        randString.add("aaasdf");

        String[] sorted3 = new String[6];
        sorted3 = randString.toArray(sorted3);
        Arrays.sort(sorted3);

        assertFalse(Arrays.equals(randString.toArray(), sorted3));

        stringSort.QuickSort(randString, 0, randString.size() - 1);
        assertArrayEquals(randString.toArray(), sorted3);
    }

    @org.junit.Test
    public void modified_QuickSort() {
        ArrayList<Integer> randInt1 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt1.contains(r)){
                r = rand.nextInt(25);
            }
            randInt1.add(r);
        }

        Integer[] sorted1 = new Integer[15];
        sorted1 = randInt1.toArray(sorted1);
        Arrays.sort(sorted1);

        assertFalse(Arrays.equals(randInt1.toArray(), sorted1));

        intSort.Modified_QuickSort(randInt1, 0, randInt1.size() - 1, 5);

        ArrayList<Integer> randInt2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt2.contains(r)){
                r = rand.nextInt(25);
            }
            randInt2.add(r);
        }

        Integer[] sorted2 = new Integer[15];
        sorted2 = randInt2.toArray(sorted2);
        Arrays.sort(sorted2);

        assertFalse(Arrays.equals(randInt2.toArray(), sorted2));

        intSort.Modified_QuickSort(randInt2, 0, randInt2.size() - 1, 7);

        assertArrayEquals(randInt2.toArray(), sorted2);

        ArrayList<String> randString = new ArrayList<>();
        randString.add("csadierjg");
        randString.add("bseysas");
        randString.add("jkyuknf");
        randString.add("sadfghds");
        randString.add("sdfgds");
        randString.add("aaasdf");

        String[] sorted3 = new String[6];
        sorted3 = randString.toArray(sorted3);
        Arrays.sort(sorted3);

        assertFalse(Arrays.equals(randString.toArray(), sorted3));

        stringSort.Modified_QuickSort(randString, 0, randString.size() - 1, 8);
        assertArrayEquals(randString.toArray(), sorted3);
    }

    @org.junit.Test
    public void cocktailSort() {
        ArrayList<Integer> randInt1 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt1.contains(r)){
                r = rand.nextInt(25);
            }
            randInt1.add(r);
        }

        Integer[] sorted1 = new Integer[15];
        sorted1 = randInt1.toArray(sorted1);
        Arrays.sort(sorted1);

        assertFalse(Arrays.equals(randInt1.toArray(), sorted1));

        intSort.cocktailSort(randInt1, 0, randInt1.size() - 1);

        ArrayList<Integer> randInt2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int r = rand.nextInt(25);
            while (randInt2.contains(r)){
                r = rand.nextInt(25);
            }
            randInt2.add(r);
        }

        Integer[] sorted2 = new Integer[15];
        sorted2 = randInt2.toArray(sorted2);
        Arrays.sort(sorted2);

        assertFalse(Arrays.equals(randInt2.toArray(), sorted2));

        intSort.cocktailSort(randInt2, 0, randInt1.size() - 1);

        assertArrayEquals(randInt2.toArray(), sorted2);

        ArrayList<String> randString = new ArrayList<>();
        randString.add("csadierjg");
        randString.add("bseysas");
        randString.add("jkyuknf");
        randString.add("sadfghds");
        randString.add("sdfgds");
        randString.add("aaasdf");

        String[] sorted3 = new String[6];
        sorted3 = randString.toArray(sorted3);
        Arrays.sort(sorted3);

        assertFalse(Arrays.equals(randString.toArray(), sorted3));

        stringSort.cocktailSort(randString, 0, randString.size() - 1);
        assertArrayEquals(randString.toArray(), sorted3);
    }
}
