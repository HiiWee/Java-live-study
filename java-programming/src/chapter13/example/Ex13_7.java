package chapter13.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex13_7 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("입력 파일명을 입력하세요 : ");
        String s = sc.next();
        FileInputStream fis = new FileInputStream(s);
        int i;
        while ((i = fis.read()) != -1) {
            System.out.println(i);
        }
        fis.close();
        System.out.println(s + " 파일로부터 바이트를 읽어 화면에 출력했습니다.");
    }
}
