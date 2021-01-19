package com.zs.rule.node.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.zs.rule.node.action.MemberShipAction.ACTIVATE;
import static com.zs.rule.node.action.MemberShipAction.UPDATE;
import static java.util.Arrays.asList;
import static org.testng.Assert.*;

public class MultipleActionsTest {

    @Test
    public void testHandleOrder() {
        MultipleActions activateMemberShipAction = new MultipleActions(asList(
                new MemberShipAction(ACTIVATE),
                new EmailAction()
        ));
        MemberShip club_member = new MemberShip("club member", ACTIVATE);
        ProductOrder memberShipActivate = new ProductOrder(club_member,
                new LocalCustomer("memberA@gmail.com"));
        activateMemberShipAction.handleOrder(memberShipActivate);
        Collection<MemberShip> memberShips = memberShipActivate.getCustomer().getMemberShips();
        assertTrue(memberShips.contains(club_member));
        assertEquals(memberShips.iterator().next().getState(), ACTIVATE);
        assertTrue(memberShipActivate.getLogs().contains("membership ACTIVATE"));

        assertTrue(memberShipActivate.getLogs().contains("Send Email: memberA@gmail.com"));
    }
}