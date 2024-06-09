import {Component, numberAttribute, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NewNoteModel} from "../model/newNote.model";
import {NoteService} from "../service/note-service";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable, tap} from "rxjs";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {diagnose} from "@angular-devkit/build-angular/src/tools/esbuild/angular/compilation/parallel-worker";
import {AccessorService} from "../service/accessor-service";
import {Patient} from "../model/patient.model";
import {Trigger} from "../model/trigger.model";
import {PatientHeaderComponent} from "../patient-header/patient-header.component";

@Component({
  selector: 'app-new-note',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    AsyncPipe,
    NgForOf,
    PatientHeaderComponent
  ],
  templateUrl: './new-note.component.html',
  styleUrl: './new-note.component.scss'
})
export class NewNoteComponent implements OnInit {

  noteForm!: FormGroup;
  note!: NewNoteModel;
  patientId: string = this.route.snapshot.params['id'];
  display: boolean = false;
  triggers$!: Observable<Trigger[]>;
  triggersName!: string[];
  sub1!: string[];
  sub2!: string[];

  constructor(private formBuilder: FormBuilder, private noteService: NoteService, private accessorService: AccessorService, private router: Router, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.noteForm = this.formBuilder.group({
      note: [null, Validators.required]
    });
    this.triggers$ = this.accessorService.getAllTriggers();
    this.accessorService.getAllTriggers().subscribe(val => {
      this.triggersName = val.map(value => value.name)
      let half = Math.floor(this.triggersName.length/2);
      this.sub1 = this.triggersName.slice(0, half);
      this.sub2 = this.triggersName.slice(half, this.triggersName.length);


    });



  }


  onReturn(): void {
    this.router.navigateByUrl(`patient/${this.patientId}`)
  }

  onSubmitForm(): void {
    this.note = new NewNoteModel(
      this.patientId,
      this.noteForm.value.note
    );
    this.noteService.saveNewNote(this.note).pipe(tap(() => this.router.navigateByUrl(`patient/${this.patientId}`))).subscribe();
  }

  onDisplay() {
    this.display = !this.display;
  }


}
