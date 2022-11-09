package chapter13.week01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Example03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("정수 3개를 입력하세요");


        int sum = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print(i + ">>");
            try {
                int num = sc.nextInt();
                sum += num;
            } catch (InputMismatchException e) {
                // 버퍼 비우기
                sc.nextLine();
                System.out.println("정수가 아닙니다. 다시 입력하세요!");
                i--;
            }
        }
        System.out.println("합은 " + sum);
    }
}
