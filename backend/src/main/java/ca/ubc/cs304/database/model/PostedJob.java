package ca.ubc.cs304.database.model;

import java.time.LocalDate;
import java.util.Optional;

public record PostedJob(
        int jobId,
        int companyId,
        LocalDate postedDate,
        String position,
        String location,
        String description,
        int salary,
        String recruiterEmail,
        Optional<Integer> numApplicants
) {
    public PostedJob(int jobId, int companyId, LocalDate postedDate, String position, String location, String description, int salary, String recruiterEmail) {
        this(jobId, companyId, postedDate, position, location, description, salary, recruiterEmail, Optional.empty());
    }
}
