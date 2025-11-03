package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    private static final String INPUT_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    private final InputConverter inputConverter;

    public InputView(InputConverter inputConverter){
        this.inputConverter = inputConverter;
    }

    public int readPayment(){
        return inputConverter.parseValidatedNumber(readLine(INPUT_PRICE_MESSAGE));
    }

    public List<Integer> readWinningNumbers(){
        return inputConverter.parseValidatedNumbers(readLine(INPUT_WINNING_NUMBERS_MESSAGE));
    }

    public int readBonusNumber(){
        System.out.println();
        String bonusNumberLine = readLine(INPUT_BONUS_NUMBER_MESSAGE);
        return inputConverter.parseValidatedNumber(bonusNumberLine);
    }

    private static String readLine(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
