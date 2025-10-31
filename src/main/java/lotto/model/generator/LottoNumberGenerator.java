package lotto.model.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator implements NumberGenerator{

    @Override
    public List<Integer> generate(int minNumber, int maxNumber, int size) {
        return Randoms.pickUniqueNumbersInRange(minNumber, maxNumber, size);
    }
}
