import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {
  PatientListComponent
} from "./patient-list/patient-list.component";
import {AsideComponent} from "./aside/aside.component";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {HttpClientModule} from "@angular/common/http";
import {KeycloakProfile} from "keycloak-js";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, PatientListComponent, AsideComponent, KeycloakAngularModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'microservice_front';

  public profile? : KeycloakProfile;
  constructor(public keycloakService:KeycloakService) {
  }
  ngOnInit() {
    if(this.keycloakService.isLoggedIn()){
      this.keycloakService.loadUserProfile().then(profile=>{
        this.profile=profile;
      });
    }
  }

}
