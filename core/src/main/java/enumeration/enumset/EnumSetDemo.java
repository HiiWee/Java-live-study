package enumeration.enumset;

import java.util.EnumSet;

enum Gfg {CODE, LEARN, CONTRIBUTE, QUIZ, MCQ};

public class EnumSetDemo {

    public static void main(String[] args) {

        EnumSet<Gfg> set1, set2, set3, set4, set5;

        set1 = EnumSet.of(Gfg.QUIZ, Gfg.CONTRIBUTE, Gfg.LEARN, Gfg.CODE);
        set2 = EnumSet.complementOf(set1);
        set3 = EnumSet.allOf(Gfg.class);
        set4 = EnumSet.range(Gfg.CODE, Gfg.CONTRIBUTE);
        set5 = EnumSet.copyOf(set4);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);
        System.out.println("Set 4: " + set4);
        System.out.println("Set 5: " + set5);
    }
}
