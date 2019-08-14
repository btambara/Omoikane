package com.tambara.resume.repository.resume;

import com.tambara.resume.model.resume.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProjectRepo extends JpaRepository<Project, Long> {
}
