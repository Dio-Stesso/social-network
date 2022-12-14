# 💻 Social Network "ChatHUB"
## - Brief -
>## A project that fully implemented a convenient and easy-to-understand messenger
## - Features -
- ### ⚙️ Registration / authentication & authorization
> ##### Each user can go to the home page of the application, log in to an existing account, or register if they don't have an account. 
> ##### A non-authenticated user will not be granted access to the basic functionality.
- ### 👤 Scrolling through the list of users
> ##### The user has the ability to view the existing users and to write (create a chat room) to them by clickable button.
- ### 🧑‍💻 Chatting
> ##### In an open chat room with a user, you will be able to send and receive messages in real time immediately.

## - Project structure
### The project back-end part is based on `three-tier architecture` with `Model-View-Controller` scheme which makes it more comfortable to use in practice and further expansion of the project.
- ### 🖥️ Presentation Layer
>##### Level of software operation, interaction with the service layer, intuitive for the user.
- ### ⚙️ Service Layer
> ##### Layer that organises the business logic of the programme, operating on commands from the top layer.
- ### 🗃️ Data Layer
> ##### Full interaction with the database and logical layer.

## - Technologies
- ### Java, Maven
- ### Spring Boot (Web, Data, Security, WebSocket, Mail)
- ### Angular (v.14.2.8) + Node.js (v.16.10.0)
- ### Docker
- ### MySQL

## - Project launch
### 1. ⬇️ Install Docker Desktop and register on DockerHub
### 2. ▶️ Run next command in terminal from social-network-api directory:
> #### mvn clean package
### 3. ▶️ Run next command in terminal from the main directory:
> #### docker-compose up --build
### 5. 🌐 Open your browser on http://localhost:4200.

## Contributors 

- ### 🧑‍💻 Oleh Sunytsia | GitHub: Oleg-pyt
- ### 🧑‍💻 Oleksandr Tsilynko (Alex Alexandrov) | GitHub: Dio-Stesso