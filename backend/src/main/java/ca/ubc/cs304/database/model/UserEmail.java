package ca.ubc.cs304.database.model;

public final class UserEmail {
    private String email;

    public UserEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
