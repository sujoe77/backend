package com.zs.prime.math;

public class Eratosthenes implements PrimeCalculator {
    public int[] getPrimes(int n) {
        int oneToN[] = new int[n + 1];
        int primeCount = n, prime = 2;
        while (prime * prime <= n) {
            primeCount = removeMultiplies(oneToN, primeCount, prime);
            prime = findNextPrime(oneToN, prime);
        }
        return toArray(oneToN, primeCount);
    }

    private int removeMultiplies(int[] oneToN, int primeCount, int prime) {
        for (int i = 2 * prime; i < oneToN.length; i += prime) {
            if (oneToN[i] == 0) {
                oneToN[i] = 1;
                primeCount--;
//                System.out.println("remove " + i);
            }
        }
        return primeCount;
    }

    private int findNextPrime(int[] oneToN, final int lastPrime) {
        for (int i = lastPrime + 1; (i * i) < oneToN.length; i++) {
            if (oneToN[i] == 0) {
//                System.out.println("new Prime is: " + i);
                return i;
            }
        }
        return oneToN.length;
    }

    private int[] toArray(int[] oneToN, int primeCount) {
        int[] ret = new int[primeCount];
        int j = 0;
        for (int i = 1; i < oneToN.length; i++) {
            if (oneToN[i] == 0) {
//                System.out.printf("%d ", i);
                ret[j++] = i;
            }
        }
        return ret;
    }
}
