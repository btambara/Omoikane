# Project Omoikane
Named after the Shinto god of wisdom and intelligence, Project Omoikane will be the code for my personal website. It will be based off the microservice architecture. All work within this repo has been written by me. 

## Gateway Service
The gateway service will serve as the entry point for users. This service was built off of Spring Boot and is capable of handling user authentication. The gateway will also handle microservice discovery by utilizing Neflix Eureka. Netflix Zuul was added in order to simplify RESTful calls to the microservices that are below it.

## Resume Service
The resume service will provide the user with RESTFUL operations for my resume. This service was developed on Spring Boot. 

## Client
The frontend was built off of Angular 7. I used the Materials framework to enhance the layout of the web application.
