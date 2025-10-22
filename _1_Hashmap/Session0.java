package _1_Hashmap;

import java.util.HashMap;
import java.util.Map;

public class Session0 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // // Brute Force
        // int max = arr[0];
        // int min = arr[0];

        // for (int i = 1; i < arr.length; i++) {
        //     if (arr[i] > max) {
        //         max = arr[i];
        //     }
        //     if (arr[i] < min) {
        //         min = arr[i];
        //     }
        // }

        // System.out.println("Max: " + max);
        // System.out.println("Min: " + min);

        // Optimal Approach
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int minFreq = Integer.MAX_VALUE;
        int maxFreq = Integer.MIN_VALUE;
        int min = -1;
        int max = -1;
        // for(int key: map.keySet()){
        //     int freq = map.get(key);
        //     if(freq < minFreq){
        //         minFreq = freq;
        //         min = key;
        //     }
        //     if(freq > maxFreq){
        //         maxFreq = freq;
        //         max = key;
        //     }
        // }
        // System.out.println("Max: " + max);
        // System.out.println("Min: " + min);

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int key = entry.getKey();
            int freq = entry.getValue();
            if(freq < minFreq){
                minFreq = freq;
                min = key;
            }
            if(freq > maxFreq){
                maxFreq = freq;
                max = key;
            }
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);


    }
}
