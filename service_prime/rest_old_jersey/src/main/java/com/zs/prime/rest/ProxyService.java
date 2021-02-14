package com.zs.prime.rest;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.japi.function.Creator;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.CompletionStage;
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

import scala.collection.convert.AsScalaExtensions;
import scala.jdk.CollectionConverters.*;

@Path("prime")
public class ProxyService {
    private static final Logger logger = Logger.getLogger(ProxyService.class.getName());
    private static final ActorSystem system = ActorSystem.create("ProxyService");

    @GET
    @Produces(TEXT_PLAIN)
    @Path("{n}")
    public Response getPrimesRest(@PathParam("n") int n,
                                  @Context CloseableService closer) throws InterruptedException {
        logger.log(INFO, "Proxy service got request: " + n);
        if (n <= 0) {
            throw new IllegalArgumentException("Input should be a positive integer!");
        }
        final ManagedChannel channel = forAddress(GRPC_ADDRESS, GRPC_PORT).usePlaintext().build();
        final CompletionStage[] stage = new CompletionStage[]{null};
        StreamingOutput stream = os -> {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                Source<Integer, NotUsed> source = Source.fromIterator(() -> getPrimes(n, channel));

                stage[0] = source.runWith(Sink.foreach(i -> {
                    try {
                        System.out.println(i);
                        writer.write(i.intValue() + ",");
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }), system)
//                source.runForeach(i -> {
//                    try {
//                        System.out.println(i);
//                        writer.write(i.intValue() + ",");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        throw new RuntimeException(e);
//                    }
//                }, system)
                        .thenRunAsync(() -> {
                            try {
                                System.out.println("done!");
                                writer.flush();
                                closer.add(() -> channel.shutdown());
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        });
//                source.runForeach(integer -> {
//                    try {
//                        writer.write(integer.intValue() + ",");
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
//        LocalCache.setCacheValue(retStream.mapToInt(i -> i).toArray());
//
        Response ret = Response.ok(stream).build();
        while(stage[0] == null){
            Thread.sleep(1000);
        }
        stage[0].thenRun(() -> System.out.println("done"));
        return ret;
    }

    private Iterator<Integer> getPrimes(int n, ManagedChannel channel) {
        Stream<Integer> retStream;
        try {
            List<Integer> ret = LocalCache.loadToN(n);
            if (!ret.isEmpty()) {
                logger.log(INFO, "Proxy service cache hit with value: " + ret);
                return ret.iterator();
            }
            logger.log(INFO, format("Calling gRPC service: %s:%d with %d", GRPC_ADDRESS, GRPC_PORT, n));
            Iterator<PrimeResponse> response = new PrimeClient().callGPPC(n, channel);
            retStream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(response, Spliterator.ORDERED), false)
                    .map(primeResponse -> primeResponse.getPrime());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        return retStream.iterator();
    }
}