package chapter16.week5_1;

import java.util.StringTokenizer;

public class Example01 {
    public static void main(String[] args) {
        String line = "public static void main(String[] args)";
        StringTokenizer st = new StringTokenizer(line);
        int count = st.countTokens();

        for (int i = 0; i < count; i++) {
            System.out.println(st.nextToken());
        }
        System.out.println("총 토큰의 개수는 " + count + "개 입니다.");
    }
}
