package ca.ubc.cs304.database.model;

public final class ParseDeleteJson {
    private int jobId;

    public ParseDeleteJson () {

    }
    public ParseDeleteJson (int jobId) {
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
