package structure;

/**
 * int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
 * void push(int data)를 구현하세요.
 * int pop()을 구현하세요.
 */

public class ArrayStack {
    private int[] array;
    private int index;

    public ArrayStack() {
        array = new int[5];
    }

    // 데이터를 넣고 인덱스 증가
    public void push(int data) {
        if (isPossibleSize()) {
            // 배열에 공간이 있으므로 그냥 push
            array[index++] = data;
        } else {
            // 사이즈를 늘려주고, 배열 이전 후 push
            increaseArray();
            array[index++] = data;
        }
    }

    // 인덱스 감소 후 데이터 pop
    public int pop() {
        if (index == 0) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return array[--index];
    }

    public int size() {
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < index; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("}");

        return sb.toString();
    }

    protected void increaseArray() {
        int[] temp = array;

        // 기존 사이즈에서 5 증가
        array = new int[temp.length + 5];
        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }
    }

    protected boolean isPossibleSize() {
        if (array.length <= index) {
            return false;
        } else {
            return true;
        }
    }


}
