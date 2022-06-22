package sortings;

import java.util.Random;

public class QuickSort {


    public void sort(int[] a) {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            exch(a, i, rand.nextInt(a.length));
        }
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] a = new int[20];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) a[i] = random.nextInt(1000);
        System.out.println("Before sorting:");
        for (int n : a) System.out.print(n + "  ");
        System.out.println("\n");
        System.out.println("After MergeSort:");
        qs.sort(a);
        for (int n : a) System.out.print(n + "  ");
        System.out.println();
    }
}
