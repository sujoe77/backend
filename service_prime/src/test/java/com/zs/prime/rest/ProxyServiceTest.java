package com.zs.prime.rest;

import com.zs.prime.grpc.PrimeClient;
import com.zs.prime.grpc.PrimeResponse;
import com.zs.prime.rest.error.InvalidInputExceptionMapper;
import com.zs.prime.rest.error.OtherExceptionMapper;
import io.grpc.ManagedChannel;
import mockit.Mock;
import mockit.MockUp;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class ProxyServiceTest extends JerseyTest {

    @BeforeTest
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterTest
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(ProxyService.class,
                InvalidInputExceptionMapper.class,
                OtherExceptionMapper.class);
    }

    @Test
    public void testGetPrimesRestWithCache() {
        Response response = target("/prime/1").request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals(content, "1,");

        response = target("/prime/20").request()
                .get();
        content = response.readEntity(String.class);
        assertEquals(content, "1,2,3,5,7,11,13,17,19,");
    }

    @Test
    public void testGetPrimesRestGrpcFailed() {
        Response response = target("/prime/50").request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals(content, "1,2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,");
    }

    @Test
    public void testGetPrimesRestGrpc() {
        new MockUp<PrimeClient>() {
            @Mock
            public Iterator<PrimeResponse> callGPPC(int n, ManagedChannel channel) throws ExecutionException, InterruptedException {
                assertEquals(n, 40);
                PrimeResponse.Builder builder = PrimeResponse.newBuilder();
                List<PrimeResponse> ret = new ArrayList<PrimeResponse>();
                for (int i : asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)) {
                    PrimeResponse response = builder.setPrime(i)
                            .setErrorCode(0)
                            .setErrorMessage("")
                            .build();
                    ret.add(response);
                }
                return ret.iterator();
            }
        };
        Response response = target("/prime/40").request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals(content, "1,2,3,5,7,11,13,17,19,23,29,31,37,");
    }

    @Test
    public void testGetPrimesRestError() {
        Response response = target("/prime/0").request()
                .get();
        checkFailedResponse(response);

        response = target("/prime/-1").request()
                .get();
        checkFailedResponse(response);
    }

    private void checkFailedResponse(Response response) {
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        String content = response.readEntity(String.class);
        assertEquals(content, "Input should be a positive integer!");
    }
}