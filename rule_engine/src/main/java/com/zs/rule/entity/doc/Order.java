package com.zs.rule.entity.doc;

import com.zs.rule.entity.Customer;
import com.zs.rule.entity.product.Product;

import java.util.Collection;
import java.util.List;

public interface Order {
    long getId();

    Product getProduct();

    Collection<Slip> getPackagingSlips();

    void addSlip(Slip Slip);

    Customer getCustomer();

    void addLog(String log);

    List<String> getLogs();
}
