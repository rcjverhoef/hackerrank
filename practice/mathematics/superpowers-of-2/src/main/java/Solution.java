
import java.util.Scanner;

/**
 *  Solution to hackerrank problem <i>Superpowers of 2</i>.<br/>
 *  <br/>
 *  First thing to note here is what a power of two looks like in binary, for now limiting to 32-bits
 *  but will work the same for higher bit counts
 *  <table>
 *      <tr><td>n=1</td><td>00000000 00000000 00000000 00000001</td></tr>
 *      <tr><td>n=2</td><td>00000000 00000000 00000000 00000010</td></tr>
 *      <tr><td>n=3</td><td>00000000 00000000 00000000 00000100</td></tr>
 *      <tr><td>n=4</td><td>00000000 00000000 00000000 00001000</td></tr>
 *      <tr><td>n=5</td><td>00000000 00000000 00000000 00010000</td></tr>
 *      <tr><td>n=6</td><td>00000000 00000000 00000000 00100000</td></tr>
 *      <tr><td>n=7</td><td>00000000 00000000 00000000 01000000</td></tr>
 *      <tr><td>n=8</td><td>00000000 00000000 00000000 10000000</td></tr>
 *      <tr><td>n=9</td><td>00000000 00000000 00000001 00000000</td></tr>
 *      <tr><td>...</td><td></td></tr>
 *  </table>
 *  <br/>
 * Next is to focus on the modular exponentiation algorithm. This algorithm basically multiplies
 * the result with itself each time starting at the first <i>1</i> in the binary sequence
 * representing the exponent. Since we will not encounter anymore <i>1's</i> going forward as we
 * are raising <i>2</i> to another power of <i>2</i>, we can simply for-loop while multiplying the result
 * with itself.<br/>
 *
 * @see <a href="https://www.hackerrank.com/challenges/superpowers/problem">Superpowers of 2</a>
 * @see <a href="https://en.wikipedia.org/wiki/Modular_exponentiation>Modular exponentiation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Exponentiation_by_squaring">Exponentiation by squaring</a>
 */
public class Solution {
    /**
     * Calculates <i>2^(2^a) mod b</i>
     *
     * @param a the exponent
     * @param b the modulo
     * @return <i>2^(2^a) mod b</i>
     */
    public static long superPowerOf2(long a, long b) {
        long result = 2 % b;
        for (int i = 1; i <= a; i++) {
            result = (result * result) % b;
        }
        return result;
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            long result = superPowerOf2(a, b);
            System.out.println(result);
        }
    }
}