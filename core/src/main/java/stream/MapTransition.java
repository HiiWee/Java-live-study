package stream;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MapTransition {
    static class File {
        String name;

        public File(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        /**
         * map의 사용
         */
        Stream<File> fileStream = Stream.of(new File("Ex1.java"),
                new File("Ex1"),
                new File("Ex1.bak"),
                new File("Ex2.java"),
                new File("Ex1.txt"));

        Stream<String> fileNameStream = fileStream.map(File::getName);
        fileNameStream.forEach(System.out::println);

        System.out.println();
        // File의 스트림에서 파일의 확장자만을 뽑은 다음 중복을 제거해 출력
        fileStream = Stream.of(new File("Ex1.java"),
                new File("Ex1"),
                new File("Ex1.bak"),
                new File("Ex2.java"),
                new File("Ex1.txt"));

        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1)
                .peek(s -> System.out.println("fileName = " + s))
                .map(s -> s.substring(s.indexOf('.') + 1))
                .peek(s -> System.out.println("extension = " + s))
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        // IntStream -> Stream<String> 변환
        IntStream intStream = new Random().ints(1, 46);
        Stream<String> lottoStream = intStream.distinct().limit(6).sorted().mapToObj(i -> i + ",");
        lottoStream.forEach(System.out::println);
        System.out.println();


        /**
         * CharSequence에 정의된 chars()는 String이나 StringBuffer에 저장된 문자들을 IntStream으로 다룰 수 있게함
         */
        IntStream charStream = "12345".chars();
        int charSum = charStream.map(ch -> ch - '0').sum();
        System.out.println(charSum);
        System.out.println();


        /**
         * flatMap() - Stream<T[]> -> Stream<T>로 변환
         */
        Stream<String[]> strArrStream = Stream.of(
                new String[]{"abc", "def", "ghi"},
                new String[]{"ABC", "GHI", "JKLMN"}
        );

        // flatMap()을 사용하지 않고 Stream<T[]> -> Stream<T>로 변환
        Stream<Stream<String>> strStrStream = strArrStream.map(Arrays::stream); // 스트림의 스트림이 됨 -> 우리가 원하는 상황이 아니다.


        strArrStream = Stream.of(
                new String[]{"abc", "def", "ghi"},
                new String[]{"ABC", "GHI", "JKLMN"}
        );
        // flatMap()을 이용해 배열에서 값을 꺼내 하나의 스트림에 담기
        Stream<String> strStream = strArrStream.flatMap(Arrays::stream);

        // 여러 문장을 요소로 하는 스트림의 문장들을 split()으로 나누어 요소가 단어인 스트림을 만들기
        String[] lineArr = {"Believe or not it is true", "Do or do not There is no try"};

        // flatMap 미사용
        Stream<String> lineStream = Arrays.stream(lineArr);
        Stream<Stream<String>> streamStream = lineStream.map(line -> Stream.of(line.split(" ")));

        // flatMap 사용
        lineStream = Arrays.stream(lineArr);
        Stream<String> stringStream = lineStream.flatMap(line -> Stream.of(line.split(" ")));
        // stringStream을 소문자 변환 후, 중복 단어 제거 이후 정렬하여 출력
        stringStream.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        // 스트림의 스트림을 하나의 스트림으로 합칠 때도 flatMap() 사용됨
        Stream<String> stream1 = Stream.of("abc", "def", "jklmn");
        Stream<String> stream2 = Stream.of("ABC", "GHI", "JKLMN");
        Stream<Stream<String>> streamInStream = Stream.of(stream1, stream2);

        // flatMap() 사용해 2개의 스트림을 합침
        Stream<String> stream = streamInStream.map(s -> s.toArray(String[]::new))
                .flatMap(Arrays::stream);

    }
}
