package com.zs.rule;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.FunctionalDepartment;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import com.zs.rule.entity.product.MemberShip;
import com.zs.rule.entity.product.Video;
import com.zs.rule.tree.Node;
import com.zs.rule.tree.action.*;
import com.zs.rule.tree.decision.MemberShipDecision;
import com.zs.rule.tree.decision.PhysicalProductDecision;
import com.zs.rule.tree.decision.ProductNameDecision;
import com.zs.rule.tree.decision.ProductTypeDecision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zs.rule.tree.action.MemberShipAction.ACTIVATE;
import static com.zs.rule.tree.action.MemberShipAction.UPDATE;
import static java.util.Arrays.asList;

public class TreeRuleEngine implements RuleEngine {
    private final Node tree;
    //ensure exactly-once
    private final Map<Long, String> processedOrders = new HashMap<>();

    public static void main(String[] args) {
        PhysicalProductDecision tree = initTree();
        TreeRuleEngine engine = new TreeRuleEngine(tree);
        for (ProductOrder order : getOrders()) {
            engine.handleOrder(order);
        }
    }

    public TreeRuleEngine(Node tree) {
        this.tree = tree;
    }

    private static List<ProductOrder> getOrders() {
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

        return asList(
                bookOrder, learnToSkiOrder, otherVideo, memberShipActivate, memberShipUpdate
        );
    }

    public static PhysicalProductDecision initTree() {
        MultipleActions bookAction = new MultipleActions(asList(
                new SlipAction(new FunctionalDepartment("Shipping")),
                new SlipAction(new FunctionalDepartment("Royalty")),
                new CommissionPaymentAction()
        ));
        MultipleActions learnToSkiAction = new MultipleActions(asList(
                new SlipAction(new FunctionalDepartment("Shipping")),
                new SlipAction(new FunctionalDepartment("Shipping"), "First Aids"),
                new CommissionPaymentAction()
        ));
        MultipleActions otherVideoAction = new MultipleActions(asList(
                new SlipAction(new FunctionalDepartment("Shipping")),
                new CommissionPaymentAction()
        ));
        MultipleActions activateMemberShipAction = new MultipleActions(asList(
                new MemberShipAction(ACTIVATE),
                new EmailAction()
        ));
        MultipleActions updateMemberShipAction = new MultipleActions(asList(
                new MemberShipAction(ACTIVATE),
                new MemberShipAction(UPDATE),
                new EmailAction()
        ));

        ProductNameDecision videoNameDecision = new ProductNameDecision(learnToSkiAction, otherVideoAction, "Learn to ski");
        ProductTypeDecision bookVideoDecision = new ProductTypeDecision(bookAction, videoNameDecision, Book.class.getName());
        MemberShipDecision memberShipActionDecision = new MemberShipDecision(activateMemberShipAction, updateMemberShipAction);
        return new PhysicalProductDecision(bookVideoDecision, memberShipActionDecision);
    }

    public void handleOrder(Order order) {
        if (processedOrders.containsKey(order.getId())) {
            throw new IllegalArgumentException(String.format("Order with Id: %d has been processed already!!", order.getId()));
        }
        System.out.println("processing order: " + order);
        tree.handleOrder(order);
        for (String log : order.getLogs()) {
            System.out.println(log);
        }
        System.out.println("---------------------------");
        processedOrders.put(order.getId(), order.getProduct().getName());
    }
}
