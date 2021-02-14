package com.zs.rule.tree.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.tree.Node;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhysicalProductDecisionTest {

    @Test
    public void testPredicate() {
        Node node  = order -> {
        };
        PhysicalProductDecision decision = new PhysicalProductDecision(node, node);
        ProductOrder bookOrder = new ProductOrder(1, new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        assertTrue(decision.predicate(bookOrder));
    }
}