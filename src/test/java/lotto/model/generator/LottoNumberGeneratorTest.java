package lotto.model.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("지정된 범위 내에서 올바른 숫자들이 생성되어야 한다")
    void shouldGenerateNumbersWithinRange() {
        LottoNumberGenerator generator = new LottoNumberGenerator();

        int min = 1;
        int max = 45;
        int size = 6;

        List<Integer> numbers = generator.generate(min, max, size);

        assertTrue(numbers.stream().allMatch(n -> n >= min && n <= max));
    }
}
