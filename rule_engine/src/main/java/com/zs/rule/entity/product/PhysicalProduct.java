package com.zs.rule.entity.product;

import com.zs.rule.entity.org.Agent;

public abstract class PhysicalProduct implements Product {
    private final Agent agent;

    protected PhysicalProduct(Agent agent) {
        this.agent = agent;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.PHYSICAL;
    }

    public Agent getAgent() {
        return agent;
    }
}
