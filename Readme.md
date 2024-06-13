# RAMONET-Joffrey_MEDILABO_SOLUTIONS

![The medilabo_solutions logo](/assets/logo.jpeg)

<div style="display: block; margin-left: 40%">

## Table of contents

- [I. What is medilabo_solutions?](#what-is-medilabo_solutions?)
    - [I-I. What is this software?](#what-is-this-software?)
    - [I-II. How does it work?](#how-does-it-work?)
    - [I-III. Does it adapt to research?](#does-it-adapt-to-research?)
- [II. Technologies used](#technologies-used)
- [III. Architecture and design](#architecture)
    - [III-I. Microservice-front](#microservice-front)
    - [III-II. Microservice-gateway](#microservice-gateway)
    - [III-III. Keycloak](#keycloak)
    - [III-IV. Microservice-configuration-server](#microservice-configuration-server)
    - [III-V. Microservice-eureka-server](#microservice-eureka-server)
    - [III-VI. Microservice-backend-patient](#microservice-backend-patient)
    - [III-VII. Microservice-backend-note](#microservice-backend-note)
    - [III-VIII. Microservice-backend-assessor](#microservice-backend-assessor)
- [IV. Docker](#docker)
- [V. Docker-compose](#docker-compose)
    - [V-I. Databases](#databases)
    - [V-II. Keycloak](#keycloak)
    - [V-III. Configuration-server](#configuration-server)
    - [V-IV. Microservice-eureka-server](#microservice-eureka-server)
    - [V-V. Microservice-gateway](#microservice-gateway)
    - [V-VI. Microservice-backend-assessor and patient](#microservice-backend-assessor-and-patient)
    - [V-VII. Microservice-backend-note](#microservice-backend-note)
- [VI. Recover the project](#recover-the-project)
    - [VI-I. Just recover the project ](#just-recover-the-project)
    - [VI-II Recover and modify the project](#recover-and-modify-the-project)
- [VII. Running the project](#running-the-project)
    - [VII-I. Local running](#local-running)
    - [VII-II. Dockers running](#docker-running)
- [VIII. Easy start](#easy-start)

</div>

___
___

## <a id="what-is-medilabo_solutions?"></a>I. What is medilabo_solutions?

Médilabo_solution is a software solutions development company, designed to help clinics and private health practices
detect disease risks.

<div style="display: block; margin-left: 2%">

___

### <a id="what-is-this-software?"></a>I-I. What is this software?

This software allows, thanks to the ratings of health professionals, to issue an index of chance of contracting a type 2
diabetes.

___

### <a id="#how-does-it-work?"></a>I-II. How does it work?

<p>
The software stores patient information and notes, writed by health professionals during consultations.<br>
Practicians have access to a list of keywords allowing the software to recognize an increase in the risk of contracting type 2 diabetes.<br>
Thus, by adding all the keywords present in the patient’s notes, according to his personal data, the software is able to calculate the level of risk that the person runs.
</p>

___

### <a id="does-it-adapt-to-research?"></a>I-III. Does it adapt to research?

Of course, practician mentors can be identified and have the rights to modify the keyword list, by adding, modifying or
deleting a word.

</div>

___
___

## <a id="technologies-used"></a>II. Technologies used

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Angular](https://img.shields.io/badge/Angular-17-cc0099)](https://v17.angular.io/docs)
[![MySQL](https://img.shields.io/badge/MySQL-8.0.36-0099ff)](https://dev.mysql.com/doc/relnotes/mysql/8.0/en/)
[![MongoDB](https://img.shields.io/badge/mongoDB-2.2.6-006600)](https://www.mongodb.com/docs/manual/release-notes/)
[![Keycloak](https://img.shields.io/badge/Keycloak-24.0.4-808080)](https://www.keycloak.org/2024/05/keycloak-2404-released)
[![Docker](https://img.shields.io/badge/Docker-26.1.4-3333ff)](https://docs.docker.com/engine/release-notes/26.1/)
[![Docker-compose](https://img.shields.io/badge/Docker--compose-2.27.1-66ccff)](https://docs.docker.com/compose/release-notes/)
<p><em>(All badges are links to their documentation)</em></p>

<p>
The project is based on a microservice architecture and uses SpringBoot as a framework.<br>
The backend is write in java language and the frontend uses Angular with Typescript.<br>
The authentication is managed by Keycloak<br>
Data are persisted in MySQL database and mongoDB.<br>
To export the project, Docker and Docker-compose are present in the repository.
</p>

___
___

## <a id="architecture"></a>III. Architecture and design

<p>
This project is a microservice software.<br>
They're three layers in the architecture:<br>

- Frontend layer, with the microservice-front angular.<br>
- Keycloak layer, to authenticate users.<br>
- Backend layer, with all microservice-backend and the gateway to communicate with the frontend.

</p>


*** schema ***

<div style="display: block; margin-left: 2%">

___

### <a id="microservice--front"></a>III-I. Microservice-front

[![Angular](https://img.shields.io/badge/Angular-17-cc0099)](https://v17.angular.io/docs)

<p>This microservice generate a <a href="https://blog.angular-university.io/why-a-single-page-application-what-are-the-benefits-what-is-a-spa/">Single Page Application</a> for the user interface.<br>
When the user call the url of the SPA, he is automatically redirect on the login's page of the keycloak server.<br>
The default url is: `localhost:4200`.
</p>



<p>
The service package contains all classes handle http requests to the backend. 
There are a service class to each microservice-backend but all requests are sent to the microservice-gateway on: ` localhost:9000`.

> `assessor-service.ts` -> `microservice-gateway` -> `microservice-backend-assessor`<br>
> `note-service.ts` -> `microservice-gateway` -> `microservice-backend-note`<br>
> `patient-service.ts` -> `microservice-gateway` -> `microservice-backend-patient`

</p>

<p>
The security package contains all classes handle keycloak management, interceptors and guards.<br>

[![Keycloak-js](https://img.shields.io/badge/keycloak--js-doc-ff0000)](https://www.keycloak.org/docs/latest/securing_apps/index.html#_javascript_adapter)
[![Keycloak-Angular](https://img.shields.io/badge/keycloak--angular-doc-ff6600)](https://www.npmjs.com/package/keycloak-angular)

> `guard` -> requires all users to be authenticated to access the application.<br>
> `admin-guard` -> requires users who want display the `aside-admin` to be authenticates on an admin account.<br>
> `keycloak-initializer` -> configures keycloak in angular.<br>
> `auth-service` -> the class handle the authentication service with keycloakService.<br>
> `security-service` -> load the keycloak profile after authentication.<br>
> `auth-Interceptor` -> adds header, essentially the `BearerToken`
 
All components require authentication. To display aside-admin component and to access to manage-trigger component, an authentication with an admin account is required.
</p>
<p>
If you require to write new environment variables, a file is provided for in the environments folder.
</p>

___

### <a id="microservice-gateway"></a>III-II. Microservice-gateway

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Spring-Gateway](https://img.shields.io/badge/SpringBoot-Gateway-006600)](https://spring.io/projects/spring-cloud-gateway)
[![Spring-Gateway](https://img.shields.io/badge/SpringBoot-Security-003300)](https://spring.io/projects/spring-security)

<p>
This microservice is the filter and unique way between the frontend and the backend. It's also the unique authentication security filter for the backend.<br>
The `SecurityWebFilterChain` handle the Cross Origin Resource Sharing to only accept http requests from `localhost:4200`.<br>
The only way to be authenticated by the filter chain is by BearerToken with the oauth2-resource-server dependency.
Naturally, the entire frontend requires authentication so requests sent to the gateway must be authenticated by JWT.
</p>
<p>
The route mapping is declared in the `microservice-gateway.properties`.<br>
All microservice's name is remove from the url and replaced by the end of the name like a predicate. 

> example:
> microservice-backend-patient/v1/all -> patient/all

The host and the port of each microservice is given by the discovery server to perform the right redirection.<br>
All microservice-backend are run with a random port, so it is not possible to define them.
</p>

___

### <a id="keycloak"></a>III-III. Keycloak

[![Spring](https://img.shields.io/badge/Keycloak-24.0.4-808080)](https://www.keycloak.org/2024/05/keycloak-2404-released)

<p>
In this project, the registration, the login page, the JWT management and the users account are manages by a keycloak server.<br>
Cause of more simplicity, keycloak is added in the docker-compose.yml to run it without installation.<br>
The keycloak realm for the project is automatically imported when the docker is builds.

Some informations:

> realm: `Medilabo-Solutions-Realm`<br>
> microservice-front client: `angular_login`<br>
> microservice-gateway client: `gateway`<br>
> host: `localhost`<br>
> default port: `8080`<br>
> default admin console username: `admin`<br>
> default admin console password: `admin`<br>
> default user name: `usertest`<br>
> default user password: `password`<br>
> default admin name: `admintest`<br>
> default admin password: `password`<br>

</p>
<br>

#### angular_login client

<p>
The angular_client is an OpenID Connect client. The user authenticates with their username and password.
If authentication is successful, keycloak send an access token to angular. This client has two roles, user and admin.
When the user is authenticated, keycloak redirects to the `localhost:4200/`.
</p>

#### gateway client

<p>
The gateway client is a Bearer only client. The user can't perform authentication directly to this client with their credentials.
When the microservice-front sends an http request to the gateway, the bearer token is added to the request header. Spring security 
takes this token and sends it to keycloak to verify its authenticity and let the request pass or return an http 401 error.
This client has juste one role, user.
</p>

<p>
The JWT sends by the keycloak realm has a validity time of 30min. No refresh token is configured for security reasons. After expiration, the user must re-authenticate.
</p>

<p>
To modify the realm imported, modify the following file:

> | root<br>
> &nbsp;&nbsp;&nbsp;|- keycloak<br>
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|- imports<br>
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- realm.json<br>

</p>

___

### <a id="microservice-configuration-server"></a>III-IV. Microservice-configuration-server

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Spring-Gateway](https://img.shields.io/badge/SpringBoot-Cloud--Config-ffff00)](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)

<p>All configuration files are stored in an external remote repository. Like that, the configuration can to be modified without reboot.

[![repository](https://img.shields.io/badge/The%20remote%20repository-00ffff)](https://github.com/MiniGTI/Ramonet_Joffrey_MediLabo_Solutions_Configuration_Repository)


Application.properties must respect the following syntax:

>  <spring.application.name>.properties -> `microservice-gateway.properties`

In each remote properties file, some informations are mandatories:

> `server.port` -> <strong>MANDATORY</strong>, required to call the microservice.<br>
> `spring.cloud.config.overrideNone=true` -> required if the configuration should be able to be modified externally, with docker-compose for example.<br>

</p>
<p>
In each module stay a file named bootstrap.properties with only this information:

> `spring.application.name` -> <strong>MANDATORY</strong>, it's required to identify the application.<br>
> `spring.cloud.config.uri` -> <strong>MANDATORY</strong>, it's required to ask configuration to the server.<br>
> `management.endpoints.web.exposure.include=refresh` -> <strong>MANDATORY</strong>, it's required to update the configuration periodically.

More generally, the fixed information will be in the bootstrap.properties and the others in the remote file.
</p>
<p>
Some informations:

> host: `localhost`<br>
> port: `9101`

</p>

___

### <a id="microservice-eureka-server"></a>III-V. Microservice-eureka-server

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Spring-Gateway](https://img.shields.io/badge/Spring%20Netflix-Eureka-bf00ff)](https://cloud.spring.io/spring-cloud-netflix/reference/html/)

<p>
This microservice is the discovery service of the project. Eureka get all microservice instance and gives instructions to be able to contact the different services at the gateway.
This is the only way for the gateway to know the other microservices. Since eureka is the only one who knows how to call other microservices,
its access is secured by identifiers.
</p>
<p>
Some informations:

> host: `localhost`<br>
> port: `9102`<br>
> default username: `eureka`<br>
> default password: `password`<br>

</p>

___

### <a id="microservice-backend-patient"></a>III-VI. Microservice-backend-patient

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Spring](https://img.shields.io/badge/MySQL-8.0.36-0099ff)](https://dev.mysql.com/doc/relnotes/mysql/8.0/en/)

<p>
This microservice manage all patient's data. Its expose endpoints like a REST api in a controller, and saves informations in a MySQL database.
It has a simple layer architecture:

> |-microserviceback<br>
> &nbsp;&nbsp;&nbsp;|-controller&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : endpoints classes<br>
> &nbsp;&nbsp;&nbsp;|-dto&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : Data Transfer Object classes<br> 
> &nbsp;&nbsp;&nbsp;|-error&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : customize error classes<br>
> &nbsp;&nbsp;&nbsp;|-microservice_client&nbsp; : service classes for other microservice treatment<br>
> &nbsp;&nbsp;&nbsp;|-model&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : entity classes<br>
> &nbsp;&nbsp;&nbsp;|-proxy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : other microservice repository classes<br>
> &nbsp;&nbsp;&nbsp;|-repository&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : MySql repository classes<br>
> &nbsp;&nbsp;&nbsp;|-service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : business treatments<br>
> &nbsp;&nbsp;&nbsp;|-stub&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : data and classes for tests<br>
</p>
<br>

#### Model

<p>
A patient model look's like:

```yaml
[
  {
    "lastName": "TestNone",
    "firstName": "Test",
    "birthDate": "1966-12-31 ",
    "gender": "F",
    "address": "1 Brookside St",
    "phone": "100-222-4444",
    "id": "73052162-5644-47c6-a76e-a3e6ee17eedb"
  }
]
```

<br>

#### DTO

A PatientSaveDto look's like:

```yaml
[
  {
    "lastName": "LastName",
    "firstName": "FirstName",
    "birthDate": "yyyy-MM-dd ",
    "gender": "F or M",
    "address": "address(optional)",
    "phone": "phone(optional)",
  }
]
```
<br>
A PatientUpdateDto look's like:

```yaml
[
  {
    "lastName": "LastName",
    "firstName": "FirstName",
    "gender": "F or M",
    "address": "address(optional)",
    "phone": "phone(optional)",
  }
]
```
<br>

Only fields address and phone are optionals. Fields birthdate and gender have restraints like:
> birthdate: (((20[012]\d|19\d\d)|(1\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))<br>
> gender: [MF]
</p>

<br>

#### Controller - endpoints

<p>
Default urls to call microservice-backend-patient are:

> default url:&nbsp;&nbsp;&nbsp;&nbsp; `/microservice-backend-patient/v1/patient`<br>
> get all:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/all`<br>
> get by id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/{id}`<br>
> save:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/save` with PatientSaveDto<br>
> update:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/update` with PatientUpdateDto<br>
> delete by id:&nbsp;&nbsp; `/delete/{id}`
</p>

<br>

#### proxy

<p>
This microservice requires to perform http request to the microservice-backend-note to delete all notes of a patient when he is removed from the database.
Thus databases never keep orphaned data. So the proxy package contains classes to do that.
</p>

___

### <a id="microservice-backend-note"></a>III-VII. Microservice-backend-note

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![MongoDB](https://img.shields.io/badge/mongoDB-2.2.6-006600)](https://www.mongodb.com/docs/manual/release-notes/)

<p>
This microservice manage all patient's notes. Its expose endpoints like a REST api in a controller, and saves informations in MongoDB.
It has a simple layer architecture:

> |-microservice_backend_note<br>
> &nbsp;&nbsp;&nbsp;|-controller&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : endpoints classes<br>
> &nbsp;&nbsp;&nbsp;|-dto&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : Data Transfer Object classes<br>
> &nbsp;&nbsp;&nbsp;|-error&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : customize error classes<br>
> &nbsp;&nbsp;&nbsp;|-initial_data&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : classes manage initial data for user test<br>
> &nbsp;&nbsp;&nbsp;|-model&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : entity classes<br>
> &nbsp;&nbsp;&nbsp;|-repository&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : MongoDB repository classes<br>
> &nbsp;&nbsp;&nbsp;|-service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : business treatments<br>
> &nbsp;&nbsp;&nbsp;|-stub&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : data and classes for tests<br>
</p>
<br>

#### Model

<p>
A note model look's like:

```yaml
[
  {
    "id": "39dbeca5-74bc-44ed-973b-03a197d852b4",
    "patient": "90229de5-9024-4cdd-8dec-f7c54669b336",
    "note": "Le patient déclare qu'il Fume depuis peu plus",
    "createdAt": "2024-06-01T17:36:18.364+00:00"
  }
]
```

``

<br>

#### DTO

A NoteDto look's like:

```yaml
[
  {
    "patient": "90229de5-9024-4cdd-8dec-f7c54669b336",
    "note": "Le patient déclare qu'il Fume depuis peu plus"
  }
]
```
<br>
A NoteUpdateDto look's like:

```yaml
[
  {
    "id": "39dbeca5-74bc-44ed-973b-03a197d852b4",
    "note": "Nouvelle note"
  }
]
```
</p>
<br>

#### Controller - endpoints

<p>
Default urls to call microservice-backend-note are:

> default url:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/microservice-backend-note/v1/note`<br>
> get all:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/all`<br>
> get by id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/{id}`<br>
> get by patient:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/patient/{id}`<br>
> save:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/save` with NoteDto<br>
> update:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/update` with NoteUpdateDto<br>
> delete by id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/delete/{id}`<br>
> delete by patient:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/patient/delete/{id}`
</p>

<br>

#### Initial_data

<p>
Since the project is in the development phase, data is required to perform real user tests.
This package contains classes to verify if all test data are present in mongodb. Otherwise, `InitialDataLoader` adds missing data.
</p>

___

### <a id="microservice-backend-assessor"></a>III-VIII. Microservice-backend-assessor

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/fr/java/technologies/downloads/#java21)
[![Spring](https://img.shields.io/badge/SpringBoot-3.3.0-green)](https://docs.spring.io/spring-boot/index.html)
[![Spring](https://img.shields.io/badge/MySQL-8.0.36-0099ff)](https://dev.mysql.com/doc/relnotes/mysql/8.0/en/)

<p>
This microservice manage all triggers and perform the risk calculation. Its expose endpoints like a REST api in a controller, and saves informations in a MySQL database.
It has a simple layer architecture:

> |-microservice_backend_assessor<br>
> &nbsp;&nbsp;&nbsp;|-bean&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : model classes for objects from other microservices.<br>
> &nbsp;&nbsp;&nbsp;|-controller&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : endpoints classes<br>
> &nbsp;&nbsp;&nbsp;|-dto&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : Data Transfer Object classes<br>
> &nbsp;&nbsp;&nbsp;|-error&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : customize error classes<br>
> &nbsp;&nbsp;&nbsp;|-microservice_client&nbsp; : service classes for other microservice treatment<br>
> &nbsp;&nbsp;&nbsp;|-model&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : entity classes<br>
> &nbsp;&nbsp;&nbsp;|-proxy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : other microservice repository classes<br>
> &nbsp;&nbsp;&nbsp;|-repository&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : MySql repository classes<br>
> &nbsp;&nbsp;&nbsp;|-service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : business treatments<br>
> &nbsp;&nbsp;&nbsp;|-stub&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : data and classes for tests<br>
</p>
<br>

#### Model

<p>
A Trigger model look's like:

```yaml
[
  {
    "id": "73052162-5644-47c6-a76e-a3e6ee17eedb",
    "name": "Hémoglobine A1C"
  }
]
```

A RiskResult model look's like:

```yaml
[
  {
    "result": "Borderline"
  }
]
```

<br>

#### DTO

A NewTriggerDto look's like:

```yaml
[
  {
    "name": "Douleur"
  }
]
```
<br>
A UpdateTriggerDto look's like:

```yaml
[
  {
    "id": "39dbeca5-74bc-44ed-973b-03a197d852b4",
    "name": "Douleurs"
  }
]
```
<br>

#### Bean

<p>
A PatientBean look's like:

```yaml
[
  {
    "lastName": "TestNone",
    "firstName": "Test",
    "birthDate": "1966-12-31 ",
    "gender": "F",
    "address": "1 Brookside St",
    "phone": "100-222-4444",
    "id": "73052162-5644-47c6-a76e-a3e6ee17eedb"
  }
]
```

A NoteBean model look's like:

```yaml
[
  {
    "id": "39dbeca5-74bc-44ed-973b-03a197d852b4",
    "patient": "90229de5-9024-4cdd-8dec-f7c54669b336",
    "note": "Le patient déclare qu'il Fume depuis peu plus",
    "createdAt": "2024-06-01T17:36:18.364+00:00"
  }
]
```
</p>
<br>

#### Controller - endpoints

<p>
Default urls to call microservice-backend-assessor are:

To perform request for Triggers:

> default url:&nbsp;&nbsp;&nbsp;&nbsp; `/microservice-backend-assessor/v1/trigger`<br>
> get all:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/all`<br>
> get by id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/{id}`<br>
> save:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/save` with PatientSaveDto<br>
> update:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/update` with PatientUpdateDto<br>
> delete by id:&nbsp;&nbsp; `/delete/{id}`


To perform request for RiskResult:

> default url:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/microservice-backend-assessor/v1/assessor`<br>
> get TriggerResult:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; `/{id}` with the patient's id
</p>

<br>

#### proxy

<p>
This microservice requires to perform http request to the microservice-backend-note to get all notes of a target patient.
With all notes, this microservice can read them and counts the number of triggers.
And this microservice requires to perform http request to the microservice-backend-patient to get informations of the target patient.
To perform the risk calculation, in additional of the number of Triggers, the service requires the gender and the age of the patient.
</p>

<br>

</div>

___
___

## <a id="docker"></a>IV. Docker

[![Docker](https://img.shields.io/badge/Docker-26.1.4-3333ff)](https://docs.docker.com/engine/release-notes/26.1/)

<p>
All microservices have a Dockerfile at the root.
All microservices Springboot have:

The following base image:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [![Eclipse-temurin:latest](https://img.shields.io/badge/eclipse%20temurin%20:latest-cc0066)](https://adoptium.net/fr/temurin/releases/)

Except the gateway, curl:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [![Curl](https://img.shields.io/badge/curl-336699)](https://curl.se/)

The gateway have net-tools:&nbsp;&nbsp;&nbsp;&nbsp; [![Net-tools](https://img.shields.io/badge/net--tools-ffcc00)](https://fr.linuxfromscratch.org/view/blfs-stable/basicnet/net-tools.html)

<br>
The microservice-front who contains Angular have curl and the following base image:

[![node:Alpine](https://img.shields.io/badge/node:alpine-009933)](https://hub.docker.com/_/node)
</p>

<br>

___
___

## <a id="docker-compose"></a>V. Docker-compose

[![Docker-compose](https://img.shields.io/badge/Docker--compose-2.27.1-66ccff)](https://docs.docker.com/compose/release-notes/)

<p>
Docker-compose is used to execute all dockers of the application, databases, and the keycloak server, at the good time, and stop the run if an error is throws.

A healthcheck system, checks if the service in the docker is ready. If this is the case, dockers who depend on it can start.

The launch order is timed by dependencies as follows:

First launch:

> keycloak-mysql<br>
> microservice-back-mysql<br>
> microservice-back-mongodb<br>
> configuration-server

Second launch:

> microservice-eureka-server<br>
> keycloak

Third launch:

> microservice-gateway<br>
> microservice-backend-patient<br>
> microservice-backend-note<br>
> microservice-backend-assessor<br>
> microservice-front

</p>

<div style="display: block; margin-left: 2%">

___

### <a id="databases"></a>V-I. Databases

<p>
There are three dockers for databases:

> `keycloak-mysql`  used by the keycloak service<br>
> `microservice-back-mysql` used by the microservice-backend-patient and assessor<br>
> `microservice-back-mongodb` used by the microservice-backend-note

`Keycloak-mysql` and `microservice-back-mysql` use &nbsp;&nbsp;&nbsp;  [![docker-mysql](https://img.shields.io/badge/Docker--mysql:8.0-0099ff)](https://hub.docker.com/_/mysql/)<br>
`microservice-back-mongodb` use &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;  [![docker-mongoDB](https://img.shields.io/badge/mongoDB:latest-006600)](https://hub.docker.com/_/mongo)
</p>

___

### <a id="keycloak"></a>V-II. Keycloak

<p>
The docker keycloak must wait the healthy return of the keycloak-mysql healthcheck.

The base image is:&nbsp;&nbsp;&nbsp;  [![Docker-keycloak](https://img.shields.io/badge/keycloak--24.0.4-a6a6a6)](quay.io/keycloak/keycloak:24.0.4)

When the docker runs, the domain is imported into the following folder:&nbsp; &nbsp; &nbsp;  [![realm](https://img.shields.io/badge/keycloak--import-b32400)](./keycloak/imports)

> `keycloak` contains the keycloak server.

A notable parameter is the `network_mode: host`. Because of keycloak realm is configured on localhost, all clients must be in localhost.
</p>

___

### <a id="configuration-server"></a>V-III. Configuration-server

The docker configuration-server use base image create by the &nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-configuration-server/Dockerfile)

___

### <a id="microservice-eureka-server"></a>V-IV. Microservice-eureka-server

The docker microservice-eureka-server use base image create by the &nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-eureka-server/Dockerfile) <br>
This docker must wait the healthy return of the configuration-server healthcheck.

___

### <a id="microservice-gateway"></a>V-V. Microservice-gateway

The docker microservice-gateway use base image create by the &nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-gateway/Dockerfile) <br>
This docker must wait the healthy return of the configuration-server healthcheck and of the keycloak healthcheck.

A notable parameter is the `network_mode: host`. It is required to perform request to the keycloak service.

___

### <a id="microservice-backend-assessor-and-patient"></a>V-VI. Microservice-backend-assessor and patient

The docker microservice-backend-assessor use base image create by the &nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-backend-assessor/Dockerfile) <br>
The docker microservice-backend-patient use base image create by the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-backend-patient/Dockerfile) <br>
This docker must wait the healthy return of the configuration-server healthcheck, of the microservice-back-mysql healthcheck and the microservice-eureka-server healthcheck.

___

### <a id="microservice-backend-note"></a>V-VII. Microservice-backend-note

The docker microservice-backend-note use base image create by the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-backend-note/Dockerfile) <br>
This docker must wait the healthy return of the configuration-server healthcheck, of the microservice-back-mongodb healthcheck and the microservice-eureka-server healthcheck.

### <a id="microservice-front"></a>V-VIII. Microservice-backend-front

The docker microservice-backend-note use base image create by the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; [![Dockerfile](https://img.shields.io/badge/Dockerfile-3333ff)](./microservice-front/Dockerfile) <br>
This docker must wait the healthy return of the keycloak healthcheck.

</div>

___
___

## <a id="recover-the-project"></a>VI. Recover the project

<div style="display: block; margin-left: 2%">

### <a id="just-recover-the-project"></a>VI-I. Just recover the project

If you’re not familiar with git:&nbsp;&nbsp;&nbsp; [![Git](https://img.shields.io/badge/Git%20tutorial-0052cc)](https://www.atlassian.com/fr/git/tutorials/what-is-git)

<p>
To clone the project on your local git repository, run your IDE or open the console and launch followings command:

> cd <your_git_directory><br>
> git clone https://github.com/MiniGTI/Ramonet_Joffrey_MediLabo_Solutions.git<br>
> cd ..<br>
> cd clone https://github.com/MiniGTI/Ramonet_Joffrey_MediLabo_Solutions_Configuration_Repository.git

You must replace the `<your_git_directory>` by your local route to the git directory.<br>
The first github link contains the project, and the second the remote repository who stores application.properties files for the configuration server.
</p>

___

### <a id="recover-and-modify-the-project"></a>VI-II. Recover and modify the project

<p>

If you want to take ownership of the project and create your own version, on the github project page, click on the grey button&nbsp;&nbsp;&nbsp; ![Git](https://img.shields.io/badge/Fork-808080) &nbsp; and create a new fork.
You must do that for the Medilabo_Solutions_Configuration_Repository too.
Then, clone your new project from your remote github repository to your local git with followings command:

> cd <your_git_directory><br>
> git clone <https://github.com/<your_profile>/<the_new_project_repository_url><br>
> cd ..<br>
> cd clone https://github.com/<your_profile>/<the_new_configuration_repository_url>

You must replace all <...>` by your own value.<br>
</p>

</div>

___
___

## <a id="running-the-project"></a>VII. Running the project

There are two ways to run the project, locally or with Dockers.

<div style="display: block; margin-left: 2%">

### <a id="local-running"></a>VII-I. Local running

<p>
If you want to run the project locally, you must do define all environment variables present in the different properties file in the configuration server remote repository.

To define them with the default values, check&nbsp;&nbsp;&nbsp; [![.env](https://img.shields.io/badge/.env%20file-ffffff)](./.env-example)

If you do not want to install locally MySQL, mongoDB and Keycloak. You can run the followings Dockers:

> keycloak-mysql<br>
> microservice-back-mysql<br>
> microservice-back-mongodb<br>
> keycloak

To perform that, you can run the following command:

> docker compose up keycloak-mysql microservice-back-mysql microservice-back-mongodb keycloak

Then, you must run first, the microservice-configuration-server with following commands:

> cd microservice-configuration-server
> mvn clean package -Dmaven.test.skip=true
> java -jar target/microservice-configuration-server-0.0.1-SNAPSHOT.jar

Ideally, after you should run the microservice-eureka-server with the previous commands.
Once previous microservices are started, you can run all remaining microservices.
</p>

___

### <a id="docker-running"></a>VII-II. Dockers running

<p>
If you want to run all microservices of the project in their docker, it's easy.

First you must package all microservices with this command:

> cd microservice-gateway && mvn clean package -Dmaven.test.skip=true &&<br>
> cd ../microservice-eureka-server && mvn clean package -Dmaven.test.skip=true &&<br>
> cd ../microservice-configuration-server && mvn clean package -Dmaven.test.skip=true &&<br>
> cd ../microservice-backend-patient && mvn clean package -Dmaven.test.skip=true &&<br>
> cd ../microservice-backend-note && mvn clean package -Dmaven.test.skip=true &&<br>
> cd ../microservice-backend-assessor && mvn clean package -Dmaven.test.skip=true && cd ..

Then, run the docker compose:

> docker compose up
</p>

</div>

___
___

## <a id="easy-start"></a>VIII. Easy start

<p>

To facilitate the handling and launch of the various microservices, a file [![Justfile](https://img.shields.io/badge/justfile-ffffff)](./justfile)  is provided in the repository.

There are two ways to use this file:

First, you can open it and launch from your IDE the command or the commands what you want to use.
Or, you can run commands in your IDE console, to display all available commands:

> just 

To run the project in dockers use the following commands:

> just p && just dr

</p>