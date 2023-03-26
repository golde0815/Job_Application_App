package ca.ubc.cs304.database;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MigrationHandler {
    private static final String CREATE_TABLES_SQL_FILE = "classpath:sql/createTables.sql";
    private static final String POPULATE_TABLES_SQL_FILE = "classpath:sql/populateTables.sql";
    private static final String CLEAR_TABLES_SQL_FILE = "classpath:sql/clearTables.sql";
    private static final String DROP_TABLES_SQL_FILE = "classpath:sql/dropTables.sql";

    private final Connection connection;
    private final ResourceLoader resourceLoader;

    public MigrationHandler(DataSource dataSource, ResourceLoader resourceLoader) throws SQLException {
        this.connection = dataSource.getConnection();
        this.resourceLoader = resourceLoader;
    }

    public void createAndPopulateTables() {
        executeSqlFile(CREATE_TABLES_SQL_FILE);
        executeSqlFile(POPULATE_TABLES_SQL_FILE);
    }

    public void clearTables() {
        executeSqlFile(CLEAR_TABLES_SQL_FILE);
    }

    public void dropTables() {
        executeSqlFile(DROP_TABLES_SQL_FILE);
    }

    private void executeSqlFile(String filePath) {
        Resource resource = resourceLoader.getResource(filePath);
        if (!resource.exists()) {
            System.err.println("Error opening SQL file: " + filePath + " file not found");
            return;
        }

        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            StringBuilder queryBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // If the line is not a comment and is not empty, append it to the query
                if (!line.startsWith("--") && !line.isEmpty()) {
                    queryBuilder.append(line);
                    if (line.endsWith(";")) {
                        queryBuilder.deleteCharAt(queryBuilder.length() - 1);
                        String query = queryBuilder.toString();
                        try (Statement statement = connection.createStatement()) {
                            statement.executeUpdate(query);
                        } catch (SQLException e) {
                            // handle exception
                            System.err.println("Error executing SQL query: " + e.getMessage());
                        }
                        queryBuilder = new StringBuilder();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from SQL file: " + e.getMessage());
        }
    }
}
