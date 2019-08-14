package com.tambara.resume.service.resume;

import com.tambara.resume.model.resume.Job;
import com.tambara.resume.repository.resume.JobRepo;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Purpose: The service class for Jobs.
@Service
public class JobService implements JobBaseService {
    @Autowired
    private JobRepo jobRepo;

    @Override
    public List<Job> getAll() {
        return jobRepo.findAll();
    }

    @Override
    public Job get(long jid) {
        return jobRepo.findById(jid)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to locate Job with ID: " + jid));
    }

    @Override
    public Job add(Job j) {
        return jobRepo.save(j);
    }

    @Override
    public void remove(long jid) {
        jobRepo.deleteById(jid);
    }

    @Override
    public Job update(Job j) {
        return jobRepo.findById(j.getJid())
                .map(job -> {
                    job.setName(j.getName());
                    job.setLocation(j.getLocation());
                    job.setWebsiteLink(j.getWebsiteLink());
                    job.setTitle(j.getTitle());
                    job.setJobSummary(j.getJobSummary());
                    job.setStarted(j.getStarted());
                    job.setEnded(j.getEnded());
                    return jobRepo.save(job);
                })
                .orElseGet(() -> add(j));
    }
}
