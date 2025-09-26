package algo.merge;

import algo.Metrics;
import algo.Sorter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void randomArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 50; t++) {
            int n  = 1 + rnd.nextInt(10_000);
            int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
            int[] b = Arrays.copyOf(a, n);
            Metrics m = new Metrics();
            Sorter s = new MergeSort(m, 24);
            s.sort(a);
            Arrays.sort(b);
            assertArrayEquals(b, a, "must be sorted");
            assertTrue(m.depthMax > 0, "depth should be tracked");
        }

    }

}
