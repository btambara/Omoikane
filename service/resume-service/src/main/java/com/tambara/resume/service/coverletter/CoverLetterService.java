package com.tambara.resume.service.coverletter;

import com.tambara.resume.persistence.dao.coverletter.CoverLetterRepo;
import com.tambara.resume.persistence.model.coverletter.CoverLetter;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Purpose: The service class for the cover letter.
//Notes:
//1) Only ONE entry should be in this table.
@Service
public class CoverLetterService implements CoverLetterBaseService{
    @Autowired
    private CoverLetterRepo coverLetterRepo;

    @Override
    public CoverLetter get() throws ResourceNotFoundException {
        return coverLetterRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Unable to locate the cover letter."));
    }

    //Purpose: Creates a Cover Letter and if a Cover Letter is created, then it will update it.
    @Override
    public CoverLetter create(CoverLetter cl) {
        if (!coverLetterRepo.findAll().isEmpty()) {
            return update(cl);
        }

        return coverLetterRepo.save(cl);
    }

    //Purpose: Updates the Cover Letter and if a Cover Letter isn't created, then it will create one.
    @Override
    public CoverLetter update(CoverLetter cl) {
        return coverLetterRepo
                .findAll()
                .stream()
                .findFirst()
                .map(contact -> {
                    contact.setHeadline(cl.getHeadline());
                    contact.setCoverLetter(cl.getCoverLetter());
                    return coverLetterRepo.save(contact);
                })
                .orElseGet(() -> create(cl));
    }
}
