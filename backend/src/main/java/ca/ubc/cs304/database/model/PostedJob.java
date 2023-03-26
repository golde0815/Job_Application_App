package ca.ubc.cs304.database.model;

import java.time.LocalDate;

public final class PostedJob {
    private int jobId;
    private int companyId;
    private LocalDate postedDate;
    private String location;
    private String description;
    private int salary;
    private String recruiterEmail;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRecruiterEmail() {
        return recruiterEmail;
    }

    public void setRecruiterEmail(String recruiterEmail) {
        this.recruiterEmail = recruiterEmail;
    }

    @Override
    public String toString() {
        return "PostedJob{" +
                "jobId=" + jobId +
                ", companyId=" + companyId +
                ", postedDate=" + postedDate +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", recruiterEmail='" + recruiterEmail + '\'' +
                '}';
    }
}
