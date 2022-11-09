package chapter13.week02;

import java.io.*;

public class Example03 {
    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("tmp2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int count = 0;
        String str;
        char c = 'a';
        while ((str = br.readLine()) != null) {
            for (int i = 0; i < str.length(); i++) {
                if (c == str.charAt(i)) {
                    count++;
                }
            }
        }
        System.out.println("a의 개수 : " + count);
    }
}
