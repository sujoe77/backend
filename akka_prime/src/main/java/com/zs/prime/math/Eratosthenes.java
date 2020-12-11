package com.zs.prime.math;

public class Eratosthenes implements PrimeCalculator {

    public int[] getPrimes(int n) {
        if (n <= 0) {
            return new int[]{};
        }
        int oneToN[] = new int[n + 1];
        int primeCount = n, prime = 2;
        while (prime * prime <= n) {
            primeCount -= removeMultiplies(oneToN, prime);
            prime = findNextPrime(oneToN, prime);
        }
        return toPrimeNumbers(oneToN, primeCount);
    }

    private int removeMultiplies(int[] oneToN, int prime) {
        int primeCount = 0;
        for (int i = 2 * prime; i < oneToN.length; i += prime) {
            if (oneToN[i] == 0) {
                oneToN[i] = 1;
                primeCount++;
            }
        }
        return primeCount;
    }

    private int findNextPrime(int[] oneToN, final int lastPrime) {
        for (int i = lastPrime + 1; (i * i) < oneToN.length; i++) {
            if (oneToN[i] == 0) {
                return i;
            }
        }
        return oneToN.length;
    }

    private int[] toPrimeNumbers(int[] oneToN, int primeCount) {
        int[] ret = new int[primeCount];
        int j = 0;
        for (int i = 1; i < oneToN.length && j < primeCount; i++) {
            if (oneToN[i] == 0) {
                ret[j++] = i;
            }
        }
        return ret;
    }
}
