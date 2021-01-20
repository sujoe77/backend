package com.zs.rule.entity.doc;

import com.zs.rule.entity.Customer;
import com.zs.rule.entity.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductOrder implements Order {
    private final long id;
    private final Product product;
    private final List<Slip> packagingSlips = new ArrayList<>();
    private final Customer customer;
    private final List<String> logs = new ArrayList<>();

    public ProductOrder(long id, Product product, Customer customer) {
        this.id = id;
        this.product = product;
        this.customer = customer;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public Collection<Slip> getPackagingSlips() {
        return packagingSlips;
    }

    @Override
    public void addSlip(Slip slip) {
        packagingSlips.add(slip);
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void addLog(String log) {
        logs.add(log);
    }

    public List<String> getLogs() {
        return logs;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "product=" + product +
                ", packagingSlips=" + packagingSlips +
                ", customer=" + customer +
                '}';
    }
}
