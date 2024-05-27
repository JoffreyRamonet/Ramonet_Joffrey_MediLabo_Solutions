import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {AuthService} from "../security/auth-service";

@Component({
  selector: 'app-aside',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './aside.component.html',
  styleUrl: './aside.component.scss'
})

export class AsideComponent {

  constructor(public keycloak: AuthService) {}

  }
