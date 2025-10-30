package _2_TwoPointers;

/*
Problem: Count the number of subarrays whose sum <= K
Condition: All array elements are non-negative.

Example:
Input: 
n = 5
b = [2, 1, 1, 5, 8]
K = 4

Subarrays with sum <= 4 are:
[2], [2,1], [2,1,1], [1], [1], [1,1]
=> Total = 6

--------------------------------------------
Two approaches are demonstrated:
1️⃣ Brute Force - O(N^2)
2️⃣ Optimized (Sliding Window) - O(N)
--------------------------------------------
*/

import java.util.Scanner;

public class Session1 {

    // -------------------- BRUTE FORCE METHOD --------------------
    /*
     * Approach:
     * - Check every possible subarray [i..j].
     * - Maintain a running sum and count all subarrays with sum <= K.
     * - Since all numbers are non-negative, if sum > K, we can break early (no further extension will work).
     */
    public static long countSubarraysBruteForce(long[] arr, long k) {
        int n = arr.length;
        long count = 0;

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum <= k) {
                    count++;
                } else {
                    // Since numbers are non-negative, further adding will only increase sum
                    break;
                }
            }
        }

        return count;
    }

    // -------------------- OPTIMIZED METHOD --------------------
    /*
     * Approach:
     * - Use two pointers (i, j) to represent a sliding window.
     * - Expand j by adding arr[j] to current sum.
     * - While sum > K, shrink the window from left by moving i.
     * - For each j, the number of valid subarrays ending at j = (j - i + 1)
     *
     * Why it works:
     * - All numbers >= 0 → sum only increases when we move j forward.
     * - If sum > K, we can safely move i to reduce it.
     */
    public static long countSubarraysOptimized(long[] arr, long k) {
        int n = arr.length;
        long count = 0;
        long sum = 0;

        int i = 0;
        for (int j = 0; j < n; j++) {
            sum += arr[j];

            // Shrink the window until sum <= K
            while (sum > k && i <= j) {
                sum -= arr[i];
                i++;
            }

            // All subarrays ending at j and starting from i..j are valid
            count += (j - i + 1);
        }

        return count;
    }

    // -------------------- MAIN FUNCTION --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: size of array and K
        System.out.print("Enter n and K: ");
        int n = sc.nextInt();
        long k = sc.nextLong();

        long[] arr = new long[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        // Brute Force Calculation
        long bruteResult = countSubarraysBruteForce(arr, k);
        System.out.println("\nBrute Force Result: " + bruteResult);

        // Optimized Calculation
        long optimizedResult = countSubarraysOptimized(arr, k);
        System.out.println("Optimized Result: " + optimizedResult);

        sc.close();
    }
}
