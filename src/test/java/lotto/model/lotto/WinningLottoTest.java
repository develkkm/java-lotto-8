package lotto.model.lotto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private static List<Integer> winningNumbers;

    @BeforeAll
    static void setUp(){
        winningNumbers = List.of(1, 2, 3, 4, 5, 6);
    }

    @Nested
    @DisplayName("정상 케이스")
    class ValidCase {

        @Test
        @DisplayName("유효한 당첨 번호와 보너스 번호로 생성하면 값이 그대로 보관된다")
        void shouldCreateWhenValid() {
            int bonus = 7;

            WinningLotto winning = WinningLotto.of(winningNumbers, bonus);

            assertThat(winning.getWinningNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(winning.getBonusNumber()).isEqualTo(7);
        }
    }

    @Nested
    @DisplayName("예외 케이스 - 보너스 번호 검증")
    class BonusValidation {

        @ParameterizedTest
        @ValueSource(ints = {0, 46, -1, 100})
        @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생해야 한다")
        void shouldThrowWhenBonusOutOfRange(int bonus) {
            assertThatThrownBy(() -> WinningLotto.of(winningNumbers, bonus))
                    .isInstanceOf(IllegalArgumentException.class);
        }


        @Test
        @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생해야 한다")
        void shouldThrowWhenBonusDuplicatedWithWinningNumbers() {
            int duplicated = 6;

            assertThatThrownBy(() -> WinningLotto.of(winningNumbers, duplicated))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
