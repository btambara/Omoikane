package com.tambara.resume.repository.resume;

import com.tambara.resume.model.resume.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JobRepo extends JpaRepository<Job, Long> {
}
