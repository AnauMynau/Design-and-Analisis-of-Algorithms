package algo.merge;

import algo.Metrics;
import algo.Arr;

public final class InsertionSort {
    public static void sort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && Arr.cmp(m, a[j], key) > 0) { // строго > 0
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
