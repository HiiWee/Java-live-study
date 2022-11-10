import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    // Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.
    @Test
    @DisplayName("Set.size()를 이용한 Set의 크기 확인 테스트")
    void size_set_자료형의_사이즈_확인() {
        // given
        // when
        int numbersSize = numbers.size();

        // then
        assertThat(numbersSize).isEqualTo(3);
    }
}
