export const Environment= {
  name: 'docker',
  keycloak: {
    issuer: 'https://localhost:8080/auth',
    realm: 'Medilabo-Solutions-realm',
    clientId: 'angular_login',
    redirectUri: 'http://localhost:4200'

  }
}
