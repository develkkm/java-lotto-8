package lotto.model;

import java.util.List;
import java.util.stream.IntStream;
import lotto.model.generator.LottoNumberGenerator;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.payment.Payment;

public class LottoShop {
    private final LottoNumberGenerator generator;

    private LottoShop(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public static LottoShop using(LottoNumberGenerator generator) {
        return new LottoShop(generator);
    }

    public Lottos sell(Payment payment) {
        int lottoAmount = payment.getLottoAmount();
        List<Lotto> issuedLottos = generateLottos(lottoAmount);
        return Lottos.from(issuedLottos);
    }

    private List<Lotto> generateLottos(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> Lotto.of(generator.generate(
                        Lotto.LOTTO_MIN_NUMBER,
                        Lotto.LOTTO_MAX_NUMBER,
                        Lotto.LOTTO_NUMBER_SIZE
                )))
                .toList();
    }
}
