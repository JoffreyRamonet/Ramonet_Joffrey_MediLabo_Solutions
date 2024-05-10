import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {
  PatientListComponent
} from "./patient-list/patient-list.component";
import {AsideComponent} from "./aside/aside.component";

@Component({
  selector: 'app-root',
  standalone: true,
	imports: [RouterOutlet, HeaderComponent, PatientListComponent, AsideComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'microservice_front';
}
