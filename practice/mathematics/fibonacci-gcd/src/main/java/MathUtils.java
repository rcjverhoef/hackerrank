import java.util.BitSet;

public class MathUtils {
    private MathUtils() {
    }


    /**
     * Utility method to determine <i><u>G</u>reatest <u>C</u>ommon <u>D</u>ivisor</i> of two numbers.<br>
     * This implementation uses recursive approach of <i>Euclid's Algorithm</i>.
     *
     * @param a a value
     * @param b b value
     * @return the greatest common divisor of {@code a} and {@code b}
     * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest Common Divisor</a>
     * @see <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">Euclidean Algorithm</a>
     */
    public static long gcd(long a, long b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    /**
     * Utility method to determine <i><u>G</u>reatest <u>C</u>ommon <u>D</u>ivisor</i> of two list of numbers.<br>
     * <br>
     * This uses the gcd property that <i><gcd(a, b, c) = gcd(gcd(a, b), c)</i> for a, b & c not equal to zero
     *
     * @param a array of longs
     * @return the greatest common divisor of the longs in the list
     * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest Common Divisor</a>
     */
    public static long gcd(long... a) {
        if (a == null || a.length < 1) {
            return -1;
        }
        long result = a[0];
        for (int i = 1; i < a.length; i++) {
            result = gcd(result, a[i]);
        }
        return result;
    }

    /**
     * Method that calculates the fibonacci number at point <tt>i</tt> in the series.
     *
     * This method does not use any memoization. It instead uses a fast doubling algorithm based on the
     * identities:<br/>
     * <ul>
     *     <li>F(2n) = F(n) * [2*F(n+1) – F(n)]</li>
     *     <li>F(2n + 1) = F(n)^2 + F(n+1)^2</li>
     * </ul>
     * Instead of using recursion to calculate <tt>F(i)</tt>, this method uses exponentiation by squaring
     * to iteratively calculate the result.
     *
     * @param i the number in the fibonacci series desired
     * @return the number at location <tt>i</tt> in the series
     * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number">Fibonacci_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Cassini_and_Catalan_identities">Cassini and Catalan identities</a>
     * @see <a href="https://en.wikipedia.org/wiki/Modular_exponentiation">Modular exponentiation</a>
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation_by_squaring">Exponentiation by squaring</a>
     *
     */
    public static long fibonacciSeriesAt(long i, long mod) {
        if (i < 1) return 0;
        if (i == 1 || i == 2) return 1;

        long fn = 1; // starts at F(1)
        long fnPlus1 = 1; //starts at F(2)
        BitSet bitSet = BitSet.valueOf(new long[]{i});
        for (int index = bitSet.length() - 2; index > -1; index--) {
            long x = fn, y = fnPlus1;
            fn = x * ((((2* y) % mod) - x) % mod) % mod;
            if (fn < 0) {
                fn += mod;
            }
            fnPlus1 = (((y * y) % mod) + ((x * x) % mod)) % mod;
            if (bitSet.get(index)) {
                long tmp = fn;
                fn = fnPlus1;
                fnPlus1 = (fn + tmp) % mod;
            }
        }
        return fn;
    }
}