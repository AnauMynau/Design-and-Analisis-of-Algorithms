package algo.select;

import algo.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {
    @Test
    void matchesSortedKth() {
        Random rnd = new Random(7);
        for (int t = 0; t < 50; t++) {
            int n = 1 + rnd.nextInt(5000);
            int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
            int[] b = a.clone();
            Arrays.sort(b);
            int k = rnd.nextInt(n);

            Metrics m = new Metrics();
            DeterministicSelect ds = new DeterministicSelect(m);
            int v = ds.select(a, k);

            assertEquals(b[k], v);
        }
    }
}
