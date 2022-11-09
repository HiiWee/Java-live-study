package chapter17.week5_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Example02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();

        System.out.println("나라 이름과 인구를 입력하세요. (예: Korea 5000");
        // 나라 입력 받아 저장하기
        while (true) {
            System.out.print("나라 이름, 인구 >> ");
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String country = st.nextToken();

            if (country.equals("그만")) {
                break;
            }
            int count = Integer.parseInt(st.nextToken());

            map.put(country, count);
        }

        // 인구 검색하기
        while (true) {

            System.out.print("인구 검색 >> ");
            String searchCountry = sc.nextLine();

            if (searchCountry.equals("그만")) {
                break;
            } else if (!map.containsKey(searchCountry)) {
                System.out.println("나라를 찾을 수 없습니다.");
                continue;
            }

            int count = map.get(searchCountry);
            System.out.println(searchCountry + "의 인구는 " + count);
        }
    }
}
