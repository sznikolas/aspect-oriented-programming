package com.nikolas.JobApp.controller;

import com.nikolas.JobApp.model.JobPost;
import com.nikolas.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    @Autowired
    private JobService jobService;


//    @GetMapping(path= "jobPosts", produces = {"application/json"})
    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }
    @GetMapping("jobPost/{id}")
    public JobPost getJob(@PathVariable("id") int id) {
        return jobService.getJob(id);
    }

//    @PostMapping(path= "jobPost", consumes = {"application/xml"})
    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost){
        jobService.addJobPost(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }


    @DeleteMapping("jobPost/{id}")
    public String deleteJob(@PathVariable("id") int id) {
        jobService.deleteJob(id);
        return "deleted";
    }

    @GetMapping("load")
    public String loadData(){
        jobService.load();

        return "success";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return jobService.search(keyword);
    }


}
