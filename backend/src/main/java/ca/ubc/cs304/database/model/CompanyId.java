package ca.ubc.cs304.database.model;

public final class CompanyId {
    private int companyId;

    public CompanyId(int companyId) {
        this.companyId = companyId;
    }

    public CompanyId() {}
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
