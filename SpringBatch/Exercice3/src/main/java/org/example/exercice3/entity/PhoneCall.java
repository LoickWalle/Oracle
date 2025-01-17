package org.example.exercice3.entity;

public class PhoneCall {
    private long id;
    private long customer_id;
    private int duration;

    public PhoneCall() {
    }

    public PhoneCall(long customer_id, int duration) {
        this.customer_id = customer_id;
        this.duration = duration;
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

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
