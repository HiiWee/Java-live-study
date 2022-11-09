package chapter13.week01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Example05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = (int) (Math.random() * 100);
        System.out.println("수를 결정하였습니다. 맞추어 보세요");
        System.out.println("0-99");

        int count = 1;
        while (true) {
            System.out.print(count + ">>");
            try {
                int input = sc.nextInt();
                if (input < 0 || input > 99) {
                    System.out.println("범위를 벗어났어요");
                    count++;
                    continue;
                }

                if (num > input) {
                    System.out.println("더 크게");
                } else if (num < input) {
                    System.out.println("더 낮게");
                } else {
                    System.out.println("정답입니다!");
                    break;
                }
                count++;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("정수만 입력하셔야 합니다!!!");
            }
        }
    }
}
