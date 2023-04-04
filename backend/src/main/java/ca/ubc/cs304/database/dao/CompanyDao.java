package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.CompanyWithRating;
import ca.ubc.cs304.database.model.TopCompany;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyDao {
    private final Connection connection;

    public CompanyDao(Connection connection) {
        this.connection = connection;
    }

    public List<String> getAllLocations() {
        String query = "SELECT DISTINCT LOCATION FROM POSTED_JOB";
        try (Statement stmt = connection.createStatement()) {
            List<String> result = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.add(rs.getString("location"));
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Get all locations failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
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

    public List<CompanyWithRating> getAllCompanies() {
        String query = "SELECT DISTINCT C.COMPANY_ID, NAME, AVG_VALUE FROM " +
                "(SELECT COMPANY_ID, AVG(VALUE) AS AVG_VALUE " +
                "FROM Rates " +
                "GROUP BY COMPANY_ID) T " +
                "RIGHT OUTER JOIN COMPANY C ON T.COMPANY_ID = C.COMPANY_ID";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<CompanyWithRating> result = buildCompanyWithRatingList(rs);
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select company failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<CompanyWithRating> selectCompanyPostedAfter(LocalDate postedAfter) {
        String query = "SELECT DISTINCT C.COMPANY_ID, NAME, AVG_VALUE FROM " +
                "(SELECT COMPANY_ID, AVG(VALUE) AS AVG_VALUE " +
                "FROM Rates " +
                "GROUP BY COMPANY_ID) T " +
                "RIGHT OUTER JOIN COMPANY C ON T.COMPANY_ID = C.COMPANY_ID " +
                "INNER JOIN (SELECT COMPANY_ID FROM POSTED_JOB WHERE POSTED_DATE >= ?) P " +
                "ON C.COMPANY_ID = P.COMPANY_ID ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(postedAfter));
            ResultSet rs = ps.executeQuery();
            List<CompanyWithRating> result = buildCompanyWithRatingList(rs);
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select company failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<CompanyWithRating> companiesWithMinimumRating(int minimumRating) {
        String query = "SELECT C.COMPANY_ID, NAME, AVG_VALUE FROM " +
                "(SELECT COMPANY_ID, AVG(VALUE) AS AVG_VALUE " +
                "FROM Rates " +
                "GROUP BY COMPANY_ID " +
                "HAVING AVG(VALUE) >= ?) T INNER JOIN COMPANY C ON T.COMPANY_ID = C.COMPANY_ID";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, minimumRating);
            ResultSet rs = ps.executeQuery();
            List<CompanyWithRating> result = buildCompanyWithRatingList(rs);
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select company with minimum rating failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    public List<TopCompany> topRatedCompanies() {
        String query = "SELECT COMPANY_ID, NAME, AVG_VALUE, AVG_SALARY FROM " +
                "(SELECT R.COMPANY_ID AS COMPANY_ID, AVG(R.VALUE) AS AVG_VALUE, AVG(P.SALARY) AS AVG_SALARY " +
                "FROM RATES R, POSTED_JOB P " +
                "WHERE R.COMPANY_ID = P.COMPANY_ID " +
                "GROUP BY R.COMPANY_ID " +
                "HAVING AVG(R.VALUE) > (SELECT AVG(R2.VALUE) FROM RATES R2) " +
                "AND AVG(P.SALARY) > (SELECT AVG(P2.SALARY) FROM POSTED_JOB P2)) NATURAL JOIN COMPANY";
        try (Statement stmt = connection.createStatement()) {
            List<TopCompany> result = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TopCompany model = new TopCompany(rs.getInt("company_id"),
                        rs.getString("name"),
                        rs.getInt("avg_value"),
                        rs.getInt("avg_salary"));
                result.add(model);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("Select top companies failed: " + e.getMessage());
            throw new GenericSQLException(e);
        }
    }

    private static List<CompanyWithRating> buildCompanyWithRatingList(ResultSet rs) throws SQLException {
        List<CompanyWithRating> result = new ArrayList<>();
        while (rs.next()) {
            CompanyWithRating model = new CompanyWithRating(rs.getInt("company_id"),
                    rs.getString("name"),
                    rs.getInt("avg_value"));
            result.add(model);
        }
        return result;
    }
}
