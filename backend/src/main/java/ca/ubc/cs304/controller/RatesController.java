package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.RatesDao;
import ca.ubc.cs304.database.model.MinimumRate;
import ca.ubc.cs304.database.model.Rates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatesController {
    private final RatesDao ratesDao;

    public RatesController(RatesDao ratesDao) {
        this.ratesDao = ratesDao;
    }

    // 8. Aggregation with HAVING
    @GetMapping("/rates")
    private Rates[] companiesRates(@RequestBody MinimumRate rates) {
        return ratesDao.companiesRates(rates);
        // return "OK";
    }
}
