package org.example.models;

import java.util.UUID;

public class Article {
    private UUID uuid;
    private String name;
    private double price;

    public Article(String name, double price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Article{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
