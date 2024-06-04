import {Component, OnInit} from '@angular/core';
import {Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NewTrigger} from "../model/newTrigger.model";
import {AccessorService} from "../service/accessor-service";
import {Router} from "@angular/router";
import {Observable, tap} from "rxjs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {Trigger} from "../model/trigger.model";

@Component({
  selector: 'app-new-trigger',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelect,
    MatOption
  ],
  templateUrl: './manage-trigger.component.html',
  styleUrl: './manage-trigger.component.scss'
})
export class ManageTriggerComponent implements OnInit {

  triggerForm!: FormGroup;
  deleteTriggerForm!: FormGroup;
  trigger!: NewTrigger;
  trigger$!: Observable<Trigger[]>
  triggers!: Trigger[]

  selection!: string;

  constructor(private formBuilder: FormBuilder, private accessorService: AccessorService, private router: Router) {
  }

  ngOnInit(): void {
    this.triggerForm = this.formBuilder.group({
        name: [null, Validators.required]
      })
    this.deleteTriggerForm = this.formBuilder.group({
      id: [null, Validators.required]
    })
    this.trigger$ = this.accessorService.getAllTriggers();
    this.accessorService.getAllTriggers().subscribe(value => this.triggers = value);
  }

  onReturn(): void {
    this.router.navigateByUrl(``);
  }

  onSubmitForm(): void {
    this.trigger = new NewTrigger(
      this.triggerForm.value.name
    );
    this.accessorService.saveNewTrigger(this.trigger).pipe(tap(() => window.location.reload())).subscribe();
  }

  onSubmitEditForm(): void{
    this.accessorService.deleteTrigger(this.selection).pipe(tap(() => window.location.reload())).subscribe();
  }


}
