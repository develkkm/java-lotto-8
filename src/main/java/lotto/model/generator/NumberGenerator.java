package lotto.model.generator;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {

    List<Integer> generate(int minNumber, int maxNumber, int size);
}
