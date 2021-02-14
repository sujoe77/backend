package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.product.ProductType;
import com.zs.rule.tree.Node;

import java.io.Serializable;

public class PhysicalProductDecision extends DecisionNode implements Decision, Serializable {

    public PhysicalProductDecision(Node trueNode, Node falseNode) {
        super(trueNode, falseNode);
    }

    @Override
    public boolean predicate(Order order) {
        return order.getProduct().getProductType() == ProductType.PHYSICAL;
    }
}
