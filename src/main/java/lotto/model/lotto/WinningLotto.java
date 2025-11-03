package lotto.model.lotto;

import static lotto.model.lotto.Lotto.LOTTO_MAX_NUMBER;
import static lotto.model.lotto.Lotto.LOTTO_MIN_NUMBER;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    private WinningLotto(List<Integer> numbers, int bonusNumber){
        this.winningLotto = Lotto.of(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(List<Integer> numbers, int bonusNumber){
        return new WinningLotto(numbers,bonusNumber);
    }

    private void validateBonusNumber(int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateDuplicateNumber(bonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if(bonusNumber < LOTTO_MIN_NUMBER || LOTTO_MAX_NUMBER  < bonusNumber){
            throw new IllegalArgumentException("보너스 번호는 " + LOTTO_MIN_NUMBER + " 이상 " + LOTTO_MAX_NUMBER + " 이하 여야합니다.");
        }
    }

    private void validateDuplicateNumber(int bonusNumber){
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getWinningNumbers(){
        return winningLotto.getNumbers();
    }

    public int getBonusNumber(){
        return bonusNumber;
    }
}
