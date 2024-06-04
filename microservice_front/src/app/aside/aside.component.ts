import {Component} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {AuthService} from "../security/auth-service";
import {RiskAsideComponent} from "../risk-aside/risk-aside.component";
import {AsideAdminComponent} from "../aside-admin/aside-admin.component";
@Component({
  selector: 'app-aside',
  standalone: true,
  imports: [
    RouterLink,
    NgIf,
    RiskAsideComponent,
    AsideAdminComponent
  ],
  templateUrl: './aside.component.html',
  styleUrl: './aside.component.scss'
})

export class AsideComponent {

  display: boolean = true;


  constructor(public keycloak: AuthService, private router: Router) {
    router.events.subscribe((val) => {
      if (router.url === '/' || router.url === '/new/patient' || router.url === '/triggers' ) {
        this.display = false;
      } else {
        this.display = true;
      }
    })

  }

}
