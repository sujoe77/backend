package com.zs.prime.cache;

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.internal.collections.Ints.asList;

public class LocalCacheTest {

    @Test
    public void testLoadToN() {
        LocalCache.setCacheValue(new int[]{1, 2, 3, 5, 7, 11, 13, 17, 19});
        List<Integer> actual = LocalCache.loadToN(12);
        assertEquals(actual, asList(1, 2, 3, 5, 7, 11));

        actual = LocalCache.loadToN(6);
        assertEquals(actual, asList(1, 2, 3, 5));

        actual = LocalCache.loadToN(0);
        assertEquals(actual, new LinkedList<>());

        actual = LocalCache.loadToN(100);
        assertEquals(actual, new LinkedList<>());
    }
}