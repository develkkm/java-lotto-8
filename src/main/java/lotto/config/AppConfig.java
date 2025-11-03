package lotto.config;

import lotto.controller.LottoController;
import lotto.model.LottoMatchMachine;
import lotto.model.LottoShop;
import lotto.model.generator.LottoNumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public LottoController lottoController() {
        return new LottoController(inputView(), new OutputView(), lottoService());
    }

    private LottoService lottoService() {
        return new LottoService(lottoShop(), new LottoMatchMachine());
    }

    private LottoShop lottoShop() {
        return LottoShop.using(new LottoNumberGenerator());
    }

    private InputView inputView() {
        return new InputView(new InputConverter());
    }
}
