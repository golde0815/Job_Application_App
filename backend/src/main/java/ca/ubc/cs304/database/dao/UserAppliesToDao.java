package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.CompanyId;
import ca.ubc.cs304.database.model.UserAppliesTo;
import ca.ubc.cs304.database.model.UserEmail;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class UserAppliesToDao {
    private final Connection connection;
    public UserAppliesToDao(Connection connection) {
        this.connection = connection;
    }
    public UserAppliesTo[] CountAppliedUsers () {
        ArrayList<UserAppliesTo> result = new ArrayList<>();
        try {
            String query = "SELECT JOB_ID, COUNT(email) FROM USER_APPLIES_TO GROUP BY JOB_ID";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UserAppliesTo model = new UserAppliesTo(rs.getInt("job_id"),
                        rs.getInt("count(email)"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Count users failed: " + e.getMessage());
        }
        return result.toArray(new UserAppliesTo[result.size()]);
    }

    public UserEmail[] appliedToAllJobsFrom(CompanyId companyId) {
        ArrayList<UserEmail> result = new ArrayList<>();
        try {
            String query = "SELECT EMAIL FROM USER_ACCOUNT U WHERE "
            + "(NOT EXISTS (SELECT JOB_ID FROM POSTED_JOB P WHERE COMPANY_ID = ? AND "
            + "NOT EXISTS (SELECT JOB_ID FROM USER_APPLIES_TO UA WHERE UA.EMAIL = U.EMAIL AND UA.JOB_ID = P.JOB_ID)))"
            + "AND (EXISTS (SELECT COMPANY_ID FROM POSTED_JOB WHERE COMPANY_ID = ?))";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, companyId.getCompanyId());
            ps.setInt(2, companyId.getCompanyId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UserEmail model = new UserEmail(rs.getString("email"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Count users failed: " + e.getMessage());
        }
        return result.toArray(new UserEmail[result.size()]);
    }
}