package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.UserAppliesToDao;
import ca.ubc.cs304.database.model.CompanyId;
import ca.ubc.cs304.database.model.UserAppliesTo;
import ca.ubc.cs304.database.model.UserEmail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAppliesToController {
    private final UserAppliesToDao userAppliesToDao;

    public UserAppliesToController(UserAppliesToDao userAppliesToDao) {
        this.userAppliesToDao = userAppliesToDao;
    }

    // 7. Aggregation with GROUP BY
    @GetMapping("/user")
    private UserAppliesTo[] countAppliedUsers() {
        return userAppliesToDao.CountAppliedUsers();
        // return "OK";
    }

    // 10. Division
    @GetMapping("/applied")
    private UserEmail[] countAppliedUsers(@RequestBody CompanyId companyId) {
        return userAppliesToDao.appliedToAllJobsFrom(companyId);
        // return "OK";
    }
}
