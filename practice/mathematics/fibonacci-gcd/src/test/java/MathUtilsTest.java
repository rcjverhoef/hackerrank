import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilsTest {

    private long recursiveFibonacci(long i, long m) {
        if (i < 1) return 0;
        if (i < 3) return 1;
        long fn = 1; //F(2)
        long fnPlus1 = 2; //F(3)
        for (int j = 3; j < i ; j++) {
            long tmp = (fn + fnPlus1) % m;
            fn = fnPlus1;
            fnPlus1 = tmp;
        }
        return fnPlus1;
    }

    @Provide
    private Arbitrary<Integer> fibonacciSeriesInputs() {
        return Arbitraries.integers().between(0, 10000);
    }

    @Property
    public void testFibonacci(@ForAll("fibonacciSeriesInputs") int i) {
        long mod = 1000000000 + 7;
        assertThat(recursiveFibonacci(i, mod)).isEqualTo(MathUtils.fibonacciSeriesAt(i, mod));
    }

    private static Stream<Arguments> gcd() {
        return Stream.of(
                Arguments.of(13, 13, 13),
                Arguments.of(37, 600, 1),
                Arguments.of(20, 100, 20),
                Arguments.of(624129, 2061517, 18913)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void gcd(long a, long b, long expected) {
        assertThat(expected).isEqualTo(MathUtils.gcd(a, b));
    }

    private long gcd(long a, long b) {
        return MathUtils.gcd(a, b);
    }

    @Property
    public void gcdCommutative(@ForAll long a,
                               @ForAll long b) {
        assertThat(gcd(a, b)).isEqualTo(gcd(b, a));
    }

    @Property
    public void gcdAssociative(@ForAll long a,
                               @ForAll long b,
                               @ForAll long c) {
        assertThat(gcd(a, gcd(b, c))).isEqualTo(gcd(gcd(a, b), c));
    }

    @Property
    public void gcdStableUnderAddingMultiplication(@ForAll int a,
                                                   @ForAll int b,
                                                   @ForAll int m) {
        assertThat(gcd(a + (long) m * b, b)).isEqualTo(gcd(a, b));
    }

    @Property
    public void multipleCanBeMovedOut(@ForAll int a,
                                      @ForAll int b,
                                      @ForAll @Positive int m) {
        assertThat(gcd((long) a * m, (long) b * m)).isEqualTo((long) m * gcd(a, b));
    }
}