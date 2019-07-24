# Superpowers of 2

Solution to [hackerrank](https://www.hackerrank.com) excercise [Superpowers of 2](https://www.hackerrank.com/challenges/superpowers/problem).

First thing to note here is what a power of two looks like in binary. For now limiting to 16-bit, but will work the same for higher bit counts


| 2^n |                   | 
| --- |:-----------------:|
| n=1 | 00000000 00000001 |
| n=2 | 00000000 00000010 |
| n=3 | 00000000 00000100 |
| n=4 | 00000000 00001000 |
| n=5 | 00000000 00010000 |
| n=6 | 00000000 00100000 |
| n=7 | 00000000 01000000 |
| n=8 | 00000000 10000000 |
| n=9 | 00000001 00000000 |
| ... |                   |

Next is to focus on the [modular exponentiation algorithm](https://en.wikipedia.org/wiki/Modular_exponentiation). 
This algorithm basically multiplies the result with itself each time starting at the first <i>1</i> in the binary sequence
representing the exponent. Since we will not encounter anymore <i>1's</i> going forward as we are raising <i>2</i> to another 
power of <i>2</i>, we can simply for-loop while multiplying the result with itself.