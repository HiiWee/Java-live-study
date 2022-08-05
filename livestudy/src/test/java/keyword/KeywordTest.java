package keyword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordTest {

    @Test
    void finalTest() {
        TestClass testClass = new TestClass();

        assertAll("all variable equal test",
                () -> assertEquals(testClass.AGE, 25),
                () -> assertEquals(testClass.A, 10),
                () -> assertEquals(testClass.B, 20),
                () -> assertEquals(TestClass.C, 30)
        );
    }

    static class TestClass {
        public final int AGE = 25; // 선언과 동시에 초기화
        public final int A;
        public final int B;
        public static final int C;

        public TestClass() {
            this.A = 10;
        }

        {
            B = 20;
        }

        static {
            C = 30;
        }
    }
}
