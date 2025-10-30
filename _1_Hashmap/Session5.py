# ---------------------------------------------
# 🐢 BRUTE FORCE METHOD
# ---------------------------------------------
# For each query (l, r), loop through all elements
# from l to r and add them up.
#
# Time Complexity: O(N * Q)
# Space Complexity: O(1)
def get_sum_brute_force(arr, l, r):
    total = 0
    for i in range(l, r + 1):
        total += arr[i]  # Add each element from l to r
    return total


# ---------------------------------------------
# ⚡ BUILD PREFIX SUM ARRAY
# ---------------------------------------------
# prefix[i] = sum of all elements from arr[0] to arr[i]
#
# Once we build this array, we can get any range sum in O(1)
# using: sum(l, r) = prefix[r] - prefix[l - 1]
#
# Time Complexity: O(N)
# Space Complexity: O(N)
def build_prefix_sum(arr):
    n = len(arr)
    prefix = [0] * n

    # Base case: prefix[0] = first element
    prefix[0] = arr[0]

    # Build the prefix array iteratively
    for i in range(1, n):
        prefix[i] = prefix[i - 1] + arr[i]

    return prefix


# ---------------------------------------------
# ⚡ GET RANGE SUM USING PREFIX ARRAY
# ---------------------------------------------
# Formula:
# Sum(l, r) = prefix[r] - prefix[l - 1]
# (If l == 0, simply return prefix[r])
#
# This gives the sum of elements between indexes l and r.
#
# Time Complexity: O(1)
# ---------------------------------------------
def get_sum_optimized(prefix, l, r):
    if l == 0:
        return prefix[r]  # sum from 0 to r
    return prefix[r] - prefix[l - 1]  # remove sum before l


# ---------------------------------------------
# 🧮 MAIN EXECUTION
# ---------------------------------------------
# Demonstrates both brute force and prefix sum
# approaches on the same queries.
if __name__ == "__main__":
    arr = [2, 4, 6, 8, 10]  # Original array
    queries = [
        (0, 2),  # sum from index 0 to 2
        (1, 3),  # sum from index 1 to 3
        (2, 4)   # sum from index 2 to 4
    ]

    # ------------------------
    # 🐢 Brute Force Results
    # ------------------------
    print("Brute Force Results:")
    for l, r in queries:
        print(f"Sum({l},{r}) = {get_sum_brute_force(arr, l, r)}")

    # ------------------------
    # ⚡ Optimized Results
    # ------------------------
    prefix = build_prefix_sum(arr)
    print("\nOptimized Results (Prefix Sum):")
    for l, r in queries:
        print(f"Sum({l},{r}) = {get_sum_optimized(prefix, l, r)}")
