/*
 * Copyright 2018 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zs.prime.grpc;

import io.grpc.ManagedChannel;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.FailsafeExecutor;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.Timeout;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static com.zs.prime.grpc.PrimeServiceGrpc.newBlockingStub;
import static io.grpc.ManagedChannelBuilder.forAddress;
import static java.lang.String.format;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public final class PrimeClient {
    private static final Logger logger = Logger.getLogger(PrimeClient.class.getName());

    public PrimeResponse callGPPC(int n, String address, int port) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        logger.log(INFO, format("Calling gRPC service: %s:%d with %d", address, port, n));
        ManagedChannel[] channel = new ManagedChannel[]{null};
        PrimeResponse response;
        try {
            channel[0] = forAddress(address, port)
                    .usePlaintext()
                    .build();

            PrimeRequest request = PrimeRequest.newBuilder()
                    .setN(n)
                    .build();
            FailsafeExecutor<Object> executor = Failsafe.with(getRetryPolicy(), getTimeOut());
            response = executor
                    .getAsync(() -> newBlockingStub(channel[0]).getPrimes(request))
                    .get();
            if (response != null) {
                logger.log(INFO, format("gRPC service return: %s, errorCode: %d, errorMessage: [%s], latency: %d milli-seconds",
                        response.getPrimeList().toString(),
                        response.getErrorCode(),
                        response.getErrorMessage(),
                        System.currentTimeMillis() - start));
            }
        } finally {
            if (channel[0] != null) {
                channel[0].shutdown();
            }
        }
        return response;
    }

    private RetryPolicy<Object> getRetryPolicy() {
        RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
                .handle(Throwable.class)
                .withDelay(Duration.ofSeconds(5))
                .withMaxRetries(3)
                .onRetriesExceeded(e -> {
                    logger.log(SEVERE, format("Exception met when call gRPC service: %s", e.getFailure().getMessage()));
                    throw e.getFailure();
                });
        return retryPolicy;
    }

    private Timeout<Object> getTimeOut() {
        return Timeout.of(Duration.ofSeconds(30)).onFailure(event -> {
            throw new RuntimeException("Send log to Azure timeout!!", event.getFailure());
        });
    }
}
