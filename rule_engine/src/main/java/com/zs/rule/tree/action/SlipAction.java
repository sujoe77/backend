package com.zs.rule.tree.action;

import com.zs.rule.entity.org.Department;
import com.zs.rule.entity.doc.Order;
import com.zs.rule.entity.doc.PackagingSlip;

public class SlipAction implements Action {
    private final Department department;
    private final String note;

    public SlipAction(Department department) {
        this.department = department;
        this.note = "";
    }

    public SlipAction(Department department, String note) {
        this.department = department;
        this.note = note;
    }

    @Override
    public void handleOrder(Order order) {
        order.addSlip(new PackagingSlip(department));
        order.addLog("add slip: " + department.getName() + ", " + note);
    }
}
