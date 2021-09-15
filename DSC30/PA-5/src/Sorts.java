/*
 * NAME: James Lu
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author James Lu
 * @since  5/3/2021
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        for (int i = start; i <= end; i++) {
            while ((i > 0) && (list.get(i).compareTo(list.get(i - 1)) < 0)) {
                T temp = list.get(i);
                list.set(i, list.get(i - 1));
                list.set(i - 1, temp);
                i--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        if (start < end)
        {
            int p = partition(list, start, end);

            QuickSort(list, start, p);
            QuickSort(list, p + 1, end);
        }
    }

    public void printArr(ArrayList<T> arr){
        for (T i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        int midpoint = (l + h) / MIDDLE_IDX;
        T pivot = arr.get(midpoint);

        int start = l;
        int end = h;

        boolean sorted = false;

        while (!sorted) {
            while (arr.get(start).compareTo(pivot) < 0) {
                start++;
            }
            while (arr.get(end).compareTo(pivot) > 0) {
                end--;
            }

            if (start < end) {
                T tmp = arr.get(start);
                arr.set(start, arr.get(end));
                arr.set(end, tmp);

                start++;
                end--;
            } else {
                sorted = true;
            }
        }
        return end;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if (end - start <= cutoff) {
            InsertionSort(list, start, end);
        } else {
            int newLowEndIdx = partition(list, start, end);
            Modified_QuickSort(list, start, newLowEndIdx, cutoff);
            Modified_QuickSort(list, newLowEndIdx + 1, end, cutoff);
        }
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        while (true){
            int i = start;
            boolean swapped = false;

            while (i < end){

                if (list.get(i).compareTo(list.get(i + 1)) > 0){
                    T tmp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, tmp);
                    swapped = true;
                }
                i++;
            }
            if (!swapped){
                break;
            }

            swapped = false;
            while (i > start){
                if (list.get(i).compareTo(list.get(i - 1)) < 0){
                    T tmp = list.get(i);
                    list.set(i, list.get(i - 1));
                    list.set(i - 1, tmp);
                    swapped = true;
                }
                i--;
            }
            if (!swapped){
                break;
            }
        }
    }
}
