package lotto.model;

import java.util.EnumMap;
import java.util.Map;
import lotto.model.enums.LottoRank;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningLotto;
import lotto.model.payment.Payment;

public class LottoMatchMachine {
    private static final int BONUS_APPLICABLE_MATCH_COUNT = 5;

    public LottoResult match(WinningLotto winningLotto, Lottos lottos) {
        EnumMap<LottoRank, Integer> counts = initializeCounts();

        lottos.getLottos().stream()
                .map(lotto -> determineLottoRank(winningLotto, lotto))
                .forEach(rank -> incrementRankCount(counts, rank));

        long totalPayout = calculateTotalPayout(counts);
        return LottoResult.of(counts, totalPayout);
    }

    private EnumMap<LottoRank, Integer> initializeCounts() {
        EnumMap<LottoRank, Integer> counts = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) counts.put(rank, 0);
        return counts;
    }

    private LottoRank determineLottoRank(WinningLotto winningLotto, Lotto lotto) {
        int matchCount = countMatchedNumbers(winningLotto, lotto);
        boolean effectiveBonus = isEffectiveBonus(winningLotto, lotto, matchCount);
        return LottoRank.fromMatchCount(matchCount, effectiveBonus);
    }

    private int countMatchedNumbers(WinningLotto winning, Lotto lotto) {
        return lotto.getNumbers().stream()
                .filter(winning.getWinningNumbers()::contains)
                .mapToInt(n -> 1)
                .sum();
    }

    private boolean isEffectiveBonus(WinningLotto winning, Lotto lotto, int matchCount) {
        return matchCount == BONUS_APPLICABLE_MATCH_COUNT && lotto.getNumbers().contains(winning.getBonusNumber());
    }

    private void incrementRankCount(Map<LottoRank, Integer> counts, LottoRank rank) {
        counts.merge(rank, 1, Integer::sum);
    }

    private long calculateTotalPayout(Map<LottoRank, Integer> counts) {
        return counts.entrySet().stream()
                .mapToLong(e -> e.getKey().totalPrize(e.getValue()))
                .sum();
    }

    public double yield(Payment payment, LottoResult result) {
        return (double) result.totalPayout() / payment.getMoney();
    }
}
