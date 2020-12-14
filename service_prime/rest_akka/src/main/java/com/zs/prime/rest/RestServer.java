package com.zs.prime.rest;

import akka.NotUsed;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletionStage;

public class RestServer extends AllDirectives {

    static void startHttpServer(Route route, ActorSystem<?> system) {
        CompletionStage<ServerBinding> futureBinding =
                Http.get(system).newServerAt("localhost", 8080).bind(route);

        futureBinding.whenComplete((binding, exception) -> {
            if (binding != null) {
                InetSocketAddress address = binding.localAddress();
                system.log().info("Server online at http://{}:{}/",
                        address.getHostString(),
                        address.getPort());
            } else {
                system.log().error("Failed to bind HTTP endpoint, terminating system", exception);
                system.terminate();
            }
        });
    }

    public static void main(String[] args) {
        Behavior<NotUsed> rootBehavior = Behaviors.setup(context -> {
            ActorRef<PrimeRegistry.Command> userRegistryActor =
                    context.spawn(PrimeRegistry.create(), "UserRegistry");

            Routes userRoutes = new Routes(context.getSystem(), userRegistryActor);
            startHttpServer(userRoutes.routes(null), context.getSystem());

            return Behaviors.empty();
        });

        ActorSystem.create(rootBehavior, "HelloAkkaHttpServer");
    }
}


