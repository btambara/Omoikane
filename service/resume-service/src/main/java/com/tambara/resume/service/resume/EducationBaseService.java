package com.tambara.resume.service.resume;

import com.tambara.resume.persistence.model.resume.Education;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EducationBaseService {
    List<Education> getAll();

    Education get(long eid);

    Education add(Education e);

    void remove(long eid);

    Education update(Education e);
}
