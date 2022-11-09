package chapter13.week02;


import java.io.*;
import java.util.Scanner;

public class Example01 {
    public static void main(String[] args) throws IOException {

        File f1 = new File(args[0]);
        File f2 = new File(args[1]);

        if (f2.exists()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("타켓 파일이 이미 존재합니다.");
            System.out.println("덮어쓰기를 수행하시겠습니까? (y/n)");
            String s = sc.next();
            if (!s.equals("y")) {
                System.out.println("종료합니다.");
                return;
            }
        }
        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos = new FileOutputStream(f2);

        System.out.println("소스파일은 : tmp2.txt");
        System.out.println("타겟파일은 : tmp22.txt");

        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);

        System.out.println("복사가 종료되었습니다.");
    }
}
