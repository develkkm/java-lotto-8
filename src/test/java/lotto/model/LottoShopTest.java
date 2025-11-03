package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.model.generator.LottoNumberGenerator;
import lotto.model.lotto.Lottos;
import lotto.model.payment.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoShopTest {

    static class FixedGenerator extends LottoNumberGenerator {
        @Override
        public List<Integer> generate(int min, int max, int size) {
            List<Integer> numbers = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                numbers.add(min + i);
            }
            return numbers;
        }
    }

    @Test
    @DisplayName("Payment 금액에 비례해 지정된 개수만큼 로또를 발행한다")
    void shouldSellLottosByPaymentAmount() {
        // given
        LottoShop shop = LottoShop.using(new FixedGenerator());
        Payment payment = Payment.from(5000);

        // when
        Lottos lottos = shop.sell(payment);

        // then
        assertThat(lottos.getSize()).isEqualTo(5);
        assertThat(lottos.getLottos().getFirst().getNumbers()).isEqualTo(List.of(1,2,3,4,5,6));
    }
}
