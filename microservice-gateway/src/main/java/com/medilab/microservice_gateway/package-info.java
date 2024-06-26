/**
 * All http requests from the microservice-front must go through this microservice-gateway to be authenticated them before requesting data from any backend microservices.
 * <p>
 * The application doesn't store any user account, all authentication/registration are the responsibility of a keycloak server.
 * The realm: Medilabo-Solutions-realm
 * The client: gateway
 * </p>
 */
package com.medilab.microservice_gateway;