import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {
  PatientListComponent
} from "./patient-list/patient-list.component";
import {AsideComponent} from "./aside/aside.component";
import {KeycloakAngularModule} from "keycloak-angular";
import {HttpClientModule} from "@angular/common/http";
import {AuthService} from "./security/auth-service";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, PatientListComponent, AsideComponent, KeycloakAngularModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'microservice_front';
  token !: null | string;

  constructor(public keycloak: AuthService) {}

  // The interceptor doesn't work if the ngOnInit is removed... But why?..

  ngOnInit(): void {
    this.keycloak.getToken().then(data => {
      this.token = data;
    })
  }
}

