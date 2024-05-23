export const environment= {
  production: false,
  envName: 'local',
  keycloak: {
    issuer: 'http://localhost:8080/',
    realm: 'Medilabo-Solutions-realm',
    clientId: 'angular_login',
    redirectUri: 'http://localhost:4200',
    responseType: 'code',
    scope: 'openid profile email',
    requireHttps: false,
    showDebugInformation: true,
    disableAtHashCheck: true
  }
}
