package sortings;

import java.util.Random;

/**
 * Quick Select is an efficient algorithm to find the Kth smallest element in an array
 */
public class QuickSelect {

    public int select(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i] <= a[lo])
                if (i == hi) break;
            while (a[--j] >= a[lo])
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        QuickSelect quickSelect = new QuickSelect();
        int[] a = new int[20];
        int[] b = new int[20];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int num = random.nextInt(1000);
            a[i] = num;
            b[i] = num;
        }
        System.out.println("Before sorting:");
        for (int n : a) System.out.print(n + "  ");
        System.out.println("\n");
        System.out.println("After MergeSort:");
        quickSort.sort(a);
        for (int n : a) System.out.print(n + "  ");
        System.out.println();

        int selectedItem = quickSelect.select(b, 4);
        System.out.println("The 4th smallest number:  " + selectedItem);
    }


}
