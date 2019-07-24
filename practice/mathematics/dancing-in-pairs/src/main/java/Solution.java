import java.util.Scanner;

/**
 * Solution to hackerrank problem <a href="https://www.hackerrank.com/challenges/dance-class/problem">Dancing in pairs</a>.<br/>
 * <br/>
 * We can start by writing out the attendance schedule for the first x students: <br/>
 * <table>
 *     <tr><td>student 1</td><td>OXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOX</td></tr>
 *     <tr><td>student 2</td><td>XOOXXOOXXOOXXOOXXOOXXOOXXOOXXOOX</td></tr>
 *     <tr><td>student 3</td><td>XXOOOXXXOOOXXXOOOXXXOOOXXXOOOXXX</td></tr>
 *     <tr><td>student 4</td><td>XXXOOOOXXXXOOOOXXXXOOOOXXXXOOOOX</td></tr>
 *     <tr><td>student 5</td><td>XXXXOOOOOXXXXXOOOOOXXXXXOOOOOXXX</td></tr>
 *     <tr><td>student 6</td><td>XXXXXOOOOOOXXXXXXOOOOOOXXXXXXOOO</td></tr>
 *     <tr><td>......</td></tr>
 * </table><br/>
 * <br/>
 * From this, we can see that student i attends on day x if and only if <tt>(x / i) % 2 == 0</tt>. So the number of students
 * attending on day x is the same as summing from 1 to x the above found formula, i.e. summing for each student. From there,
 * we need to determine if that sum is even or not, i.e. check <i>mod 2</i>.<br/>
 * <br/>
 * Next interesting observation is using modular arithmetic rule <tt>(a+b)%m=((a%m)+(b%m))%m</tt>. That implies we can
 * first sum all x/i and then do the modulo at then end. Since java x/i return the floor for non-decimal numbers,
 * our found formula is equivalent to the <a href="https://en.wikipedia.org/wiki/Divisor_summatory_function">Divisor summatory function</a>.
 * that means we can write it as <tt>(2 * ((sum i==1...u (u/i)) - u^2) % 2, with u=sqrt(x)</tt>. We can then rewrite this to
 * <tt>(((2*(sum i=1...u 1/u))%2) - ((u^2)%2)) % 2</tt>. We can now simply discard the summation bit, since it is of the form
 * <tt>(2k)%2</tt>, which is always even and hence the mod 2 will give 0. Since we can ignore the minus going forward,
 * we are left with <tt>(u^2)%2</tt>. We can then rewrite this last bit as <tt>(sqrt(x)%2)*(sqrt(x)%2)%2</tt>, which in
 * essence is the same as merely calculating <tt>sqrt(x)%2</tt>.<br/>
 * <br/>
 * The final interesting thing is java 8 and calculate the sqrt for double values. Which means we need to do some extra to deal with
 * larger long values. But there we can take a peak at the java 9 implementation of BigInteger.
 *
 * @see <a href="https://www.hackerrank.com/challenges/dance-class/problem">Dancing in Pairs</a>
 * @see <a href="https://en.wikipedia.org/wiki/Divisor_summatory_function">Divisor summatory function</a>
 * @see <a href="https://en.wikipedia.org/wiki/Modular_arithmetic">Modular arithmetic</a>
 */
public class Solution {
    /**
     * Returns the floored square root of n. The implementation is "inspired" by
     * the implementation of BigInteger.sqrt of bit length < 64 available in java 9.
     *
     * @param n number to calculate square root of
     * @return the floored square root of n
     * @see <a href="https://en.wikipedia.org/wiki/Square_root">Square root</a>
     */
    public static long sqrt(long n) {
        long xk = (long) Math.floor(Math.sqrt(n));
        do {
            long xk1 = (xk + n / xk) / 2;
            if (xk1 >= xk) {
                return xk;
            }
            xk = xk1;
        } while (true);
    }

    /**
     * @param day the day on which to check if the number of students attending is even or not
     * @return returns if the number of students on that is even or not
     */
    public static boolean evenNumberOfStudentsAttending(long day) {
        long u = sqrt(day);
        return u % 2 == 0;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            for (int i = 0; i < testCases; i++) {
                long day = scanner.nextLong();
                boolean isEven = evenNumberOfStudentsAttending(day);
                System.out.println(isEven ? "even" : "odd");
            }
        }
    }
}