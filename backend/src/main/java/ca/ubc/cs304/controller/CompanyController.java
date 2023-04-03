package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.CompanyDao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.ResponseMessage;
import ca.ubc.cs304.database.model.TopCompany;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CompanyController {
    private final CompanyDao companyDao;

    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    // 2. DELETE
    @DeleteMapping("/companies/{companyId}")
    @ResponseBody
    private ResponseMessage deleteCompany(@PathVariable int companyId) {
        try {
            companyDao.deleteCompany(companyId);
            return new ResponseMessage("OK");
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }

    // 6. JOIN
    @GetMapping("/company")
    private List<Company> selectCompanyPostedJob(@RequestParam LocalDate postedDate) {
        System.out.println(postedDate);
        return companyDao.selectCompanyPostedJob(postedDate);
    }

    // 9. Nested Aggregation with GROUP BY
    @GetMapping("/topcompany")
    private List<TopCompany> topRatedCompanies() {
        try {
            return companyDao.topRatedCompanies();
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }
}
