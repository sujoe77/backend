package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.tree.Node;

public interface Decision extends Node {
    boolean predicate(Order order);
    Node getTrueNode();
    Node getFalseNode();
}
