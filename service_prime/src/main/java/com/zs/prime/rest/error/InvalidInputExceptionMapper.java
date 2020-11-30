package com.zs.prime.rest.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    private static final Logger logger = Logger.getLogger(InvalidInputExceptionMapper.class.getName());

    @Override
    public Response toResponse(IllegalArgumentException e) {
        logger.log(Level.SEVERE, e.getMessage());
        return Response.status(BAD_REQUEST).entity(e.getMessage()).build();
    }
}
