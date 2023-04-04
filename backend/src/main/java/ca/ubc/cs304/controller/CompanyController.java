package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.CompanyDao;
import ca.ubc.cs304.database.model.Company;
import ca.ubc.cs304.database.model.CompanyWithRating;
import ca.ubc.cs304.database.model.ResponseMessage;
import ca.ubc.cs304.database.model.TopCompany;
import ca.ubc.cs304.exception.GenericSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
    private final CompanyDao companyDao;

    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @GetMapping("/locations")
    private List<String> getAllLocations() {
        try {
            return companyDao.getAllLocations();
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
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

    @GetMapping("/companies/{companyId}")
    private Company getCompany(@PathVariable int companyId) {
        try {
            return companyDao.getCompany(companyId);
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }

    // 6. JOIN and 8. Aggregation with HAVING
    @GetMapping("/companies")
    private List<CompanyWithRating> companiesRates(@RequestParam(required = false) Integer minimumRating,
                                                   @RequestParam(required = false) LocalDate postedAfter) {
        try {
            if (minimumRating == null && postedAfter == null) {
                return companyDao.getAllCompanies();
            } else if (postedAfter == null) {
                return companyDao.companiesWithMinimumRating(minimumRating);
            } else if (minimumRating == null) {
                return companyDao.selectCompanyPostedAfter(postedAfter);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot have both minimumRating and postedAfter");
            }
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }

    // 9. Nested Aggregation with GROUP BY
    @GetMapping("/topcompanies")
    private List<TopCompany> topRatedCompanies(@RequestParam int minimumAverageRating) {
        try {
            return companyDao.topRatedCompanies(minimumAverageRating);
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }
}
