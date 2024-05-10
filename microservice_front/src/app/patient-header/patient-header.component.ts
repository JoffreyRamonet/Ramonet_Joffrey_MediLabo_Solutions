import {
  Component,
  Input
} from '@angular/core';
import {Patient} from "../model/patient.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-patient-header',
  standalone: true,
  imports: [],
  templateUrl: './patient-header.component.html',
  styleUrl: './patient-header.component.scss'
})
export class PatientHeaderComponent {

  @Input() patient!: Patient;

  constructor(private router: Router) {
  }

  onViewPatient() {
    this.router.navigateByUrl(`patient/${this.patient.id}`);
  }
}
