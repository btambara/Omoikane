package com.tambara.resume.web.controller;

import com.tambara.resume.mapper.resume.StatementMapper;
import com.tambara.resume.persistence.model.resume.Statement;
import com.tambara.resume.service.resume.StatementService;
import com.tambara.resume.web.dto.resume.StatementDto;
import com.tambara.resume.web.http.OmoikaneMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Purpose: The REST (HATEOAS) controller for the statement part of the application.
//Notes:
//1) Only ONE entry should be in this table.
@RestController
@CrossOrigin
@RequestMapping(value = "/statement")
public class StatementRestController {

    @Bean
    public StatementMapper createStatementMapper() {
        return new StatementMapper();
    }

    @Autowired
    private StatementService statementService;

    @Autowired
    private StatementMapper statementMapper;

    @GetMapping(produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resource<StatementDto> getStatement() {
        StatementDto statementDto = statementMapper.convertToDto(statementService.get());
        Link link = linkTo(methodOn(StatementRestController.class).getStatement()).withSelfRel();
        return new Resource<>(statementDto, link);
    }

    @PostMapping
    @ResponseBody
    public Resource<StatementDto> addStatement(@RequestBody StatementDto sdto) {
        StatementDto statementDto = statementMapper.convertToDto(statementService.create(statementMapper.convertToModel(sdto)));
        Link link = linkTo(methodOn(StatementRestController.class).addStatement(sdto)).withSelfRel();
        return new Resource<>(statementDto, link);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateStatement(@RequestBody StatementDto sdto) {
        Statement statement = statementMapper.convertToModel(sdto);
        statementService.update(statement);
    }
}