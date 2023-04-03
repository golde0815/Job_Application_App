package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.PostedJobDao;
import ca.ubc.cs304.database.model.UpdatePostedJob;
import ca.ubc.cs304.database.model.PostedJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PostedJobController {
    private final PostedJobDao postedJobDao;

    public PostedJobController(PostedJobDao postedJobDao) {
        this.postedJobDao = postedJobDao;
    }

    // 1. INSERT
    @PostMapping("/jobs")
    private String createPostedJob(@RequestBody PostedJob postedJob) {
        postedJobDao.createPostedJob(postedJob);
        return "OK";
    }

    // 4. SELECT
    @GetMapping("/jobs")
    private PostedJob[] selectPostedJob() {
        return postedJobDao.selectPostedJob();
    }

    @GetMapping("/jobs/{column}")
    private Map<String, Object>[] projectPostedJob(@PathVariable String column) {
        return postedJobDao.projectPostedJob(column);
        // return "OK";
    }

    // 3. UPDATE
    @PatchMapping("/jobs")
    private String updatePostedJob(@RequestBody UpdatePostedJob updatePostedJob) {
        postedJobDao.updatePostedJob(updatePostedJob);
        return "OK";
    }
}
