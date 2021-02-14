package com.zs.rule.tree.action;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.tree.Node;

public interface Action extends Node {
    void handleOrder(Order order);
}
