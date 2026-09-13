// Binary Search pattern — reference implementations (Java)
// Compile: javac BinarySearch.java   Run: java BinarySearch
public class BinarySearch {

    // 1) Classic: exact match on a sorted array. O(log n) / O(1).
    static int search(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;      // overflow-safe
            if (a[mid] == target) return mid;
            else if (a[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    // 2) Lower bound: first index with a[i] >= target (may be n). O(log n).
    static int lowerBound(int[] a, int target) {
        int lo = 0, hi = a.length;             // half-open
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= target) hi = mid;    // keep mid as candidate
            else lo = mid + 1;
        }
        return lo;
    }

    // 3) Binary search on the ANSWER: min ship capacity to deliver in `days`.
    //    O(n log(sum)). Feasibility check is a linear greedy simulation.
    static int shipWithinDays(int[] w, int days) {
        int lo = 0, hi = 0;
        for (int x : w) { lo = Math.max(lo, x); hi += x; }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (feasible(w, mid, days)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    static boolean feasible(int[] w, int cap, int days) {
        int need = 1, load = 0;
        for (int x : w) {
            if (load + x > cap) { need++; load = 0; }
            load += x;
        }
        return need <= days;
    }

    // 4) Search in rotated sorted array. O(log n).
    static int searchRotated(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) return mid;
            if (a[lo] <= a[mid]) {                       // left half sorted
                if (a[lo] <= target && target < a[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {                                     // right half sorted
                if (a[mid] < target && target <= a[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1,3,5,7,9,11,13,15}, 13)); // 6
        System.out.println(lowerBound(new int[]{1,2,4,4,5}, 4));       // 2
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5)); // 15
        System.out.println(searchRotated(new int[]{4,5,6,7,0,1,2}, 0)); // 4
    }
}
