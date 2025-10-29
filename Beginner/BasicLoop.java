package Beginner;

import java.util.*;

public class BasicLoop {

    //1. Print table of a numbers from 1 to 10 using a for loop
    public static void forLoop(int x){
        System.out.println("This is for Loop:");
        for(int i=1; i<=10; i++){
            System.out.println(i*x);
        }
    }

    //2. Print all even numbers between 1 and 100
    public static void evenNumbers(){       
        System.out.println("Even Numbers are:");
        for(int i=1; i<=100; i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }

    // 3. Print the sum of the first N natural numbers
    public static void sumOfNNaturalNumbers(int n){
        System.out.println("The sum of first "+ n + " Natural Numbers are:");
        int sum = 0;
        for(int i=1; i<=n; i++){
            sum += i;
        }
        System.out.println(sum);
    }
    // 4. Find the factorial of a given number
    public static void factorial(int n){
        System.out.println("The factorial of "+ n + "is:");
        int fact = 1;
        for(int i=1; i<=n; i++){
            fact *= i;
        }
        System.out.println(fact);
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        forLoop(n);
        evenNumbers();
        sumOfNNaturalNumbers(n);
        factorial(n);
    }
}
