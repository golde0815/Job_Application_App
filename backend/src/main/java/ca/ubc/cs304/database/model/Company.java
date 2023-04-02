package ca.ubc.cs304.database.model;

import java.time.LocalDate;

public final class Company {

    private String name;

    private LocalDate postedDate = null;


    public Company(String name, LocalDate postedDate) {
        this.name = name;
        this.postedDate = postedDate;
    }

    public Company(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public Company() {}

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }
}
