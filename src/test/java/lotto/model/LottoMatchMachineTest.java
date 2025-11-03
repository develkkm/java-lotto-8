package lotto.model;

import lotto.model.enums.LottoRank;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningLotto;
import lotto.model.payment.Payment;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatchMachineTest {

    private static LottoResult result;
    private static long totalPayout;
    private static Payment payment;
    private static double yield;

    @BeforeAll
    static void setUp() {
        LottoMatchMachine machine = new LottoMatchMachine();

        WinningLotto winning = WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottos lottos = Lottos.from(List.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6)),    // FIRST
                Lotto.of(List.of(1, 2, 3, 4, 5, 7)),    // SECOND (5개 + 보너스)
                Lotto.of(List.of(1, 2, 3, 4, 5, 8)),    // THIRD  (5개)
                Lotto.of(List.of(1, 2, 3, 4, 9, 10)),   // FOURTH
                Lotto.of(List.of(1, 2, 3, 11, 12, 13)), // FIFTH
                Lotto.of(List.of(1, 2, 14, 15, 16, 17)) // NONE
        ));

        result = machine.match(winning, lottos);
        totalPayout = result.totalPayout();

        payment = Payment.from(6_000);
        yield = machine.yield(payment, result);
    }

    @Test
    @DisplayName("등급별 개수 집계가 올바르다")
    void shouldCountRanksCorrectly() {
        Map<LottoRank, Integer> counts = result.counts();

        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(counts.get(LottoRank.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 당첨금이 올바르다")
    void shouldCalculateTotalPayoutCorrectly() {
        long expected =
                LottoRank.FIRST.getPrize() +
                        LottoRank.SECOND.getPrize() +
                        LottoRank.THIRD.getPrize() +
                        LottoRank.FOURTH.getPrize() +
                        LottoRank.FIFTH.getPrize(); // NONE은 0

        assertThat(totalPayout).isEqualTo(expected);
        assertThat(totalPayout).isEqualTo(2_031_555_000L);
    }

    @Test
    @DisplayName("수익률 = 총 당첨금 / 구매 금액")
    void shouldCalculateYieldAsTotalPayoutDividedByPayment() {
        double expected = (double) totalPayout / payment.getMoney();
        assertThat(yield).isEqualTo(expected);
    }
}
