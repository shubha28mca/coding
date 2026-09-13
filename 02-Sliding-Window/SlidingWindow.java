// Sliding Window pattern — reference implementations (Java)
// Compile: javac SlidingWindow.java   Run: java SlidingWindow
import java.util.*;

public class SlidingWindow {

    // 1) FIXED window: maximum sum of any contiguous subarray of length k.
    //    Time O(n), Space O(1).
    static int maxSumK(int[] a, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += a[i];
        int best = sum;
        for (int r = k; r < a.length; r++) {
            sum += a[r] - a[r - k];   // enter r, leave r-k
            best = Math.max(best, sum);
        }
        return best;
    }

    // 2) VARIABLE window: longest substring with all distinct characters.
    //    Time O(n), Space O(min(n, alphabet)).
    static int longestUnique(String s) {
        Map<Character,Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c) && last.get(c) >= left) {
                left = last.get(c) + 1;   // jump past the repeat
            }
            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // 3) VARIABLE window (positives only): smallest subarray with sum >= target.
    //    Time O(n), Space O(1). Returns 0 if none.
    static int minSubarrayLen(int target, int[] a) {
        int left = 0, sum = 0, best = Integer.MAX_VALUE;
        for (int right = 0; right < a.length; right++) {
            sum += a[right];
            while (sum >= target) {                 // shrink while valid
                best = Math.min(best, right - left + 1);
                sum -= a[left++];
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    public static void main(String[] args) {
        System.out.println(maxSumK(new int[]{2,1,5,1,3,2}, 3));   // 9
        System.out.println(longestUnique("abcabd"));             // 3
        System.out.println(minSubarrayLen(7, new int[]{2,3,1,2,4,3})); // 2  ([4,3])
    }
}
