package ca.ubc.cs304.database.model;

public final class ParseUpdateJson {
    private String attribute;
    private int jobId;
    private String value;

    public ParseUpdateJson(String attribute, int jobId, String value) {
        this.attribute = attribute;
        this.jobId = jobId;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
