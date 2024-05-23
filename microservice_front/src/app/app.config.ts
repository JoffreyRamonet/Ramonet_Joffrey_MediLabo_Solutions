import {APP_INITIALIZER, ApplicationConfig} from '@angular/core';
import { provideRouter} from '@angular/router';
import {routes} from './app.routes';
import { provideHttpClient} from "@angular/common/http";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';

import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {initializeKeycloak} from "./security/keycloak-initializer";


export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideHttpClient(), provideAnimationsAsync(), KeycloakAngularModule, KeycloakService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true,
    }
  ]
};














