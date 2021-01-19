package com.zs.rule.node.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.product.MemberShip;
import org.testng.annotations.Test;

import static com.zs.rule.node.action.MemberShipAction.UPDATE;
import static org.testng.Assert.*;

public class EmailActionTest {

    @Test
    public void testHandleOrder() {
        EmailAction action = new EmailAction();
        ProductOrder memberShipUpdate = new ProductOrder(new MemberShip("club member", UPDATE),
                new LocalCustomer("memberU@gmail.com"));
        action.handleOrder(memberShipUpdate);
        assertTrue(memberShipUpdate.getLogs().contains("Send Email: memberU@gmail.com"));
    }
}