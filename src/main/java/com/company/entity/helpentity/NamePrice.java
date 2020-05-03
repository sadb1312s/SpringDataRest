package com.company.entity.helpentity;

import lombok.Data;

@Data
public class NamePrice {
    private String title;
    private double price;

    public NamePrice(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
