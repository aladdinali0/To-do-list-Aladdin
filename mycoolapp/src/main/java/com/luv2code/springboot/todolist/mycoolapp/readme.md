# Todolist Application

Welcome to my first to-do list application built with Java Springboot.

## Overview 
This application provides basic CRUD operations for managing tasks in a to-do list. Users can create, read, update, and delete tasks through RESTful API endpoints.


## Features

- Create a new task
- Read all tasks
- Update an existing task
- Delete all tasks

## Project Setup 
Clone the repository to your local machine.
Open the project in IntelliJ IDEA.
Ensure your MySQL server is running and configured correctly.
Run the application from IntelliJ.
Open Postman and start interacting with your API endpoints.

# Tech used 
- Java
- Spring Boot
- Spring Data JPA
- Spring Web
- MySQL Workbench
- Postman
- MySQL Connector
- React 


## API Endpoints

- `GET /api/todolists`: Retrieve all tasks.
- `POST /api/todolists`: Create a new task.
- `PUT /api/todolists`: Update an existing task.
- `DELETE /api/todolists`: Delete all tasks.
- `POST /api/todolists/addTask`: Add the necessary task and description params.

## Viewing and Managing 

You can view and manage your tasks through MySQL Workbench or by accessing the application URL:

- Backend Results: Access via MySQL Workbench
- Frontend Interface: Visit http://localhost:3000/ to input tasks and descriptions



## Feedback 
Feel free to provide any suggestions or feedback for improvement. Thank you !

## New Updates:
- In my new TodolistRestController `POST /api/todolists/addTask`: 
this simply makes it easier for myself to create new entries for 
my todolist backend application.So instead of hardcoding all the entries 
needed to process: id, task, description and completed for my POST, 
I only need to type in the params needed which is ,Task and Description.


- In my client folder, I used React to create a small front end server to communicate/create a fetch request between 
my backend, still in progress.
- Added CorsConfig class in my backend project to make it happen. 
- For now, with the addition of my frontend server/react app 'http://localhost:3000/',
this url allows the user to input things in my to-do-list and my backend will 
retrieve the information that was entered.
- More functionalities will be implemented soon ! 



