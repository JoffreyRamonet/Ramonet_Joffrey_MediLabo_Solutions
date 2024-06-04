import {Injectable} from "@angular/core";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakEventType, KeycloakService} from "keycloak-angular";
import {AuthService} from "./auth-service";

@Injectable({providedIn: "root"})
export class SecurityService {
  public profile?: KeycloakProfile;
  role!: string[];

  constructor(public keycloakService: KeycloakService, private auth: AuthService) {
    this.init();
    this.role = this.auth.getRoles();
  }

  init() {
    console.log("Init ....")
    this.keycloakService.keycloakEvents$.subscribe({
      next: (e) => {
        console.log(e);
        if (e.type == KeycloakEventType.OnAuthSuccess) {
          console.log("OnAuthSuccess")
          this.keycloakService.loadUserProfile().then(profile => {
            this.profile = profile;
          });
        }
      },
      error: err => {
        console.log(err);
      }
    });
  }

  public isAdmin(): boolean {
    return (this.role.includes('ADMIN'))

  }
}
