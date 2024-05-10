import {
  RouterModule,
  Routes
} from '@angular/router';
import {
  PatientListComponent
} from "./patient-list/patient-list.component";
import {NgModule} from "@angular/core";
import {
  SinglePatientComponent
} from "./single-patient/single-patient.component";
import {NewPatientComponent} from "./new-patient/new-patient.component";
import {EditPatientComponent} from "./edit-patient/edit-patient.component";

export const routes: Routes = [
  { path: ``, component: PatientListComponent },
  { path: `patient/:id`, component: SinglePatientComponent },
  { path: `new`, component: NewPatientComponent},
  { path: `patient/edit/:id`, component: EditPatientComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
