package algo.closest;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void smallFixed() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(10, 10)
        };
        double fast = ClosestPair.closestPair(pts);
        double slow = ClosestPair.brute(pts);
        assertEquals(slow, fast, 1e-9);
    }

    @Test
    void randomMatchesBruteforceOnSmall() {
        Random rnd = new Random(123);
        for (int t = 0; t < 50; t++) {
            int n = 50 + rnd.nextInt(150); // чтобы брутфорс был быстрым
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(
                        rnd.nextDouble() * 1e6,
                        rnd.nextDouble() * 1e6
                );
            }
            double fast = ClosestPair.closestPair(pts);
            double slow = ClosestPair.brute(pts);
            assertEquals(slow, fast, 1e-8, "t=" + t);
        }
    }

    @Test
    void degenerateCases() {
        // одинаковые точки
        ClosestPair.Point[] same = {
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(5, 5)
        };
        assertEquals(0.0, ClosestPair.closestPair(same), 1e-12);

        // точки на одной вертикали
        ClosestPair.Point[] vertical = {
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(2, 10),
                new ClosestPair.Point(2, 3),
                new ClosestPair.Point(2, 7)
        };
        double fast = ClosestPair.closestPair(vertical);
        double slow = ClosestPair.brute(vertical);
        assertEquals(slow, fast, 1e-12);
    }
}

