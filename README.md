# Description
This project consists of: 
- api: Spring Boot application
- ui: Angular application

# Requirement
Backend
- JDK 8

Frontend
- Angular CLI: 1.6.4
- Node: 8.9.0
- Angular: 5.2.6

Docker

# Dockerization
Frontend has Dockerfile to create images. It runs in nginx server with additional configuration to access backend (see ui\nginx\default.conf)
Backend docker image can be created with mvn docker:build.

# How to create images
run ./create_image.sh in each project or ./create_images.sh from root

# How to run
Project contains docker-compose.yml file to run both containers.
To start applications(after images are built) run docker-compose up -d. Frontend will run on the port 80.

# How to run as developer
- backend:  run TomatoesApplication.main from your IDE with spring profile dev
- frontend: run npm install; ng serve --proxy-config proxy.conf.json;