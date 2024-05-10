import { Component } from '@angular/core';
import {Patient} from "../model/patient.model";
import {Observable, tap} from "rxjs";
import {
  PatientService
} from "../service/patient-service";
import {
  ActivatedRoute, Router,
  RouterLink
} from "@angular/router";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-single-patient',
  standalone: true,
  imports: [
    AsyncPipe,
    RouterLink,
    NgIf
  ],
  templateUrl: './single-patient.component.html',
  styleUrl: './single-patient.component.scss'
})
export class SinglePatientComponent {

  patient$!: Observable<Patient>;
  id: string = this.route.snapshot.params['id'];

  constructor(private patientService: PatientService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.patient$ = this.patientService.getPatientById(this.id);
  }

  onEditPatient(): void {
    this.router.navigateByUrl(`patient/edit/${this.id}`);
  }

  onDeletePatient(): void {
    this.patientService.deletePatient(this.id).pipe(tap(() => this.router.navigateByUrl(``))).subscribe();
  }
}
