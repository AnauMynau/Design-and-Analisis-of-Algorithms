package algo.quick;

import algo.Arr;
import algo.Metrics;
import algo.Sorter;

import java.util.Random;

public final class QuickSort implements Sorter {

    private final Metrics m;
    private final Random rnd = new Random(42);

    public QuickSort(Metrics m) {
        this.m = m;
    }

    @Override public String name() {
        return "QuickSort(rand, smaller-first";
    }

    @Override
    public void sort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        shuffle(a);   // reduces the chance of bad splits
        quick(a, 0, a.length - 1);
    }

    private void quick(int[] a, int lo, int hi) {
        while (lo < hi) {
            int p = partition(a, lo, hi);   // pivot position
            int leftSize = p - lo;
            int rightSize = hi - p;

            // We recurse to a smaller part, and iteratively to a larger part (we shift the boundaries).
            m.incDepth();
            if (leftSize < rightSize) {
                if(lo < p - 1) quick(a, lo, p -1);
                lo = p + 1;
            } else {
                if(hi > p + 1) quick(a, p + 1, hi);
                hi = p - 1;
            }
            m.decDepth();
        }
    }

    private int partition(int[] a, int lo, int hi) {
        int pivotIdx = lo + rnd.nextInt(hi - lo + 1);
        swap(a, pivotIdx, hi);
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (Arr.cmp(m, a[j], pivot) <= 0) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi);
        return i;
    }

    private void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(a, i, j);
        }
    }

    private void swap(int[] a, int i, int j) {
        if (i != j) {
            int t = a[i]; a[i] = a[j]; a[j] = t;
            m.swp();
        }
    }

}
