package com.pineapple.openhft;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Node {
    int value;
    AtomicReference<Node> next;
    int enqTid;
    AtomicInteger deqTid;

    Node(int val, int etid) {
        value = val;
        next = new AtomicReference<Node>(null);
        enqTid = etid;
        deqTid = new AtomicInteger(-1);
    }
}


