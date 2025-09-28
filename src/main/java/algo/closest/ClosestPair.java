package algo.closest;

import java.util.*;
import java.util.stream.Collectors;

public final class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double distance(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }

    /** O(n log n) */
    public static double closestPair(Point[] pts) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;

        // Px — по x, Py — по y (оба содержат те же ССЫЛКИ на объекты Point)
        Point[] Px = pts.clone();
        Arrays.sort(Px, Comparator.comparingDouble(p -> p.x));
        Point[] Py = pts.clone();
        Arrays.sort(Py, Comparator.comparingDouble(p -> p.y));

        return solve(Px, Py);
    }

    private static double solve(Point[] Px, Point[] Py) {
        int n = Px.length;
        if (n <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    best = Math.min(best, distance(Px[i], Px[j]));
            return best;
        }

        int mid = n / 2;
        double midX = Px[mid].x;

        // Левая/правая половины по X
        Point[] Qx = Arrays.copyOfRange(Px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(Px, mid, n);

        // Разделяем Py по принадлежности к Qx/Rx через множество ссылок
        // (equals у Point по умолчанию ссылочный — это именно то, что нам нужно)
        Set<Point> leftSet = new HashSet<>(Arrays.asList(Qx));
        Point[] Qy = new Point[mid];
        Point[] Ry = new Point[n - mid];
        int qi = 0, ri = 0;
        for (Point p : Py) {
            if (leftSet.contains(p)) Qy[qi++] = p;
            else Ry[ri++] = p;
        }
        // здесь гарантированно qi == mid и ri == n - mid, без null и переполнений

        double dl = solve(Qx, Qy);
        double dr = solve(Rx, Ry);
        double d = Math.min(dl, dr);

        // Полоса ширины d вокруг midX (Py уже по y, порядок сохраняем)
        Point[] strip = new Point[n];
        int si = 0;
        for (Point p : Py) if (Math.abs(p.x - midX) < d) strip[si++] = p;

        // Каждой точке в полосе достаточно проверить ~7 следующих по y
        for (int i = 0; i < si; i++) {
            for (int j = i + 1; j < si && (strip[j].y - strip[i].y) < d; j++) {
                d = Math.min(d, distance(strip[i], strip[j]));
            }
        }
        return d;
    }

    /** O(n^2) для проверки в тестах */
    public static double brute(Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++)
            for (int j = i + 1; j < pts.length; j++)
                best = Math.min(best, distance(pts[i], pts[j]));
        return best;
    }
}

