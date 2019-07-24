# matrix-tracing
Solution in java for [Hackerrank](https://www.hackerrank.com) mathematics exercise [matrix tracing](https://www.hackerrank.com/challenges/matrix-tracing/problem)

The essential thing to notice in this exercise is that for a given nxm matrix, we have to traverse right _n-1_ times
and down _m-1_ times for each path we can take. So to get to all paths, the question becomes how many combinations
of _n-1_ right and _m-1_ down we can make. This is essentially a minor variation of the n-choose-k problem. It is the
same as asking how many ways we can order x yellow balls and y red balls in a straight line. So this gives we can
plugin our numbers to formula <tt>(n-1 + m-1)! / ((n-1)! * (m-1)!)</tt>


The other thing is that we can stick to using primitives here due to the use of the modulo. The only secret there
is that <tt>a/b mod m</tt> is <u>not</u> the same <tt>((a%m) / (b%m))%m</tt>, but that the mod inverse is required.