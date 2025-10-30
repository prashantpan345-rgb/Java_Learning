"""
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
1️⃣ Brute Force - O(N²)
2️⃣ Optimized (Sliding Window) - O(N)
--------------------------------------------
"""

# -------------------- BRUTE FORCE METHOD --------------------
# Approach:
# - Check every possible subarray [i..j].
# - Maintain a running sum and count all subarrays with sum <= K.
# - Since all numbers are non-negative, if sum > K, we can break early (no further extension will work).
def count_subarrays_bruteforce(arr, k):
    n = len(arr)
    count = 0

    for i in range(n):
        total = 0
        for j in range(i, n):
            total += arr[j]
            if total <= k:
                count += 1
            else:
                # Since numbers are non-negative, further adding will only increase sum
                break

    return count


# -------------------- OPTIMIZED METHOD --------------------
# Approach:
# - Use two pointers (i, j) to represent a sliding window.
# - Expand j by adding arr[j] to current sum.
# - While sum > K, shrink the window from left by moving i.
# - For each j, the number of valid subarrays ending at j = (j - i + 1)
#
# Why it works:
# - All numbers >= 0 → sum only increases when we move j forward.
# - If sum > K, we can safely move i to reduce it.
def count_subarrays_optimized(arr, k):
    n = len(arr)
    count = 0
    total = 0
    i = 0

    for j in range(n):
        total += arr[j]

        # Shrink window until sum <= K
        while total > k and i <= j:
            total -= arr[i]
            i += 1

        # All subarrays ending at j and starting from i..j are valid
        count += (j - i + 1)

    return count


# -------------------- MAIN FUNCTION --------------------
def main():
    # Input: size of array and K
    n, k = map(int, input("Enter n and K: ").split())

    print("Enter array elements:")
    arr = list(map(int, input().split()))

    # Brute Force Calculation
    brute_result = count_subarrays_bruteforce(arr, k)
    print("\nBrute Force Result:", brute_result)

    # Optimized Calculation
    optimized_result = count_subarrays_optimized(arr, k)
    print("Optimized Result:", optimized_result)


if __name__ == "__main__":
    main()
