package lotto.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import lotto.model.enums.LottoRank;

public class LottoResult {

    private final Map<LottoRank, Integer> counts;
    private final long totalPayout;

    private LottoResult(Map<LottoRank, Integer> counts, long totalPayout) {
        this.counts = new EnumMap<>(counts);
        this.totalPayout = totalPayout;
    }

    public static LottoResult of(Map<LottoRank, Integer> counts, long totalPayout) {
        return new LottoResult(counts, totalPayout);
    }

    public Map<LottoRank, Integer> counts() {
        return Collections.unmodifiableMap(counts);
    }

    public long totalPayout() {
        return totalPayout;
    }
}
