package chapter13.week02;

import java.io.File;
import java.util.Scanner;

public class Example05 {
    public static void main(String[] args) {
        String currentLocation = "/Users/ihoseok";
        Scanner sc = new Scanner(System.in);
        System.out.println("************ 파일 탐색기입니다. ************");
        while (true) {
            System.out.println(currentLocation);

            File totalFile = new File(currentLocation);
            String[] lists = totalFile.list();

            for (String list : lists) {
                File file = new File(currentLocation + "/" + list);
                if (file.isDirectory()) {
                    System.out.print("dir\t\t\t\t\t");
                } else {
                    System.out.print("file\t\t\t\t\t");
                }
                System.out.print(file.length() + "바이트\t\t\t\t\t");
                System.out.println(file.getName());
            }

            System.out.print(">>");
            String next = sc.next();
            sc.nextLine();

            if (next.equals("exit")) {
                System.out.println("종료합니다.");
                return;
            }

            if (next.equals("...")) {
                currentLocation = totalFile.getParent();
            } else {
                File temp = new File(currentLocation + "/" + next);
                if (temp.isDirectory()) {
                    currentLocation = temp.getAbsolutePath();
                }
            }
        }

    }
}
