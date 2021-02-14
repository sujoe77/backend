package com.zs.rule.tree.action;

import com.zs.rule.entity.LocalCustomer;
import com.zs.rule.entity.doc.ProductOrder;
import com.zs.rule.entity.org.FunctionalDepartment;
import com.zs.rule.entity.org.MediaAgent;
import com.zs.rule.entity.product.Book;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SlipActionTest {

    @Test
    public void testHandleOrder() {
        SlipAction action = new SlipAction(new FunctionalDepartment("Packing"), "note");
        ProductOrder bookOrder = new ProductOrder(1, new Book(new MediaAgent("Agent1"), "Book1"),
                new LocalCustomer("abc@gmail.com"));
        action.handleOrder(bookOrder);
        assertTrue(!bookOrder.getPackagingSlips().isEmpty());
        assertEquals(bookOrder.getPackagingSlips().iterator().next().getDepartment().getName(), "Packing");
    }
}