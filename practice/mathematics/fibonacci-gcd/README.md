# Fibonacci GCD
Solution to [hackerrank](https://www.hackerrank.com) problem [Fibonacci GCD](https://www.hackerrank.com/challenges/fibonacci-gcd/problem).

A few things make this problem pretty straight forward. First of, the we can use [gcd property](https://en.wikipedia.org/wiki/Greatest_common_divisor#Properties) that <tt>gcd(a, b, c) = gcd(gcd(a, b), c)</tt>. This same principle holds for a list of numbers.

The second interesting observation is that we can swap calculating the [Fibonacci operation and the gcd operation](https://en.wikipedia.org/wiki/Fibonacci_number#Primes_and_divisibility), so <tt>gcd(F(a1), ..., F(an)) = F(gcd(a1, ..., an))</tt>. So essentially, we only have to calculate one number of the Fibonacci series.

Next up, to help avoid long painfull recursive or iterative calulation of the Fibonacci number at location n, we can use the [Cassini and Catalan identities](https://en.wikipedia.org/wiki/Cassini_and_Catalan_identities) <tt>F(2n) = F(n)(2F(n+1) – F(n))</tt> and <tt>F(2n + 1) = F(n)^2 + F(n+1)^2</tt>.

Finally, to ease weight on the stack, we can use [left to right modular exponentiation](https://en.wikipedia.org/wiki/Modular_exponentiation#Left-to-right_binary_method) to iteratively calculate F(n). 