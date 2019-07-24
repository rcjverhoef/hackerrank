/**
 * Utility class to avoid calculating n! over and over again
 */
public class FactorialMemoizer {
    private final long[] factorialCache;
    private final long mod;
    private int cacheIndex;

    public FactorialMemoizer(int maxCacheSize, long mod) {
        if (maxCacheSize < 2) {
            throw new IllegalArgumentException("really? why do want a cache for less than 2 elements?");
        }
        this.mod = mod;
        factorialCache = new long[maxCacheSize];
        factorialCache[0] = 1;
        factorialCache[1] = 1;
        cacheIndex = 1;
    }

    public long compute(int i) {
        if (i < 0) {
            return -1;
        }
        if (i <= cacheIndex) {
            return factorialCache[i];
        }
        long result = factorialCache[cacheIndex];
        for (int j = cacheIndex + 1; j <= i; j++) {
            result = (result * j) % mod;
            if (j < factorialCache.length) {
                factorialCache[j] = result;
                cacheIndex = j;
            }
        }
        return result;
    }
}