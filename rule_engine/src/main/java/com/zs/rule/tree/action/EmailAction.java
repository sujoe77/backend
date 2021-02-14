package com.zs.rule.tree.action;

import com.zs.rule.entity.doc.Order;

import java.io.Serializable;

public class EmailAction implements Action, Serializable {

    @Override
    public void handleOrder(Order order) {
        sendEmail(order);
    }

    public void sendEmail(Order order) {
        order.addLog("Send Email: " + order.getCustomer().getEmail());
    }
}
