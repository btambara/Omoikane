import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Project } from '../models/project';
import { HalRetrieval } from '../models/hal-retrieval';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class ProjectService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getProjectInformation() {
    return this.http
      .get(this.BASE_URL + "project");
  }

  getAllProjectsInformation() {
    return this.http
      .get<HalRetrieval>(this.BASE_URL + "project/all");
  }

  addProjectInformation(project: Project) {
    return this.http
      .post(this.BASE_URL + "project", JSON.stringify(project), httpOptions);
  }

  removeProject(id: number) {
    return this.http
      .delete(this.BASE_URL + "project/" + id);
  }

  updateProjectInformation(project: Project) {
    return this.http
      .put(this.BASE_URL + "project/" + project.pid, JSON.stringify(project), httpOptions)
  }
}
