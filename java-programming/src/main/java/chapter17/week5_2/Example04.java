package chapter17.week5_2;

import java.util.Scanner;
import java.util.Vector;

public class Example04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Integer> v = new Vector<>();

        while (true) {
            System.out.print("강수량 입력 (0 입력시 종료)>> ");
            int precipitation = sc.nextInt();
            if (precipitation == 0) {
                break;
            }
            v.add(precipitation);
            printVector(v);
            System.out.println("현재 평균 " + getAverage(v));
        }
    }

    public static void printVector(Vector<Integer> vector) {
        vector.forEach((o) -> System.out.print(o + " "));
        System.out.println();
    }

    public static int getAverage(Vector<Integer> vector) {
        int sum = vector.stream().mapToInt(e -> e).sum();
        return sum / vector.size();
    }
}
