import {APP_INITIALIZER, ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';
import {routes} from './app.routes';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';

import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {AuthService} from "./security/auth-service";
import {initializeKeycloak} from "./security/keycloak-initializer";
import {AuthInterceptor} from "./security/auth-Interceptor";


export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideHttpClient(withInterceptorsFromDi()), provideAnimationsAsync(), KeycloakAngularModule, KeycloakService, AuthService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
};















