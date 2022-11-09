package chapter16.week5_1;

import java.util.StringTokenizer;

public class Example02 {

    public static void main(String[] args) {
        int totalCount = 0;
        String line = "대한민국 서울+마포+공덕동 185번지)";
        // 1자적으로 +로 토큰을 분리
        StringTokenizer st = new StringTokenizer(line, "+");
        int count = st.countTokens();

        for (int i = 0; i < count; i++) {
            // 각 토큰에 대해서 다시 " " 공백으로 토큰을 분리
            StringTokenizer st2 = new StringTokenizer(st.nextToken());
            totalCount += st2.countTokens();
            while (st2.hasMoreTokens()) {
                System.out.println(st2.nextToken());
            }
        }

        System.out.println("총 토큰의 개수는 " + totalCount + "개 입니다.");
    }
}
