package algo;

public final class Arr {
    public static int cmp(Metrics m, int x, int y) {
        m.cmp(); return Integer.compare(x, y);
    }
    public static void swp(Metrics m, int[] a, int i, int j) {
        if ( i!=j ) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
            m.swp();
        }
    }
}
