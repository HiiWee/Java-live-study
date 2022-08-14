package stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        String[] strArr = {"aaa", "ddd", "ccc"};
        List<String> strList = Arrays.asList(strArr);

        Stream<String> strStream2 = Arrays.stream(strArr);
        Stream<String> strStream1 = strList.stream();

        strStream1.sorted().forEach(System.out::println);
        strStream2.sorted().forEach(System.out::println);

        // 아래 코드는 원래 방식, 스트림에 비해 좀 더 표현해야 할 것이 많다.
        Arrays.sort(strArr);
        Collections.sort(strList);

        for (String str : strArr) {
            System.out.println(str);
        }
        for (String str : strList) {
            System.out.println(str);
        }

        // 스트림의 특징
        // 스트림은 데이터 소스를 변경하지 않음
//        List<String> sortedList = strStream2.sorted().collect(Collectors.toList());

        // 스트림은 일회용
//        strStream1.sorted().forEach(System.out::println);
//        int numOfstr = (int) strStream1.count();
//        System.out.println(numOfstr);

        // 스트림은 일회용이므로 재 생성해서 사용
        strStream2 = Arrays.stream(strArr);
        strStream1 = strList.stream();
    }
}
