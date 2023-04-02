package ca.ubc.cs304.database.dao;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseConnectionHandler {
    private final Connection connection;

    public DatabaseConnectionHandler(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void exampleQuery() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
        while (rs.next()) {
            String firstName = rs.getString("AU_FNAME");
            String lastName = rs.getString("AU_LNAME");
            System.out.println(firstName + " " + lastName);
        }
    }
}
