package com.zs.rule.tree.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import org.testng.annotations.Test;

import static com.zs.rule.tree.action.MemberShipAction.UPDATE;
import static org.testng.Assert.*;

public class EmailActionTest {

    @Test
    public void testHandleOrder() {
        EmailAction action = new EmailAction();
        ProductOrder memberShipUpdate = new ProductOrder(1, new MemberShip("club member", UPDATE),
                new LocalCustomer("memberU@gmail.com"));
        action.handleOrder(memberShipUpdate);
        assertTrue(memberShipUpdate.getLogs().contains("Send Email: memberU@gmail.com"));
    }
}