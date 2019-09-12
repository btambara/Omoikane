package com.tambara.resume.service.coverletter;

import com.tambara.resume.persistence.model.coverletter.CoverLetter;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface CoverLetterBaseService {
    CoverLetter get() throws ResourceNotFoundException;

    CoverLetter create(CoverLetter cl);

    CoverLetter update(CoverLetter cl);
}
