package com.zs.prime.math;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.primitives.Ints.asList;

public class PrimeIterator implements Iterator<Integer> {
    private final int upperLimitN;
    private int index = 0;
    private final List<Integer> queue = new LinkedList() {{
        addAll(asList(new int[]{1, 2, 3, 5, 7}));
    }};

    public PrimeIterator(int upperLimitN) {
        this.upperLimitN = upperLimitN;
    }

    @Override
    public boolean hasNext() {
        int lastPrimeInQueue = queue.get(queue.size() - 1);
        if (index < queue.size() && queue.get(index) <= upperLimitN) {
            return true;
        }
        if (lastPrimeInQueue > upperLimitN) {
            return false;
        }
        int next = lastPrimeInQueue + 1;
        for (int i = 1; i < (queue.size() - 1) && Math.pow(queue.get(i), 2) <= next && next <= upperLimitN; i++) {
            if (next % queue.get(i) == 0) {
                next++;
                i = 0;
            }
        }
        if (next > upperLimitN) {
            return false;
        }
        queue.add(next);
        return true;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return queue.get(index++);
        }
        return -1;
    }
}
