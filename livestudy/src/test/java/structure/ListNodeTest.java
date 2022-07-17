package structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListNodeTest {

    private static ListNode linkedList = new ListNode();
    private static ListNode head = linkedList;

    @AfterEach
    void afterEach() {
        linkedList = new ListNode();
        head = linkedList;
    }

    @Test
    @DisplayName("LinkedList: Basic Add Test")
    void basicAdd() {
        // given
        linkedList.add(head, new ListNode(1), 0);
        linkedList.add(head, new ListNode(2), 1);
        linkedList.add(head, new ListNode(3), 2);

        //when
        ListNode move = head;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!move.isHead()) {
                sb.append(move.getElement());
            }
            move = move.getNext();
            if (move == null) {
                break;
            }
        }

        // then
        Assertions.assertEquals(sb.toString(), "123");
    }

    @Test
    @DisplayName("LinkedList: Wrong Add Test")
    void wrongAdd() {
        // given
        ListNode nodeToAdd = new ListNode(1);

        // when
        ListNode resultA = linkedList.add(head, nodeToAdd, 1);
        ListNode resultB = linkedList.add(head, nodeToAdd, -1);
        ListNode resultC = nodeToAdd.add(head, nodeToAdd, 0);

        // then
        Assertions.assertNull(resultA);
        Assertions.assertNull(resultB);
        Assertions.assertNull(resultC);
    }

    @Test
    @DisplayName("LinkedList: Basic Remove Test")
    void basicRemove() {
        // given
        linkedList.add(head, new ListNode(1), 0);
        linkedList.add(head, new ListNode(2), 1);
        linkedList.add(head, new ListNode(3), 2);

        //when
        ListNode result = linkedList.remove(head, 2);

        ListNode move = head;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!move.isHead()) {
                sb.append(move.getElement());
            }
            move = move.getNext();
            if (move == null) {
                break;
            }
        }

        // then
        Assertions.assertEquals(result.getElement(), 3);
        Assertions.assertEquals(sb.toString(), "12");
    }

    @Test
    @DisplayName("LinkedList: fail Remove Test")
    void wrongRemove() {
        // given
        ListNode nodeToAdd = new ListNode(1);
        linkedList.add(head, nodeToAdd, 0);
        linkedList.add(head, new ListNode(2), 1);
        linkedList.add(head, new ListNode(3), 2);


        // when
        ListNode resultA = linkedList.remove(head, 3);// 범위 초과
        ListNode resultB = linkedList.remove(head, -1);// 잘못된 인덱스
        ListNode resultC = nodeToAdd.remove(head, 0);// head가 아닌 노드는 삭제 금지

        // then
        Assertions.assertNull(resultA);
        Assertions.assertNull(resultB);
        Assertions.assertNull(resultC);
    }

    @Test
    @DisplayName("LinkedList: Basic Contains Node Test")
    void basicContains() {
        // given
        linkedList.add(head, new ListNode(1), 0);
        linkedList.add(head, new ListNode(2), 1);
        ListNode nodeToFind = linkedList.add(head, new ListNode(3), 2);

        // when
        boolean containsCheck = linkedList.contains(head, nodeToFind);

        // then
        Assertions.assertTrue(containsCheck);
    }

    @Test
    @DisplayName("LinkedList: fail Contains Node Test")
    void wrongContains() {
        // given
        ListNode nodeNotInList = new ListNode(10);
        linkedList.add(head, new ListNode(1), 0);
        linkedList.add(head, new ListNode(2), 1);
        linkedList.add(head, new ListNode(3), 2);
        // when
        boolean checkA = linkedList.contains(head, nodeNotInList);
        boolean checkB = nodeNotInList.contains(head, nodeNotInList);

        // then
        Assertions.assertFalse(checkA);
        Assertions.assertFalse(checkB);
    }
}
