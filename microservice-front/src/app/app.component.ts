import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ActivationStart, NavigationEnd, NavigationStart, Route, RouteConfigLoadStart, Router, RouterOutlet, RoutesRecognized} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {
  PatientListComponent
} from "./patient-list/patient-list.component";
import {AsideComponent} from "./aside/aside.component";
import {KeycloakAngularModule} from "keycloak-angular";
import {HttpClientModule} from "@angular/common/http";
import {AuthService} from "./security/auth-service";
import {RiskAsideComponent} from "./risk-aside/risk-aside.component";
import {NgIf} from "@angular/common";
import {AsideAdminComponent} from "./aside-admin/aside-admin.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, PatientListComponent, AsideComponent, KeycloakAngularModule, HttpClientModule, RiskAsideComponent, NgIf, AsideAdminComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'microservice_front';
  token !: null | string;
  showRiskAside: boolean = false;
  url!: string;


  constructor(public keycloak: AuthService) {

  }

  // The interceptor doesn't work if the ngOnInit is removed... But why?..

  ngOnInit(): void {
    this.keycloak.getToken().then(data => {
      this.token = data;
    })
  }
}

