package _1_Hashmap;

import java.util.HashMap;
import java.util.Map;

public class Session2 {

    /*
     * | Feature                      | `countPairsWithSum`    | `countPairs` |
| ---------------------------- | -------------------------  | ------------ |
| Uses index map               | ✅ Yes (stores last index) | ❌ No         |
| Uses frequency map           | ❌ No                      | ✅ Yes        |
| Handles duplicates correctly | ❌ No                      | ✅ Yes        |
| Correct total pair count     | ❌ Wrong                   | ✅ Correct    |
| Time complexity              | O(n)                       | O(n)         |
| Space complexity             | O(n)                       | O(n)         |

     */


    public static int bruteForceCountPairs(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] + arr[j] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int OptimizedCountPairs(int[] arr, int k) {
        int count = 0;
        Map<Integer, Integer> seen = new HashMap<>();

        for (int j = 0; j < b.length; ++j) {
            int complement = k - b[j];
            if (seen.containsKey(complement)) {
                count++;
            }
            seen.put(b[j], j);
        }

        return count;
    }

    static int countPairs(int[] b, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;

        for (int num : b) {
            int complement = k - num;

            if (freq.containsKey(complement)) {
                count += freq.get(complement); // add all occurrences
            }

            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int k = 6;
        // System.out.println("Count of pairs: " + bruteForceCountPairs(arr, k));
        System.out.println("Count of pairs: " + OptimizedCountPairs(arr, k));
        System.out.println("Count of pairs: " + countPairs(arr, k));
    }
}
