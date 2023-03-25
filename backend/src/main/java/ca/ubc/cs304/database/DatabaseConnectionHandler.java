package ca.ubc.cs304.database;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnectionHandler {
    private final DataSource dataSource;

    public DatabaseConnectionHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void exampleQuery() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
            while (rs.next()) {
                String firstName = rs.getString("AU_FNAME");
                String lastName = rs.getString("AU_LNAME");
                System.out.println(firstName + " " + lastName);
            }
        }
    }
}
