# Session2.py

# ----------------------------------------------
# This program demonstrates three approaches to 
# counting pairs in an array whose sum equals 'k'
# ----------------------------------------------

# 1️⃣ Brute Force Approach
# Time Complexity: O(n²)
# Space Complexity: O(1)
# ----------------------------------------------
def brute_force_count_pairs(arr, k):
    count = 0
    # check every pair (i, j)
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            if arr[i] + arr[j] == k:
                count += 1
    return count


# 2️⃣ Optimized Approach (using index map)
# Uses a dictionary to store the last index of each number
# ⚠️ Does NOT handle duplicates correctly (same as Java version)
# Time Complexity: O(n)
# Space Complexity: O(n)
# ----------------------------------------------
def optimized_count_pairs(arr, k):
    count = 0
    seen = {}  # acts like Java's HashMap<Integer, Integer>

    for j in range(len(arr)):
        complement = k - arr[j]

        # if complement has been seen before, count one pair
        if complement in seen:
            count += 1

        # store/update the last index of current number
        seen[arr[j]] = j

    return count


# 3️⃣ Correct Optimal Approach (using frequency map)
# Uses frequency count to correctly handle duplicates
# Example: if k = 6 and arr = [1, 5, 5],
# the pair (1,5) appears twice because 5 appears twice.
# Time Complexity: O(n)
# Space Complexity: O(n)
# ----------------------------------------------
def count_pairs(arr, k):
    freq = {}   # frequency map (num → how many times seen so far)
    count = 0

    for num in arr:
        complement = k - num

        # If complement exists, add its frequency (can be >1)
        if complement in freq:
            count += freq[complement]

        # Increment frequency of current number
        freq[num] = freq.get(num, 0) + 1

    return count


# ----------------------------------------------------
# MAIN FUNCTION
# ----------------------------------------------------
def main():
    arr = [1, 2, 3, 4, 5]
    k = 6

    print("Count of pairs (Brute Force):", brute_force_count_pairs(arr, k))
    print("Count of pairs (Optimized - index map):", optimized_count_pairs(arr, k))
    print("Count of pairs (Correct - frequency map):", count_pairs(arr, k))


# Standard Python entry point
if __name__ == "__main__":
    main()
