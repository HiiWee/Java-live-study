package stream;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 스트림의 중간 연산
 */
public class IntermediateOperation {

    public static void main(String[] args) {

        /**
         * 스트림 자르기 - skip(), limit()
         * 3개의 요소 스킵 후 5개로 제한해 출력
         */
        IntStream intStream1 = IntStream.rangeClosed(1, 10);
        intStream1.skip(3).limit(5).forEach(System.out::print);


        System.out.println();
        /**
         * 스트림의 요소 걸러내기 - filter(), distinct()
         */
        IntStream intStream2 = IntStream.of(1, 2, 2, 3, 3, 3, 4, 5, 5, 6);
        intStream2.distinct().forEach(System.out::print);

        System.out.println();
        IntStream intStream3 = IntStream.rangeClosed(1, 10);
        intStream3.filter(i -> i % 2 == 0).forEach(System.out::print);

        System.out.println();
        intStream3 = IntStream.rangeClosed(1, 10);
        intStream3.filter(i -> i % 2 != 0).filter(i -> i % 3 == 0).forEach(System.out::print);

        System.out.println();
        /**
         * 정렬 - sorted()
         */
        Stream<String> strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted().forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(Comparator.naturalOrder()).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(String::compareTo).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(Comparator.reverseOrder()).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(Comparator.<String>naturalOrder().reversed()).forEach(System.out::print);
        System.out.println();

        // 대소문자 구분 안함
        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(String.CASE_INSENSITIVE_ORDER).forEach(System.out::print);
        System.out.println();

        // 대소문자 구분 안함, 역순
        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(String.CASE_INSENSITIVE_ORDER.reversed()).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        // 길이 순 정렬
        strStream.sorted(Comparator.comparing(String::length)).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        // no 오토 박싱
        strStream.sorted(Comparator.comparing(String::length)).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(Comparator.comparingInt(String::length)).forEach(System.out::print);
        System.out.println();

        strStream = Stream.of("dd", "aaa", "CC", "cc", "b");
        strStream.sorted(Comparator.comparing((String s) -> s.length()).reversed()).forEach(System.out::print);
        System.out.println();


    }
}
