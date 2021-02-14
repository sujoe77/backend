package com.zs.rule.entity.org;

import com.zs.rule.entity.doc.Payment;

import java.util.ArrayList;
import java.util.List;

public class MediaAgent implements Agent {
    private final String name;
    private List<Payment> payments = new ArrayList<>();

    public MediaAgent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addCommissionPayment(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getPayments() {
        return payments;
    }
}
