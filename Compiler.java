public class Compiler {
    public static int countSubarraysBrute2(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        int[] prefix = new int[n];
        
        // Build prefix sum array
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        // Check all subarray sums using prefix[]
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int sum = prefix[end] - (start > 0 ? prefix[start - 1] : 0);
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int k = 3;
        System.out.println("Brute Force 2: " + countSubarraysBrute2(arr, k));
    }
}
