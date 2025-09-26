package app;

import algo.Metrics;
import algo.Sorter;
import algo.merge.MergeSort;

import java.util.Arrays;
import java.util.Random;

public class BenchMain {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 100_000;
        int cutoff  = args.length > 1 ? Integer.parseInt(args[1]) : 24;
        long seed = args.length > 2 ? Long.parseLong(args[2]) : 42L;

        int[] a = new Random(seed).ints(n).toArray();
        Metrics m = new Metrics();
        Sorter sorter = new MergeSort(m, cutoff);

        long t0 = System.nanoTime();
        sorter.sort(a);
        long t1 = System.nanoTime();

        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        if (!Arrays.equals(a, b)) throw new AssertionError("not sorted");

        System.out.println("algo,n,cutoff,seed,time_ns,depth_max,comparisons,swaps,alloc_bytes");
        System.out.printf("%s,%d,%d,%d,%d,%d,%d,%d%n",
                sorter.name(), n , cutoff, seed, (t1-t0),
                m.depthMax, m.comparisons, m.swaps, m.allocations);

    }
}
