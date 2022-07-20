package binarytree;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {

    private BinaryTree tree = new BinaryTree();

    /**
     * [트리의 구조]
     *         n1
     *        /    \
     *      n2      n3
     *     /  \     /
     *    n4  n5   n6
     *   /    / \
     *  n7   n8  n9
     */
    @BeforeEach
    void beforeEach() {
        Node n9 = tree.makeNode(null, 9, null);
        Node n8 = tree.makeNode(null, 8, null);
        Node n7 = tree.makeNode(null, 7, null);
        Node n6 = tree.makeNode(null, 6, null);
        Node n5 = tree.makeNode(n8, 5, n9);
        Node n4 = tree.makeNode(n7, 4, null);
        Node n3 = tree.makeNode(n6, 3, null);
        Node n2 = tree.makeNode(n4, 2, n5);
        Node n1 = tree.makeNode(n2, 1, n3);
        tree.setRoot(n1);
    }

    @AfterEach
    void afterEach() {
        tree.sb = new StringBuilder();
    }

    @Test
    @DisplayName("BFS test")
    void BFS() {
        // given
        String result = "123456789";

        // when
        tree.BFS(tree.getRoot());
        String answer = tree.sb.toString();

        // then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("inorder DFS Test")
    void recurDFS() {
        // given
        String result = "742859163";

        // when
        tree.recurDFS(tree.getRoot());
        String answer = tree.sb.toString();
        System.out.println(answer);

        // then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("[번외] stack DFS test: 전위 연산(DLR)")
    void stackDFS() {
        // given
        String result = "124758936";

        // when
        tree.stackDFS(tree.getRoot());
        String answer = tree.sb.toString();

        // then
        assertEquals(result, answer);
    }
}
