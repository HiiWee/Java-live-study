import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringTest {

    @Test
    @DisplayName("split() 메소드 이용시 지정한 delimiter 기준 잘 분리되는지 테스트")
    void stringSplit_separate_success() {
        // given
        String multiNumber = "1,2";
        String singleNumber = "1";

        // when
        String[] splitMultiNumber = multiNumber.split(",");
        String[] splitSingleNumber = singleNumber.split(",");

        // then
        assertAll(() -> {
            assertThat(splitMultiNumber).contains("1");
            assertThat(splitMultiNumber).contains("2");
            assertThat(splitMultiNumber).containsExactly("1", "2");
            assertThat(splitSingleNumber).contains("1");
            assertThat(splitSingleNumber).containsExactly("1");
        });
    }
}
