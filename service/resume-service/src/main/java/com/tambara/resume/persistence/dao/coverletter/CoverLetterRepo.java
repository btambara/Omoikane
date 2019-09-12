package com.tambara.resume.persistence.dao.coverletter;

import com.tambara.resume.persistence.model.coverletter.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CoverLetterRepo extends JpaRepository<CoverLetter, Long> {
}
