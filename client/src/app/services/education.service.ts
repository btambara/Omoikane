import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Education } from '../models/education';

@Injectable({
  providedIn: 'root'
})

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export class EducationService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getEducationInformation() {
    return this.http
      .get<Education>(this.BASE_URL + "education");
  }

  getAllEducationInformation() {
    return this.http
      .get<Education[]>(this.BASE_URL + "education/all");
  }

  addEducationInformation(education: Education) {
    return this.http
      .post(this.BASE_URL + "education", JSON.stringify(education), httpOptions);
  }

  removeEducation(id: number) {
    return this.http
      .delete(this.BASE_URL + "education/" + id);
  }

  updateEducationInformation(education: Education) {
    return this.http
      .put(this.BASE_URL + "education/" + education.eid, JSON.stringify(education), httpOptions)
  }
}
