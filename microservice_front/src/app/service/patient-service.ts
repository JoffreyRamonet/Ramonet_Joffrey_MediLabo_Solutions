import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Patient} from "../model/patient.model";
import {Observable} from "rxjs";
import {NewPatient} from "../model/newPatient.model";
import {UpdatePatient} from "../model/updatePatient.model";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>('http://localhost:9000/patient/all');
  }

  getPatientById(id: string): Observable<Patient> {
    return this.http.get<Patient>(`http://localhost:9000/patient/${id}`)
  }

  saveNewPatient(newPatient: NewPatient): Observable<Patient>{
    return this.getAllPatients().pipe(switchMap => this.http.post<Patient>(`http://localhost:9000/patient/save`, newPatient));
  }

  updatePatient(updatePatient: UpdatePatient): Observable<Patient> {
    return this.http.patch<Patient>(`http://localhost:9000/patient/update`, updatePatient);
  }

  deletePatient(id: string): Observable<void> {
    return this.http.delete<void>(`http://localhost:9000/patient/delete/${id}`);
  }
}
