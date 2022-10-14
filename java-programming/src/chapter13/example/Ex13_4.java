package chapter13.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ex13_4 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String source = "aaaaaa\nbbbbbbbb\ncccccccc";
        System.out.println("파일명을 입력하세요 : ");
        String s = sc.next();
        FileWriter fw = new FileWriter(s);
        fw.write(source);
        fw.close();
        System.out.println(s + "파일이 생성되었습니다.");
    }
}
