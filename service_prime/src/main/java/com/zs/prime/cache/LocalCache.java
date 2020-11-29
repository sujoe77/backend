package com.zs.prime.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache {
    private static final String KEY = "PRIME_NUMBERS";
    //use ConcurrentHashMap since it is thread safe
    private static final ConcurrentHashMap<String, int[]> cache = new ConcurrentHashMap<String, int[]>() {{
        put(KEY, new int[]{1, 2, 3, 5, 7});
    }};

    public static int[] getCacheValue() {
        return cache.get(KEY);
    }

    public static boolean setCacheValue(int[] value) {
        if (value.length > cache.get(KEY).length) {
            cache.put(KEY, value);
            return true;
        }
        return false;
    }

    /**
     * if result in cache, return the prime list, otherwise return an empty list
     * @param n, integer n
     * @return list of primes less than n or empty in case cache does not contains enough numbers
     */
    public static List<Integer> loadToN(int n) {
        int[] cachedValues = getCacheValue();
        List<Integer> ret = new LinkedList<>();
        if (cachedValues.length > 0 && cachedValues[cachedValues.length - 1] >= n) {
            for (int i : cachedValues) {
                if (i <= n) {
                    ret.add(i);
                }
            }
        }
        return ret;
    }
}
