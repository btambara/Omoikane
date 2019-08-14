package com.tambara.resume.repository.resume;

import com.tambara.resume.model.resume.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EducationRepo extends JpaRepository<Education, Long> {
}
