package lotto.model.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Nested
    @DisplayName("정상 케이스")
    class ValidCase {

        @Test
        @DisplayName("동일한 순서의 로또 목록을 보관한다")
        void shouldKeepSameOrder() {
            Lotto a = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
            Lotto b = Lotto.of(List.of(7, 8, 9, 10, 11, 12));
            Lotto c = Lotto.of(List.of(13, 14, 15, 16, 17, 18));

            Lottos lottos = Lottos.from(List.of(a, b, c));

            assertThat(lottos.getLottos())
                    .containsExactly(a, b, c);
        }

        @Test
        @DisplayName("getSize는 보관 중인 로또 개수를 반환한다")
        void shouldReturnCorrectSize() {
            Lotto a = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
            Lotto b = Lotto.of(List.of(7, 8, 9, 10, 11, 12));

            Lottos lottos = Lottos.from(List.of(a, b));

            assertThat(lottos.getSize()).isEqualTo(2);
        }
    }
}
