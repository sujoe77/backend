package com.zs.prime.rest;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;


public class PrimeRegistry extends AbstractBehavior<PrimeRegistry.Command> {
    interface Command {
    }

    private PrimeRegistry(ActorContext<Command> context) {
        super(context);
    }

    public static Behavior<Command> create() {
        return Behaviors.setup(PrimeRegistry::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder().build();
    }
}
