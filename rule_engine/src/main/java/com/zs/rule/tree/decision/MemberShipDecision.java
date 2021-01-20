package com.zs.rule.tree.decision;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.product.MemberShip;
import com.zs.rule.tree.Node;

import static com.zs.rule.tree.action.MemberShipAction.ACTIVATE;

public class MemberShipDecision extends DecisionNode implements Decision {

    public MemberShipDecision(Node trueNode, Node falseNode) {
        super(trueNode, falseNode);
    }

    @Override
    public boolean predicate(Order order) {
        if (!(order.getProduct() instanceof MemberShip)) {
            throw new IllegalArgumentException("Expect Membership but found: " + order.getProduct().getClass().getName());
        }
        MemberShip memberShip = (MemberShip) order.getProduct();
        return memberShip.getMemberShipAction().equals(ACTIVATE);
    }
}
