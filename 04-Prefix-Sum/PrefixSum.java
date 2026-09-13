// Prefix Sum pattern — reference implementations (Java)
// Compile: javac PrefixSum.java   Run: java PrefixSum
import java.util.*;

public class PrefixSum {

    // 1) Immutable range sum: build O(n), query O(1). Long guards overflow.
    static class RangeSum {
        private final long[] p;
        RangeSum(int[] a) {
            p = new long[a.length + 1];
            for (int k = 0; k < a.length; k++) p[k + 1] = p[k] + a[k];
        }
        long query(int i, int j) { return p[j + 1] - p[i]; }   // inclusive a[i..j]
    }

    // 2) Subarray Sum Equals K — prefix sum + hash map. O(n) / O(n).
    static int subarraySum(int[] a, int k) {
        Map<Integer,Integer> count = new HashMap<>();
        count.put(0, 1);
        int prefix = 0, ans = 0;
        for (int x : a) {
            prefix += x;
            ans += count.getOrDefault(prefix - k, 0);
            count.merge(prefix, 1, Integer::sum);
        }
        return ans;
    }

    // 3) 2D prefix sum — region sum via inclusion-exclusion. Build O(mn), query O(1).
    static class Matrix2D {
        private final long[][] p;
        Matrix2D(int[][] m) {
            int R = m.length, C = m[0].length;
            p = new long[R + 1][C + 1];
            for (int r = 0; r < R; r++)
                for (int c = 0; c < C; c++)
                    p[r+1][c+1] = m[r][c] + p[r][c+1] + p[r+1][c] - p[r][c];
        }
        long region(int r1, int c1, int r2, int c2) {   // inclusive corners
            return p[r2+1][c2+1] - p[r1][c2+1] - p[r2+1][c1] + p[r1][c1];
        }
    }

    // 4) Difference array — apply many range updates, then one sweep. O(n + q).
    static int[] rangeUpdates(int n, int[][] updates) {   // {i, j, val} inclusive
        int[] diff = new int[n + 1];
        for (int[] u : updates) { diff[u[0]] += u[2]; diff[u[1] + 1] -= u[2]; }
        int[] out = new int[n];
        int run = 0;
        for (int i = 0; i < n; i++) { run += diff[i]; out[i] = run; }
        return out;
    }

    public static void main(String[] args) {
        RangeSum rs = new RangeSum(new int[]{2,4,1,3,5});
        System.out.println(rs.query(1, 3));                 // 8
        System.out.println(subarraySum(new int[]{1,1,1}, 2)); // 2
        Matrix2D md = new Matrix2D(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        System.out.println(md.region(0,0,1,1));             // 12
        System.out.println(Arrays.toString(
            rangeUpdates(5, new int[][]{{1,3,2},{2,4,3}}))); // [0, 2, 5, 5, 3]
    }
}
