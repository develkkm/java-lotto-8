package lotto.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    public int convertPayment(String input) {
        validateNumberFormat(input);
        return parseToInt(input);
    }

    private void validateNumberFormat(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식이 잘못 됐습니다.");
        }
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용 범위를 넘어갔습니다.");
        }
    }
}

