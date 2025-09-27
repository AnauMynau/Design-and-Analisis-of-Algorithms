package algo.closest;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    @Test
    void matchesBruteforceOnSmall() {
        Random rnd = new Random(123);
        for (int t = 0; t < 50; t++) {
            int n = 100 + rnd.nextInt(500);
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) pts[i] =
                    new ClosestPair.Point(rnd.nextDouble()*1e6, rnd.nextDouble()*1e6);

            double fast = ClosestPair.closestPair(pts);
            double slow = ClosestPair.brute(pts);
            assertEquals(slow, fast, 1e-9);
        }
    }
}
