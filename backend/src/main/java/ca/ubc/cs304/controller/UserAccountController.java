package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.UserAccountDao;
import ca.ubc.cs304.database.model.UserAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAccountController {
    private final UserAccountDao userAccountDao;

    public UserAccountController(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    // 10. Division
    @GetMapping("/users/applications/{companyId}")
    private List<UserAccount> countAppliedUsers(@PathVariable int companyId) {
        return userAccountDao.appliedToAllJobsFrom(companyId);
    }
}
