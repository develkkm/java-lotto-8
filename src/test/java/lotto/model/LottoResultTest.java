package lotto.model;

import lotto.model.enums.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private EnumMap<LottoRank, Integer> prepareCounts() {
        EnumMap<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        counts.put(LottoRank.FIFTH, 2);   // 3개 일치 2개
        counts.put(LottoRank.FOURTH, 1);  // 4개 일치 1개
        counts.put(LottoRank.NONE, 3);    // 꽝 3개
        return counts;
    }

    @Test
    @DisplayName("생성 후 counts와 totalPayout이 그대로 보관된다")
    void shouldKeepCountsAndTotalPayout() {
        EnumMap<LottoRank, Integer> counts = prepareCounts();
        long total = LottoRank.FIFTH.totalPrize(2)
                + LottoRank.FOURTH.totalPrize(1);

        LottoResult result = LottoResult.of(counts, total);

        assertThat(result.totalPayout()).isEqualTo(total);
        assertThat(result.counts())
                .containsEntry(LottoRank.FIFTH, 2)
                .containsEntry(LottoRank.FOURTH, 1)
                .containsEntry(LottoRank.NONE, 3)
                .hasSize(3);
    }
}
