package structure;

public class ArrayQueue {
    private static final int SIZE = 10;
    private int[] array;
    private int head;
    private int tail;

    public ArrayQueue() {
        array = new int[SIZE];
    }


    public void offer(int data) {
        if (isPossibleSize()) {
            array[tail++] = data;
        } else {
            increaseArray();
            array[tail++] = data;
        }
    }

    public int poll() {
        if (tail == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = array[head];
        // 위치 조정
        for (int i = 1; i < tail; i++) {
            array[i - 1] = array[i];
        }
        tail--;
        return data;
    }

    public int size() {
        return tail;
    }

    protected void increaseArray() {
        int[] temp = array;

        // 기존 사이즈에서 5 증가
        array = new int[temp.length + SIZE];
        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }
    }

    protected boolean isPossibleSize() {
        if (array.length <= tail) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < tail; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

}
