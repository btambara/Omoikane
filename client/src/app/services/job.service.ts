import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Job } from '../models/job';

@Injectable({
  providedIn: 'root'
})

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export class JobService {
  BASE_URL = "http://157.230.207.59:8761/api/resume/"

  constructor(private http: HttpClient) { }

  getJobInformation() {
    return this.http
      .get<Job>(this.BASE_URL + "job");
  }

  getAllJobsInformation() {
    return this.http
      .get<Job[]>(this.BASE_URL + "job/all");
  }

  addJobInformation(job: Job) {
    return this.http
      .post(this.BASE_URL + "job", JSON.stringify(job), httpOptions);
  }

  removeJob(id: number) {
    return this.http
      .delete(this.BASE_URL + "job/" + id);
  }

  updateJobInformation(job: Job) {
    return this.http
      .put(this.BASE_URL + "job/" + job.jid, JSON.stringify(job), httpOptions)
  }
}
