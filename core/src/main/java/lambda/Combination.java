package lambda;


import java.util.function.Predicate;
import java.util.function.Supplier;

public class Combination {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "def";

        Predicate<String> p = Predicate.isEqual(str1);
        boolean result = p.test(str2);

        System.out.println("result = " + result);

        Supplier<Combination> s = Combination::new;
        s.get();

    }
}
