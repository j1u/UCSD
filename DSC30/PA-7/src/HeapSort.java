/*
 * Name: James Lu
 */

/**
 * Class that sorts an integer array using heaps
 */
public class HeapSort {

    /**
     * Sorting algorithm using a heap to sort the array
     *
     * @param arr array to be sorted
     * @param start start index
     * @param end end index
     */
    public static void heapSort(int[] arr, int start, int end) {
        dHeap<Integer> sort = new dHeap<>(2, arr.length, false);
        for (int i = start; i <= end; i++) {
            sort.add(arr[i]);
        }

        for (int i = start; i <= end; i++) {
            arr[i] = sort.remove();
        }
    }
}
