package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorList {
    public static void main(String[] args) {
        // Step 1: Create a List
        List<Integer> list = new ArrayList<>();

        // Step 2: Add elements
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        // Step 3: Get iterator after adding elements
        Iterator<Integer> it = list.iterator();

        // Step 4: Iterate through the list
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Iterator List");

    }
}
