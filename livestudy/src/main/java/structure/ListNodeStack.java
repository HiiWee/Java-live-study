package structure;

/**
 * ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
 * void push(int data)를 구현하세요.
 * int pop()을 구현하세요.
 */
public class ListNodeStack {
    private final ListNode head;
    private int index;

    public ListNodeStack() {
        head = new ListNode();
    }
    // 데이터를 넣고 인덱스 증가
    public void push(int data) {
        ListNode node = new ListNode(data);
        head.add(head, node, index++);
    }

    // 인덱스 감소 후 데이터 pop
    public int pop() {
        if (index == 0) {
            System.out.println("Stack is Empty");
            return -1;
        }
        ListNode node = head.remove(head, --index);
        return node.getElement();
    }

    public int size() {
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode move = head;

        sb.append("{ ");
        for (int i = 0; i < index; i++) {
            move = move.getNext();
            sb.append(move.getElement()).append(" ");
        }
        sb.append("}");

        return sb.toString();
    }


}
