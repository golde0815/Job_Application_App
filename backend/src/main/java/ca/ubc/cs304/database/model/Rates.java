package ca.ubc.cs304.database.model;

public final class Rates {
    private int companyId;
    private int avgValues;
    public Rates(int companyId, int avgValues) {
        this.companyId = companyId;
        this.avgValues = avgValues;
    }
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAvgValues() {
        return avgValues;
    }

    public void setAvgValues(int avgValues) {
        this.avgValues = avgValues;
    }
}
