package chapter16.week5_1;

public class Example05 {

    // 직접 스택 구현해보자 ㅋㅋ
    static class GStack<T> {
        private Object[] stack;
        private int index = 0;

        public GStack() {
            stack = new Object[10];
        }

        public void push(T item) {
            if (index >= 10) {
                System.out.println("Stack is full");
                return;
            }
            stack[index++] = item;
        }

        public T pop() {
            if (index == 0) {
                System.out.println("Stack is empty");
            }
            return (T) stack[--index];
        }
    }

    public static void main(String[] args) {

        GStack<String> stringGStack = new GStack<>();
        stringGStack.push("Seoul");
        stringGStack.push("Busan");
        stringGStack.push("LA");

        for (int i = 0; i < 3; i++) {
            System.out.println(stringGStack.pop());
        }

        GStack<Integer> integerGStack = new GStack<>();
        integerGStack.push(1);
        integerGStack.push(3);
        integerGStack.push(5);

        for (int i = 0; i < 3; i++) {
            System.out.println(integerGStack.pop());
        }
    }
}
