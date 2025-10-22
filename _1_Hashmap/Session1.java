package _1_Hashmap;

import java.util.Scanner;

public class Session1 {

    // static boolean distanceLessThanK(int[] arr, int n, int k) {
    //     //  Brute Force: Implement the logic to find pairs with distance less than k
    //     int count = 0;
    //     for (int i = 0; i < n; i++) {
    //         for (int j = i + 1; j <= n && j<= i+k; j++) {
    //             if(arr[j] == arr[i]){
    //                 System.out.println("Pair Found: " + i + ", " + j );
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    static boolean distanceLessThanK(int[] arr, int n, int k) {
        // Optimal Approach: Implement the logic to find pairs with distance less than k
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                int prevIndex = map.get(arr[i]);
                if (i - prevIndex <= k) {
                    System.out.println("Pair Found: " + prevIndex + ", " + i);
                    return true;
                }
            }
            map.put(arr[i], i);
        }
        return false;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        // Your code here
        distanceLessThanK(arr, n, k);
    }
}
