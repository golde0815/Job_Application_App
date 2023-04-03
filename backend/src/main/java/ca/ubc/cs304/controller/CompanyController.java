package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.CompanyDao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.ParseDeleteJson;
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
    private String deleteCompany(@RequestBody ParseDeleteJson parseDeleteJson) {
        companyDao.deleteCompany(parseDeleteJson);
        return "OK";
    }
    @GetMapping("/company/{postedDate}")
    private Company[] selectCompanyPostedJob(@PathVariable LocalDate postedDate) {
        return companyDao.selectCompanyPostedJob(postedDate);
        // return "OK";
    }

    @GetMapping("/topcompany")
    private TopCompany[] topRatedCompanies() {
        return companyDao.topRatedCompanies();
    }
}
