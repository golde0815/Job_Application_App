package ca.ubc.cs304.database.model;

public class DeleteCompany {
    private int company_id;
    public DeleteCompany () {}

    public DeleteCompany(int company_id) {
        this.company_id = company_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
