package com.pineapple.function;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public interface Handler<T> extends UnaryOperator<T> {

    default Handler<T> andThen(Handler<T> handler) {
        return t -> handler.apply(this.apply(t));
    }

    static <T> Handler<T> fromHandlerList(Iterator<Handler<T>> handlers) {
        return t -> {
            T ret = t;
            while (handlers.hasNext()) {
                ret = handlers.next().apply(ret);
            }
            return ret;
        };
    }

//    static <T> Handler<T> fromFunctionList(Iterator<Function<T, T>> functions) {
//        return fromList(functions)::apply;
//    }

    static <T> Function<T, T> fromList(Iterator<Function<T, T>> functions) {
        return t -> {
            T ret = t;
            while (functions.hasNext()) {
                ret = functions.next().apply(ret);
            }
            return ret;
        };
    }
//
//    private static <T> Iterator<Function<T, T>> toFunctions(Iterator<Handler<T>> handlers) {
//        Stream<Handler<T>> handlerStream = stream(spliteratorUnknownSize(handlers, Spliterator.ORDERED), false);
//        return handlerStream.map(h -> (Function<T, T>) h).toList().iterator();
//    }
}
