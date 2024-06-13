import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PatientService} from "../service/patient-service";
import {NewPatient} from "../model/newPatient.model";
import {tap} from "rxjs";

@Component({
  selector: 'app-new-patient',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-patient.component.html',
  styleUrl: './new-patient.component.scss'
})
export class NewPatientComponent implements OnInit {

  patientForm!: FormGroup;
  patient!: NewPatient;

  constructor(private formBuilder: FormBuilder, private patientService: PatientService ,private router: Router) {
  }


  ngOnInit() : void {

    this.patientForm = this.formBuilder.group({
      lastName: [null, Validators.required],
      firstName: [null, Validators.required],
      birthDate: [null, Validators.required],
      gender: [null, Validators.required],
      address: [null],
      phone: [null]
    })
  }

  onReturn(): void {
    this.router.navigateByUrl(``);
  }

  onSubmitForm() : void {
    this.patient = new NewPatient(
      this.patientForm.value.lastName,
      this.patientForm.value.firstName,
      this.patientForm.value.birthDate,
      this.patientForm.value.gender,
      this.patientForm.value.address,
      this.patientForm.value.phone
    );
    this.patientService.saveNewPatient(this.patient).pipe(tap(() => this.router.navigateByUrl(``))).subscribe();
}
}
