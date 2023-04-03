package ca.ubc.cs304.database.dao;

import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.ParseDeleteJson;
import ca.ubc.cs304.database.model.TopCompany;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class CompanyDao {
    private final Connection connection;
    public CompanyDao(Connection connection) {
        this.connection = connection;
    }

    public void deleteCompany(ParseDeleteJson parseDeleteJson) {
        try {
            System.out.println(parseDeleteJson);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM COMPANY WHERE company_id = ?");
            ps.setInt(1, parseDeleteJson.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Delete Company failed: " + e.getMessage());
        }
    }
    public Company[] selectCompanyPostedJob(LocalDate postedDate) {
        ArrayList<Company> result = new ArrayList<>();
        try {
            String query = "SELECT C.NAME, P.POSTED_DATE FROM POSTED_JOB P JOIN COMPANY C on P.COMPANY_ID = C.COMPANY_ID AND P.POSTED_DATE > ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(postedDate));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Company model = new Company(rs.getString("name"),
                        rs.getDate("posted_date").toLocalDate());
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Select company failed: " + e.getMessage());
        }
        return result.toArray(new Company[result.size()]);
    }

    public TopCompany[] topRatedCompanies() {
        ArrayList<TopCompany> result = new ArrayList<>();
        try {
            String query = "SELECT R.COMPANY_ID, AVG(VALUE), AVG(SALARY) FROM RATES R JOIN POSTED_JOB P ON R.COMPANY_ID = P.COMPANY_ID GROUP BY R.COMPANY_ID HAVING AVG(VALUE) > (SELECT AVG(VALUE) FROM RATES R) AND AVG(SALARY) > (SELECT AVG(SALARY) FROM POSTED_JOB)";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                TopCompany model = new TopCompany(rs.getInt("company_id"),
                        rs.getInt("avg(value)"),
                        rs.getInt("avg(salary)"));
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Select company failed: " + e.getMessage());
        }
        return result.toArray(new TopCompany[result.size()]);
    }
}
