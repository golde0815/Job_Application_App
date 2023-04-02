package ca.ubc.cs304.database.model;

import java.time.LocalDate;

public final class UserAppliesTo {
    private int jobId;
    private int counter;
    public UserAppliesTo() {}

    public UserAppliesTo(int jobId, int counter) {
        this.jobId = jobId;
        this.counter = counter;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
