package org.example.exercice3.entity;

public class Email {
    private long id;
    private long customer_id;
    private String subject;
    private String content;

    public Email() {
    }

    public Email(long idClient, String subject, String content) {
        this.customer_id = idClient;
        this.subject = subject;
        this.content = content;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
