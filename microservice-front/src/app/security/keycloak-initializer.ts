import {KeycloakService} from "keycloak-angular";
import {Environment} from "../../environments/environment";

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: Environment.keycloak.issuer,
        realm: Environment.keycloak.realm,
        clientId: Environment.keycloak.clientId
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe : false,
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}
