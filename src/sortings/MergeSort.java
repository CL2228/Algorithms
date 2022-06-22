package sortings;

import java.util.Random;

public class MergeSort {

    public void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private void sort(int[] a, int[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else {
                if (aux[i] <= aux[j]) a[k] = aux[i++];
                else a[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] a = new int[20];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) a[i] = random.nextInt(1000);
        System.out.println("Before sorting:");
        for (int n : a) System.out.print(n + "  ");
        System.out.println("\n");
        System.out.println("After MergeSort:");
        ms.sort(a);
        for (int n : a) System.out.print(n + "  ");
        System.out.println();
    }
}
