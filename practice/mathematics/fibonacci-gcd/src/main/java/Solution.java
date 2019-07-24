import java.util.Scanner;

public class Solution {
    private static final long MAGIC_MODULO = 1000000000L + 7L;

    /**
     * Few simple properties are help to determine the greatest common divisor of a list of Fibonacci numbers.<br/>
     * First of, not that a property of the gcd is that <tt>gcd(a, b ,c) = gcd(gcd(a, b), c)</tt> and that this also holds
     * for the extended version <tt>gcd(a1, ..., an) = gcd(gcd(a1, ..., an-1), an)</tt>.<br/>
     * The second part to observe is that <tt>gcd(F(a), F(b)) = F(gcd(a, b))</tt>. Hence the gcd of the
     * input list can be determined first, before we have to calculate the Fibonacci number.
     *
     * @param a list of location to determine <tt>gcd[F(a1), ..., F(an)]</tt>
     * @return <tt>gcd[F(a1), ..., F(an)]</tt>
     * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor#Properties">Greatest common divisor</a>
     * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number#Primes_and_divisibility">Fibonacci number</a>
     */
    public static long fibonacciGcd(long[] a) {
        long gcd = MathUtils.gcd(a);
        return MathUtils.fibonacciSeriesAt(gcd, MAGIC_MODULO);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextLong();
            }
            long result = fibonacciGcd(a);
            System.out.println(result);
        }
    }
}