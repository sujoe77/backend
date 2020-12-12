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
import com.zs.prime.math.PrimeIterator;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        long start = System.currentTimeMillis();
        List<Integer> ret = new ArrayList<>();
        Iterator iterator;
        try {
            int n = request.getN();
            logger.log(INFO, "gRPC server got request: " + n);
            iterator = LocalCache.loadToN(n).iterator();
            if (!iterator.hasNext()) {
                logger.log(INFO, "gRPC server cache miss, compute it!!");
                iterator = computePrimes(n);
            }
            toResponse(observer, iterator, "");
        } catch (Throwable t) {
            logger.log(Level.SEVERE, format("Met exception when calculate primes, %s", t.getMessage()));
            toResponse(observer, new LinkedList<Integer>().iterator(), t.getMessage());
        }
        logger.log(INFO, format("gRPC server return: [%s], with latency %d milli-seconds",
                ret.toString(), System.currentTimeMillis() - start));
    }

    private Iterator<Integer> computePrimes(int n) {
        return new PrimeIterator(n);
    }

    private void toResponse(StreamObserver<PrimeResponse> observer, Iterator<Integer> iterator, String errorMessage) {
        PrimeResponse.Builder builder = PrimeResponse.newBuilder();
        while (iterator.hasNext()) {
            PrimeResponse response = builder.setPrime(iterator.next())
                    .setErrorMessage(errorMessage)
                    .build();
            observer.onNext(response);
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            PrimeResponse response = builder.setPrime(-1)
                    .setErrorMessage(errorMessage)
                    .build();
            observer.onNext(response);
        }
        observer.onCompleted();
    }
}
