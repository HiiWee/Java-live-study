package enumeration;


class Card1 {
    static final int CLOVER = 0;
    static final int HEART = 1;
    static final int DIAMOND = 2;
    static final int SPADE = 3;

    static final int TWO = 0;
    static final int THREE = 1;
    static final int FOUR = 2;
}

class Card2 {
    // 열거형의 정의 default로 0부터 순차적으로 값이 초기화된다.
    enum Kind {CLOVER, HEART, DIAMOND, SPADE}
    enum Value {TWO, THREE, FOUR}
}

public class Main {
    public static void main(String[] args) {

        // 타입체크가 되지 않음, true지만, 의미상 false여야 함
        if (Card1.CLOVER == Card1.TWO) {
            System.out.println("Card1.CLOVER == Card1.TWO");
        }

//        if (Card2.Kind.CLOVER == Card2.Value.TWO) {
//            System.out.println("Card2.Kind.CLOVER == Card2.Value.TWO");
//        }
    }
}
