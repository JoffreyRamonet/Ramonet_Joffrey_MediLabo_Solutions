import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Patient} from "../model/patient.model";
import {UpdatePatient} from "../model/updatePatient.model";
import {PatientService} from "../service/patient-service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Observable, Subscription, tap} from "rxjs";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    AsyncPipe,
    NgIf,
    RouterLink
  ],
  templateUrl: './edit-patient.component.html',
  styleUrl: './edit-patient.component.scss'
})
export class EditPatientComponent implements OnInit {

  editForm!: FormGroup;
  patient$!: Observable<Patient>;
  updatePatient!: UpdatePatient;
  id: string = this.route.snapshot.params['id'];

  firstName!: string;
  lastName!: string;
  constructor(private formBuilde: FormBuilder, private patientService: PatientService, private router: Router, private route: ActivatedRoute) {
  }


  ngOnInit(): void {
    this.patient$ = this.patientService.getPatientById(this.id);
    this.patient$.subscribe(patient => { this.firstName = patient.firstName});
    this.patient$.subscribe(patient => { this.lastName = patient.lastName});
    this.editForm = this.formBuilde.group({
      lastName: [null],
      firstName: [null],
      gender: [null],
      address: [null],
      phone: [null]
    })
  }

  onReturn(): void {
    this.router.navigateByUrl(`patient/${this.id}`);
  }


  onSubmitEditForm(): void {
this.updatePatient = new UpdatePatient(
  this.lastName,
  this.firstName,
  this.editForm.value.gender,
  this.editForm.value.address,
  this.editForm.value.phone
);
this.patientService.updatePatient(this.updatePatient).pipe(tap(() => this.router.navigateByUrl(`patient/${this.id}`))).subscribe();
  }
}
