package com.zs.prime.rest.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.GATEWAY_TIMEOUT;

@Provider
public class TimeOutExceptionMapper implements ExceptionMapper<TimeoutException> {
    private static final Logger logger = Logger.getLogger(TimeOutExceptionMapper.class.getName());

    @Override
    public Response toResponse(TimeoutException e) {
        logger.log(Level.SEVERE, e.getMessage());
        return Response.status(GATEWAY_TIMEOUT).entity("Server is busy, please try again later!!").build();
    }
}
