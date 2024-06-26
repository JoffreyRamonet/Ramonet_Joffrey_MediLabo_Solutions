import {Component, Input} from '@angular/core';
import {AsyncPipe, DatePipe, NgIf} from "@angular/common";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Note} from "../model/note.model";
import {NoteService} from "../service/note-service";
import {tap} from "rxjs";
@Component({
  selector: 'app-single-note',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    RouterLink,
    DatePipe
  ],
  templateUrl: './single-note.component.html',
  styleUrl: './single-note.component.scss'
})
export class SingleNoteComponent {

  @Input() note!: Note;
  id: string = this.route.snapshot.params['id'];

    constructor(private noteService: NoteService, private router: Router, private route: ActivatedRoute){
    }

  onEditNote(): void{
      this.router.navigateByUrl(`note/edit/${this.note.id}`)
  }

  onDeleteNote(): void {
     this.noteService.deleteNote(this.note.id).pipe(tap(() => window.location.reload())).subscribe();

  }
}


