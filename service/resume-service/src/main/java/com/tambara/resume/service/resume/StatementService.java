package com.tambara.resume.service.resume;

import com.tambara.resume.persistence.dao.resume.StatementRepo;
import com.tambara.resume.persistence.model.resume.Statement;
import com.tambara.resume.service.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Purpose: The service class for Statement.
@Service
public class StatementService implements StatementBaseService{
    @Autowired
    private StatementRepo statementRepo;

    @Override
    public Statement get() {
        return statementRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Unable to locate statement."));
    }

    //Purpose: Creates a Statement and if a Statement is created, then it will update it.
    @Override
    public Statement create(Statement c) {
        if (!statementRepo.findAll().isEmpty()) {
            return update(c);
        }

        return statementRepo.save(c);
    }

    //Purpose: Updates the Statement and if a Contact isn't created, then it will create one.
    @Override
    public Statement update(Statement s) {
        return statementRepo
                .findAll()
                .stream()
                .findFirst()
                .map(statement -> {
                    statement.setStatement(s.getStatement());
                    return statementRepo.save(statement);
                })
                .orElseGet(() -> create(s));
    }
}
