package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.TopCompany;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyDao {
    private final Connection connection;

    public CompanyDao(Connection connection) {
        this.connection = connection;
    }

    public void deleteCompany(int companyId) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM COMPANY WHERE COMPANY_ID = ?")) {
            ps.setInt(1, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Delete Company failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<Company> selectCompanyPostedJob(LocalDate postedDate) {
        String query = "SELECT C.NAME, P.POSTED_DATE FROM POSTED_JOB P JOIN COMPANY C on P.COMPANY_ID = C.COMPANY_ID AND P.POSTED_DATE >= ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            List<Company> result = new ArrayList<>();
            ps.setDate(1, Date.valueOf(postedDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Company model = new Company(rs.getString("name"),
                        rs.getDate("posted_date").toLocalDate());
                result.add(model);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select company failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<TopCompany> topRatedCompanies() {
        String query = "SELECT R.COMPANY_ID AS COMPANY_ID, AVG(R.VALUE) AS AVG_VALUE, AVG(P.SALARY) AS AVG_SALARY " +
                "FROM RATES R, POSTED_JOB P " +
                "WHERE R.COMPANY_ID = P.COMPANY_ID " +
                "GROUP BY R.COMPANY_ID " +
                "HAVING AVG(R.VALUE) > (SELECT AVG(R2.VALUE) FROM RATES R2) " +
                "AND AVG(P.SALARY) > (SELECT AVG(P2.SALARY) FROM POSTED_JOB P2)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            List<TopCompany> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TopCompany model = new TopCompany(rs.getInt("company_id"),
                        rs.getInt("avg_value"),
                        rs.getInt("avg_salary"));
                result.add(model);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select top company failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }
}
