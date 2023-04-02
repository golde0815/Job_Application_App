package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.dao.DatabaseConnectionHandler;
import ca.ubc.cs304.database.dao.PostedJobDao;
import ca.ubc.cs304.database.model.ParseDeleteJson;
import ca.ubc.cs304.database.model.ParseUpdateJson;
import ca.ubc.cs304.database.model.PostedJob;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/jobs")
    private PostedJob[] selectPostedJob() {
        // postedJobDao.selectPostedJob();
        // return "OK";
        return postedJobDao.selectPostedJob();
    }

    @PatchMapping("/jobs")
    private String updatePostedJob(@RequestBody ParseUpdateJson parseUpdateJson) {
        postedJobDao.updatePostedJob(parseUpdateJson);
        return "OK";
    }

    @DeleteMapping("/jobs")
    private String deletePostedJob(@RequestBody ParseDeleteJson parseDeleteJson) {
        postedJobDao.deletePostedJob(parseDeleteJson);
        return "OK";
    }
}
