package com.zs.prime.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static com.zs.prime.Config.REST_PORT;

public class RestServer {

    public static void main(String[] args) {
        Server server = new Server(REST_PORT);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages", "com.zs.prime.rest");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(RestServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}