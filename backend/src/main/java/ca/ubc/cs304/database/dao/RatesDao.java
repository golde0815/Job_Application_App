package ca.ubc.cs304.database.dao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.MinimumRate;
import ca.ubc.cs304.database.model.Rates;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
@Component
public class RatesDao {
    private final Connection connection;

    public RatesDao(Connection connection) {
        this.connection = connection;
    }

    public Rates[] companiesRates(MinimumRate rates) {
        ArrayList<Rates> result = new ArrayList<>();
        try {
            String query = "SELECT COMPANY_ID, AVG(VALUE) FROM Rates GROUP BY COMPANY_ID HAVING AVG(VALUE) > ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, rates.getMinRate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int companyId = rs.getInt("company_id");
                int avgValue = rs.getInt("avg(value)");
                Rates model = new Rates(companyId, avgValue);
                result.add(model);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Select posted job failed: " + e.getMessage());
        }
        return result.toArray(new Rates[result.size()]);
    }
}