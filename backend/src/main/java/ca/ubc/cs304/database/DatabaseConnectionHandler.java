package ca.ubc.cs304.database;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnectionHandler {
    private final Connection connection;

    public DatabaseConnectionHandler(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
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
