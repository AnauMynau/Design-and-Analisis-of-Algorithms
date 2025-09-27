package algo.quick;

import algo.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void sortsSmallArray() {
        int[] a = {5, 3, 8, 1, 2, 2, -10, 7};
        int[] expected = a.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort sorter = new QuickSort(m);
        sorter.sort(a);

        assertArrayEquals(expected, a, "Array must be sorted");
        assertTrue(m.depthMax > 0, "Recursion depth should be > 0");
    }

    @Test
    void sortsRandomLargeArray() {
        Random rnd = new Random(123);
        int[] a = rnd.ints(20000, -1_000_000, 1_000_000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort sorter = new QuickSort(m);
        sorter.sort(a);

        assertArrayEquals(expected, a, "Array must be sorted");
        // Для QuickSort с рандомным pivot глубина должна быть порядка O(log n)
        assertTrue(m.depthMax < 500, "Recursion depth too high for 20k elements");
    }

    @Test
    void handlesAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int[] expected = a.clone();

        Metrics m = new Metrics();
        QuickSort sorter = new QuickSort(m);
        sorter.sort(a);

        assertArrayEquals(expected, a, "Already sorted array should remain sorted");
    }

    @Test
    void handlesArrayWithDuplicates() {
        int[] a = {7, 7, 7, 7, 7, 7, 7};
        int[] expected = a.clone();

        Metrics m = new Metrics();
        QuickSort sorter = new QuickSort(m);
        sorter.sort(a);

        assertArrayEquals(expected, a, "Array with duplicates must be handled correctly");
    }
}
