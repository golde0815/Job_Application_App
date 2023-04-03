package ca.ubc.cs304.database.model;

public class CreatePostedJob {
    private int companyId;
    private String position;
    private String location;
    private String description;
    private int salary;
    private String recruiterEmail;

    public CreatePostedJob(int companyId, String position, String location, String description, int salary, String recruiterEmail) {
        this.companyId = companyId;
        this.position = position;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.recruiterEmail = recruiterEmail;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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
        return "CreatePostedJob{" +
                "companyId=" + companyId +
                ", position='" + position + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", recruiterEmail='" + recruiterEmail + '\'' +
                '}';
    }
}
