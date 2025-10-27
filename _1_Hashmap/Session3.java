package _1_Hashmap;

import java.util.HashMap;
import java.util.Map;

public class Session3 {
    /*
     * | Feature                      | `bruteForceCountPairsDiff` | `optimizedCountPairsDiff` |
     * | ----------------------------- | --------------------------- | -------------------------- |
     * | Uses nested loops             | ✅ Yes                      | ❌ No                      |
     * | Uses HashMap (frequency map)  | ❌ No                       | ✅ Yes                     |
     * | Handles duplicates correctly  | ✅ Yes                      | ✅ Yes                     |
     * | Time complexity               | O(n^2)                      | O(n)                       |
     * | Space complexity              | O(1)                        | O(n)                       |
     *
     * Problem: Count all pairs (i, j) such that b[i] - b[j] == k and i < j
     */

    // 🔹 Brute Force Approach (O(n²))
    public static int bruteForceCountPairsDiff(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] - arr[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // 🔹 Optimized Approach using HashMap (O(n))
    public static int optimizedCountPairsDiff(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            // We want to find previous elements b[i] such that b[i] - num == k
            // i.e., b[i] == num + k
            if (freq.containsKey(num + k)) {
                count += freq.get(num + k);
            }

            // Optional: if you want both directions (abs difference)
            // Uncomment the next block
            /*
            if (freq.containsKey(num - k)) {
                count += freq.get(num - k);
            }
            */

            // Update frequency of the current element
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 5, 3, 4, 2 };
        int k = 2;

        System.out.println("Brute Force Count: " + bruteForceCountPairsDiff(arr, k));
        System.out.println("Optimized Count: " + optimizedCountPairsDiff(arr, k));
    }
}
