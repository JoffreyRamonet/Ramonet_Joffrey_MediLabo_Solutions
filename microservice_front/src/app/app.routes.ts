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
import {AuthGuard} from "./security/guard";
import {EditNoteComponent} from "./edit-note/edit-note.component";
import {NewNoteComponent} from "./new-note/new-note.component";
import {ManageTriggerComponent} from "./manage-trigger/manage-trigger.component";
import {AdminGuard} from "./security/admin-guard";

export const routes: Routes = [
  { path: ``, component: PatientListComponent, canActivate: [AuthGuard]},
  { path: `patient/:id`, component: SinglePatientComponent},
  { path: `new/patient`, component: NewPatientComponent},
  { path: `triggers`, component: ManageTriggerComponent, canActivate: [AdminGuard], data: {role: 'ADMIN'}},
  { path: `patient/edit/:id`, component: EditPatientComponent},
  { path: `note/edit/:id`, component: EditNoteComponent},
  { path: `new/note/:id`, component: NewNoteComponent}
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
