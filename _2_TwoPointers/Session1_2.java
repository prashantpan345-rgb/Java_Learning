package _2_TwoPointers;

import java.util.*;

public class Session1_2 {
    /*
  Problem: Number of subarrays with at most K distinct elements.

  This file contains:
  1) bruteForceAtMostK(...)     - O(N^2) brute force (with early break)
  2) countAtMostKDistinct(...) - O(N) sliding window (two-pointer) method
  3) main(...)                 - reads input and prints both results

  Usage:
    Input:
      n K
      array elements (n integers)
    Example:
      6 2
      1 2 1 2 3 2

    Output:
      Brute force (at most K distinct) = ...
      Optimized  (at most K distinct) = ...
      Exactly K distinct (optional) = ...
*/


    // ---------------- BRUTE FORCE ----------------
    // Count subarrays with at most K distinct elements using nested loops.
    // For each starting index i, expand j and maintain a HashSet or frequency map.
    // Since adding new elements cannot reduce the number of distinct elements,
    // when distinct > K we can break the inner loop early.
    public static long bruteForceAtMostK(int[] arr, int K) {
        int n = arr.length;
        long count = 0;

        for (int i = 0; i < n; i++) {
            // Use a HashSet to maintain distinct values in current subarray [i..j]
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(arr[j]);
                if (set.size() <= K) {
                    count++;
                } else {
                    // Since all numbers are fixed, adding more elements can't reduce distinct count,
                    // so we can break the inner loop early for this starting i.
                    break;
                }
            }
        }

        return count;
    }

    // ---------------- OPTIMIZED (SLIDING WINDOW) ----------------
    // Count subarrays with at most K distinct elements in O(N) time.
    //
    // We maintain a window [left..right] with:
    //  - freq map of elements in window
    //  - distinct = number of keys with freq > 0
    //
    // For each right (j) we:
    //  - add arr[j] to freq
    //  - while distinct > K, shrink from left (decrease freq, maybe remove key)
    //  - now window [left..j] has at most K distinct -> add (j - left + 1) to count
    public static long countAtMostKDistinct(int[] arr, int K) {
        int n = arr.length;
        if (K < 0) return 0; // convenience: no subarray can have negative distinct count

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        long count = 0;
        int distinct = 0;

        for (int right = 0; right < n; right++) {
            int x = arr[right];
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            if (freq.get(x) == 1) {
                // new distinct element added
                distinct++;
            }

            // If too many distinct elements, shrink from left until distinct <= K
            while (distinct > K && left <= right) {
                int y = arr[left];
                freq.put(y, freq.get(y) - 1);
                if (freq.get(y) == 0) {
                    freq.remove(y);
                    distinct--;
                }
                left++;
            }

            // All subarrays that end at 'right' and start between left..right are valid.
            // Number of such subarrays = (right - left + 1)
            count += (right - left + 1);
        }

        return count;
    }

    // Helper: number of subarrays with exactly K distinct elements
    // Exactly(K) = AtMost(K) - AtMost(K-1)
    public static long countExactlyKDistinct(int[] arr, int K) {
        if (K <= 0) return 0;
        return countAtMostKDistinct(arr, K) - countAtMostKDistinct(arr, K - 1);
    }

    // ---------------- MAIN / IO ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input
        // First line: n K
        // Next line or continuing inputs: n integers
        System.out.print("Enter n and K: ");
        int n = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Brute force result
        long brute = bruteForceAtMostK(arr, K);
        System.out.println("\nBrute force (at most " + K + " distinct) = " + brute);

        // Optimized result
        long optimized = countAtMostKDistinct(arr, K);
        System.out.println("Optimized  (at most " + K + " distinct) = " + optimized);

        // Exactly K (optional)
        long exactly = countExactlyKDistinct(arr, K);
        System.out.println("Exactly " + K + " distinct = " + exactly);

        sc.close();
    }
}