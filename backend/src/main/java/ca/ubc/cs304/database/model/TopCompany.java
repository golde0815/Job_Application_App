package ca.ubc.cs304.database.model;

public final class TopCompany {
    private int companyId;
    private int avgRating;
    private int avgSalary;

    public TopCompany(int companyId, int avgRating, int avgSalary) {
        this.companyId = companyId;
        this.avgRating = avgRating;
        this.avgSalary = avgSalary;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(int avgSalary) {
        this.avgSalary = avgSalary;
    }
}
