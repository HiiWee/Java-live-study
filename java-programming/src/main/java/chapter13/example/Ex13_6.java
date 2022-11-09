package chapter13.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex13_6 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("출력 파일명을 입력하세요 : ");
        String s = sc.next();
        FileOutputStream fos = new FileOutputStream(s);
        for (int i = 1; i <= 260; i++) {
            fos.write(i);
        }
        fos.close();
        System.out.println(s + " 파일명으로 바이트 파일을 생성하였습니다.");
    }
}
