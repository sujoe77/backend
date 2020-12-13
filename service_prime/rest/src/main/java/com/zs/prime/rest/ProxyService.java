package com.zs.prime.rest;

import com.zs.prime.cache.LocalCache;
import com.zs.prime.grpc.PrimeClient;
import com.zs.prime.grpc.PrimeResponse;
import io.grpc.ManagedChannel;
import org.glassfish.jersey.server.CloseableService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.zs.prime.Config.GRPC_ADDRESS;
import static com.zs.prime.Config.GRPC_PORT;
import static io.grpc.ManagedChannelBuilder.forAddress;
import static java.lang.String.format;
import static java.util.logging.Level.INFO;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("prime")
public class ProxyService {
    private static final Logger logger = Logger.getLogger(ProxyService.class.getName());

    @GET
    @Produces(TEXT_PLAIN)
    @Path("{n}")
    public Response getPrimesRest(@PathParam("n") int n,
                                  @Context CloseableService closer) {
        logger.log(INFO, "Proxy service got request: " + n);
        if (n <= 0) {
            throw new IllegalArgumentException("Input should be a positive integer!");
        }
        final ManagedChannel channel = forAddress(GRPC_ADDRESS, GRPC_PORT).usePlaintext().build();
        StreamingOutput stream = os -> {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                getPrimes(n, channel).forEach(integer -> {
                    try {
                        writer.write(integer.intValue() + ",");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
//        LocalCache.setCacheValue(retStream.mapToInt(i -> i).toArray());
        closer.add(() -> channel.shutdown());
        return Response.ok(stream).build();
    }

    private Stream<Integer> getPrimes(int n, ManagedChannel channel) throws ExecutionException, InterruptedException {
        List<Integer> ret = LocalCache.loadToN(n);
        if (!ret.isEmpty()) {
            logger.log(INFO, "Proxy service cache hit with value: " + ret);
            return ret.stream();
        }
        logger.log(INFO, format("Calling gRPC service: %s:%d with %d", GRPC_ADDRESS, GRPC_PORT, n));
        Iterator<PrimeResponse> response = new PrimeClient().callGPPC(n, channel);
        Stream<Integer> retStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(response, Spliterator.ORDERED), false)
                .map(primeResponse -> primeResponse.getPrime());
        return retStream;
    }
}