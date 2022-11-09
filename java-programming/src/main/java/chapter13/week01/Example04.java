package chapter13.week01;

public class Example04 {
    public static void main(String[] args) {
        String[] str = {"23", "12", "3.141592"};

        for (int i = 0; i < str.length; i++) {
            try {
                System.out.println("문자로 변환된 값은 " + Integer.parseInt(str[i]));
            } catch (NumberFormatException e) {
                System.out.println(str[i] + "는 정수로 변환할 수 없습니다.");
            }
        }
    }
}
