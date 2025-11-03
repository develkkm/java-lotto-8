package lotto.model.enums;

import java.util.Arrays;

public enum LottoRank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;

    LottoRank(int matchCount, boolean matchBonus, long prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public long getPrize() {
        return prize;
    }

    public long totalPrize(int count) {
        return prize * count;
    }

    public static LottoRank fromMatchCount(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.matchBonus == matchBonus)
                .findFirst()
                .orElse(NONE);
    }
}
