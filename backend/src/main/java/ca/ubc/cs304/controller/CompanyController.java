package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.CompanyDao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.DeleteCompany;
import ca.ubc.cs304.database.model.TopCompany;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class CompanyController {
    private final CompanyDao companyDao;

    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @DeleteMapping("/company")
    private String deleteCompany(@RequestBody DeleteCompany deleteCompany) {
        companyDao.deleteCompany(deleteCompany);
        return "OK";
    }

    // 6. JOIN
    @GetMapping("/company")
    private Company[] selectCompanyPostedJob(@RequestParam LocalDate postedDate) {
        return companyDao.selectCompanyPostedJob(postedDate);
        // return "OK";
    }

    // 9. Nested Aggregation with GROUP BY
    @GetMapping("/topcompany")
    private TopCompany[] topRatedCompanies() {
        return companyDao.topRatedCompanies();
    }
}
