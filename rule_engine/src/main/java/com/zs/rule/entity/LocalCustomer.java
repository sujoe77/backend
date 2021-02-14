package com.zs.rule.entity;

import com.zs.rule.entity.product.MemberShip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocalCustomer implements Customer {
    private final String email;
    private final List<MemberShip> memberShips = new ArrayList<>();

    public LocalCustomer(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Collection<MemberShip> getMemberShips() {
        return memberShips;
    }

    @Override
    public void addMemberShip(MemberShip memberShip) {
        memberShips.add(memberShip);
    }

    @Override
    public String toString() {
        return "LocalCustomer{" +
                "email='" + email + '\'' +
                '}';
    }
}
