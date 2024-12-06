package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private LocalDate date;
    private String location;
    private List<ComplexPrestation> prestationList;

    public Event() {
    }

    public Event(String name, LocalDate date, String location, List<ComplexPrestation> prestationList) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.prestationList = prestationList;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public List<ComplexPrestation> getPrestationList() {
        return prestationList;
    }

    @Override
    public String toString() {
        return "Event[name : "+name+", date : " + date.toString() + ", location : "+ location+"]";
    }

    public static class Builder {
        private String name;
        private LocalDate date;
        private String location;
        private List<ComplexPrestation> prestationList = new ArrayList<>();

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder prestationList(List<ComplexPrestation> prestationList) {
            this.prestationList = prestationList;
            return this;
        }

        public Event build(){
            return new Event(this.name, this.date, this.location, this.prestationList);
        }
    }
}
