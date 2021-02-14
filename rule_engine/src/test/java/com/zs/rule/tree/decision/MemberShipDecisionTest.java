package com.zs.rule.tree.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import org.testng.annotations.Test;

import static com.zs.rule.tree.action.MemberShipAction.ACTIVATE;
import static org.testng.Assert.*;

public class MemberShipDecisionTest {

    @Test
    public void testPredicate() {
        final int[] a = {0};
        MemberShipDecision node = new MemberShipDecision(order -> a[0] = 1, order -> a[0] = 2);
        ProductOrder memberShipActivate = new ProductOrder(1, new MemberShip("club member", ACTIVATE),
                new LocalCustomer("memberA@gmail.com"));
        assertTrue(node.predicate(memberShipActivate));
    }
}