package com.zs.rule.entity.product;

import com.zs.rule.entity.org.Agent;

public class Book extends PhysicalProduct implements Product {
    private final String bookName;

    public Book(Agent agent, String bookName) {
        super(agent);
        this.bookName = bookName;
    }

    @Override
    public String getName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                '}';
    }
}
