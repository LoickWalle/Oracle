package org.example.exercice3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long customer_id;
    private int interaction_number;
    private LocalDate date;

    public Interaction() {
    }

    public Interaction(long customerId, int interactionNumber, LocalDate date) {
        this.customer_id = customerId;
        this.interaction_number = interactionNumber;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customerId) {
        this.customer_id = customerId;
    }

    public int getInteraction_number() {
        return interaction_number;
    }

    public void setInteraction_number(int interactionNumber) {
        this.interaction_number = interactionNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
