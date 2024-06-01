import { Component } from '@angular/core';
import {Patient} from "../model/patient.model";
import {map, Observable, tap} from "rxjs";
import {
  PatientService
} from "../service/patient-service";
import {
  ActivatedRoute, Router,
  RouterLink
} from "@angular/router";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {SingleNoteComponent} from "../single-note/single-note.component";
import {Note} from "../model/note.model";
import {NoteService} from "../service/note-service";

@Component({
  selector: 'app-single-patient',
  standalone: true,
  imports: [
    AsyncPipe,
    RouterLink,
    NgIf,
    SingleNoteComponent,
    NgForOf
  ],
  templateUrl: './single-patient.component.html',
  styleUrl: './single-patient.component.scss'
})
export class SinglePatientComponent {

  patient$!: Observable<Patient>;
  notes$!: Observable<Note[]>;
  id: string = this.route.snapshot.params['id'];

  constructor(private patientService: PatientService, private noteService: NoteService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.patient$ = this.patientService.getPatientById(this.id);
    this.notes$ = this.noteService.getNoteByPatientId(this.id);
    this.notes$ = this.notes$.pipe(map(notes => notes.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime())));
  }

  onEditPatient(): void {
    this.router.navigateByUrl(`patient/edit/${this.id}`);
  }

  onDeletePatient(): void {
    this.patientService.deletePatient(this.id).pipe(tap(() => {
      this.noteService.deleteNoteByPatientId(this.id).subscribe();
      this.router.navigateByUrl(``);
    })).subscribe();
  }

  onNewNote(): void {
    this.router.navigateByUrl(`new/note/${this.id}`);
  }
}
