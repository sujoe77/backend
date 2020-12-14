package com.zs.prime.rest;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.http.javadsl.common.EntityStreamingSupport;
import akka.http.javadsl.marshalling.Marshaller;
import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.http.scaladsl.model.StatusCodes;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import com.zs.prime.grpc.PrimeClient;
import com.zs.prime.grpc.PrimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Routes extends AllDirectives {
    private final static Logger log = LoggerFactory.getLogger(Routes.class);
    private final ActorSystem<?> systemDefault;

    public Routes(ActorSystem<?> system, ActorRef<PrimeRegistry.Command> userRegistryActor) {
        this.systemDefault = system;
    }

    final Marshaller<PrimeResponse, ByteString> marshaller =
            Marshaller.withFixedContentType(ContentTypes.TEXT_CSV_UTF8,
                    t -> ByteString.fromString(t.getPrime() + ",")
            );

    public Route routes(ActorSystem<?> system) {
        return pathPrefix("prime", () ->
                concat(
                        path(PathMatchers.segment(), (String n) ->
                                concat(
                                        get(() -> {
                                            int n1;
                                            try {
                                                n1 = Integer.parseInt(n);
                                                if (n1 < 1) {
                                                    throw new IllegalArgumentException();
                                                }
                                            } catch (IllegalArgumentException e) {
                                                return complete(new StatusCodes.ClientError(400,  "", ""),
                                                        "N should be a positive integer!!");
                                            }
                                            Source<PrimeResponse, ?> source = new PrimeClient().callGPPC(n1, system == null ? systemDefault : system);
                                            return completeWithSource(
                                                    source,
                                                    marshaller,
                                                    EntityStreamingSupport.csv()
                                            );
                                        })
                                )
                        )
                )
        );
    }
}
