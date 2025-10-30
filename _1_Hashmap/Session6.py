# ---------------------------------------------
# 🐢 Approach 1: Brute Force (O(N²))
# ---------------------------------------------
# For each index j, count all subarrays that end at j
# and check if their sum equals k.
#
# Time Complexity: O(N²)
# Space Complexity: O(1)
def count_subarrays_brute1(arr, k):
    n = len(arr)
    count = 0

    # Fix the end index of the subarray
    for j in range(n):
        total = 0

        # Move backward to include all subarrays ending at j
        for i in range(j, -1, -1):
            total += arr[i]  # Add current element
            if total == k:
                count += 1  # Found a valid subarray

    return count


# ---------------------------------------------
# 🧮 Approach 2: Brute Force using Prefix Sum (O(N²))
# ---------------------------------------------
# Step 1: Build prefix sum array → prefix[i] = arr[0] + ... + arr[i]
# Step 2: For each (l, r) pair, compute sum = prefix[r] - prefix[l-1]
# Step 3: Count all pairs where sum == k
#
# Time Complexity: O(N²)
# Space Complexity: O(N)
def count_subarrays_brute2(arr, k):
    n = len(arr)
    count = 0

    # Step 1: Build prefix sum array
    prefix = [0] * n
    prefix[0] = arr[0]
    for i in range(1, n):
        prefix[i] = prefix[i - 1] + arr[i]

    # Step 2: Iterate through all possible subarrays
    for l in range(n):
        for r in range(l, n):
            # Subarray sum using prefix array
            sub_sum = prefix[r] - (prefix[l - 1] if l > 0 else 0)
            if sub_sum == k:
                count += 1

    return count


# ---------------------------------------------
# ⚡ Approach 3: Optimized using Prefix Sum + HashMap (O(N))
# ---------------------------------------------
# Idea:
#   prefixSum[j] = sum(arr[0..j])
#   For each index j, we want to know how many i exist such that:
#       prefixSum[j] - prefixSum[i-1] = k
#   ⇒ prefixSum[i-1] = prefixSum[j] - k
#
# We maintain a HashMap that stores:
#   { prefixSumValue → frequency }
#
# Each time we see prefixSum[j] - k in the map,
# we add its frequency to our result.
#
# Time Complexity: O(N)
# Space Complexity: O(N)
def count_subarrays_optimized(arr, k):
    freq = {0: 1}  # sum 0 has occurred once (for subarrays starting at index 0)
    prefix_sum = 0
    count = 0

    for num in arr:
        prefix_sum += num  # Current prefix sum

        # Calculate the required prefix sum to form a subarray with sum = k
        target = prefix_sum - k

        # If target exists in map → found valid subarray(s)
        if target in freq:
            count += freq[target]

        # Update frequency of current prefix sum
        freq[prefix_sum] = freq.get(prefix_sum, 0) + 1

    return count


# ---------------------------------------------
# 🧠 Main Driver Code
# ---------------------------------------------
if __name__ == "__main__":
    arr = [1, 2, 3]
    k = 3

    print("Array:", arr)
    print("Target Sum (k):", k)

    print("\n🔹 Brute Force (O(N²)) Result:", count_subarrays_brute1(arr, k))
    print("🔹 Brute Force + Prefix Sum (O(N²)) Result:", count_subarrays_brute2(arr, k))
    print("⚡ Optimized (Prefix + HashMap) Result:", count_subarrays_optimized(arr, k))
