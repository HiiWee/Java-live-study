package structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {

    private ArrayQueue arrayQueue = new ArrayQueue();

    @AfterEach
    void afterEach() {
        arrayQueue = new ArrayQueue();
    }

    @Test
    @DisplayName("ArrayQueue: offer test")
    void offer() {
        // given
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);
        arrayQueue.offer(4);
        arrayQueue.offer(5);
        arrayQueue.offer(6);
        arrayQueue.offer(7);
        arrayQueue.offer(8);

        // when
        int size = arrayQueue.size();
        System.out.println(arrayQueue);

        //then
        assertEquals(size, 8);
    }

    @Test
    @DisplayName("ArrayQueue: basic poll test")
    void basicPoll() {
        // given
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);
        arrayQueue.offer(4);
        arrayQueue.offer(5);
        arrayQueue.offer(6);

        // when
        int dataA = arrayQueue.poll();
        int dataB = arrayQueue.poll();
        int dataC = arrayQueue.poll();
        int size = arrayQueue.size();
        System.out.println(arrayQueue);

        // then
        assertAll("basic poll",
                () -> assertEquals(dataA, 1),
                () -> assertEquals(dataB, 2),
                () -> assertEquals(dataC, 3),
                () -> assertEquals(size, 3)
        );
    }

    @Test
    @DisplayName("ArrayQueue: wrong poll test")
    void wrongPoll() {
        // given
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);

        // when
        int dataA = arrayQueue.poll();
        int dataB = arrayQueue.poll();
        int dataC = arrayQueue.poll();
        int dataD = arrayQueue.poll();
        System.out.println(arrayQueue);

        // then
        assertAll("basic poll",
                () -> assertEquals(dataA, 1),
                () -> assertEquals(dataB, 2),
                () -> assertEquals(dataC, 3),
                () -> assertEquals(dataD, -1)
        );
    }
}
