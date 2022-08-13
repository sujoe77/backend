package com.pineapple.openhft;

public class OpDesc {
    long phase;
    boolean pending;
    boolean enqueue;
    Node node;

    public OpDesc(long ph, boolean pend, boolean enq, Node n) {
        phase = ph;
        pending = pend;
        enqueue = enq;
        node = n;
    }
}
