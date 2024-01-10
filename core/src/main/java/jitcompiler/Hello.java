package jitcompiler;

import java.util.stream.IntStream;

/** -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:LogFile=/Users/ihoseok/develop
 * https://www.youtube.com/watch?v=CQi3SS2YspY
 * JVM warm up / if(kakao)2022 샘플 코드 실습
 */
public class Hello {

    public int findMax(int[] arr) {
        int max = 0;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        IntStream.rangeClosed(1, Integer.parseInt(args[0]))
                .forEach(i -> {
                    try {
                        hello.findMax(arr);
                        if (i % 100 == 0) {
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                    }
                });
    }
}
