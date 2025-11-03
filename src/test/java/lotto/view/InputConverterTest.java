package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputConverterTest {
    private final InputConverter converter = new InputConverter();

    @Nested
    @DisplayName("정상 케이스")
    class ValidCase {

        @ParameterizedTest
        @ValueSource(strings = {"1", "12345", "100", "1000"})
        @DisplayName("양의 정수 문자열을 입력하면 int로 변환해야 한다")
        void shouldConvertToIntWhenInputIsPositiveInteger(String input) {
            int result = converter.parseValidatedNumber(input);

            assertThat(result).isEqualTo(Integer.parseInt(input));
        }

    }

    @Nested
    @DisplayName("예외 케이스")
    class ExceptionCase {

        @ParameterizedTest
        @ValueSource(strings = {
                "0", "-1", "001", "12a3", "", " ", "1 2", "1,000", "+1", "3.14"
        })
        @DisplayName("정수가 아닌 문자열을 입력하면 예외를 던져야 한다")
        void shouldThrowExceptionWhenInputIsNotPositiveInteger(String input) {
            assertThatThrownBy(() -> converter.parseValidatedNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("int 범위를 초과하면 예외를 던져야 한다")
        void shouldThrowExceptionWhenInputExceedsIntRange() {
            String overMaxInt = String.valueOf((long) Integer.MAX_VALUE + 1);
            assertThatThrownBy(() -> converter.parseValidatedNumbers(overMaxInt))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
