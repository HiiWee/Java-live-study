package structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListNodeQueueTest {

    private ListNodeQueue queue = new ListNodeQueue();

    @AfterEach
    void afterEach() {
        queue = new ListNodeQueue();
    }

    @Test
    @DisplayName("ListNodeQueue: offer test")
    void offer() {
        // given
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        // when
        int size = queue.size();
        System.out.println(queue);

        // then
        assertEquals(size, 6);
    }

    @Test
    @DisplayName("ListNodeQueue: basic poll test")
    void basicPoll() {
        // given
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        // when
        int dataA = queue.poll();
        int dataB = queue.poll();
        int dataC = queue.poll();
        int size = queue.size();
        System.out.println(queue);

        // then
        assertAll("basic poll",
                () -> assertEquals(dataA, 1),
                () -> assertEquals(dataB, 2),
                () -> assertEquals(dataC, 3),
                () -> assertEquals(size, 3)
        );
    }

    @Test
    @DisplayName("ListNodeQueue: fail poll test")
    void wrongPoll() {
        // given
        queue.offer(1);
        queue.offer(2);


        // when
        int dataA = queue.poll();
        int dataB = queue.poll();
        int dataC = queue.poll();
        int size = queue.size();
        System.out.println(queue);

        // then
        assertAll("basic poll",
                () -> assertEquals(dataA, 1),
                () -> assertEquals(dataB, 2),
                () -> assertEquals(dataC, -1),
                () -> assertEquals(size, 0)
        );
    }
}
