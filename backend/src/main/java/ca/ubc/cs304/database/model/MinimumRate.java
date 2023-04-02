package ca.ubc.cs304.database.model;

public final class MinimumRate {
    private int minRate;

    public MinimumRate(int minRate) {
        this.minRate = minRate;
    }

    public MinimumRate(){}

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }
}
