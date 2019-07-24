import java.util.Scanner;

/**
 * Solution to exercise <a href="https://www.hackerrank.com/challenges/matrix-tracing/problem">matrix tracing</a>
 * posted on hackerrank<br>
 * <br>
 * The essential thing to notice in this exercise is that for a given nxm matrix, we have to traverse right n-1 times
 * and down m-1 times for each path we can take. So to get to all paths, the question becomes how many combinations
 * of n-1 right and m-1 down we can make. This is essentially a minor variation of the n-choose-k problem. It is the
 * same as asking how many ways we can order x yellow balls and y red balls in a straight line. So this gives we can
 * plugin our numbers to formula <tt>(n-1 + m-1)! / ((n-1)! * (m-1)!)</tt><br>
 * <br>
 * The other thing is that we can stick to using primitives here due to the use of the modulo. The only secret there
 * is that <tt>a/b mod m</tt> is <u>not</u> the same <tt>((a%m) / (b%m))%m</tt>, but that the mod inverse is required.<br>
 * <br>
 *
 * @see <a href="https://www.hackerrank.com/challenges/matrix-tracing/problem">Matrix tracing</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binomial_coefficient">Binomial coefficient</a>
 * @see <a href="https://en.wikipedia.org/wiki/Combination">Combination</a>
 * @see <a href="https://en.wikipedia.org/wiki/Modular_arithmetic">Modular arethmetic</a>
 */
public class Solution {
    private static final long DEFAULT_MODULO = 1000000007L;
    private static final int MAX_FACTORIAL_CACHE_SIZE = 20001;

    private final long mod;
    private final boolean modIsPrime;
    private final FactorialMemoizer factorialMemoizer;

    public Solution() {
        this(DEFAULT_MODULO);
    }

    public Solution(long mod) {
        this.mod = mod;
        modIsPrime = MathUtils.isPrime(mod);
        factorialMemoizer = new FactorialMemoizer(MAX_FACTORIAL_CACHE_SIZE, mod);
    }


    public long factorial(int i) {
        return factorialMemoizer.compute(i);
    }

    public long calculateNumberOfArrangements(int n, int m) {
        long nominator = factorial(n - 1 + m - 1);
        long denominator = (factorial(n - 1) * factorial(m - 1)) % mod;
        long modInverse = modIsPrime ? MathUtils.fermatModInverse(denominator, mod) : MathUtils.euclideanModInverse(denominator, mod);
        return (nominator * modInverse) % mod;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfTestCases = scanner.nextInt();
            for (int i = 0; i < numberOfTestCases; i++) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                long numberOfArrangements = calculateNumberOfArrangements(n, m);
                System.out.println(numberOfArrangements);
            }
        }
    }

    public static void main(String[] args) {
        new Solution().run();
    }
}