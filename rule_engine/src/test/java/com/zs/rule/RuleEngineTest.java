package com.zs.rule;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.entity.product.MemberShip;
import com.zs.rule.entity.product.Video;
import com.zs.rule.node.Node;
import org.testng.annotations.Test;

import static com.zs.rule.node.action.MemberShipAction.ACTIVATE;
import static com.zs.rule.node.action.MemberShipAction.UPDATE;
import static java.util.Arrays.asList;
import static org.testng.Assert.*;

public class RuleEngineTest {
    @Test
    public void testTree() {
        Node root = RuleEngine.getTree();
        ProductOrder bookOrder = new ProductOrder(new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        ProductOrder learnToSkiOrder = new ProductOrder(new Video(new MediaAgent("ski video agent"), "Learn to ski"),
                new LocalCustomer("111@gmail.com"));
        ProductOrder otherVideo = new ProductOrder(new Video(new MediaAgent("2nd video agent"), "Learn to run"),
                new LocalCustomer("222@gmail.com"));
        ProductOrder memberShipActivate = new ProductOrder(new MemberShip("club member", ACTIVATE),
                new LocalCustomer("memberA@gmail.com"));
        ProductOrder memberShipUpdate = new ProductOrder(new MemberShip("club member", UPDATE),
                new LocalCustomer("memberU@gmail.com"));
        RuleEngine.handleOrder(root, bookOrder);
        assertEquals(bookOrder.getLogs().size(), 3);
        assertTrue(bookOrder.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "add slip: Royalty, ",
                        "Commission payment to Agent1")
        ));

        RuleEngine.handleOrder(root, learnToSkiOrder);
        assertEquals(learnToSkiOrder.getLogs().size(), 3);
        assertTrue(learnToSkiOrder.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "add slip: Shipping, First Aids",
                        "Commission payment to ski video agent")
        ));

        RuleEngine.handleOrder(root, otherVideo);
        assertEquals(otherVideo.getLogs().size(), 2);
        assertTrue(otherVideo.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "Commission payment to 2nd video agent")
        ));

        RuleEngine.handleOrder(root, memberShipActivate);
        assertEquals(memberShipActivate.getLogs().size(), 2);
        assertTrue(memberShipActivate.getLogs().containsAll(
                asList("membership ACTIVATE",
                        "Send Email: memberA@gmail.com")
        ));

        RuleEngine.handleOrder(root, memberShipUpdate);
        assertEquals(memberShipUpdate.getLogs().size(), 3);
        assertTrue(memberShipUpdate.getLogs().containsAll(
                asList("membership ACTIVATE",
                        "membership UPDATE",
                        "Send Email: memberU@gmail.com")
        ));
    }
}