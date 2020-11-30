package com.zs.prime.grpc;

import com.zs.prime.cache.LocalCache;
import com.zs.prime.math.Eratosthenes;
import io.grpc.stub.StreamObserver;
import mockit.Mock;
import mockit.MockUp;
import org.testng.annotations.Test;
import org.testng.internal.collections.Ints;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.joor.Reflect.on;
import static org.testng.Assert.*;

public class PrimeServerTest {

    @Test
    public void testGetPrimesFromCache() {
        PrimeServer server = new PrimeServer();
        LocalCache.setCacheValue(new int[]{1, 2, 3, 5, 7, 11, 13});
        new MockUp<PrimeServer>() {
            @Mock
            private List<Integer> computePrimes(int n) {
                throw new RuntimeException("Calling not expected!!");
            }
        };
        boolean[] completeCalled = new boolean[]{false};
        List<Integer> ret = new LinkedList<>();
        server.getPrimes(PrimeRequest.newBuilder().setN(10).build(), new StreamObserver<PrimeResponse>() {
            @Override
            public void onNext(PrimeResponse primeResponse) {
                ret.addAll(primeResponse.getPrimeList());
            }

            @Override
            public void onError(Throwable throwable) {
                throw new RuntimeException(throwable);
            }

            @Override
            public void onCompleted() {
                completeCalled[0] = true;
            }
        });
        assertEquals(ret, asList(1, 2, 3, 5, 7));
        assertTrue(completeCalled[0]);
    }

    @Test
    public void testGetPrimesFromCompute() {
        PrimeServer server = new PrimeServer();
        LocalCache.setCacheValue(new int[]{1, 2, 3, 5, 7, 11, 13});
        new MockUp<PrimeServer>() {
            @Mock
            private List<Integer> computePrimes(int n) {
                return asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
            }
        };
        boolean[] completeCalled = new boolean[]{false};
        List<Integer> ret = new LinkedList<>();
        server.getPrimes(PrimeRequest.newBuilder().setN(30).build(), new StreamObserver<PrimeResponse>() {
            @Override
            public void onNext(PrimeResponse primeResponse) {
                ret.addAll(primeResponse.getPrimeList());
            }

            @Override
            public void onError(Throwable throwable) {
                throw new RuntimeException(throwable);
            }

            @Override
            public void onCompleted() {
                completeCalled[0] = true;
            }
        });
        assertEquals(ret, asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        assertTrue(completeCalled[0]);
    }

    @Test
    public void testComputePrimes() {
        final int[] expected = {1, 2, 3, 5, 7};
        new MockUp<Eratosthenes>() {
            @Mock
            public int[] getPrimes(int n) {
                return expected;
            }
        };
        new MockUp<LocalCache>() {
            @Mock
            public boolean setCacheValue(int[] value) {
                assertEquals(value.length, 5);
                for (int i = 0; i < 5; i++) {
                    value[i] = expected[i];
                }
                return true;
            }
        };
        PrimeServer server = new PrimeServer();
        List<Integer> actual = on(server).call("computePrimes", 10).get();
        assertEquals(actual, Ints.asList(expected));
    }
}