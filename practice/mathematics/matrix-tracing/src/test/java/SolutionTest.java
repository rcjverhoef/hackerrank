import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final Solution solution = new Solution();

    private static Stream<Arguments> numberOfArrangements() {
        return Stream.of(
                Arguments.of(2, 3, 3),
                Arguments.of(1469, 1490, 524961341),
                Arguments.of(1267, 1357, 723982994),
                Arguments.of(1095, 1415, 482162240),
                Arguments.of(1368, 1285, 873677923),
                Arguments.of(1363, 1231, 395143026),
                Arguments.of(1386, 1114, 3189050)
        );
    }


    @ParameterizedTest
    @MethodSource
    public void numberOfArrangements(int n, int m, long expected) {
        long arrangements = solution.calculateNumberOfArrangements(n, m);
        assertEquals(expected, arrangements);
    }
}