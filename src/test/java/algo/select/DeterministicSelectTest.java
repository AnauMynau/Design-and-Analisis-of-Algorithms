package algo.select;

import algo.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void smallFixedCases() {
        int[] a = {5, -2, 9, 9, 1, 0};
        int[] b = a.clone(); Arrays.sort(b);

        Metrics m = new Metrics();
        DeterministicSelect ds = new DeterministicSelect(m);

        for (int k = 0; k < a.length; k++) {
            int v = ds.select(a.clone(), k);
            assertEquals(b[k], v, "k=" + k);
        }
    }

    @Test
    void randomArraysCompareWithSort() {
        Random rnd = new Random(7);
        for (int t = 0; t < 50; t++) {
            int n = 1 + rnd.nextInt(5000);
            int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
            int[] b = a.clone(); Arrays.sort(b);

            int k = rnd.nextInt(n);

            Metrics m = new Metrics();
            DeterministicSelect ds = new DeterministicSelect(m);
            int v = ds.select(a, k);

            assertEquals(b[k], v, "Mismatch on t=" + t + " n=" + n + " k=" + k);
        }
    }

    @Test
    void manyDuplicates() {
        int[] a = new int[1000];
        Arrays.fill(a, 42);
        a[123] = 41; a[777] = 43; // чуть разбавили
        int[] b = a.clone(); Arrays.sort(b);

        Metrics m = new Metrics();
        DeterministicSelect ds = new DeterministicSelect(m);

        for (int k : new int[]{0, 500, 999}) {
            assertEquals(b[k], ds.select(a.clone(), k));
        }
    }
}
