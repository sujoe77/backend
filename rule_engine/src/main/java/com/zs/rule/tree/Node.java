package com.zs.rule.tree;

import com.zs.rule.entity.doc.Order;

public interface Node {
    void handleOrder(Order order);
}
