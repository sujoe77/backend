package com.zs.rule.node.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import com.zs.rule.node.Node;
import org.testng.annotations.Test;

import static com.zs.rule.node.action.MemberShipAction.ACTIVATE;
import static org.testng.Assert.*;

public class MemberShipDecisionTest {

    @Test
    public void testPredicate() {
        final int[] a = {0};
        MemberShipDecision node = new MemberShipDecision(order -> a[0] = 1, order -> a[0] = 2);
        ProductOrder memberShipActivate = new ProductOrder(new MemberShip("club member", ACTIVATE),
                new LocalCustomer("memberA@gmail.com"));
        assertTrue(node.predicate(memberShipActivate));
    }
}