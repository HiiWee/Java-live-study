import junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        Assertions.assertEquals(2, calculator.add(1, 1));
    }
}
