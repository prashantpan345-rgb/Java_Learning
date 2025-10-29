import java.util.*;

public class Session6 {
    /**
     * 🐢 Approach 1: Brute Force (O(N²))
     * 
     * For each index j, count all subarrays that end at j
     * and check if their sum equals k.
     *
     * Time Complexity: O(N²)
     * Space Complexity: O(1)
     */
    public static int countSubarraysBrute1(int[] arr, int k) {
        int n = arr.length;
        int count = 0;

        // Fix the end index of subarray
        for (int j = 0; j < n; j++) {
            int sum = 0;

            // Move backward to include all subarrays ending at j
            for (int i = j; i >= 0; i--) {
                sum += arr[i]; // Add current element
                if (sum == k) {
                    count++; // Found a valid subarray
                }
            }
        }

        return count;
    }

    /**
     * 🧮 Approach 2: Brute Force using Prefix Sum (O(N²))
     * 
     * Step 1: Build prefix sum array → prefix[i] = arr[0] + ... + arr[i]
     * Step 2: For each (l, r) pair, compute sum = prefix[r] - prefix[l-1]
     * Step 3: Count all pairs where sum == k
     *
     * Time Complexity: O(N²)
     * Space Complexity: O(N)
     */
    public static int countSubarraysBrute2(int[] arr, int k) {
        int n = arr.length;
        int count = 0;

        // Step 1: Build prefix sum array
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        // Step 2: Iterate through all possible subarrays
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                // Subarray sum using prefix array
                int sum = prefix[r] - (l > 0 ? prefix[l - 1] : 0);
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * ⚡ Approach 3: Optimized using Prefix Sum + HashMap (O(N))
     * 
     * Idea:
     *   prefixSum[j] = sum(arr[0..j])
     *   For each index j, we want to know how many i exist such that:
     *       prefixSum[j] - prefixSum[i-1] = k
     *   ⇒ prefixSum[i-1] = prefixSum[j] - k
     * 
     * So we maintain a HashMap that stores:
     *   { prefixSumValue → frequency }
     *
     * Each time we see prefixSum[j] - k in the map,
     * we add its frequency to our result.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int countSubarraysOptimized(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int prefixSum = 0;
        int count = 0;

        // Initialize: sum 0 has occurred once
        // This handles subarrays starting from index 0
        freq.put(0, 1);

        for (int num : arr) {
            prefixSum += num; // Current prefix sum

            // Calculate the required prefix sum to form a subarray with sum = k
            int target = prefixSum - k;

            // If target exists in map → found valid subarray(s)
            if (freq.containsKey(target)) {
                count += freq.get(target);
            }

            // Update frequency of current prefix sum
            freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    /**
     * 🧠 Main driver method to test all approaches
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int k = 3;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum (k): " + k);

        System.out.println("\n🔹 Brute Force (O(N²)) Result: " + countSubarraysBrute1(arr, k));
        System.out.println("🔹 Brute Force + Prefix Sum (O(N²)) Result: " + countSubarraysBrute2(arr, k));
        System.out.println("⚡ Optimized (Prefix + HashMap) Result: " + countSubarraysOptimized(arr, k));
    }
}
