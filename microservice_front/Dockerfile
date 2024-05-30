FROM node:alpine
RUN apk --no-cache add curl

WORKDIR /usr/src
COPY . /usr/src

RUN npm install -g @angular/cli
RUN npm install

CMD ng serve --host 0.0.0.0 --configuration=docker
EXPOSE 4200
