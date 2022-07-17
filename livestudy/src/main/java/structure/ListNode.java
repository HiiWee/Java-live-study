package structure;

/**
 * LinkedList에 대해 공부하세요.
 * 정수를 저장하는 ListNode 클래스를 구현하세요.
 * ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
 * ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
 * boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.
 */
public class ListNode {
    private int element;
    private ListNode next;
    private boolean headCheck;

    public ListNode() {
        this.headCheck = true;
    }

    public ListNode(int element) {
        this.element = element;
    }

    public int getElement() {
        return element;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode node) {
        next = node;
    }

    public boolean isHead() {
        if (headCheck) {
            return true;
        }
        return false;
    }

    public int size() {
        if (!isHead()) {
            return -1;
        }
        int count = 0;
        ListNode countNode = this;
        while (countNode.getNext() != null) {
            count++;
            countNode = countNode.getNext();
        }
        return count;
    }

    // head만 리스트에 특정 명령 수행 가능
    public boolean isValidation(int position) {
        if (!isHead()) {
            return false;
        }
        if (size() < position) {
            return false;
        }
        if (position < 0) {
            return false;
        }

        return true;

    }

    public ListNode add(ListNode head, ListNode nodeToAdd, int position) {
        if (!isValidation(position)) {
            return null;
        }
        for (int i = 0; i < position; i++) {
            head = head.getNext();
        }
        nodeToAdd.setNext(head.getNext());
        head.setNext(nodeToAdd);

        return nodeToAdd;
    }

    public ListNode remove(ListNode head, int positionToRemove) {
        if (!isValidation(positionToRemove) || size() == positionToRemove) {
            return null;
        }

        for (int i = 0; i < positionToRemove; i++) {
            head = head.getNext();
        }
        // 지워야 할 노드
        ListNode nodeToRemove = head.getNext();
        head.setNext(nodeToRemove.getNext());
        return nodeToRemove;
    }

    public boolean contains(ListNode head, ListNode nodeTocheck) {
        if (size() == 0) {
            return false;
        }
        if (!isHead()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            head = head.getNext();
            if (head == nodeTocheck) {
                return true;
            }
        }
        return false;
    }

}
