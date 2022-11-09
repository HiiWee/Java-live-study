package chapter13.week01;

public class Example02 {
    public static void main(String[] args) {
        int[] intArray = new int[4];
        intArray[0] = 0;
        intArray[1] = 1;
        intArray[2] = 3;
        intArray[3] = 6;

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("intArray[" + i + "]=" + intArray[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("배열의 인덱스가 범위를 벗어났습니다.");
            }
        }
    }
}
