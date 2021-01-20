package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.tree.Node;
import org.apache.commons.lang3.StringUtils;

public class ProductNameDecision extends DecisionNode implements Decision {
    private final String name;

    public ProductNameDecision(Node trueNode, Node falseNode, String name) {
        super(trueNode, falseNode);
        this.name = name;
    }

    @Override
    public boolean predicate(Order order) {
        return StringUtils.equals(order.getProduct().getName(), name);
    }
}
