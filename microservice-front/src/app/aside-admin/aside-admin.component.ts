import { Component } from '@angular/core';
import {NgIf} from "@angular/common";
import {RiskAsideComponent} from "../risk-aside/risk-aside.component";
import {RouterLink} from "@angular/router";
import {SecurityService} from "../security/SecurityService";

@Component({
  selector: 'app-aside-admin',
  standalone: true,
	imports: [
		NgIf,
		RiskAsideComponent,
		RouterLink
	],
  templateUrl: './aside-admin.component.html',
  styleUrl: './aside-admin.component.scss'
})
export class AsideAdminComponent {


  isAdmin!: boolean;

  constructor(private securityService: SecurityService) {
    this.isAdmin = securityService.isAdmin();
  }

}
