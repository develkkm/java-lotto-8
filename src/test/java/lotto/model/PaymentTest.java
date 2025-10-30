package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentTest {

    @Nested
    @DisplayName("정상 케이스")
    class ValidCase {

        @Test
        @DisplayName("정상 입력 시 올바른 로또 개수를 계산한다.")
        void shouldCreateAndGetLottoAmountCorrectly() {
            // given
            int money = 5000;

            // when
            Payment payment = Payment.from(money);

            // then
            int expected = money / Payment.LOTTO_PRICE;
            assertThat(payment.getLottoAmount()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("예외 케이스")
    class ExceptionCase {

        @ParameterizedTest
        @ValueSource(ints = {-1000, 0, 500})
        @DisplayName("1000원 미만일 경우 예외가 발생한다.")
        void shouldThrowExceptionWhenMoneyIsLessThanLottoPrice(int money) {
            // expect
            assertThatThrownBy(() -> Payment.from(money))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이상이여야");
        }

        @Test
        @DisplayName("로또 가격 단위로 나누어 떨어지지 않으면 예외 발생")
        void shouldThrowExceptionWhenMoneyNotDivisibleByLottoPrice() {
            // given
            int money = 1500;

            // expect
            assertThatThrownBy(() -> Payment.from(money))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("나누어져야");
        }
    }
}
