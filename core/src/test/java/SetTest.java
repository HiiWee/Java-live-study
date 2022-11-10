import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    // JUnit의 ParameterizedTest를 활용해 1, 2, 3에 대한 Set.contains() 메소드를 테스트하자
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("Set.contains() 메소드를 테스트")
    void contains_set_내부에_존재하는_데이터확인(int number) {
        // given
        // when
        // then
        assertTrue(numbers.contains(number));
    }

    // 1,2,3의 값은 contains 메소드 실행결과 true, 4, 5값은 false가 반환되는 테스트를 구성
    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    void contains_set_내부에_존재하거나_존재하지_않는_데이터확인(int input, boolean expected) {
        // given
        // when
        // then
        assertThat(numbers.contains(input)).isEqualTo(expected);
    }
}
