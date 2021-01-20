package com.zs.rule.entity.org;

import com.zs.rule.entity.doc.Payment;

import java.util.List;

public interface Agent {
    String getName();
    void addCommissionPayment(Payment payment);
    List<Payment> getPayments();
}
