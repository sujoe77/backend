package com.zs.rule.node.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.zs.rule.node.action.MemberShipAction.UPDATE;
import static org.testng.Assert.*;

public class MemberShipActionTest {

    @Test
    public void testHandleOrder() {
        MemberShipAction action = new MemberShipAction(MemberShipAction.UPDATE);
        MemberShip club_member = new MemberShip("club member", UPDATE);
        ProductOrder memberShipUpdate = new ProductOrder(club_member,
                new LocalCustomer("memberU@gmail.com"));
        action.handleOrder(memberShipUpdate);
        Collection<MemberShip> memberShips = memberShipUpdate.getCustomer().getMemberShips();
        assertTrue(memberShips.contains(club_member));
        assertEquals(memberShips.iterator().next().getState(), UPDATE);
        assertTrue(memberShipUpdate.getLogs().contains("membership UPDATE"));
    }
}