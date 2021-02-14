package com.zs.rule.tree.decision;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Video;
import com.zs.rule.tree.Node;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProductNameDecisionTest {

    @Test
    public void testPredicate() {
        Node node = order -> {
        };
        ProductNameDecision decision = new ProductNameDecision(node, node, "Learn to run");
        ProductOrder otherVideo = new ProductOrder(1, new Video(new MediaAgent("2nd video agent"), "Learn to run"),
                new LocalCustomer("222@gmail.com"));
        assertTrue(decision.predicate(otherVideo));
    }
}