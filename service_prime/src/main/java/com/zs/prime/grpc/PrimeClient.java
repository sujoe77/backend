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

import java.util.List;
import java.util.logging.Logger;

import static com.zs.prime.grpc.PrimeServiceGrpc.newBlockingStub;
import static io.grpc.ManagedChannelBuilder.forAddress;
import static java.lang.String.format;
import static java.util.logging.Level.INFO;

public final class PrimeClient {
    private static final Logger logger = Logger.getLogger(PrimeClient.class.getName());

    public PrimeResponse callGPPC(int n, String address, int port) {
        long start = System.currentTimeMillis();
        logger.log(INFO, format("Calling gRPC service: %s:%d with %d", address, port, n));
        ManagedChannel channel = forAddress(address, port)
                .usePlaintext()
                .build();

        PrimeRequest request = PrimeRequest.newBuilder().setN(n).build();
        PrimeResponse response = newBlockingStub(channel).getPrimes(request);
        channel.shutdown();
        logger.log(INFO, format("gRPC service return: %s, errorCode: %d, errorMessage: [%s], latency: %d milli-seconds",
                response.getPrimeList().toString(), response.getErrorCode(), response.getErrorMessage(), System.currentTimeMillis() - start));
        return response;
    }
}
