package com.pineapple.openhft;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class WFQueue {
    public static final int TID = 10;
    public static final int NUM_THRDS = 5;
    private AtomicReference<Node> head, tail;
    private AtomicReferenceArray<OpDesc> state;

    public WFQueue() {
        Node sentinel = new Node(-1, -1);
        head = new AtomicReference<Node>(sentinel);
        tail = new AtomicReference<Node>(sentinel);
        state = new AtomicReferenceArray<OpDesc>(NUM_THRDS);
        for (int i = 0; i < state.length(); i++) {
            state.set(i, new OpDesc(-1, false, true, null));
        }
    }

    public void enq(int value) {
        long phase = maxPhase() + 1;
        // cf. Figure 3b
        state.set(TID, new
                OpDesc(phase, true, true, new Node(value, TID)));
        help(phase);
        help_finish_enq();
    }

    public int deq() throws EmptyException {
        long phase = maxPhase() + 1;
        // cf. Figure 5a
        state.set(TID, new OpDesc(phase, true, false, null));
        help(phase);
        help_finish_deq();
        Node node = state.get(TID).node;
        //
        if (node == null) {
            throw new EmptyException();
        }
        return node.next.get().value;
    }

    private void help(long phase) {
        for (int i = 0; i < state.length(); i++) {
            OpDesc desc = state.get(i);
            if (desc.pending && desc.phase <= phase) {
                if (desc.enqueue) {
                    help_enq(i, phase);
                } else {
                    help_deq(i, phase);
                }
            }
        }
    }

    private long maxPhase() {
        long maxPhase = -1;
        for (int i = 0; i < state.length(); i++) {
            long phase = state.get(i).phase;
            if (phase > maxPhase) {
                maxPhase = phase;
            }
        }
        return maxPhase;
    }

    private boolean isStillPending(int tid, long ph) {
        return state.get(tid).pending && state.get(tid).phase <= ph;
    }

    private void help_enq(int tid, long phase) {
        while (isStillPending(tid, phase)) {
            Node last = tail.get();
            Node next = last.next.get();
            if (last == tail.get()) {
                if (next == null) {
                    // enqueue can be applied
                    if (isStillPending(tid, phase)) {
                        if (last.next.compareAndSet(next, state.get(tid).node)) {
                            // cf. Figure 3c
                            help_finish_enq();
                            return;
                        }
                    }
                } else {
                    // some enqueue is in progress
                    help_finish_enq();
                    // help it first, then retry
                }
            }
        }
    }

    private void help_finish_enq() {
        Node last = tail.get();
        Node next = last.next.get();
        if (next != null) {
            int tid = next.enqTid;
            // read enqTid of the last element
            OpDesc curDesc = state.get(tid);
            if (last == tail.get() && state.get(tid).node == next) {
                OpDesc newDesc = new
                        // cf. Figure 3d
                        OpDesc(state.get(tid).phase, false, true, next);
                state.compareAndSet(tid, curDesc, newDesc);
                tail.compareAndSet(last, next);
                // cf. Figure 3e
            }
        }
    }

    private void help_deq(int tid, long phase) {
        while (isStillPending(tid, phase)) {
            Node first = head.get();
            Node last = tail.get();
            Node next = first.next.get();
            if (first == head.get()) {
                if (first == last) {
                    // queue might be empty
                    if (next == null) {
                        // queue is empty
                        OpDesc curDesc = state.get(tid);
                        if (last == tail.get() && isStillPending(tid, phase)) {
                            OpDesc newDesc = new
                                    OpDesc(state.get(tid).phase, false, false, null);
                            state.compareAndSet(tid, curDesc, newDesc);
                        }
                    } else {
                        // some enqueue is in progress
                        help_finish_enq();
                        // help it first, then retry
                    }
                } else {
                    // queue is not empty
                    OpDesc curDesc = state.get(tid);
                    Node node = curDesc.node;
                    if (!isStillPending(tid, phase)) break;
                    if (first == head.get() && node != first) {
                        OpDesc newDesc = new
                                // cf. Figure 5b
                                OpDesc(state.get(tid).phase, true, false, first);
                        if (!state.compareAndSet(tid, curDesc, newDesc)) {
                            continue;
                        }
                    }
                    first.deqTid.compareAndSet(-1, tid);
                    // cf. Figure 5c
                    help_finish_deq();
                    //
                }
                //
            }
        }
    }

    private void help_finish_deq() {
        Node first = head.get();
        Node next = first.next.get();
        int tid = first.deqTid.get();
        // read deqTid of the first element
        if (tid != -1) {
            OpDesc curDesc = state.get(tid);
            if (first == head.get() && next != null) {
                OpDesc newDesc = new
                        // cf. Figure 5d
                        OpDesc(state.get(tid).phase, false, false,
                        state.get(tid).node);
                state.compareAndSet(tid, curDesc, newDesc);
                head.compareAndSet(first, next);
                // cf. Figure 5e
            }
        }
    }
}