import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    private static Stream<Arguments> superPowerOf2() {
        return Stream.of(
                Arguments.of(2, 7, 2),
                Arguments.of(3363, 4951, 256),
                Arguments.of(290, 443, 16)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void superPowerOf2(int a, int b, int expected) {
        assertThat(expected).isEqualTo(Solution.superPowerOf2(a, b));
    }

    @Property
    public void superPowerOf2IsEven(@ForAll @Positive int a) {
        assertThat(0).isEqualTo(Solution.superPowerOf2(a, 2));
    }
}