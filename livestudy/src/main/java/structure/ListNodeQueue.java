package structure;

public class ListNodeQueue {
    private ListNode head;
    private int index;

    public ListNodeQueue() {
        head = new ListNode();
    }

    public void offer(int data) {
        ListNode node = new ListNode(data);
        head.add(head, node, index++);
    }

    public int poll() {
        if (index == 0) {
            System.out.println("ListNodeQueue is empty");
            return -1;
        }
        ListNode node = head.remove(head, 0);
        index--;
        return node.getElement();
    }

    public int size() {
        return head.size();
    }

    @Override
    public String toString() {
        ListNode move = head;
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");
        for (int i = 0; i < index; i++) {
            move = move.getNext();
            sb.append(move.getElement()).append(" ");
        }
        sb.append("}");

        return sb.toString();
    }

}
