package org.example.exercice14.entity;

import java.util.Objects;

public class Order {
    private int id;
    private String item;

    public Order() {
    }

    public Order(int id, String item) {
        this.id = id;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && item.equals(order.item);
    }

}
