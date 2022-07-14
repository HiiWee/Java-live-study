package junit5;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;


import org.junit.jupiter.api.Test;

class AssertionsDemoTest {

    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2),
                "하나의 옵션인 추가 메시지는 마지막 파라미터에 넣음");
        assertTrue('a' < 'b', () -> "Assertion messages는 지연로딩과 같이 동작 하여 -- "
                + "복잡한 메시지를 불필요하게 구성하지 않도록 한다.");
    }

    @Test
    void groupedAssertions() {
        // 그룹 assertion에서 모든 assertions가 실행되고 모든 실패가 같이 보고됩니다.
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // 코드 블록내에서 특정 assertion이 실패하면 동일 블록에서 후속 코드는 생략합니다.
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    // assertNotNull()이 검증 됐을경우에만 실행됨
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    // 그룹화된 assertion, 이전의 이름 assertion과 독립적으로 처리된다.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // assertNotNull(lastName);이 검증됐을 경우에만 실행됨
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // 2분내로 테스트가 종료되므로 성공
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // 테스트가 성공하면 String 타입의 결과 반환
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        // 시간초과 테스트가 성공했는지 검증
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // AssertionsDemoTest 클래스의 메소드 참조를 호출하고 객체를 반환받는다.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemoTest::greeting);
        // 반환받은 객체(메시지) 검증
        assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // 10밀세컨 뒤에 타임아웃이 발생하지만 람다 내부에서 실행하는 본문은 100밀세컨을 기다리므로 테스트 실패
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // 위와 동일한 이유로(시간 초과) 테스트 실패
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    // 테스트서 사용되는 메소드
    private static String greeting() {
        return "Hello, World!";
    }

}