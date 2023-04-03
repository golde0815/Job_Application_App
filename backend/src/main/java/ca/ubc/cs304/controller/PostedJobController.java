package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.PostedJobDao;
import ca.ubc.cs304.database.model.CreatePostedJob;
import ca.ubc.cs304.database.model.PostedJob;
import ca.ubc.cs304.database.model.ResponseMessage;
import ca.ubc.cs304.database.model.UpdatePostedJob;
import ca.ubc.cs304.exception.GenericSQLException;
import ca.ubc.cs304.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class PostedJobController {
    private final PostedJobDao postedJobDao;

    public PostedJobController(PostedJobDao postedJobDao) {
        this.postedJobDao = postedJobDao;
    }

    // 1. INSERT
    @PostMapping("/jobs")
    @ResponseBody
    private ResponseMessage createPostedJob(@RequestBody CreatePostedJob createPostedJob) {
        try {
            Utils.validateCreatePostedJob(createPostedJob);
            postedJobDao.createPostedJob(createPostedJob);
            return new ResponseMessage("OK");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }

    // 4. SELECT
    @GetMapping("/jobs")
    @ResponseBody
    private List<PostedJob> selectPostedJob(@RequestParam(required = false) String attribute,
                                            @RequestParam(required = false) String value) {
        try {
            if (attribute == null || value == null) {
                return postedJobDao.getAllPostedJobs();
            } else {
                return postedJobDao.selectPostedJob(attribute, value);
            }
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }

    // 5. PROJECTION
    @GetMapping("/jobs/{column}")
    private Map<String, Object>[] projectPostedJob(@PathVariable String column) {
        return postedJobDao.projectPostedJob(column);
    }

    // 3. UPDATE
    @PatchMapping("/jobs")
    @ResponseBody
    private ResponseMessage updatePostedJob(@RequestBody UpdatePostedJob updatePostedJob) {
        try {
            postedJobDao.updatePostedJob(updatePostedJob);
            return new ResponseMessage("OK");
        } catch (GenericSQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
        }
    }
}
