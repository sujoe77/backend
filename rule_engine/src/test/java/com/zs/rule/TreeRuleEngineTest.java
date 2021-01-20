package com.zs.rule;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.entity.product.MemberShip;
import com.zs.rule.entity.product.Video;
import com.zs.rule.tree.Node;
import org.testng.annotations.Test;

import static com.zs.rule.tree.action.MemberShipAction.ACTIVATE;
import static com.zs.rule.tree.action.MemberShipAction.UPDATE;
import static java.util.Arrays.asList;
import static org.testng.Assert.*;

public class TreeRuleEngineTest {
    @Test
    public void testTree() {
        Node root = TreeRuleEngine.initTree();
        ProductOrder bookOrder = new ProductOrder(1, new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        ProductOrder learnToSkiOrder = new ProductOrder(2, new Video(new MediaAgent("ski video agent"), "Learn to ski"),
                new LocalCustomer("111@gmail.com"));
        ProductOrder otherVideo = new ProductOrder(3, new Video(new MediaAgent("2nd video agent"), "Learn to run"),
                new LocalCustomer("222@gmail.com"));
        ProductOrder memberShipActivate = new ProductOrder(4, new MemberShip("club member", ACTIVATE),
                new LocalCustomer("memberA@gmail.com"));
        ProductOrder memberShipUpdate = new ProductOrder(5, new MemberShip("club member", UPDATE),
                new LocalCustomer("memberU@gmail.com"));
        TreeRuleEngine treeRuleEngine = new TreeRuleEngine(root);
        treeRuleEngine.handleOrder(bookOrder);
        assertEquals(bookOrder.getLogs().size(), 3);
        assertTrue(bookOrder.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "add slip: Royalty, ",
                        "Commission payment to Agent1")
        ));

        treeRuleEngine.handleOrder(learnToSkiOrder);
        assertEquals(learnToSkiOrder.getLogs().size(), 3);
        assertTrue(learnToSkiOrder.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "add slip: Shipping, First Aids",
                        "Commission payment to ski video agent")
        ));

        treeRuleEngine.handleOrder(otherVideo);
        assertEquals(otherVideo.getLogs().size(), 2);
        assertTrue(otherVideo.getLogs().containsAll(
                asList("add slip: Shipping, ",
                        "Commission payment to 2nd video agent")
        ));

        treeRuleEngine.handleOrder(memberShipActivate);
        assertEquals(memberShipActivate.getLogs().size(), 2);
        assertTrue(memberShipActivate.getLogs().containsAll(
                asList("membership ACTIVATE",
                        "Send Email: memberA@gmail.com")
        ));

        treeRuleEngine.handleOrder(memberShipUpdate);
        assertEquals(memberShipUpdate.getLogs().size(), 3);
        assertTrue(memberShipUpdate.getLogs().containsAll(
                asList("membership ACTIVATE",
                        "membership UPDATE",
                        "Send Email: memberU@gmail.com")
        ));
    }
}