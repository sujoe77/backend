package com.zs.prime.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PrimeClientTest {
    @Mocked
    ManagedChannelBuilder builder;
    @Mocked
    PrimeResponse response;
    @Mocked
    PrimeServiceGrpc.PrimeServiceBlockingStub stub;

    @Test
    public void testCallGPPC() {
        new MockUp<ManagedChannelBuilder>() {
            @Mock
            public ManagedChannelBuilder<?> forAddress(String name, int port) {
                assertEquals(name, "localhost");
                assertEquals(port, 8080);
                return builder;
            }
        };
        new MockUp<PrimeServiceGrpc>() {
            @Mock
            public PrimeServiceGrpc.PrimeServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
                return stub;
            }
        };

        new MockUp<PrimeServiceGrpc.PrimeServiceBlockingStub>() {
            @Mock
            public com.zs.prime.grpc.PrimeResponse getPrimes(com.zs.prime.grpc.PrimeRequest request) {
                assertEquals(request.getN(), 100);
                return response;
            }
        };
        PrimeClient client = new PrimeClient();
        PrimeResponse ret = client.callGPPC(100, "localhost", 8080);
        assertEquals(ret, response);
    }
}