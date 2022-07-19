package javaclass;

public class TestClass {
    private int instanceField;
    private static int classField;

    public class Name {
        public Name() {
            instanceField = 10;
            int instanceField = getInstanceField();
        }
    }
    // 생성자
    public TestClass() {
        System.out.println(instanceField);
        System.out.println(classField);
    }

    // 인스턴스 변수 초기화 블록
    {
        System.out.println("생성자 보다 먼저 실행됨");
        instanceField = 10;
    }

    // 클래스 변수 초기화 블록
    static {
        System.out.println("생성자 보다 먼저 실행됨");
        classField = 20;
    }

    // 메소드
    public int getInstanceField() {
        return instanceField;
    }

    // 메소드
    public int getClassField() {
        return classField;
    }

    // 메소드
    public static void main(String[] args) {
    }
}
