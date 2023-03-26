package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.PostedJobDao;
import ca.ubc.cs304.database.model.PostedJob;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostedJobController {
    private final PostedJobDao postedJobDao;

    public PostedJobController(PostedJobDao postedJobDao) {
        this.postedJobDao = postedJobDao;
    }

    @PostMapping("/jobs")
    private String createPostedJob(@RequestBody PostedJob postedJob) {
        System.out.println(postedJob);
        postedJobDao.createPostedJob(postedJob);
        return "OK";
    }
}
