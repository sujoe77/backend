package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.tree.Node;

public abstract class DecisionNode implements Decision {
    private final Node trueNode;
    private final Node falseNode;

    public DecisionNode(Node trueNode, Node falseNode) {
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    @Override
    public Node getTrueNode() {
        return trueNode;
    }

    @Override
    public Node getFalseNode() {
        return falseNode;
    }

    @Override
    public void handleOrder(Order order) {
        if (predicate(order)) {
            trueNode.handleOrder(order);
        } else {
            falseNode.handleOrder(order);
        }
    }
}
