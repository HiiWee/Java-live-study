package chapter17.week5_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example01 {
    public static void main(String[] args) {
        String[] arr = {"Apple", "Banana", "Mango", "Pear", "Grape"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));

        System.out.println(list);
        System.out.println(list.indexOf("Mango"));
    }
}
