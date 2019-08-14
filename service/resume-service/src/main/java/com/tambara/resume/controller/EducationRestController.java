package com.tambara.resume.controller;

import com.tambara.resume.dto.resume.EducationDto;
import com.tambara.resume.http.OmoikaneMediaType;
import com.tambara.resume.mapper.resume.EducationMapper;
import com.tambara.resume.model.resume.Education;
import com.tambara.resume.service.resume.EducationBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Purpose: The REST (HATEOAS) controller for the educational part of the application.
@RestController
@RequestMapping(value = "/education")
public class EducationRestController {

    @Bean
    public EducationMapper createEducationMapper() {
        return new EducationMapper();
    }

    @Autowired
    private EducationMapper educationMapper;

    @Autowired
    private EducationBaseService educationService;

    @GetMapping(path = "/all", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resources<Education> getAllEducation() {
        List<Education> allEducation = educationService.getAll();
        long id;

        for (Education education : allEducation) {
            id = education.getEid();
            Link selfLink = linkTo(EducationRestController.class).slash(id).withSelfRel();
            education.add(selfLink);
        }

        Link link = linkTo(methodOn(EducationRestController.class).getAllEducation()).withSelfRel();
        return new Resources<>(allEducation, link);
    }

    @GetMapping(path = "/{jid}", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resource<EducationDto> getJob(@PathVariable final Long jid) {
        EducationDto educationDto = educationMapper.convertToDto(educationService.get(jid));
        Link link = linkTo(methodOn(EducationRestController.class).getJob(jid)).withSelfRel();
        return new Resource<>(educationDto, link);
    }

    @PostMapping
    public void addEducation(@RequestBody EducationDto edto) {
        educationService.add(educationMapper.convertToModel(edto));
    }

    @DeleteMapping("/{eid}")
    public void removeEducation(@PathVariable final Long eid) {
        educationService.remove(eid);
    }

    @PutMapping("/{eid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEducation(@RequestBody EducationDto edto) {
        educationService.update(educationMapper.convertToModel(edto));
    }
}
