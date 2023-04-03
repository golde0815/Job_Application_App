package ca.ubc.cs304.database.model;

import java.time.LocalDate;

public final class PostedJob {
    private int jobId;
    private int companyId;
    private LocalDate postedDate;
    private String position;
    private String location;
    private String description;
    private int salary;
    private String recruiterEmail;

    public PostedJob(int jobId, int companyId, LocalDate postedDate, String position, String location, String description,
                     int salary, String recruiterEmail) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.postedDate = postedDate;
        this.position = position;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.recruiterEmail = recruiterEmail;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
