# Session3.py

# --------------------------------------------------------------
# Problem:
# Count all pairs (i, j) such that arr[i] - arr[j] == k and i < j
# --------------------------------------------------------------
# | Feature                      | brute_force_count_pairs_diff | optimized_count_pairs_diff |
# | ----------------------------- | ---------------------------- | --------------------------- |
# | Uses nested loops             | ✅ Yes                       | ❌ No                       |
# | Uses HashMap (frequency map)  | ❌ No                        | ✅ Yes                      |
# | Handles duplicates correctly  | ✅ Yes                       | ✅ Yes                      |
# | Time complexity               | O(n²)                       | O(n)                        |
# | Space complexity              | O(1)                        | O(n)                        |
# --------------------------------------------------------------


# 🔹 Brute Force Approach (O(n²))
def brute_force_count_pairs_diff(arr, k):
    count = 0
    n = len(arr)

    # Compare every pair (i, j) where i < j
    for i in range(n - 1):
        for j in range(i + 1, n):
            if arr[i] - arr[j] == k:
                count += 1
    return count


# 🔹 Optimized Approach using HashMap (O(n))
def optimized_count_pairs_diff(arr, k):
    freq = {}   # Dictionary to store frequencies of elements
    count = 0

    for num in arr:
        # We want previous numbers 'x' such that: x - num == k  ⟹  x = num + k
        if (num + k) in freq:
            count += freq[num + k]

        # Optional: Uncomment this block if you want to count *absolute differences*
        # i.e., pairs where |arr[i] - arr[j]| == k
        """
        if (num - k) in freq:
            count += freq[num - k]
        """

        # Update frequency of current number
        freq[num] = freq.get(num, 0) + 1

    return count


# --------------------------------------------------------------
# MAIN FUNCTION
# --------------------------------------------------------------
def main():
    arr = [1, 5, 3, 4, 2]
    k = 2

    print("Brute Force Count:", brute_force_count_pairs_diff(arr, k))
    print("Optimized Count:", optimized_count_pairs_diff(arr, k))


if __name__ == "__main__":
    main()
