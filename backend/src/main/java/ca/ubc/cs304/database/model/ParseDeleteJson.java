package ca.ubc.cs304.database.model;

public final class ParseDeleteJson {
    private int id;

    public ParseDeleteJson () {

    }
    public ParseDeleteJson (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
