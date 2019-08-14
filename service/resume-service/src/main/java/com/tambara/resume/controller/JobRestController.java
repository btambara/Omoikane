package com.tambara.resume.controller;

import com.tambara.resume.dto.resume.JobDto;
import com.tambara.resume.http.OmoikaneMediaType;
import com.tambara.resume.mapper.resume.JobMapper;
import com.tambara.resume.model.resume.Job;
import com.tambara.resume.service.resume.JobBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Purpose: The REST (HATEOAS) controller for the job part of the application.
@RestController
@RequestMapping(value = "/job")
public class JobRestController {

    @Bean
    public JobMapper createJobMapper() {
        return new JobMapper();
    }

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobBaseService jobService;

    @GetMapping(path = "/all", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resources<Job> getAllJobs() {
        List<Job> allJobs = jobService.getAll();
        long id;

        for (Job job : allJobs) {
            id = job.getJid();
            Link selfLink = linkTo(JobRestController.class).slash(id).withSelfRel();
            job.add(selfLink);
        }

        Link link = linkTo(methodOn(JobRestController.class).getAllJobs()).withSelfRel();
        return new Resources<>(allJobs, link);
    }

    @GetMapping(path = "/{jid}", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resource<JobDto> getJob(@PathVariable final Long jid) {
        JobDto jobDto = jobMapper.convertToDto(jobService.get(jid));
        Link link = linkTo(methodOn(JobRestController.class).getJob(jid)).withSelfRel();
        return new Resource<>(jobDto, link);
    }

    @PostMapping
    public void addJob(@RequestBody JobDto jdto) {
        jobService.add(jobMapper.convertToModel(jdto));
    }

    @DeleteMapping("/{jid}")
    public void removeJob(@PathVariable final Long jid) {
        jobService.remove(jid);
    }

    @PutMapping("/{jid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateJob(@RequestBody JobDto jdto) {
        jobService.update(jobMapper.convertToModel(jdto));
    }
}
