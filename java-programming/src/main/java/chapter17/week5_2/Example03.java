package chapter17.week5_2;

import java.util.*;

public class Example03 {

    public static void main(String[] args) {
        Map<String, Double> map = new TreeMap<>();
        Scanner sc = new Scanner(System.in);

        read(map, sc);
        StringBuilder result = new StringBuilder();
        select(map, sc, result);

        System.out.println("장학생 명단 : " + result);
    }

    private static void select(Map<String, Double> map, Scanner sc, StringBuilder result) {
        System.out.print("장학생 선발 학점 기준 입력>> ");
        Double policy = Double.parseDouble(sc.nextLine());
        Set<Map.Entry<String, Double>> entries = map.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            if (entry.getValue() >= policy) {
                result.append(entry.getKey()).append(" ");
            }
        }
    }

    private static void read(Map<String, Double> map, Scanner sc) {
        for (int i = 0; i < 5; i++) {
            System.out.print("이름과 학점>> ");
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String name = st.nextToken();
            double grade = Double.parseDouble(st.nextToken());
            map.put(name, grade);
        }
    }
}
