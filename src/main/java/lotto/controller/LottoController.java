package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.model.LottoResult;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningLotto;
import lotto.model.payment.Payment;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Payment payment = readPayment();
        Lottos lottos = purchaseLotto(payment);
        WinningLotto winningLotto = createWinningLotto();

        LottoResult result = lottoService.matchLotto(winningLotto, lottos);
        double yield = lottoService.calculateYield(payment, result);

        printResult(result, yield);
    }

    private Lottos purchaseLotto(Payment payment) {
        Lottos lottos = lottoService.buyLotto(payment);
        outputView.printLottosAmount(lottos.getSize());
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }

    private void printResult(LottoResult result, double yield) {
        outputView.printResultHeader();
        outputView.printRankCounts(result.counts());
        outputView.printYieldPercent(yield);
    }

    private Payment readPayment() {
        return retryUntil(() -> Payment.from(inputView.readPayment()));
    }

    private List<Integer> readWinningNumbers() {
        return retryUntil(inputView::readWinningNumbers);
    }

    private int readBonusNumber() {
        return retryUntil(inputView::readBonusNumber);
    }

    private WinningLotto createWinningLotto() {
        return retryUntil(() -> {
            List<Integer> winningNumbers = readWinningNumbers();
            int bonusNumber = readBonusNumber();
            return WinningLotto.of(winningNumbers, bonusNumber);
        });
    }

    private <T> T retryUntil(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
