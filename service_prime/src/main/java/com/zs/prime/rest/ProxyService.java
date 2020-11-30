package com.zs.prime.rest;

import com.zs.prime.cache.LocalCache;
import com.zs.prime.grpc.PrimeClient;
import com.zs.prime.grpc.PrimeResponse;
import com.zs.prime.grpc.PrimeServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static com.zs.prime.Config.GRPC_ADDRESS;
import static com.zs.prime.Config.GRPC_PORT;
import static java.lang.String.format;
import static java.util.logging.Level.INFO;

@Path("prime")
public class ProxyService {
    private static final Logger logger = Logger.getLogger(PrimeServer.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{n}")
    public Response getPrimesRest(@PathParam("n") int n) {
        logger.log(INFO, "Proxy service got request: " + n);
        if (n <= 0) {
            throw new IllegalArgumentException("Input should be a positive integer!");
        }
        final int[] state = new int[]{200};
        StreamingOutput stream = os -> {
            Writer writer = new BufferedWriter(new OutputStreamWriter(os));
            List<Integer> ret;
            try {
                ret = getPrimes(n);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (int i : ret) {
                writer.write(i + ",");
            }
            writer.flush();
        };
        return Response.status(state[0]).entity(stream).build();
    }

    private List<Integer> getPrimes(int n) throws ExecutionException, InterruptedException {
        List<Integer> ret = LocalCache.loadToN(n);
        if (!ret.isEmpty()) {
            logger.log(INFO, "Proxy service cache hit with value: " + ret);
            return ret;
        }
        PrimeResponse response = new PrimeClient().callGPPC(n, GRPC_ADDRESS, GRPC_PORT);
        if (response.getErrorCode() != 0) {
            throw new RuntimeException(format("gRPC service return error: %d:%s", response.getErrorCode(), response.getErrorMessage()));
        }
        ret = response.getPrimeList();
        LocalCache.setCacheValue(ret.stream().mapToInt(i -> i).toArray());
        return ret;
    }
}