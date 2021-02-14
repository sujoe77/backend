package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.tree.Node;

public class ProductTypeDecision extends DecisionNode implements Decision {
    private final String className;

    public ProductTypeDecision(Node trueNode, Node falseNode, String className) {
        super(trueNode, falseNode);
        this.className = className;
    }

    @Override
    public boolean predicate(Order order) {
        return order.getProduct().getClass().getName().equals(className);
    }
}
