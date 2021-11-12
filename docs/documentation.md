# Design Documentation

## Requirements
For this project, we were required to develop a simple web-based Point of Sale application that allowed a user to enter their credit card details to make a transaction. The application would then need to communicate with the mock banking service using the ReST service supplied to either confirm or deny the transaction.

The Point of Sale device also needed to be configurable by an admin user as transactions would require the data associated with the PoS device to transfer funds to or from the correct user account.

The full assessment requirements can be viewed [here](https://learn.solent.ac.uk/pluginfile.php/2938474/mod_resource/content/1/Assessment%20Brief%20-%20COM528-504%20-%20AE1%20-%20GROUP%20%282021-2022%29%201.0.pdf).

## Goals and Non-Goals

### **Goals**

- The UI should be a user-friendly web interface
- The identity of the device should be enterable by an administrator
- The device should record all successful and unsuccessful transactions
- Users should be able to enter their credit card details (name, credit card number, expiry date, cvv code)
- Users should be able to make a payment
- Users should be able to make a refund
- The application should interact with the bank service using a ReST service
- Money should be transferred between accounts correctly
- Data input validation required throughout
- Authentication required on administrator account
- Authentication required on transaction request
- All error messages should be user-friendly and informative

### **Non-Goals**

- CVV codes should not be recorded
- The PoS device should not validate the CVV code

## Project Plan and Task Assignment

We statrted planning this project by splitting it down into smaller, manageable tasks that only addressed one requirement at a time. We then recorded these on Github Projects and divided the tasks up into three *To Do* categories - front-end, back-end, and documentation. 

We initially recorded these tasks as notes within the project, updating them to include the relevant name of the person doing the task when it was moved to *In Progress*. However, later we learned about creating Issues within Github so we converted all outstanding tasks to these.

![Github Project Screenshot](images/githubProject.PNG)
>Screenshot of Github Project in progress

We decided on a top-down approach to this project, as how data was handled in the front-end would directly affect how it needed to be handled in the back-end. 

We initially designed the project to be multi-module but, as the application only required simple functionality, we refactored this into a single WAR with packages for the layers instead.

We also decided to use MVC for the project structure, as this separated back-end functionality from the front-end, and would therefore make the code a lot more readable and easier to debug.

We assigned the first tasks, such as setting up the project in NetBeans, creating the UIs, etc, but task assignment thereafter was managed dynamically, depending on what had already been implemented and who was currently available to work on the project, with regular meetings online and in-person to discuss. As each task was assigned, the ticket on Github Projects was updated accordingly.

All the following tasks are also available on [Github Projects](https://github.com/TomStrong/Com528-Assessment/projects/1):

| Assignee        | Task     | Description    |
| :------------- | :----------: | :----------: | 
| Jake / Jo | ![Task 1](images/tasks/task1.PNG) | Jake initially created a new project in NetBeans. This was then refactored by Jo to include all the client packages. |
| Jo | ![Task 2](images/tasks/task2.PNG) | All imported client packages needed to refactored into our group name. |
| Jo | ![Task 3](images/tasks/task3.PNG) | A standalone bank client module became available so project was started again without multi-modules. |
| Tom | ![Task 4](images/tasks/task4.PNG) | Design and implement a user-friendly UI that resembles a real PoS device, using JavaScript and CSS where appropriate. |
| Jo | ![Task 5](images/tasks/task5.PNG) | Design and implement a basic administrator page that allows entry of the identity for the PoS device.  |
| Jo | ![Task 6](images/tasks/task6.PNG) | Implement a basic index page which allows easy access to both admin and PoS pages. |
| Jo | ![Task 7](images/tasks/task7.PNG) | Create default.properties file and ensure data is valid. |
| Jo | ![Task 8](images/tasks/task8.PNG) | Class creation. |
| Tom / Jo | ![Task 9](images/tasks/task9.PNG) | Basic functionality implemented for existing pages. |
| Merlin | ![Task 10](images/tasks/task10.PNG) | Created use case UML diagram. |
| Jo | ![Task 111](images/tasks/task11.PNG) | Implemented functionality of the admin page, with data retrieved and updated in the .properties file. Also implemented functionality for transactions, reading in PoS identity data and input data to make a ReST call to the bank service. |
| Jo | ![Task 12](images/tasks/task12.PNG) | Implemented workflow through Github actions so all code is tested as soon as pushed to repo. |
| Tom | ![Task 13](images/tasks/task13.PNG) | Ensured all error messages are user-friendly and informative. |
| Tom | ![Task 14](images/tasks/task14.PNG) | Updated formatting on PoS to be more user-friendly and readable. |
| Jo / Jake | ![Task 15](images/tasks/task15.PNG) | Jo updated Log4J to include transaction logs. However, this would not work so Jake created a bespoke logging class. |

>>**_NOTE:_**  
We included an index page for ease of the assessor for this assessment only. In a real-life situation, the user would only have access to the PoS device itself.

## Use Cases and Features

The following uses cases describe how users will use the application and outlines the system behaviour in response. 

| ID   | Actor    | Action     | System Behaviour |
| :-------------: | :-------------:  | :------------- | :-------------  | 
| UC1   | Admin  | Admin configures the PoS device   | The identity of the device is updated locally. | 
| UC2   | Admin |  Admin configures the PoS device but misses a field  | The identity of the device is not updated, and an error message is displayed on page.    | 
| UC3   | User  |    |     | 
| UC4   | User  |    |     | 

**To do**

## Test Plan

**To do**

## UML Diagrams

**Use Case UML Diagram**


![Use Case UML Diagram](UMLDiagrams/UseCaseDiagram.jpg)


**Robustness UML Diagram**

**To do**