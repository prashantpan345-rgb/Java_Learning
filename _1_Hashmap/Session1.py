def distance_less_than_k_brute(arr, n, k):
    # Brute Force: Check all pairs within distance k
    for i in range(n):
        for j in range(i + 1, min(n, i + k + 1)):
            if arr[i] == arr[j]:
                print(f"Pair Found: {i}, {j}")
                return True
    return False


def distance_less_than_k_optimal(arr, n, k):
    # Optimal Approach: Using HashMap (dictionary)
    index_map = {}

    for i in range(n):
        if arr[i] in index_map:
            prev_index = index_map[arr[i]]
            if i - prev_index <= k:
                print(f"Pair Found: {prev_index}, {i}")
                return True
        index_map[arr[i]] = i

    return False


def main():
    n = int(input("Enter n: "))
    arr = list(map(int, input("Enter array elements: ").split()))
    k = int(input("Enter k: "))

    print("\n--- Brute Force ---")
    found_brute = distance_less_than_k_brute(arr, n, k)
    if not found_brute:
        print("No pair found using Brute Force")

    print("\n--- Optimal Approach ---")
    found_optimal = distance_less_than_k_optimal(arr, n, k)
    if not found_optimal:
        print("No pair found using Optimal Approach")


if __name__ == "__main__":
    main()
