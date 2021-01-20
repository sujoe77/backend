package com.zs.rule.tree.action;

import com.zs.rule.entity.doc.CommissionPayment;
import com.zs.rule.entity.org.Agent;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.product.PhysicalProduct;
import com.zs.rule.entity.product.Product;
import com.zs.rule.entity.product.ProductType;

public class CommissionPaymentAction implements Action {
    @Override
    public void handleOrder(Order order) {
        Product product = order.getProduct();
        if (product.getProductType() == ProductType.PHYSICAL) {
            Agent agent = ((PhysicalProduct) product).getAgent();
            generatePayment(agent);
            order.addLog("Commission payment to " + agent.getName());
        }
    }

    private void generatePayment(Agent agent) {
        agent.addCommissionPayment(new CommissionPayment());
    }
}
