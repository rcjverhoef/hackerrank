import java.math.BigInteger;

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
     * Utility method to see if a value x is a prime number or not.<br>
     * <br>
     * A natural number (1, 2, 3, 4, 5, 6, etc.) is called a prime number (or a prime) if it is greater than 1 and
     * cannot be written as a product of two natural numbers that are both smaller than it.
     *
     * @param x x value
     * @return <tt>true</tt> if x is a prime number and <tt>false</tt> otherwise
     * @see <a href="https://en.wikipedia.org/wiki/Prime_number">Prime number</a>
     */
    public static boolean isPrime(long x) {
        if (x < 2) return false;
        if (x % 2 == 0) return x == 2; //besides 2 itself, even numbers are multiples of 2 hence not prime
        for (int i = 3; i <= Math.sqrt(x); i += 2) { //skip even numbers since we already checked mod 2
            if (x % i == 0) return false;
        }
        return true;
    }

    /**
     * Calculates x to the power y under modulo m.
     *
     * @param x the base
     * @param y the exponent
     * @param m the modulo
     * @return (x^y)%m
     * @see <a href="https://en.wikipedia.org/wiki/Modular_exponentiation">Modular exponentiation</a>
     */
    public static long power(long x, long y, long m) {
        if (y == 0) {
            return 1;
        }
        long p = power(x, y / 2, m) % m;
        p = (p * p) % m;

        return (y % 2 == 0) ? p : (x * p) % m;
    }


    /**
     * Calculates the modular inverse of x for a given modulo. Leverages {@link java.math.BigInteger#modInverse(BigInteger)}, which is slow
     * but very precise
     *
     * @param a a value
     * @param m the modulo
     * @return modular inverse of a under m
     * @see <a href="https://en.wikipedia.org/wiki/Modular_multiplicative_inverse">Modular multiplicative inverse</a>
     */
    public static long defaultModInverse(long a, long m) {
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigM = BigInteger.valueOf(m);
        return bigA.modInverse(bigM).longValue();
    }


    /**
     * Calculates the modular inverse of x for a given modulo. This approach leverages the <i>extended Euclidean algorithm</i>
     * to achieve the inverse.
     *
     * @param a a value
     * @param m the modulo
     * @return modular inverse of a under m
     * @see <a href="https://en.wikipedia.org/wiki/Modular_multiplicative_inverse">Modular multiplicative inverse</a>
     * @see <a href="https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm">Extended Euclidean algorithm</a>
     */
    public static long euclideanModInverse(long a, long m) {
        if (m == 1) {
            return 0;
        }

        long m0 = m;
        long y = 0, x = 1;
        while (a > 1) {
            long q = a / m;
            long t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        return x < 0 ? x + m0 : x;
    }


    /**
     * Calculates the modular inverse of a for a given prime modulo. This approach leverages <i>Fermat's little theorem</i>
     * which states for a prime <i>m</i> and with <i>a</i> and <i>m</i> relatively prime, the modular inverse is equal to <i>(a^(m-2))%m</i>
     *
     * @param a a value
     * @param m the modulo, which has to be a prime number for this to work. otherwise, all bets are off ...
     * @return modular inverse if exists, otherwise returns <tt>-1</tt>
     * @see <a href="https://en.wikipedia.org/wiki/Modular_multiplicative_inverse">Modular multiplicative inverse</a>
     * @see <a href="https://en.wikipedia.org/wiki/Fermat%27s_little_theorem">Fermat's little theorem</a>
     */
    public static long fermatModInverse(long a, long m) {
        if (MathUtils.gcd(a, m) != 1) {
            return -1;
        }
        return power(a % m, m - 2, m);
    }
}