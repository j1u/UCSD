import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void heapSort() {
        int[] arr1 = new int[]{77, 49, 66, 85, 58, 17, 20, 54, 57, 63, 46, 95};
        int[] arr2 = new int[]{77, 49, 66, 85, 58, 17, 20, 54, 57, 63, 46, 95};

        Arrays.sort(arr2);
        HeapSort.heapSort(arr1, 0, arr1.length - 1);

        assertArrayEquals(arr2, arr1);
    }
}