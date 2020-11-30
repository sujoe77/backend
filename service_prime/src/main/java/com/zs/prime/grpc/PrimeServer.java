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

import com.zs.prime.Config;
import com.zs.prime.cache.LocalCache;
import com.zs.prime.math.Eratosthenes;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.primitives.Ints.asList;
import static java.lang.String.format;
import static java.util.logging.Level.INFO;

public final class PrimeServer extends PrimeServiceGrpc.PrimeServiceImplBase {
    private static final Logger logger = Logger.getLogger(PrimeServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        new PrimeServer().start();
    }

    private void start() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(Config.GRPC_PORT).addService(this).build();
        logger.log(INFO, "Starting gRPC server...");
        server.start();
        logger.log(INFO, "Server started!");
        server.awaitTermination();
        logger.log(INFO, "Server shutdown!");
    }

    @Override
    public void getPrimes(PrimeRequest request, StreamObserver<PrimeResponse> observer) {
        PrimeResponse response;
        long start = System.currentTimeMillis();
        try {
            int n = request.getN();
            logger.log(INFO, "gRPC server got request: " + n);
            List<Integer> ret = LocalCache.loadToN(n);
            if (ret.isEmpty()) {
                logger.log(INFO, "gRPC server cache miss, compute it!!");
                ret = computePrimes(n);
            }
            response = toResponse(observer, ret, "", 0);
        } catch (Throwable t) {
            logger.log(Level.SEVERE, format("Met exception when calculate primes, %s", t.getMessage()));
            response = toResponse(observer, new LinkedList<>(), t.getMessage(), -1);
        }
        logger.log(INFO, format("gRPC server return: [%s], with latency %d milli-seconds",
                response.toString(), System.currentTimeMillis() - start));
    }

    private List<Integer> computePrimes(int n) {
        int[] valuesFromCompute = new Eratosthenes().getPrimes(n);
        LocalCache.setCacheValue(valuesFromCompute);
        return asList(valuesFromCompute);
    }

    private PrimeResponse toResponse(StreamObserver<PrimeResponse> observer, List<Integer> ret, String errorMessage, int errorCode) {
        PrimeResponse.Builder builder = PrimeResponse.newBuilder();
        PrimeResponse response = builder.addAllPrime(ret)
                .setErrorCode(errorCode)
                .setErrorMessage(errorMessage)
                .build();
        observer.onNext(response);
        observer.onCompleted();
        return response;
    }
}
