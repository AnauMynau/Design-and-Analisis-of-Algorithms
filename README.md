Design and Analysis of Algorithms

This project implements and benchmarks several fundamental algorithms using Java 17 and JUnit 5.
The focus is on divide-and-conquer techniques, sorting, and selection problems, with clean code, tests, and CI integration.

📂 Project Structure
Implemented Algorithms

#MergeSort
Top-down merge sort with cutoff to insertion sort.
Uses reusable buffer array to reduce allocations.

#QuickSort
Randomized pivot.
Always recurses on the smaller subarray first (stack depth optimization).

#Deterministic Select
Median-of-Medians (MoM5) algorithm for linear-time selection.
Tested against brute-force results.

#Closest Pair of Points
Divide-and-conquer algorithm in O(n log n).
Tested against brute-force method for correctness.

Metrics & Utilities
Track comparisons, swaps, recursion depth.
Helpers for arrays and sorting.
      
Tests

Unit tests are written with JUnit 5 (junit-jupiter).
Run them with Maven:
*mvn test*

#Tests include:
Sorting correctness on random arrays.
Selection correctness compared to brute-force.
Closest Pair correctness compared to brute-force.

Run Benchmarks

The BenchMain class allows running experiments and outputs CSV-style metrics:
*mvn exec:java -Dexec.mainClass="algo.app.BenchMain"*

Example output:
*algo,n,cutoff,seed,time_ns,depth_max,comparisons,swaps,alloc_bytes
MergeSort(cutoff=24),100000,24,42,42224000,13,1770510,0*


Continuous Integration
- CI configured via GitHub Actions.
- Java 17, Maven build, and JUnit tests run on every pull request.
-Ensures code quality and correctness across branches.

#Commit Storyline

init: maven, junit5, ci, readme
feat(metrics): counters, depth tracker, CSV writer
feat(mergesort): baseline + reuse buffer + cutoff + tests
feat(quicksort): smaller-first recursion, randomized pivot + tests
feat(select): deterministic select (MoM5) + tests
feat(closest): divide-and-conquer implementation + tests
bench(jmh): harness for select vs sort
docs(report): master cases & AB intuition, initial plots
fix: edge cases (duplicates, tiny arrays)
release: v1.0


<img width="1018" height="451" alt="image" src="https://github.com/user-attachments/assets/1ffbd850-57df-4927-a198-1142fb942306" />


