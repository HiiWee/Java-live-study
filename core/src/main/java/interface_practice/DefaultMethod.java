package interface_practice;


interface MyInterface {
    // private method
    private void method2() {
        System.out.println("This is private method");
    }

    //  default method에서 private method 호출
    default void method() {
        method2();
        System.out.println("This is default Method");
    }

    // private static method
    private static String getType() {
        return "person";
    }

    // private static method는 정적 메소드에서만 사용 가능
    static String getName() {
        return getType() + " name";
    }
}

interface ChildMyInterface extends MyInterface {

    @Override
    void method();


}

class DefaultMethod implements MyInterface {
}

class OverriddenDefaultMethod implements ChildMyInterface {
    @Override
    public void method() {
        System.out.println("This is Overridden default method");
    }
}

class Test {
    public static void main(String[] args) {
        DefaultMethod defaultMethod = new DefaultMethod();
        defaultMethod.method();
        System.out.println(MyInterface.getName());

        OverriddenDefaultMethod overriddenDefaultMethod = new OverriddenDefaultMethod();
        overriddenDefaultMethod.method();
    }
}
