package com.tambara.resume.persistence.dao.resume;

import com.tambara.resume.persistence.model.resume.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProjectRepo extends JpaRepository<Project, Long> {
}
