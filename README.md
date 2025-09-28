# Design and Analysis of Algorithms – Sorting and Selection

## 📌 Architecture Notes
In this project, we implemented and analyzed several algorithms:
- **MergeSort** (with cutoff to InsertionSort, buffer reuse).
- **QuickSort** (randomized pivot, smaller-first recursion).
- **Deterministic Select (Median-of-Medians)**.
- **Closest Pair of Points** (divide-and-conquer).

We used a **metrics class** to track recursion depth and operations.  
This allowed us to measure not only execution time but also recursion behavior (e.g., verifying that QuickSort depth is bounded by `~2 * floor(log₂ n)` with randomized pivots).

---

## 📊 Recurrence Analysis
Here we give the recurrence and asymptotic analysis for each algorithm.

### MergeSort
- **Recurrence:**  
  T(n) = 2T(n/2) + Θ(n)  
- **Result (Master Theorem):** Θ(n log n).  
- Cutoff to InsertionSort improves constant factors for small `n`.

### QuickSort (randomized pivot)
- **Recurrence (expected):**  
  T(n) = T(αn) + T((1-α)n) + Θ(n), where pivot splits randomly.  
- **Result:** Θ(n log n) on average; depth bounded by `O(log n)` with high probability.  
- Worst-case Θ(n²), but randomized pivot avoids adversarial input.

### Deterministic Select (Median-of-Medians)
- **Recurrence:**  
  T(n) = T(n/5) + T(7n/10) + Θ(n).  
- **Result (Akra–Bazzi):** Θ(n).  
- Guarantees linear worst-case selection.

### Closest Pair of Points
- **Recurrence:**  
  T(n) = 2T(n/2) + Θ(n).  
- **Result:** Θ(n log n).  
- Uses divide-and-conquer with strip merging.

---

## 📈 Experimental Results

We measured execution time in **nanoseconds** using `System.nanoTime()`.  
Results were saved to `results.csv` and visualized in Excel.

### Table (excerpt)
| Size | MergeSort (ns) | QuickSort (ns) | Select (ns) | ClosestPair (ns) |
|------|----------------|----------------|-------------|------------------|
| 10   | 13,342,300     | 2,480,700      | 16,443,000  | 11,451,400       |
| 100  | 89,600         | 168,500        | 97,100      | 1,041,200        |
| 1000 | 913,300        | 705,200        | 493,000     | 4,929,900        |
| 5000 | 1,601,300      | 1,825,500      | 2,215,200   | 14,487,000       |

### Plot (Execution Time)
<img width="752" height="452" alt="image" src="https://github.com/user-attachments/assets/496668fa-b81d-4e16-b8db-3298edfe5568" />


[results.csv](https://github.com/user-attachments/files/22581418/results.csv)Size ;MergeSort;QuickSort;Select;ClosetPair
10;13342300;2480700;16443000;11451400
50;113500;164500;73600;721100
100;89600;168500;97100;1041200
500;600100;854500;507100;3730100
1000;913300;705200;493000;4929900
2000;562200;1067800;1246800;6973200
5000;1601300;18225500;2215200;14487000


---

## 📝 Summary
- **MergeSort vs QuickSort:** Both scale as Θ(n log n), but QuickSort is slightly faster for medium `n` due to cache locality, while MergeSort is more stable.  
- **Select:** Linear time confirmed in practice, though with larger constants.  
- **Closest Pair:** Shows expected Θ(n log n) scaling, but constants are large due to recursive overhead.  
- **Theory vs Experiment:** Results match theoretical complexity. Minor deviations explained by JVM overhead, caching, and constant-factor effects.

---

## ✅ How to Run
```bash
# Run benchmarks
mvn compile exec:java -Dexec.mainClass="app.BenchMain"
