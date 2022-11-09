package chapter13.week01;

import java.util.Scanner;

public class Example01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("나뉨수를 입력하시오: ");
            int num1 = sc.nextInt();
            System.out.print("나눗수를 입력하시오: ");
            int num2 = sc.nextInt();
            int result = 0;
            try {
                result = num1 / num2;
            } catch (ArithmeticException e) {
                System.out.println("0으로 나눌 수 없습니다! 다시 입력하세요");
                continue;
            }

            System.out.println(num1 + "를 " + num2 + "로 나누면 몫은 " + result + "입니다.");
            break;
        }
    }
}
