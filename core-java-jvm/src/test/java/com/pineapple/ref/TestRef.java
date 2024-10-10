package com.pineapple.ref;

import org.junit.jupiter.api.Test;

import java.lang.ref.*;

public class TestRef {
    @Test
    public void testRef() {
        ReferenceQueue<String> rq = new ReferenceQueue<>();
        Reference<String> sf = getPhantomReference(rq);//getWeak(rq);//getSoft(rq);
        printInfo(sf, rq);
        System.gc();
        printInfo(sf, rq);
    }

    private static void printInfo(Reference<String> sf, ReferenceQueue<String> rq) {
        System.out.println(sf.get());
        System.out.println(rq.poll());
    }

    public SoftReference<String> getSoft(ReferenceQueue<String> rq) {
        String a = "a";
        return new SoftReference<>(a, rq);
    }

    public WeakReference<String> getWeak(ReferenceQueue<String> rq) {
        String a = "a";
        return new WeakReference<>(a, rq);
    }

    public PhantomReference<String> getPhantomReference(ReferenceQueue<String> rq) {
        String a = "a";
        return new PhantomReference<>(a, rq);
    }
}
