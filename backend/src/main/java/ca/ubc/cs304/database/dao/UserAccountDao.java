package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.UserAccount;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAccountDao {
    private final Connection connection;

    public UserAccountDao(Connection connection) {
        this.connection = connection;
    }

    public List<UserAccount> appliedToAllJobsFrom(int companyId) {
        String query = "SELECT * FROM USER_ACCOUNT U " +
                "WHERE NOT EXISTS " +
                "((SELECT JOB_ID FROM POSTED_JOB WHERE COMPANY_ID = ?) " +
                "MINUS " +
                "(SELECT JOB_ID FROM USER_APPLIES_TO UA WHERE UA.EMAIL = U.EMAIL)) " +
                "AND EXISTS (SELECT COMPANY_ID FROM POSTED_JOB WHERE COMPANY_ID = ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            List<UserAccount> result = new ArrayList<>();
            ps.setInt(1, companyId);
            ps.setInt(2, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserAccount model = new UserAccount(rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone_number"));
                result.add(model);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Count users failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }
}
