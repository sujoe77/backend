package com.zs.rule.entity.doc;

import com.zs.rule.entity.org.Department;

public class PackagingSlip implements Slip {
    private final Department department;

    public PackagingSlip(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }
}
