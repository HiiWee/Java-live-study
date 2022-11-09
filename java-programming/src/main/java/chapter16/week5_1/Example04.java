package chapter16.week5_1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Example04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("1번째 날짜 입력(입력 형태 : 2000-03-22) :");
        String startDate = sc.next();
        System.out.print("2번째 날짜 입력(입력 형태 : 2000-03-22) :");
        String endDate = sc.next();

        StringTokenizer st = new StringTokenizer(startDate, "-");
        int startYear = Integer.parseInt(st.nextToken());
        int startMonth = Integer.parseInt(st.nextToken());
        int startDay = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(endDate, "-");
        int endYear = Integer.parseInt(st.nextToken());
        int endMonth = Integer.parseInt(st.nextToken());
        int endDay = Integer.parseInt(st.nextToken());

        LocalDate start = LocalDate.of(startYear, startMonth, startDay);
        LocalDate end = LocalDate.of(endYear, endMonth, endDay);

        long until = start.until(end, ChronoUnit.DAYS);
        long year = until / 365;
        until %= 365;
        long month = until / 30;
        until %= 30;
        long day = until;
        System.out.println("날짜의 차이는(년월일) " + year + "년" + month + "월" + day + "일");
    }
}
