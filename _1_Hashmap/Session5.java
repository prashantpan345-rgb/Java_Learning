package _1_Hashmap;

public class Session5 {
    /*
     * ---------------------------------------------
     * 🐢 BRUTE FORCE METHOD
     * ---------------------------------------------
     * For each query (l, r), loop through all elements
     * from l to r and add them up.
     *
     * Time Complexity: O(N * Q)
     * Space Complexity: O(1)
     */
    public static int getSumBruteForce(int[] arr, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i]; // Add each element from l to r
        }
        return sum;
    }

    /*
     * ---------------------------------------------
     * ⚡ BUILD PREFIX SUM ARRAY
     * ---------------------------------------------
     * prefix[i] = sum of all elements from arr[0] to arr[i]
     *
     * Once we build this array, we can get any range sum in O(1)
     * using: sum(l, r) = prefix[r] - prefix[l - 1]
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] buildPrefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];

        // Base case: prefix[0] = first element
        prefix[0] = arr[0];

        // Build the prefix array iteratively
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        return prefix;
    }

    /*
     * ---------------------------------------------
     * ⚡ GET RANGE SUM USING PREFIX ARRAY
     * ---------------------------------------------
     * Formula:
     * Sum(l, r) = prefix[r] - prefix[l - 1]
     * (If l == 0, simply return prefix[r])
     *
     * This gives the sum of elements between indexes l and r.
     *
     * Time Complexity: O(1)
     * ---------------------------------------------
     */
    public static int getSumOptimized(int[] prefix, int l, int r) {
        if (l == 0)
            return prefix[r]; // sum from 0 to r
        return prefix[r] - prefix[l - 1]; // remove sum before l
    }

    /*
     * ---------------------------------------------
     * 🧮 MAIN METHOD
     * ---------------------------------------------
     * Demonstrates both brute force and prefix sum
     * approaches on the same queries.
     */
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10}; // Original array
        int[][] queries = {
            {0, 2}, // sum from index 0 to 2
            {1, 3}, // sum from index 1 to 3
            {2, 4}  // sum from index 2 to 4
        };

        // ------------------------
        // 🐢 Brute Force Results
        // ------------------------
        System.out.println("Brute Force Results:");
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            System.out.println("Sum(" + l + "," + r + ") = " + getSumBruteForce(arr, l, r));
        }

        // ------------------------
        // ⚡ Optimized Results
        // ------------------------
        int[] prefix = buildPrefixSum(arr);
        System.out.println("\nOptimized Results (Prefix Sum):");
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            System.out.println("Sum(" + l + "," + r + ") = " + getSumOptimized(prefix, l, r));
        }
    }
}
