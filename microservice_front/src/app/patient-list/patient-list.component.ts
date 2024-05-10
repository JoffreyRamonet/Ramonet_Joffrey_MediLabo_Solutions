import {Component, OnInit} from '@angular/core';
import {Patient} from "../model/patient.model";
import {Observable} from "rxjs";
import {
  PatientService
} from "../service/patient-service";
import {
  PatientHeaderComponent
} from "../patient-header/patient-header.component";
import {
  AsyncPipe,
  NgForOf
} from "@angular/common";
import {AsideComponent} from "../aside/aside.component";


@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [
    PatientHeaderComponent,
    NgForOf,
    AsyncPipe,
    AsideComponent
  ],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss'
})
export class PatientListComponent implements OnInit {

  patients$!: Observable<Patient[]>;

  constructor(private patientService: PatientService) {
  }

  ngOnInit(): void {
    this.patients$ = this.patientService.getAllPatients();
  }

}
