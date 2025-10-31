package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    private final InputConverter inputConverter;

    public InputView(InputConverter inputConverter){
        this.inputConverter = inputConverter;
    }

    public int readPayment(){
        String priceLine = readLine(INPUT_PRICE_MESSAGE);
        return inputConverter.convertPayment(priceLine);
    }

    public String readWinningNumbers(){
        return readLine(INPUT_WINNING_NUMBERS_MESSAGE);
    }

    public String readBonusNumber(){
        return readLine(INPUT_BONUS_NUMBER_MESSAGE);
    }

    private static String readLine(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
