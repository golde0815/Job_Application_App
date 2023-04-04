package ca.ubc.cs304.database.migration;

import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MigrationHandler {
    private static final String CREATE_TABLES_SQL_FILE = "classpath:sql/createTables.sql";
    private static final String POPULATE_TABLES_SQL_FILE = "classpath:sql/populateTables.sql";
    private static final String CLEAR_TABLES_SQL_FILE = "classpath:sql/clearTables.sql";
    private static final String DROP_TABLES_SQL_FILE = "classpath:sql/dropTables.sql";

    private final Connection connection;
    private final ResourceLoader resourceLoader;

    public MigrationHandler(Connection connection, ResourceLoader resourceLoader) {
        this.connection = connection;
        this.resourceLoader = resourceLoader;
    }

    public List<String> getTables() {
        String query = "SELECT TABLE_NAME FROM USER_TABLES";
        try (Statement statement = connection.createStatement()) {
            List<String> result = new ArrayList<>();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result.add(rs.getString("TABLE_NAME"));
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            // handle exception
            System.err.println("Error executing SQL query: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<String> getTableColumns(String tableName) {
        String query = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            List<String> result = new ArrayList<>();
            ps.setString(1, tableName.toUpperCase());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("COLUMN_NAME"));
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            // handle exception
            System.err.println("Error executing SQL query: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<Map<String, String>> projectColumns(String tableName, List<String> columns) {
        String query = "SELECT " + String.join(", ", columns.stream().map(String::toUpperCase).toList()) +
                " FROM " + tableName.toUpperCase();
        try (Statement statement = connection.createStatement()) {
            List<Map<String, String>> result = new ArrayList<>();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (String column : columns) {
                    row.put(column, rs.getString(column));
                }
                result.add(row);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            // handle exception
            System.err.println("Error executing SQL query: " + e.getMessage());
            throw new GenericSQLException(e);
        }
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
