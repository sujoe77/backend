package com.zs.rule.entity.product;

import static com.zs.rule.tree.action.MemberShipAction.ACTIVATE;
import static com.zs.rule.tree.action.MemberShipAction.UPDATE;

public class MemberShip extends VirtualProduct {
    private final String name;
    private final String memberShipAction;
    private String state;

    public MemberShip(String name, String memberShipAction) {
        this.name = name;
        this.memberShipAction = memberShipAction;
    }

    public void activate() {
        state = ACTIVATE;
    }

    public void update() {
        state = UPDATE;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getMemberShipAction() {
        return memberShipAction;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "MemberShip{" +
                "name='" + name + '\'' +
                ", memberShipAction='" + memberShipAction + '\'' +
                '}';
    }
}
