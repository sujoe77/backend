package com.zs.rule;

import com.zs.rule.entity.doc.Order;

public interface RuleEngine {
    void handleOrder(Order order);
}
