package algo.merge;

import algo.*;

public final class MergeSort implements Sorter {
    private final Metrics m;
    private final int cutoff;

    public MergeSort(Metrics m, int cutoff) {
        this.m = m;
        this.cutoff = Math.max(0, cutoff);
    }

    @Override public String name() { return "MergeSort(cutoff=" + cutoff + ")"; }

    @Override public void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        int[] aux = new int[a.length];
        m.alloc((long) Integer.BYTES * aux.length);
        sort(a, aux, 0, a.length - 1);
    }

    private void sort(int[] a, int[] aux, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= cutoff) { InsertionSort.sort(a, lo, hi, m); return; }

        int mid = lo + ((hi - lo) >>> 1);

        m.incDepth();
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1,  hi);
        m.decDepth();

        // already sorted? (important for constants, but does not affect correctness)
        if (Arr.cmp(m, a[mid], a[mid + 1]) <= 0) return;

        merge(a, aux, lo, mid, hi);
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // copy EXACTLY [lo..hi] in aux
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (Arr.cmp(m, aux[i], aux[j]) <= 0) { // <= important for stability
                a[k++] = aux[i++];
            } else {
                a[k++] = aux[j++];
            }
        }
        // the left half can stay — copy it
        while (i <= mid) a[k++] = aux[i++];
        // the right half is already in place — we are NOT copying it
    }
}
