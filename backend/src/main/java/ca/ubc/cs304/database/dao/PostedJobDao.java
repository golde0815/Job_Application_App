package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.PostedJob;
import ca.ubc.cs304.database.model.UpdatePostedJob;
import ca.ubc.cs304.exception.GenericSQLException;
import ca.ubc.cs304.util.Utils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class PostedJobDao {
    private final Connection connection;

    public PostedJobDao(Connection connection) {
        this.connection = connection;
    }

    public void createPostedJob(PostedJob postedJob) {
        postedJob.setJobId(Utils.generateRandomNumber());
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO POSTED_JOB VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, postedJob.getJobId());
            ps.setInt(2, postedJob.getCompanyId());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setString(4, postedJob.getPosition());
            ps.setString(5, postedJob.getLocation());
            ps.setString(6, postedJob.getDescription());
            if (postedJob.getSalary() == 0) {
                ps.setNull(7, java.sql.Types.INTEGER);
            } else {
                ps.setInt(7, postedJob.getSalary());
            }
            if (postedJob.getRecruiterEmail() == null) {
                ps.setNull(8, java.sql.Types.VARCHAR);
            } else {
                ps.setString(8, postedJob.getRecruiterEmail());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Create posted job failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<PostedJob> selectPostedJob(String attribute, String value) {
        String query = "SELECT * FROM POSTED_JOB WHERE " + attribute + " = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ArrayList<PostedJob> result = new ArrayList<>();
            setPreparedStatementParameter(ps, 1, attribute, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PostedJob model = new PostedJob(rs.getInt("job_id"),
                        rs.getInt("company_id"),
                        rs.getDate("posted_date").toLocalDate(),
                        rs.getString("position"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getInt("salary"),
                        rs.getString("recruiter_email"));
                result.add(model);
            }
            rs.close();
            return result;
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Select posted job failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    // TODO: update this
    public Map<String, Object>[] projectPostedJob(String column) {
        Set<Map<String, Object>> result = new HashSet<>();
        try {
            String query = "SELECT " + column + " FROM POSTED_JOB";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> model = new HashMap<>();
                Object value = rs.getObject(column);
                model.put(column, value);
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("project posted job failed: " + e.getMessage());
        }
        return result.toArray(new Map[result.size()]);
    }

    public void updatePostedJob(UpdatePostedJob updatePostedJob) {
        StringBuilder sql = new StringBuilder("UPDATE POSTED_JOB SET ");
        for (UpdatePostedJob.toUpdateFields field : updatePostedJob.toUpdate()) {
            if (field.attribute().equals("job_id")) {
                throw new IllegalArgumentException("Cannot update job_id");
            }
            sql.append(field.attribute()).append(" = ?, ");
        }
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE job_id = ?");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            int index = 1;
            for (UpdatePostedJob.toUpdateFields field : updatePostedJob.toUpdate()) {
                setPreparedStatementParameter(ps, index, field.attribute(), field.value());
                index++;
            }
            ps.setInt(index, updatePostedJob.jobId());
            ps.executeUpdate();
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Update posted job failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    private static void setPreparedStatementParameter(PreparedStatement ps, int index, String attribute, String value)
            throws SQLException {
        switch (attribute) {
            case "job_id", "company_id" -> {
                Integer parsedValue = Utils.parseInt(value);
                if (parsedValue == null) {
                    throw new IllegalArgumentException("Invalid value for " + attribute + ": " + value);
                }
                ps.setInt(index, parsedValue);
            }
            case "salary" -> {
                Integer parsedValue = Utils.parseInt(value);
                if (parsedValue == null) {
                    ps.setNull(index, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(index, parsedValue);
                }
            }
            case "posted_date" -> {
                LocalDate parsedValue = Utils.parseDate(value);
                if (parsedValue == null) {
                    throw new IllegalArgumentException("Invalid value for " + attribute + ": " + value);
                }
                ps.setDate(index, Date.valueOf(parsedValue));
            }
            case "position", "location", "description" -> {
                Utils.validateNotBlank(value, attribute);
                ps.setString(index, value);
            }
            case "recruiter_email" -> {
                try {
                    Utils.validateNotBlank(value, attribute);
                    ps.setString(index++, value);
                } catch (IllegalArgumentException e) {
                    ps.setNull(index, java.sql.Types.VARCHAR);
                }
            }
            default -> throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }
}
