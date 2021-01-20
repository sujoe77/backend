package com.zs.rule.entity;

import com.zs.rule.entity.product.MemberShip;

import java.util.Collection;

public interface Customer {
    String getEmail();

    Collection<MemberShip> getMemberShips();

    void addMemberShip(MemberShip memberShip);
}
