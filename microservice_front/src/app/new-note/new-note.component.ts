import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NewNote} from "../model/newNote";
import {NoteService} from "../service/note-service";
import {ActivatedRoute, Router} from "@angular/router";
import {tap} from "rxjs";

@Component({
  selector: 'app-new-note',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './new-note.component.html',
  styleUrl: './new-note.component.scss'
})
export class NewNoteComponent implements OnInit{

  noteForm!: FormGroup;
  note!: NewNote;
  patientId: string = this.route.snapshot.params['id'];

  constructor(private formBuilder: FormBuilder, private noteService: NoteService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
       this.noteForm = this.formBuilder.group({
         note: [null, Validators.required]
       });
    }

    onReturn(): void{
    this.router.navigateByUrl(`patient/${this.patientId}`)
    }

    onSubmitForm(): void {
    this.note = new NewNote(
      this.patientId,
      this.noteForm.value.note
    );
    this.noteService.saveNewNote(this.note).pipe(tap(() => this.router.navigateByUrl(`patient/${this.patientId}`))).subscribe();
    }


}
