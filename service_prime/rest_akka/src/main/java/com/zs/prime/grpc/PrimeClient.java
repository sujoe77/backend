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

import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.grpc.GrpcClientSettings;
import akka.stream.javadsl.Source;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.FailsafeExecutor;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.Timeout;

import java.time.Duration;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;

public final class PrimeClient {
    private static final Logger logger = Logger.getLogger(PrimeClient.class.getName());

    public Source<PrimeResponse, NotUsed> callGPPC(int n, ActorSystem system) {
        PrimeRequest request = PrimeRequest.newBuilder()
                .setN(n)
                .build();

        PrimeServiceClient client = PrimeServiceClient.create(
                GrpcClientSettings.connectToServiceAt("127.0.0.1", 10001, system)
                        .withTls(false), system);

        FailsafeExecutor<Object> executor = Failsafe.with(getRetryPolicy(), getTimeOut());

        try {
            return executor
                    .getAsync(() -> client.getPrimes(request))
                    .get();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
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
            throw new RuntimeException("calling gPRC timeout!!", event.getFailure());
        });
    }
}
