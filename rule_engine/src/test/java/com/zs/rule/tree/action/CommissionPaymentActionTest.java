package com.zs.rule.tree.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.entity.product.PhysicalProduct;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommissionPaymentActionTest {

    @Test
    public void testHandleOrder() {
        CommissionPaymentAction action = new CommissionPaymentAction();
        ProductOrder bookOrder = new ProductOrder(1, new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        action.handleOrder(bookOrder);
        PhysicalProduct physicalProduct = (PhysicalProduct) bookOrder.getProduct();
        assertFalse(physicalProduct.getAgent().getPayments().isEmpty());
    }
}