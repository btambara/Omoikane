package com.tambara.resume.persistence.dao.resume;

import com.tambara.resume.persistence.model.resume.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StatementRepo extends JpaRepository<Statement, Long> {
}
