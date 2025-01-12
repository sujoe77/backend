package com.pineapple.function;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static com.pineapple.function.Handler.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeFunc {


    @Test
    public void testFuncs() {
        Function<Integer, Integer> f1 = integer -> integer + 1;
        Function<Integer, Integer> f2 = integer -> integer * 2;
        List<Function<Integer, Integer>> funcs = List.of(f1, f2);

        //Function<Integer, Integer> f3 = fromList1(funcs);
        //Function<Integer, Integer> f4 = fromList(funcs.iterator());
//        Handler<Integer> f5 = t -> fromList(funcs.iterator()).apply(t);
//        Handler<Integer> f05 = fromFunctionList(funcs.iterator());

        Handler<Integer> f01 = integer -> integer + 1;
        Handler<Integer> f02 = integer -> integer * 2;
        List<Handler<Integer>> handlers = List.of(f01, f02);

        Handler<Integer> f04 = fromHandlerList(handlers.iterator());
        assertEquals(2, f1.apply(1));
        assertEquals(6, f2.apply(3));
        assertEquals(4, f1.andThen(f2).apply(1));
        //assertEquals(4, f3.apply(1));
        assertEquals(4, f04.apply(1));
//        assertEquals(4, f5.apply(1));
//        assertEquals(4, f05.apply(1));
//        assertEquals(4, f4.apply(1));
        //assertEquals(4, f04.apply(1));
    }

    @Test
    public void testFuncs2() {
//        Handler<Integer> f1 = i -> i + 1;
//        Handler<Integer> f2 = i -> i * 2;
        //Handler<Integer> f3 = f1.andThen(f2);
    }
}

