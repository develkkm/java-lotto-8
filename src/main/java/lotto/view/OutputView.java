package lotto.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.model.lotto.Lottos;
import lotto.model.enums.LottoRank;

public class OutputView {
    private static final String LOTTO_AMOUNT_MESSAGE = "개를 구매했습니다.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String NUMBER_PREFIX = "[";
    private static final String NUMBER_SUFFIX = "]";
    private static final String RESULT_HEADER_MESSAGE = "당첨 통계\n---";
    private static final String RANK_INFO_FORMAT_MESSAGE = "%d개 일치%s (%,d원) - %d개";
    private static final String BONUS_MATCH_MESSAGE = ", 보너스 볼 일치";
    private static final String YIELD_FORMAT_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printLottosAmount(int amount) {
        printBlankLine();
        System.out.println(amount + LOTTO_AMOUNT_MESSAGE);
    }

    public void printPurchasedLottos(Lottos lottos) {
        lottos.getLottos().stream()
                .map(lotto -> lotto.getNumbers().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(NUMBER_DELIMITER, NUMBER_PREFIX, NUMBER_SUFFIX)))
                .forEach(System.out::println);
        printBlankLine();
    }

    public void printResultHeader() {
        printBlankLine();
        System.out.println(RESULT_HEADER_MESSAGE);
    }

    public void printRankCounts(Map<LottoRank, Integer> counts) {
        counts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .filter(e -> e.getKey() != LottoRank.NONE)
                .map(this::formatRankInfo)
                .forEach(System.out::println);
    }

    private String formatRankInfo(Entry<LottoRank, Integer> entry) {
        LottoRank rank = entry.getKey();
        int count = entry.getValue();
        String bonusMessage = getBonusMessage(rank);

        return String.format(RANK_INFO_FORMAT_MESSAGE,
                rank.getMatchCount(),
                bonusMessage,
                rank.getPrize(),
                count);
    }

    private static void printBlankLine() {
        System.out.println();
    }

    private static String getBonusMessage(LottoRank rank) {
        if (rank.isMatchBonus()) {
            return BONUS_MATCH_MESSAGE;
        }
        return "";
    }

    public void printYieldPercent(double yield) {
        System.out.printf(YIELD_FORMAT_MESSAGE, yield * 100);
    }

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
