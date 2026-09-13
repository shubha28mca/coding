// HashMap / Frequency pattern — reference implementations (Java)
// Compile: javac HashMapFrequency.java   Run: java HashMapFrequency
import java.util.*;

public class HashMapFrequency {

    // 1) Two Sum (unsorted) in one pass. Time O(n), Space O(n).
    static int[] twoSum(int[] a, int target) {
        Map<Integer,Integer> seen = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int need = target - a[i];
            if (seen.containsKey(need)) return new int[] { seen.get(need), i };
            seen.put(a[i], i);
        }
        return new int[] { -1, -1 };
    }

    // 2) Group Anagrams. Time O(n * L log L), Space O(n * L).
    static List<List<String>> groupAnagrams(String[] words) {
        Map<String,List<String>> groups = new HashMap<>();
        for (String w : words) {
            char[] c = w.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }
        return new ArrayList<>(groups.values());
    }

    // 3) Subarray Sum Equals K — key is the running PREFIX SUM. Time O(n), Space O(n).
    static int subarraySum(int[] a, int k) {
        Map<Integer,Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);           // empty prefix
        int prefix = 0, count = 0;
        for (int x : a) {
            prefix += x;
            count += prefixCount.getOrDefault(prefix - k, 0);
            prefixCount.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    // 4) Frequency counter — first unique character index, or -1.
    static int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3,5,2,8}, 10))); // [2, 3]
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
        System.out.println(subarraySum(new int[]{1,1,1}, 2));               // 2
        System.out.println(firstUniqChar("leetcode"));                      // 0
    }
}
