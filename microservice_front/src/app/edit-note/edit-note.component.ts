import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Note} from "../model/note.model";
import {Observable, tap} from 'rxjs';
import {UpdateNote} from "../model/updateNote";
import {NoteService} from "../service/note-service";
import {ActivatedRoute, Router} from "@angular/router";
import {AsyncPipe, DatePipe, NgIf} from "@angular/common";
import {MatFormField, MatHint, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-edite-note',
  standalone: true,
  imports: [
    AsyncPipe,
    MatFormField,
    MatHint,
    MatInput,
    MatLabel,
    NgIf,
    ReactiveFormsModule,
    DatePipe
  ],
  templateUrl: './edit-note.component.html',
  styleUrl: './edit-note.component.scss'
})
export class EditNoteComponent implements OnInit {

  editForm!: FormGroup;
  note$!: Observable<Note>;
  updateNote!: UpdateNote;
  patientId!: string;
  id: string = this.route.snapshot.params['id'];
  noteId!: string;

  constructor(private formBuilder: FormBuilder, private noteService: NoteService, private router: Router, private route: ActivatedRoute) {  }

  ngOnInit(): void {
 this.note$ = this.noteService.getNoteById(this.id);
 this.note$.subscribe(note => {
   this.patientId = note.patient;
   this.noteId = note.id;
 });

 this.editForm = this.formBuilder.group({
   note: [null]
 });
    }

    onReturn(): void{
    this.router.navigateByUrl(`patient/${this.patientId}`);
    }

    onSubmitEditForm(): void {
    this.updateNote = new UpdateNote(
      this.noteId,
      this.editForm.value.note
    );
    this.noteService.updateNote(this.updateNote).pipe(tap(() => this.router.navigateByUrl(`patient/${this.patientId}`))).subscribe();
    }
}
