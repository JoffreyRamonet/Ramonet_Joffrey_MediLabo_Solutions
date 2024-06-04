import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Trigger} from "../model/trigger.model";
import {NewTrigger} from "../model/newTrigger.model";
import {Risk} from "../model/risk.model";


@Injectable({
  providedIn: 'root'
})
export class AccessorService{

  constructor(private http: HttpClient){}

  getAllTriggers(): Observable<Trigger[]>{
    return this.http.get<Trigger[]>('http://localhost:9000/accessor/trigger/all');
  }

  getTriggerById(id: string): Observable<Trigger>{
    return this.http.get<Trigger>(`http://localhost:9000/accessor/trigger/${id}`)
  }

  saveNewTrigger(newTrigger: NewTrigger): Observable<Trigger>{
    return this.getAllTriggers().pipe(switchMap => this.http.post<Trigger>('http://localhost:9000/accessor/trigger/save', newTrigger));
  }

  deleteTrigger(id: string): Observable<void>{
    return this.http.delete<void>(`http://localhost:9000/accessor/trigger/delete/${id}`);
  }

  getRisk(id: string): Observable<Risk>{
    return this.http.get<Risk>(`http://localhost:9000/accessor/${id}`)
  }

}
