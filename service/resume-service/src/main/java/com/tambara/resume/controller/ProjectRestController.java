package com.tambara.resume.controller;

import com.tambara.resume.dto.resume.ProjectDto;
import com.tambara.resume.http.OmoikaneMediaType;
import com.tambara.resume.mapper.resume.ProjectMapper;
import com.tambara.resume.model.resume.Project;
import com.tambara.resume.service.resume.ProjectBaseService;
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

//Purpose: The REST (HATEOAS) controller for the project part of the application.
@RestController
@RequestMapping(value = "/api/project")
public class ProjectRestController {

    @Bean
    public ProjectMapper createProjectMapper() {
        return new ProjectMapper();
    }

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectBaseService projectService;

    @GetMapping(path = "/all", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resources<Project> getAllJobs() {
        List<Project> allProjects = projectService.getAll();
        long id;

        for (Project project : allProjects) {
            id = project.getPid();
            Link selfLink = linkTo(ProjectRestController.class).slash(id).withSelfRel();
            project.add(selfLink);
        }

        Link link = linkTo(methodOn(ProjectRestController.class).getAllJobs()).withSelfRel();
        return new Resources<>(allProjects, link);
    }

    @GetMapping(path = "/{jid}", produces = OmoikaneMediaType.APPLICATION_HAL_JSON_VALUE)
    @ResponseBody
    public Resource<ProjectDto> getJob(@PathVariable final Long jid) {
        ProjectDto projectDto = projectMapper.convertToDto(projectService.get(jid));
        Link link = linkTo(methodOn(ProjectRestController.class).getJob(jid)).withSelfRel();
        return new Resource<>(projectDto, link);
    }

    @PostMapping
    public void addProject(@RequestBody ProjectDto pdto) {
        projectService.add(projectMapper.convertToModel(pdto));
    }

    @DeleteMapping("/{pid}")
    public void removeProject(@PathVariable final Long pid) {
        projectService.remove(pid);
    }

    @PutMapping("/{pid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProject(@RequestBody ProjectDto pdto) {
        projectService.update(projectMapper.convertToModel(pdto));
    }
}
