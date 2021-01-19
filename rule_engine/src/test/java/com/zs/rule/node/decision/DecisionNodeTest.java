package com.zs.rule.node.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.node.Node;
import mockit.Mocked;
import mockit.Verifications;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DecisionNodeTest {

    @Test
    public void testHandleOrder() {
        final int[] a = {0};
        DecisionNode node = new ProductTypeDecision(order -> a[0] = 1, order -> a[0] = 2, Book.class.getName());
        ProductOrder bookOrder = new ProductOrder(new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        node.handleOrder(bookOrder);
        assertEquals(a[0], 1);
    }
}