"""
Problem: Number of subarrays with at most K distinct elements.

This file contains:
1) brute_force_at_most_k(...)   - O(N²) brute force (with early break)
2) count_at_most_k_distinct(...) - O(N) sliding window (two-pointer) method
3) main()                        - reads input and prints all results

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
    Exactly K distinct = ...
"""

from collections import defaultdict

# ---------------- BRUTE FORCE ----------------
# Count subarrays with at most K distinct elements using nested loops.
# For each starting index i, expand j and maintain a set of distinct elements.
# Since adding new elements cannot reduce the number of distinct elements,
# when distinct > K, we break early.
def brute_force_at_most_k(arr, K):
    n = len(arr)
    count = 0

    for i in range(n):
        distinct = set()
        for j in range(i, n):
            distinct.add(arr[j])
            if len(distinct) <= K:
                count += 1
            else:
                # Adding more elements will only increase distinct count
                break

    return count


# ---------------- OPTIMIZED (SLIDING WINDOW) ----------------
# Count subarrays with at most K distinct elements in O(N) time.
#
# Approach:
#   Maintain a sliding window [left..right].
#   - Use a frequency map to count occurrences of elements in the window.
#   - Track how many distinct elements are in the window.
#   - If distinct > K, move left pointer until distinct <= K.
#
# For each `right`, number of valid subarrays ending at `right` is (right - left + 1).
def count_at_most_k_distinct(arr, K):
    n = len(arr)
    if K < 0:
        return 0  # No subarray can have negative distinct count

    freq = defaultdict(int)
    left = 0
    count = 0
    distinct = 0

    for right in range(n):
        x = arr[right]
        freq[x] += 1
        if freq[x] == 1:
            distinct += 1  # New distinct element added

        # Shrink window until we have at most K distinct
        while distinct > K and left <= right:
            y = arr[left]
            freq[y] -= 1
            if freq[y] == 0:
                del freq[y]
                distinct -= 1
            left += 1

        # All subarrays ending at `right` and starting in [left..right] are valid
        count += (right - left + 1)

    return count


# ---------------- EXACTLY K DISTINCT ----------------
# Exactly K = AtMost(K) - AtMost(K-1)
def count_exactly_k_distinct(arr, K):
    if K <= 0:
        return 0
    return count_at_most_k_distinct(arr, K) - count_at_most_k_distinct(arr, K - 1)


# ---------------- MAIN ----------------
def main():
    # Input reading
    n, K = map(int, input("Enter n and K: ").split())
    print("Enter array elements:")
    arr = list(map(int, input().split()))

    # Brute force result
    brute = brute_force_at_most_k(arr, K)
    print(f"\nBrute force (at most {K} distinct) = {brute}")

    # Optimized result
    optimized = count_at_most_k_distinct(arr, K)
    print(f"Optimized  (at most {K} distinct) = {optimized}")

    # Exactly K distinct result
    exactly = count_exactly_k_distinct(arr, K)
    print(f"Exactly {K} distinct = {exactly}")


if __name__ == "__main__":
    main()
