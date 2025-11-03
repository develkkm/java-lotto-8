package lotto.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @ParameterizedTest
    @CsvSource({
            "3, false, FIFTH",
            "4, false, FOURTH",
            "5, false, THIRD",
            "5, true, SECOND",
            "6, false, FIRST"
    })
    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 LottoRank를 반환한다")
    void shouldReturnCorrectRank(int matchCount, boolean bonus, LottoRank expected) {
        assertThat(LottoRank.fromMatchCount(matchCount, bonus)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, false",
            "2, false",
            "3, true",
            "4, true",
            "7, false",
            "-1, false"
    })
    @DisplayName("정의되지 않은 조합이면 NONE을 반환한다")
    void shouldReturnNoneWhenInvalid(int matchCount, boolean bonus) {
        assertThat(LottoRank.fromMatchCount(matchCount, bonus))
                .isEqualTo(LottoRank.NONE);
    }

    @ParameterizedTest
    @CsvSource({
            "FIFTH, 1, 5000",
            "FOURTH, 2, 100000",
            "THIRD, 3, 4500000",
            "SECOND, 1, 30000000",
            "FIRST, 1, 2000000000",
            "NONE, 10, 0"
    })
    @DisplayName("totalPrize는 prize * count 값을 반환한다")
    void shouldReturnTotalPrize(LottoRank rank, int count, long expected) {
        assertThat(rank.totalPrize(count)).isEqualTo(expected);
    }
}
