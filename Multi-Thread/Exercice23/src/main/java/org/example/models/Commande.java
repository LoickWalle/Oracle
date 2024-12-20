package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commande {
    private UUID uuid;
    private List<Article> orderList = new ArrayList<>();

    public Commande(List<Article> orderList) {
        this.uuid = UUID.randomUUID();
        this.orderList = orderList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Article> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Article> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "uuid=" + uuid +
                ", orderList=" + orderList +
                '}';
    }
}
