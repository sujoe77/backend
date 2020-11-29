package com.zs.prime;

import com.zs.prime.math.Eratosthenes;
import com.zs.prime.math.PrimeCalculator;
import org.testng.annotations.Test;

import static org.joor.Reflect.on;
import static org.testng.Assert.assertEquals;
import static org.testng.internal.collections.Ints.asList;

public class EratosthenesTest {

    @Test
    public void testGetPrimes() {
        PrimeCalculator calculator = new Eratosthenes();
        int[] ret = calculator.getPrimes(-10);
        assertEquals(ret, new int[]{});

        ret = calculator.getPrimes(-1);
        assertEquals(ret, new int[]{});

        ret = calculator.getPrimes(0);
        assertEquals(ret, new int[]{});


        ret = calculator.getPrimes(1);
        assertEquals(ret, new int[]{1});

        ret = calculator.getPrimes(2);
        assertEquals(ret, new int[]{1, 2});

        ret = calculator.getPrimes(3);
        assertEquals(ret, new int[]{1, 2, 3});

        ret = calculator.getPrimes(4);
        assertEquals(ret, new int[]{1, 2, 3});

        ret = calculator.getPrimes(5);
        assertEquals(ret, new int[]{1, 2, 3, 5});

        ret = calculator.getPrimes(10);
        assertEquals(ret, new int[]{1, 2, 3, 5, 7});

        ret = calculator.getPrimes(20);
        assertEquals(ret, new int[]{1, 2, 3, 5, 7, 11, 13, 17, 19});

        ret = calculator.getPrimes(100);
        assertEquals(ret, new int[]{1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97});
    }

    //test private method, use reflection, make sure method with minimum visibility
    @Test
    public void testToPrimeNumbers() {
        int[] array = new int[]{1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0};
        Eratosthenes eratosthenes = new Eratosthenes();
        int[] actual = on(eratosthenes).call("toPrimeNumbers", array, 0).get();
        assertEquals(asList(actual), asList(new int[]{}));

        actual = on(eratosthenes).call("toPrimeNumbers", array, 1).get();
        assertEquals(asList(actual), asList(new int[]{1}));

        actual = on(eratosthenes).call("toPrimeNumbers", array, 5).get();
        assertEquals(asList(actual), asList(new int[]{1, 2, 3, 5, 7}));
    }

    @Test
    public void testRemoveMultiplies() {
        Eratosthenes eratosthenes = new Eratosthenes();
        int[] array = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // 0 to 11
        int count = on(eratosthenes).call("removeMultiplies", array, 2).get();
        assertEquals(count, 4);
        assertEquals(asList(array), asList(new int[]{0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0}));

        count = on(eratosthenes).call("removeMultiplies", array, 3).get();
        assertEquals(count, 1);
        assertEquals(asList(array), asList(new int[]{0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0}));
    }
}