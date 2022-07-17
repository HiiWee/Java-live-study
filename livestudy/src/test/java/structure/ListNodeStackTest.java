package structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListNodeStackTest {
    private ListNodeStack listNodeStack = new ListNodeStack();

    @AfterEach
    void afterEach() {
        listNodeStack = new ListNodeStack();
    }

    @Test
    @DisplayName("ListNodeStack: push Test")
    void push() {
        // given
        listNodeStack.push(1);
        listNodeStack.push(2);
        listNodeStack.push(3);

        // when
        int size = listNodeStack.size();
        System.out.println(listNodeStack);

        // then
        Assertions.assertEquals(size, 3);

    }

    @Test
    @DisplayName("ListNodeStack: basic pop test")
    void basicPop() {
        // given
        listNodeStack.push(1);
        listNodeStack.push(2);
        listNodeStack.push(3);

        // when
        int dataA = listNodeStack.pop();
        int dataB = listNodeStack.pop();
        int dataC = listNodeStack.pop();
        int size = listNodeStack.size();

        // then
        Assertions.assertAll("basic pop",
                () -> Assertions.assertEquals(dataA, 3),
                () -> Assertions.assertEquals(dataB, 2),
                () -> Assertions.assertEquals(dataC, 1),
                () -> Assertions.assertEquals(size, 0)
        );
    }

    @Test
    @DisplayName("ListNodeStack: wrong pop test")
    void wrongPop() {
        // given
        listNodeStack.push(1);
        listNodeStack.push(2);
        listNodeStack.push(3);

        // when: push한 데이터보다 pop한 데이터가 많다면
        int dataA = listNodeStack.pop();
        int dataB = listNodeStack.pop();
        int dataC = listNodeStack.pop();
        int dataD = listNodeStack.pop();

        // then
        Assertions.assertAll("wrong pop",
                () -> Assertions.assertEquals(dataA, 3),
                () -> Assertions.assertEquals(dataB, 2),
                () -> Assertions.assertEquals(dataC, 1),
                () -> Assertions.assertEquals(dataD, -1)
        );
    }
}
