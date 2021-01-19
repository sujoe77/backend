package com.zs.rule.node.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.node.Node;
import mockit.Mocked;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhysicalProductDecisionTest {

    @Test
    public void testPredicate() {
        Node node  = order -> {
        };
        PhysicalProductDecision decision = new PhysicalProductDecision(node, node);
        ProductOrder bookOrder = new ProductOrder(new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        assertTrue(decision.predicate(bookOrder));
    }
}