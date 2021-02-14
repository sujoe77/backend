package com.zs.rule.tree.action;

import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.product.MemberShip;

public class MemberShipAction implements Action {
    public static final String UPDATE = "UPDATE";
    public static final String ACTIVATE = "ACTIVATE";

    private final String action;

    public MemberShipAction(String action) {
        this.action = action;
    }

    @Override
    public void handleOrder(Order order) {
        MemberShip memberShip = (MemberShip) order.getProduct();
        if (UPDATE.equals(action)) {
            memberShip.update();
        } else {
            memberShip.activate();
        }
        order.getCustomer().addMemberShip(memberShip);
        order.addLog("membership " + action);
    }
}
