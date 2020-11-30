package com.zs.prime.grpc;

import io.grpc.ManagedChannelBuilder;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.jodah.failsafe.*;
import net.jodah.failsafe.function.CheckedSupplier;
import org.joor.Reflect;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.joor.Reflect.onClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PrimeClientTest {
    @Mocked
    PrimeResponse response;
    @Mocked
    PrimeServiceGrpc.PrimeServiceBlockingStub stub;

    @Test
    public void testCallGPPC() throws ExecutionException, InterruptedException {
        final ManagedChannelBuilder[] builder = new ManagedChannelBuilder[]{null};
        builder[0] = new MockUp<ManagedChannelBuilder>() {
            @Mock
            public ManagedChannelBuilder<?> forAddress(String name, int port) {
                assertEquals(name, "localhost");
                assertEquals(port, 8080);
                return builder[0];
            }

            @Mock
            public ManagedChannelBuilder usePlaintext() {
                return builder[0];
            }
        }.getMockInstance();
        new MockUp<PrimeServiceGrpc>() {
            @Mock
            public PrimeServiceGrpc.PrimeServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
                return stub;
            }
        };
        List<PrimeRequest> requestList = new LinkedList<>();
        new Expectations() {{
            stub.getPrimes(withCapture(requestList));
            result = response;
        }};

        PrimeClient client = new PrimeClient();
        PrimeResponse ret = client.callGPPC(100, "localhost", 8080);
        assertEquals(ret, response);
        assertEquals(requestList.get(0).getN(), 100);
    }

    @Test
    public void testCallGPPCFailsafe() throws ExecutionException, InterruptedException {
        final ManagedChannelBuilder[] builder = new ManagedChannelBuilder[]{null};
        builder[0] = new MockUp<ManagedChannelBuilder>() {
            @Mock
            public ManagedChannelBuilder<?> forAddress(String name, int port) {
                assertEquals(name, "localhost");
                assertEquals(port, 8080);
                return builder[0];
            }

            @Mock
            public ManagedChannelBuilder usePlaintext() {
                return builder[0];
            }
        }.getMockInstance();
        new MockUp<PrimeServiceGrpc>() {
            @Mock
            public PrimeServiceGrpc.PrimeServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
                return stub;
            }
        };
        new MockUp<FailsafeExecutor>() {
            @Mock
            void $init(List<Policy> policies) {
                assertEquals(policies.size(), 2);
                assertTrue(policies.get(0) instanceof RetryPolicy);
                assertEquals(((RetryPolicy<?>) policies.get(0)).getMaxRetries(), 3);
                assertEquals(((RetryPolicy<?>) policies.get(0)).getDelay(), Duration.ofSeconds(5));
                assertTrue(policies.get(1) instanceof Timeout);
                assertEquals(((Timeout<?>) policies.get(1)).getTimeout(), Duration.ofSeconds(30));
            }

            @Mock
            private <T> CompletableFuture<T> callAsync(Function<AsyncExecution, Supplier<CompletableFuture<ExecutionResult>>> supplierFn, boolean asyncExecution) {
                return new CompletableFuture<T>(){
                    @Override
                    public T get() throws InterruptedException, ExecutionException {
                        return (T) response;
                    }
                };
            }
        };

        PrimeClient client = new PrimeClient();
        PrimeResponse ret = client.callGPPC(100, "localhost", 8080);
        assertEquals(ret, response);
    }
}