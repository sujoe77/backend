package com.zs.prime.rest.error;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;

@Provider
public class OtherExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger logger = Logger.getLogger(OtherExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable t) {
        logger.log(SEVERE, format("Error message: %s, stackTrace: %s", t.getMessage(), ExceptionUtils.getStackTrace(t)));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception Met, please try again later!!").build();
    }
}
