package Collection;

import java.util.ArrayList;
import java.util.List;

public class ArraList {
    public static void main(String[] args) {

        List<Integer> arrList = new ArrayList<>();

        arrList.add(10);
        arrList.add(20);
        arrList.add(30);
        arrList.add(40);
        arrList.add(50);

        System.out.println(arrList); // [10, 20, 30, 40, 50]
        System.out.println(arrList.size()); // 5
        System.out.println(arrList.get(2)); // 30
        arrList.remove(2); // remove element at index 2
        System.out.println(arrList); // [10, 20, 40, 50]
        System.out.println(arrList.contains(30)); // false
        arrList.set(2, 100);// set index 2 to 100
        System.out.println(arrList); // [10, 20, 100, 50]
        arrList.clear();// clear the list
        System.out.println(arrList);// []
        System.out.println(arrList.isEmpty());// true


    }
}
