package algo.select;

import algo.Metrics;

public final class DeterministicSelect {
    private final Metrics m;
    public DeterministicSelect(Metrics m) { this.m = m; }

    /** k — 0-based индекс порядковой статистики */
    public int select(int[] a, int k) {
        if (a == null || a.length == 0) throw new IllegalArgumentException("empty");
        if (k < 0 || k >= a.length) throw new IllegalArgumentException("bad k");
        return selectRange(a, 0, a.length - 1, k);
    }

    private int selectRange(int[] a, int lo, int hi, int k) {
        while (true) {
            if (lo == hi) return a[lo];

            int pivotIndex = medianOfMedians(a, lo, hi);
            int pivotValue = a[pivotIndex];

            int lt = lo, i = lo, gt = hi;
            while (i <= gt) {
                int cmp = Integer.compare(a[i], pivotValue);
                if (cmp < 0) { swap(a, lt, i); lt++; i++; }
                else if (cmp > 0) { swap(a, i, gt); gt--; }
                else i++;
            }
            int leftSize = lt - lo;
            int midSize = gt - lt + 1;

            m.incDepth();
            if (k < leftSize) { hi = lt - 1; }
            else if (k < leftSize + midSize) { m.decDepth(); return pivotValue; }
            else { k -= (leftSize + midSize); lo = gt + 1; }
            m.decDepth();
        }
    }

    /** Возвращает индекс медианы медиан */
    private int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 5) {
            insertionSort(a, lo, hi);
            return lo + n / 2;
        }

        int groups = (n + 4) / 5;
        for (int i = 0; i < groups; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(a, subLo, subHi);
            int medianIdx = subLo + (subHi - subLo) / 2;
            swap(a, lo + i, medianIdx); // складываем медианы в начало
        }
        return medianOfMedians(a, lo, lo + groups - 1);
    }

    private void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i], j = i - 1;
            while (j >= lo && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }

    private void swap(int[] a, int i, int j) {
        if (i != j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
    }
}
