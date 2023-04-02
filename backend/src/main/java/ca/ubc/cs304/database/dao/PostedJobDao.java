package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.ParseDeleteJson;
import ca.ubc.cs304.database.model.ParseUpdateJson;
import ca.ubc.cs304.database.model.PostedJob;
import ca.ubc.cs304.util.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.sql.Date;
import java.util.*;

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

    public PostedJob[] selectPostedJob() {
        ArrayList<PostedJob> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM POSTED_JOB");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PostedJob model = new PostedJob(rs.getInt("job_id"),
                                                rs.getInt("company_id"),
                                                rs.getDate("posted_date").toLocalDate(),
                                                rs.getString("location"),
                                                rs.getString("description"),
                                                rs.getInt("salary"),
                                                rs.getString("recruiter_email"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Select posted job failed: " + e.getMessage());
        }
        return result.toArray(new PostedJob[result.size()]);
    }

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

    public void updatePostedJob(ParseUpdateJson parseUpdateJson) {
        try {
            // System.out.println(jobId);
            PreparedStatement ps;
            switch (parseUpdateJson.getAttribute()) {
                case "companyId":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET company_id = ?  WHERE job_id = ?");
                    ps.setInt(1, Integer.parseInt(parseUpdateJson.getValue()));
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                case "postedDate":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET posted_date = ?  WHERE job_id = ?");
                    ps.setDate(1, Date.valueOf(parseUpdateJson.getValue()));
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                case "location":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET location = ?  WHERE job_id = ?");
                    ps.setString(1, parseUpdateJson.getValue());
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                case "description":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET description = ?  WHERE job_id = ?");
                    ps.setString(1, parseUpdateJson.getValue());
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                case "salary":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET salary = ?  WHERE job_id = ?");
                    ps.setInt(1, Integer.parseInt(parseUpdateJson.getValue()));
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                case "recruiterEmail":
                    ps = connection.prepareStatement("UPDATE POSTED_JOB SET recruiterEmail = ?  WHERE job_id = ?");
                    ps.setString(1, parseUpdateJson.getValue());
                    ps.setInt(2, parseUpdateJson.getJobId());
                    break;
                default:
                    return;
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Update posted job failed: " + e.getMessage());
        }
    }

    public void deletePostedJob(ParseDeleteJson parseDeleteJson) {
        try {
            System.out.println(parseDeleteJson);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM POSTED_JOB WHERE job_id = ?");
            ps.setInt(1, parseDeleteJson.getJobId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Delete posted job failed: " + e.getMessage());
        }
    }
}
