FROM node:16.10.0-stretch-slim
WORKDIR /usr/src/app
COPY package.json .
RUN npm i -g @angular/cli@~14.2.8
RUN npm i
COPY . .
RUN ng build
CMD ng serve --host 0.0.0.0
