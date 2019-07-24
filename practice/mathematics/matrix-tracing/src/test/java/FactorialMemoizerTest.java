import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialMemoizerTest {
    private final FactorialMemoizer factorialMemoizer = new FactorialMemoizer(2000001, 1000000007L);

    private static Stream<Arguments> factorial() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 6),
                Arguments.of(4, 24),
                Arguments.of(5, 120),
                Arguments.of(10, 3628800),
                Arguments.of(15, 674358851),
                Arguments.of(200000, 107146451)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void factorial(int input, long expected) {
        long factorial = factorialMemoizer.compute(input);
        assertEquals(expected, factorial);
    }
}