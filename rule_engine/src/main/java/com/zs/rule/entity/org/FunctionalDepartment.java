package com.zs.rule.entity.org;

public class FunctionalDepartment implements Department {
    private final String name;

    public FunctionalDepartment(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
