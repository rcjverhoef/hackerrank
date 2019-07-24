
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {
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
        long gcd = MathUtils.gcd(a, b);
        assertEquals(expected, gcd);
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

    @ParameterizedTest
    @ValueSource(longs = {2, 3, 5, 7, 11, 971, 73363, 104729})
    public void isPrime(long x) {
        boolean isPrime = MathUtils.isPrime(x);
        assertTrue(isPrime);
    }

    @ParameterizedTest
    @ValueSource(longs = {-2, -1, 0, 1, 4, 6, 9, 25, 481, 737, 1071, 104730})
    public void isNotPrime(long x) {
        boolean isPrime = MathUtils.isPrime(x);
        assertFalse(isPrime);
    }

    @Property
    public void modularInverse(@ForAll @Positive long a,
                               @ForAll("primes") long m) {
        if (a % m != 0) {
            assertThat(MathUtils.defaultModInverse(a, m))
                    .isEqualTo(MathUtils.euclideanModInverse(a, m))
                    .isEqualTo(MathUtils.fermatModInverse(a, m));
        }

    }

    @Provide
    private Arbitrary<Long> primes() {
        return Arbitraries.integers()
                .filter(MathUtils::isPrime)
                .map(Long::valueOf);
    }
}