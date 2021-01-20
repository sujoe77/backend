package com.zs.rule.entity.product;

public abstract class VirtualProduct implements Product {
    @Override
    public ProductType getProductType() {
        return ProductType.VIRTUAL;
    }
}
