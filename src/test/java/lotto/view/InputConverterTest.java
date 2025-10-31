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

        @Test
        @DisplayName("양의 정수 문자열을 입력하면 int로 변환해야 한다")
        void shouldConvertToIntWhenInputIsPositiveInteger() {
            assertThat(converter.convertPayment("1")).isEqualTo(1);
            assertThat(converter.convertPayment("12345")).isEqualTo(12345);
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
            assertThatThrownBy(() -> converter.convertPayment(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력 형식이 잘못 됐습니다.");
        }

        @Test
        @DisplayName("int 범위를 초과하면 예외를 던져야 한다")
        void shouldThrowExceptionWhenInputExceedsIntRange() {
            String overMaxInt = String.valueOf((long) Integer.MAX_VALUE + 1);
            assertThatThrownBy(() -> converter.convertPayment(overMaxInt))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("허용 범위를 넘어갔습니다.");
        }
    }
}
