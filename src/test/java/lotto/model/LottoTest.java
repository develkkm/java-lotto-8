package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Nested
    @DisplayName("정상 케이스")
    class ValidCase {

        @Test
        @DisplayName("유효한 6개 숫자를 입력하면 같은 숫자 리스트가 반환되어야 한다")
        void shouldReturnSameNumbersWhenValid() {
            Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));

            assertThat(lotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        }

    }

    @Nested
    @DisplayName("예외 케이스")
    class ExceptionCase {

        static Stream<List<Integer>> invalidSizeCases() {
            return Stream.of(
                    List.of(),
                    List.of(1, 2, 3, 4, 5, 6, 7)
            );
        }

        static Stream<List<Integer>> outOfRangeCases() {
            return Stream.of(
                    List.of(0, 2, 3, 4, 5, 6),
                    List.of(1, 2, 3, 4, 5, 46)
            );
        }

        @ParameterizedTest
        @MethodSource("invalidSizeCases")
        @DisplayName("로또 번호의 개수가 올바르지 아니면 예외가 발생해야 한다")
        void shouldThrowWhenSizeIsNotCorrect(List<Integer> input) {
            assertThatThrownBy(() -> Lotto.of(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @MethodSource("outOfRangeCases")
        @DisplayName("로또 번호가 범위를 벗어나면 예외가 발생해야 한다")
        void shouldThrowWhenOutOfRange(List<Integer> input) {
            assertThatThrownBy(() -> Lotto.of(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생해야 한다")
        void shouldThrowWhenContainsDuplicateNumbers() {
            assertThatThrownBy(() -> Lotto.of(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
