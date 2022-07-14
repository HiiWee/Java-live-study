package junit5;

import junit5.custom.annotation.Fast;
import junit5.custom.annotation.FastTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CustomAnnotationTest {


    private Calculator calculator = new Calculator();

    // Tag를 커스텀 어노테이션으로 만듦
    @Fast
    @Test
    @DisplayName("Fast Test")
    void myFastTest() {
        Assertions.assertEquals(2, calculator.add(1, 1));
    }

    // Test + 커스텀 어노테이션(@Tag)
    @FastTest
    @DisplayName("FastTest Test")
    void theFastTest() {
        Assertions.assertEquals(2, calculator.add(1, 1));
    }
}
