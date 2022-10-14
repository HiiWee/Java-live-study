package chapter13.week02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Example04 {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<String> vector = new Vector<>();
        Scanner fsc = new Scanner(new FileInputStream("words.txt"));
        while (fsc.hasNext()) {
            vector.add(fsc.nextLine());
        }
        fsc.close();

        // 사용자 입력 받기
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("단어>>");
            String str = sc.nextLine();

            if (str.equals("그만")) {
                System.out.println("종료합니다");
                return;
            }

            int count = 0;
            for (String word : vector) {
                boolean flag = true;
                if (word.length() < str.length()) {
                    continue;
                }
                String temp = word.substring(0, str.length());
                if (!temp.equals(str)) {
                    flag = false;
                }
                if (flag) {
                    count++;
                    System.out.println(word);
                }
            }
            if (count == 0) {
                System.out.println("발견할 수 없음");
            }
        }
    }
}
