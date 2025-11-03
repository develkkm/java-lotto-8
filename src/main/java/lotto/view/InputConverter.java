package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public int parseValidatedNumber(String input) {
        validateNumericFormat(input);
        return parseIntSafely(input);
    }

    public List<Integer> parseValidatedNumbers(String input) {
        return Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(String::trim)
                .map(num -> {
                    validateNumericFormat(num);
                    return parseIntSafely(num);
                })
                .toList();
    }

    private void validateNumericFormat(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식이 잘못됐습니다.");
        }
    }

    private int parseIntSafely(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용 범위를 넘어갔습니다.");
        }
    }
}
