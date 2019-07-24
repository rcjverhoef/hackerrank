# Dancing in Pairs
Solution to hackerrank problem [Dancing in pairs](https://www.hackerrank.com/challenges/dance-class/problem).

We can start by writing out the attendance schedule for the first <i>x</i> students: 

| Student| schedule |
| --- | --- |
| student 1 | OXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOX |
| student 2 | XOOXXOOXXOOXXOOXXOOXXOOXXOOXXOOX |
| student 3 | XXOOOXXXOOOXXXOOOXXXOOOXXXOOOXXX |
| student 4 | XXXOOOOXXXXOOOOXXXXOOOOXXXXOOOOX |
| student 5 | XXXXOOOOOXXXXXOOOOOXXXXXOOOOOXXX |
| student 6 | XXXXXOOOOOOXXXXXXOOOOOOXXXXXXOOO |
| ...... |

From this, we can deduct that student <i>i</i> attends on day <i>x</i> if and only if:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tt>(x / i) % 2 == 0</tt>. 

So the number of students attending on day <i>x</i> is the same as summing from 1 to <i>x</i> the above found formula, i.e. summing for each student. From there,
we need to determine if that sum is even or not, i.e. check <i>mod 2</i>.
 
Next interesting observation is using modular arithmetic rule

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tt>(a + b) % m = ((a % m) + (b % m)) % m</tt>. 

That implies we can first sum all <i>x / i</i> and then do the modulo at then end. Since in java <i>x / i</i> return the floor for non-decimal numbers, our found formula is equivalent to the [Divisor summatory function](https://en.wikipedia.org/wiki/Divisor_summatory_function), <i>mod 2</i> of course to determine even or uneven.

Next observation is that we can then write the sum as outlined in the wikipedia article. Again, using modular arithmetic, we can 
then just drop the sum since for all k we have:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tt>(2k) % 2 == 0</tt>

So that means we are essential down to having to calculate:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tt>(u * u) % 2 = ((u % 2) * (u % 2)) % 2 = u % 2</tt>

with <tt>u = sqrt(2)</tt>.

So the last bit then is the nuisance in java 8 and lower we only have a square root on double values available, which doesn't work that 
well for larger long values. With some inspiration from the java 9 and above implementation of [BigInteger.sqrt](https://github.com/netroby/jdk9-dev/blob/master/jdk/src/java.base/share/classes/java/math/MutableBigInteger.java)
we can easily get around that.