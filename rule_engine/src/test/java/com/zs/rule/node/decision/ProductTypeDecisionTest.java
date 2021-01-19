package com.zs.rule.node.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.entity.product.Video;
import com.zs.rule.node.Node;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductTypeDecisionTest {

    @Test
    public void testPredicate() {
        Node node  = order -> {
        };
        ProductTypeDecision decision = new ProductTypeDecision(node, node, Book.class.getName());
        ProductOrder bookOrder = new ProductOrder(new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        ProductOrder learnToSkiOrder = new ProductOrder(new Video(new MediaAgent("ski video agent"), "Learn to ski"),
                new LocalCustomer("111@gmail.com"));
        assertTrue(decision.predicate(bookOrder));
        assertFalse(decision.predicate(learnToSkiOrder));
    }
}