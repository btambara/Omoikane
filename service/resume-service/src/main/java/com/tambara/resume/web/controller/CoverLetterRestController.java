package com.tambara.resume.web.controller;

import com.tambara.resume.mapper.coverletter.CoverLetterMapper;
import com.tambara.resume.persistence.model.coverletter.CoverLetter;
import com.tambara.resume.service.coverletter.CoverLetterService;
import com.tambara.resume.web.dto.coverletter.CoverLetterDto;
import com.tambara.resume.web.http.OmoikaneMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Purpose: The REST (HATEOAS) controller for the cover letter part of the application.
//Notes:
//1) Only ONE entry should be in this table.
@RestController
@CrossOrigin
@RequestMapping(value = "/coverletter")
public class CoverLetterRestController {

    @Bean
    public CoverLetterMapper createCoverLetterMapper() {
        return new CoverLetterMapper();
    }

    @Autowired
    private CoverLetterService coverLetterService;

    @Autowired
    private CoverLetterMapper coverLetterMapper;

    @GetMapping(produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resource<CoverLetterDto> getCoverLetter() {
        CoverLetterDto coverLetterDto = coverLetterMapper.convertToCoverLetterDto(coverLetterService.get());
        Link link = linkTo(methodOn(ContactRestController.class).getContact()).withSelfRel();
        return new Resource<>(coverLetterDto, link);
    }

    @PostMapping
    @ResponseBody
    public Resource<CoverLetterDto> addCoverLetter(@RequestBody CoverLetterDto cldto) {
        CoverLetterDto coverLetterDto = coverLetterMapper.convertToCoverLetterDto(coverLetterService.create(coverLetterMapper.convertToCoverLetter(cldto)));
        Link link = linkTo(methodOn(CoverLetterRestController.class).addCoverLetter(cldto)).withSelfRel();
        return new Resource<>(coverLetterDto, link);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCoverLetter(@RequestBody CoverLetterDto cldto) {
        CoverLetter coverLetter = coverLetterMapper.convertToCoverLetter(cldto);
        coverLetterService.update(coverLetter);
    }
}
