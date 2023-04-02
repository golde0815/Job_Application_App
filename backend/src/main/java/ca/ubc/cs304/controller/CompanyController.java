package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.CompanyDao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.TopCompany;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class CompanyController {
    private final CompanyDao companyDao;

    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @GetMapping("/company")
    private Company[] selectCompanyPostedJob(@RequestBody Company company) {
        return companyDao.selectCompanyPostedJob(company);
        // return "OK";
    }

    @GetMapping("/topcompany")
    private TopCompany[] topRatedCompanies() {
        return companyDao.topRatedCompanies();
    }
}
