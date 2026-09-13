// Stack / Monotonic Stack pattern — reference implementations (Java)
// Compile: javac MonotonicStack.java   Run: java MonotonicStack
import java.util.*;

public class MonotonicStack {

    // 1) Plain stack: valid parentheses. O(n) / O(n).
    static boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        Map<Character,Character> pair = Map.of(')','(', ']','[', '}','{');
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') st.push(c);
            else if (st.isEmpty() || st.pop() != pair.get(c)) return false;
        }
        return st.isEmpty();
    }

    // 2) Monotonic stack: next greater element (value, or -1). O(n) amortized.
    static int[] nextGreater(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();   // indices, decreasing values
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[i] > a[stack.peek()]) res[stack.pop()] = a[i];
            stack.push(i);
        }
        return res;
    }

    // 3) Daily temperatures: days until a warmer temperature (distance). O(n).
    static int[] dailyTemperatures(int[] t) {
        int n = t.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && t[i] > t[stack.peek()]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }
        return res;
    }

    // 4) Largest rectangle in histogram — monotonic increasing stack. O(n).
    static int largestRectangle(int[] h) {
        int n = h.length, best = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? 0 : h[i];
            while (!st.isEmpty() && cur < h[st.peek()]) {
                int height = h[st.pop()];
                int left = st.isEmpty() ? -1 : st.peek();
                best = Math.max(best, height * (i - left - 1));
            }
            st.push(i);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(isValid("([]{})"));                              // true
        System.out.println(Arrays.toString(nextGreater(new int[]{2,1,3,4})));// [3, 3, 4, -1]
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(largestRectangle(new int[]{2,1,5,6,2,3}));        // 10
    }
}
