import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Note} from "../model/note.model";
import {NewNoteModel} from "../model/newNote.model";
import {UpdateNoteModel} from "../model/updateNote.model";

@Injectable({
  providedIn: 'root'
})
export class NoteService{

  constructor(private http: HttpClient){}

  getAllNotes(): Observable<Note[]>{
    return this.http.get<Note[]>('http://localhost:9000/note/all');
  }

  getNoteById(id: string): Observable<Note>{
    return this.http.get<Note>(`http://localhost:9000/note/${id}`)
  }

  getNoteByPatientId(id: string): Observable<Note[]>{
    return this.http.get<Note[]>(`http://localhost:9000/note/patient/${id}`)
  }

  saveNewNote(newNote: NewNoteModel): Observable<Note>{
    return this.getAllNotes().pipe(switchMap => this.http.post<Note>('http://localhost:9000/note/save', newNote));
  }

  updateNote(updateNote: UpdateNoteModel): Observable<Note>{
    return this.http.patch<Note>(`http://localhost:9000/note/update`, updateNote);
  }

  deleteNote(id: string): Observable<void>{
    return this.http.delete<void>(`http://localhost:9000/note/delete/${id}`);
  }
  deleteNoteByPatientId(id: string): Observable<void>{
    return this.http.delete<void>(`http://localhost:9000/note/patient/delete/${id}`);
  }
}
