package chapter13.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ex13_5 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("읽어 들일 파일명을 입력하세요.");
        String s = sc.next();
        FileReader fr = new FileReader(s);
        int i;
        while ((i = fr.read()) != -1) {
            System.out.println((char) i);
        }

        fr.close();
    }
}
