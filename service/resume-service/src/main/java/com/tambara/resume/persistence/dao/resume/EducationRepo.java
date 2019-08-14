package com.tambara.resume.persistence.dao.resume;

import com.tambara.resume.persistence.model.resume.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EducationRepo extends JpaRepository<Education, Long> {
}
