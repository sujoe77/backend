package com.zs.rule.tree.action;

import com.zs.rule.entity.doc.Order;

import java.util.List;

public class MultipleActions implements Action {
    private final List<Action> actions;

    public MultipleActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void handleOrder(Order order) {
        for (Action action : actions) {
            action.handleOrder(order);
        }
    }
}
