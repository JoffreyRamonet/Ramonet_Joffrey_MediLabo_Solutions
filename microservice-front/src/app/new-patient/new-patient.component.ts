import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, ValidationErrors, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PatientService} from "../service/patient-service";
import {NewPatient} from "../model/newPatient.model";
import {Observable, tap} from "rxjs";
import {DatePipe, NgIf} from "@angular/common";
import {Patient} from "../model/patient.model";

@Component({
  selector: 'app-new-patient',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './new-patient.component.html',
  styleUrl: './new-patient.component.scss'
})
export class NewPatientComponent implements OnInit {

  patientForm!: FormGroup;
  patient!: NewPatient;
  patientList!: Patient[];
  alreadyExist = false;
  today!: string | undefined;

  constructor(private formBuilder: FormBuilder, private patientService: PatientService, private router: Router, private datePipe: DatePipe) {

  }


  ngOnInit(): void {
    var currentDate = new Date();
    this.today = this.datePipe.transform(currentDate, "yyyy-MM-dd")?.toString();

    this.patientForm = this.formBuilder.group({
      lastName: [null, Validators.required],
      firstName: [null, Validators.required],
      birthDate: [null, Validators.required],
      gender: [null, Validators.compose([Validators.required, Validators.pattern('[MF]')])],
      address: [null],
      phone: [null]
    })
    this.patientService.getAllPatients().subscribe(data => this.patientList = data);
  }

  onReturn(): void {
    this.router.navigateByUrl(``);
  }

  onSubmitForm(): void {
    this.patient = new NewPatient(
      this.patientForm.value.lastName,
      this.patientForm.value.firstName,
      this.patientForm.value.birthDate,
      this.patientForm.value.gender,
      this.patientForm.value.address,
      this.patientForm.value.phone
    );

    for (let i = 0; i < this.patientList.length; i++) {
      if (this.patientList[i].firstName === this.patient.firstName && this.patientList[i].lastName === this.patient.lastName) {
        this.alreadyExist = true;
      }
    }
    if (!this.alreadyExist) {
      this.patientService.saveNewPatient(this.patient).pipe(tap(() => this.router.navigateByUrl(``))).subscribe();
    }
  }


}
