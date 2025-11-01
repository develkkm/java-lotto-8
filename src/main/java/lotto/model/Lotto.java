package lotto.model;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto of(List<Integer> numbers){
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoNumbersRange(numbers);
        validateLottoDuplicateNumber(numbers);
    }

    private void validateLottoDuplicateNumber(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoNumbersRange(List<Integer> numbers) {
        boolean hasOutOfRange = numbers.stream()
                .anyMatch(n -> n < LOTTO_MIN_NUMBER || n > LOTTO_MAX_NUMBER);

        if (hasOutOfRange) {
            throw new IllegalArgumentException(
                    "로또 번호는 " + LOTTO_MIN_NUMBER + " 이상 " + LOTTO_MAX_NUMBER + " 이하의 숫자여야 합니다."
            );
        }
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는" + LOTTO_NUMBER_SIZE + "개여야 합니다.");
        }
    }

    public List<Integer> getNumbers(){
        return numbers;
    }
}
