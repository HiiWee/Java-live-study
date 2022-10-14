package chapter13.example;

import java.io.File;

public class Ex13_2 {
    public static void main(String[] args) {
        String dir = "/Users/ihoseok";
        File f1 = new File(dir);

        if (f1.isDirectory()) {
            System.out.println("검색 디렉터리 " + dir);
            System.out.println("================================");
            String[] s = f1.list();
            for (int i = 0; i < s.length; i++) {
                File f = new File(dir + "/" + s[i]);
                if (f.isDirectory()) {
                    System.out.println(s[i] + " : 디렉터리");
                } else {
                    System.out.println(s[i] + " : 파일");
                }
            }
        } else {
            System.out.println("지정한 " + dir + " 는 디렉터리가 아님");
        }

    }
}
