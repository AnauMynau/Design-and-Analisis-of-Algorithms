package algo;

public final class Metrics {
    public long comparisons;
    public long swaps;
    public long allocations;
    public long depthMax;
    public long depthCur;

    public void incDepth(){ depthCur++; if (depthCur > depthMax) depthMax = depthCur; }
    public void decDepth() { depthCur--; }
    public void cmp() { comparisons++; }
    public void swp() { swaps++; }
    public void alloc(long bytes) { allocations += bytes; }
}
