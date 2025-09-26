package algo.select;

import algo.Arr;
import algo.Metrics;

public final class DeterministicSelect {
    private final Metrics m;

    public DeterministicSelect(Metrics m) { this.m = m; }

    public int select( int[] a, int k ) {
        if( a == null || a.length == 0 ) throw new IllegalArgumentException("empty");
        if (k < 0 || k > a.length) throw new IllegalArgumentException("invalid k");
        return select(a, 0, a.length -1 , k);
    }

    private int select( int[] a, int low, int high, int k) {
        while (true) {
            if (low == high) return a[low];

            int pivot = medianOfMedians(a, low, high);
            int p = partition()
        }
    }

    private int medianOfMedians(int[] a, int low, int high) {
        return
    }

    private void insertionSort(int[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++ ) {
            int key = a[i], j = i - 1;

            while (j >= low && Arr.cmp(m , a[j], key) > 0) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
    private int partition(int[] a, int low, int high, int pivotValue) {

        int pvIndx = low;
        while (pvIndx <= high && ) {

        }
        return
    }

    private void swap(int[] a, int i, int j) {
        if (i != j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


}
