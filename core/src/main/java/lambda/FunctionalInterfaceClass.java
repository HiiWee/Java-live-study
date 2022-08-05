package lambda;

@FunctionalInterface
interface MyFunction {
    void myMethod();
}

@FunctionalInterface
interface MyFunction2 {
    public abstract int max(int a, int b);
}

public class FunctionalInterfaceClass {

    void aMethod(MyFunction f) {
        f.myMethod();
    }
    public static void main(String[] args) {
        // MyFunction2 예제
        MyFunction2 f = new MyFunction2() {
            @Override
            public int max(int a, int b) {
                return a > b ? a : b;
            }
        };

        int big = f.max(5, 3);

        // 람다식으로 변환
        MyFunction2 f2 = (int a, int b) -> a > b ? a : b;
        int big2 = f2.max(5, 3);

        System.out.println("big = " + big);
        System.out.println("big2 = " + big2);


        // MyFunction 예제
        MyFunction f3 = () -> System.out.println("myMethod()");
        f3.myMethod();
    }


}
