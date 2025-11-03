package lotto.service;

import lotto.model.*;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningLotto;
import lotto.model.payment.Payment;

public class LottoService {
    private final LottoShop lottoShop;
    private final LottoMatchMachine lottoMatchMachine;

    public LottoService(LottoShop lottoShop, LottoMatchMachine lottoMatchMachine) {
        this.lottoShop = lottoShop;
        this.lottoMatchMachine = lottoMatchMachine;
    }

    public Lottos buyLotto(Payment payment) {
        return lottoShop.sell(payment);
    }

    public LottoResult matchLotto(WinningLotto winningLotto, Lottos lottos) {
        return lottoMatchMachine.match(winningLotto, lottos);
    }

    public double calculateYield(Payment payment, LottoResult lottoResult) {
        return lottoMatchMachine.yield(payment, lottoResult);
    }
}
