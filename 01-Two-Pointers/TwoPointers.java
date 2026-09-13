// Two Pointers pattern — reference implementations (Java)
// Compile: javac TwoPointers.java   Run: java TwoPointers
import java.util.*;

public class TwoPointers {

    // 1) Opposite-ends on a SORTED array: find the pair that sums to target.
    //    Time O(n), Space O(1).
    static int[] twoSumSorted(int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left < right) {
            int sum = a[left] + a[right];
            if (sum == target) return new int[] { left, right };
            else if (sum < target) left++;   // need a bigger sum
            else right--;                     // need a smaller sum
        }
        return new int[] { -1, -1 };
    }

    // 2) Same-direction (slow/fast): remove duplicates in place from a SORTED array.
    //    Returns length of the unique prefix. Time O(n), Space O(1).
    static int removeDuplicates(int[] a) {
        if (a.length == 0) return 0;
        int slow = 0;
        for (int fast = 1; fast < a.length; fast++) {
            if (a[fast] != a[slow]) {
                slow++;
                a[slow] = a[fast];
            }
        }
        return slow + 1;
    }

    // 3) Opposite-ends classic: Container With Most Water. Move the shorter wall.
    //    Time O(n), Space O(1).
    static int maxArea(int[] height) {
        int left = 0, right = height.length - 1, best = 0;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            best = Math.max(best, h * (right - left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        return best;
    }

    // 4) Same-direction: Move Zeroes to the end, keep relative order.
    //    Time O(n), Space O(1).
    static void moveZeroes(int[] a) {
        int slow = 0;
        for (int fast = 0; fast < a.length; fast++) {
            if (a[fast] != 0) {
                int t = a[slow]; a[slow] = a[fast]; a[fast] = t;
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSumSorted(new int[]{1,2,4,7,11,15}, 15))); // [2, 4]

        int[] dup = {1,1,2,2,2,3,4};
        int k = removeDuplicates(dup);
        System.out.println(k + " " + Arrays.toString(Arrays.copyOf(dup, k)));            // 4 [1, 2, 3, 4]

        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));                        // 49

        int[] z = {0,1,0,3,12};
        moveZeroes(z);
        System.out.println(Arrays.toString(z));                                          // [1, 3, 12, 0, 0]
    }
}
