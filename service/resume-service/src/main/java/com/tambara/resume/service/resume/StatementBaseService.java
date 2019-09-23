package com.tambara.resume.service.resume;

import com.tambara.resume.persistence.model.resume.Statement;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface StatementBaseService {
    Statement get() throws ResourceNotFoundException;

    Statement create(Statement c);

    Statement update(Statement c);
}
