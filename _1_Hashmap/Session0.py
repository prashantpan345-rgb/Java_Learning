# HashMap equivalent in Python is a dictionary

def main():
    arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    # # Brute Force
    # max_val = arr[0]
    # min_val = arr[0]

    # for i in range(1, len(arr)):
    #     if arr[i] > max_val:
    #         max_val = arr[i]
    #     if arr[i] < min_val:
    #         min_val = arr[i]

    # print("Max:", max_val)
    # print("Min:", min_val)

    # Optimal Approach using dictionary
    freq_map = {}

    for num in arr:
        freq_map[num] = freq_map.get(num, 0) + 1

    min_freq = float('inf')
    max_freq = float('-inf')
    min_key = -1
    max_key = -1

    for key, freq in freq_map.items():
        if freq < min_freq:
            min_freq = freq
            min_key = key
        if freq > max_freq:
            max_freq = freq
            max_key = key

    print("Max:", max_key)
    print("Min:", min_key)


if __name__ == "__main__":
    main()
