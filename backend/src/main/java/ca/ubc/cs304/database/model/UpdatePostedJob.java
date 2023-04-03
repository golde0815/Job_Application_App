package ca.ubc.cs304.database.model;

import java.util.List;

public record UpdatePostedJob(List<toUpdateFields> toUpdate, int jobId) {
    public record toUpdateFields(String attribute, String value) {
    }
}
