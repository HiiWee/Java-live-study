package structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArrayStackTest {

    private ArrayStack arrayStack = new ArrayStack();

    @AfterEach
    void afterEach() {
        arrayStack = new ArrayStack();
    }

    @Test
    @DisplayName("ArrayStack: push Test")
    void push() {
        // given
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);

        // when
        System.out.println(arrayStack);
        int size = arrayStack.size();

        // then
        Assertions.assertEquals(size, 6);
    }

    @Test
    @DisplayName("ArrayStack: basic pop test")
    void basicPop() {
        // given
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);

        // when
        int dataA = arrayStack.pop();
        int dataB = arrayStack.pop();
        int size = arrayStack.size();

        // then
        Assertions.assertAll("basic pop",
                () -> Assertions.assertEquals(dataA, 6),
                () -> Assertions.assertEquals(dataB, 5),
                () -> Assertions.assertEquals(size, 4)
        );
    }

    @Test
    @DisplayName("ArrayStack: fail pop test")
    void wrongPop() {
        // given
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        // when
        int dataA = arrayStack.pop();
        int dataB = arrayStack.pop();
        int dataC = arrayStack.pop();
        int dataD = arrayStack.pop();

        // then
        Assertions.assertAll("wrong pop",
                () -> Assertions.assertEquals(dataA, 3),
                () -> Assertions.assertEquals(dataB, 2),
                () -> Assertions.assertEquals(dataC, 1),
                // 더이상 데이터가 없는데 pop
                () -> Assertions.assertEquals(dataD, -1)
        );
    }
}
