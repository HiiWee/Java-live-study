package chapter13.week02;

import java.io.*;
import java.util.Arrays;

public class Example02 {

    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 3, 4, 5};
        String[] lastNames = {"Kim", "Park", "Jung", "Lee"};

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp.txt"));

        // 파일에 객체 쓰고 스트림 닫기
        oos.writeObject(nums);
        oos.writeObject(lastNames);
        oos.close();

        // 파일에 쓴 객체 읽어서 캐스팅 후 저장 후 스트림 닫기
        int[] readNums = (int[]) ois.readObject();
        String[] readLastNames = (String[]) ois.readObject();
        ois.close();

        // 파일 출력
        Arrays.stream(readNums).forEach((int num) -> System.out.print(num + " "));
        System.out.println();
        Arrays.stream(readLastNames).forEach((String str) -> System.out.print(str + " "));
        System.out.println();
    }
}
