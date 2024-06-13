import {Injectable} from "@angular/core";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile, KeycloakTokenParsed} from "keycloak-js";
import {from, Observable} from "rxjs";

@Injectable()
export class AuthService{
  constructor(private keycloakService: KeycloakService){}

  token !: string | null;

  public getLoggedUser(): KeycloakTokenParsed | undefined {
    try {
      const userDetails: KeycloakTokenParsed | undefined= this.keycloakService.getKeycloakInstance().idTokenParsed;
      return userDetails;
    } catch (er){
      console.error("Exception", er);
      return undefined
    }
  }
  public isLoggedIn(): boolean {
    return this.keycloakService.isLoggedIn();
  }

  public loadUserProfile(): Promise<KeycloakProfile>{
    return this.keycloakService.loadUserProfile();
  }

  public logout(): void {
    this.keycloakService.logout(window.location.origin);
  }

  public isTokenExpired() {
    return this.keycloakService?.getKeycloakInstance().isTokenExpired();
  }

  public getRoles(): string[]{
    return this.keycloakService.getUserRoles();
  }

  refresh(): Observable<any>{
    return from(this.keycloakService.getKeycloakInstance().updateToken(1800));
  }

  async getToken() {
    if (!this.isLoggedIn()) {
      return null;
    }
    if (this.isTokenExpired()) {
      try {
        this.refresh();
      } catch (error) {
        return null;
      }
    }

    this.token = await this.keycloakService.getToken().then(jwt => this.token = jwt);
    return this.token;
    };


}
