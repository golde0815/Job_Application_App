package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.PostedJob;
import ca.ubc.cs304.util.NumberUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class PostedJobDao {
    private final Connection connection;

    public PostedJobDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void createPostedJob(PostedJob postedJob) {
        postedJob.setJobId(NumberUtils.generateRandomNumber());
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO POSTED_JOB VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, postedJob.getJobId());
            ps.setInt(2, postedJob.getCompanyId());
            if (postedJob.getPostedDate() == null) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, java.sql.Date.valueOf(postedJob.getPostedDate()));
            }
            ps.setString(4, postedJob.getLocation());
            ps.setString(5, postedJob.getDescription());
            if (postedJob.getSalary() == 0) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, postedJob.getSalary());
            }
            if (postedJob.getRecruiterEmail() == null) {
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else {
                ps.setString(7, postedJob.getRecruiterEmail());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Create posted job failed: " + e.getMessage());
        }
    }
}
