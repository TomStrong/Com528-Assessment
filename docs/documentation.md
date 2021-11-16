# Design Documentation

<summary> <h2>Table of Contents</h2> </summary>
    <ol>
        <li>
            <a href="#Requirement-Brief">Requirement Brief</a>
        </li>
        <li>
            <a href="#Goals-and-Non--Goals">Goals and Non-Goals</a>
            <ul>
                <li><a href="#Goals">Goals</a></li>
                <li><a href="#Non-Goals">Non-Goals</a></li>
            </ul>
        </li>
        <li>
            <a href="#Project-Plan-and-Task-Assignment ">Project Plan and Task Assignment</a>
        </li>
        <li>
            <a href="#Use-Cases-and-Features">Use Cases and Features</a>
        </li>
        <li>
            <a href="#Test-Plan">Test Plan</a>
        </li>
        <li>
            <a href="#Development-Design-Structure">Agile Methodology</a>
            <ul>
                <li><a href="#Use-case-text">Use case text</a></li>
            </ul>
            <ul>
                <li><a href="#UML-Diagrams">#UML Diagrams</a></li>
            </ul>
        </li>
    </ol>

## Requirement Brief
For this project you are required to use inheritance to develop a simple web based, Java Object Orientated, Point of Sale application which allows the user to enter credit card details and a cash amount for a transaction. 

When your program runs it should have a simple menu system that allows an administrator to configure the Point of Sale device. The menu should also have the option to use the Point of Sale device which will open the device interface for the user to input their information in order to make the transaction.

The Point of Sale should allow an admin to log in and configure the Point of Sale identity data, which the transactions require to successfully transfer funds to or from the correct account. 

The Point of Sale Device should first present the user with a choice about what type of transaction they would like to make, either a payment or a refund option should be available. The user should then be asked to enter their credit card information at which point the account details for the account the user is making a transaction with should be retrieved. 

The application should communicate using a ReST interface to a mock banking service supplied. The service will confirm or deny the transaction. A transaction will be unsuccessful if the credit card number is entered incorrectly, in which case the user should be prompted to enter their card details correctly. 

Once a transaction has been completed the user account should be updated with the new balance of the account.

The device should record all successful and unsuccessful transactions locally in a log file, cvv codes must not be recorded.



The full application requirements can be viewed [here](https://learn.solent.ac.uk/pluginfile.php/2938474/mod_resource/content/1/Assessment%20Brief%20-%20COM528-504%20-%20AE1%20-%20GROUP%20%282021-2022%29%201.0.pdf).

## Goals and Non-Goals

### **Goals**

- The UI will be a user-friendly web interface.
- The identity of the device will be enterable by an administrator.
- The device will record all successful and unsuccessful transactions.
- Users will be able to enter their credit card details (name, credit card number, expiry date, cvv code).
- Users will be able to make a payment.
- Users will be able to make a refund.
- The application will interact with the bank service using a ReST service.
- Money will be transferred between accounts correctly.
- Data input validation will be implemented throughout.
- Authentication will be implemented on the administrator account.
- Authentication will be implemented on transaction request.
- All error messages will be user-friendly and informative.

### **Non-Goals**

- CVV codes will not be recorded.
- The PoS device will not validate the CVV code.

## Project Plan and Task Assignment

The team started planning this project by splitting it down into smaller, manageable tasks that only addressed one requirement at a time. These were recorded on GitHub Projects and divided up into three *To Do* categories - front-end, back-end, and documentation. These were originally recorded as notes within the project, and were updated to include the relevant name of the person doing the task when it was moved to *In Progress*. However, Github Issues were later used for all outstanding tasks.

![GitHub Project Screenshot](images/githubProject.PNG)
>Screenshot of GitHub Project in progress

The team decided on a top-down approach to this project, as how data was handled in the front-end would directly affect how it needed to be handled in the back-end. 

The project was initially designed to be multi-module but, as the application only required simple functionality, this was refactored into a single WAR with layers as packages.

The team also decided to use MVC for the project structure, as this separated back-end functionality from the front-end, and would therefore make the code a lot more readable and easier to debug.

The team assigned the first tasks, such as setting up the project in NetBeans, creating the UIs, etc, but task assignment thereafter was managed dynamically, depending on what had already been implemented and who was currently available to work on the project, with regular meetings online and in-person to discuss. As each task was assigned, the ticket on Github Projects was updated accordingly.

All the following tasks are also available on [GitHub Projects](https://github.com/TomStrong/Com528-Assessment/projects/1):

| Owner        | Task     | Description    |
| :------------- | :----------: | :----------: | 
| Jake / Jo | ![Task 1](images/tasks/task1.PNG) | Jake initially created a new project in NetBeans. This was then replaced by Jo to use a class excerise with all the client packages included. |
| Jo | ![Task 2](images/tasks/task2.PNG) | All imported client packages needed to refactored to the team name. |
| Jo | ![Task 3](images/tasks/task3.PNG) | A standalone bank client module became available so project was started again without multi-modules. |
| Tom | ![Task 4](images/tasks/task4.PNG) | Designed and implemented a user-friendly UI that resembles a real PoS device, using JavaScript and CSS where appropriate. |
| Jo | ![Task 5](images/tasks/task5.PNG) | Designed and implemented a basic administrator page that allows entry of the identity for the PoS device.  |
| Jo | ![Task 6](images/tasks/task6.PNG) | Implemented a basic index page which allows easy access to both admin and PoS pages. |
| Jo | ![Task 7](images/tasks/task7.PNG) | Created default.properties file with valid data so the PoS would not need configuring every time it was tested. |
| Jo | ![Task 8](images/tasks/task8.PNG) | Created the MVC Controller class. |
| Tom / Jo | ![Task 9](images/tasks/task9.PNG) | Basic functionality implemented with GET requests to access all JSP pages. |
| Merlin | ![Task 10](images/tasks/task10.PNG) | Created use case UML diagram. |
| Jo | ![Task 111](images/tasks/task11.PNG) | Implemented functionality of the admin page, with data retrieved and updated in the .properties file. Also implemented functionality for transactions, reading in PoS identity data and input data to make a ReST call to the bank service. |
| Jo | ![Task 12](images/tasks/task12.PNG) | Implemented workflow through GitHub actions so all code is tested as soon as pushed to repo. |
| Tom | ![Task 13](images/tasks/task13.PNG) | Ensured all error messages are user-friendly and informative. |
| Tom | ![Task 14](images/tasks/task14.PNG) | Updated formatting on PoS to be more user-friendly and readable. |
| Jo / Jake | ![Task 15](images/tasks/task15.PNG) | Jo updated Log4J to include transaction logs. However, this would not work so Jake created a bespoke logging class with corresponding unit test. |
| Jo | ![Task 16](images/tasks/task16.PNG) | Updated MVC Controller to perform the [Luhn Algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm) on inputted credit card numbers within both Admin and PoS pages and ensured only credit card numbers that passed would be accepted. |
| Jo | ![Task 17](images/tasks/task17.PNG) | Created use cases and included them within the design documentation. |
| Jo | ![Task 18](images/tasks/task18.PNG) | Created test cases and included them within the design documentation. |
| Tom | ![Task 19](images/tasks/task19.PNG) | Updated Admin page to use JavaScript to validate form input. |
| Jo | ![Task 20](images/tasks/task20.PNG) | Created all documentation required for project, including use and test cases. |
| Merlin | ![Task 21](images/tasks/task21.PNG) | Created robustness UML diagram. |
| Merlin | ![Task 22](images/tasks/task22.PNG) | Created sequence diagram. |
| Tom | ![Task 23](images/tasks/task23.PNG) | Created error JSP for in case exception is thrown. |
| Jake | ![Task 24](images/tasks/task24.PNG) | Implemented authentication on admin page, utilising the Password class to encrypt the password. |
| Jake | ![Task 25](images/tasks/task25.PNG) | Created all javadocs within code. |
| Jake / Tom | ![Task 26](images/tasks/task26.PNG) | Ensured exception handling used throughout. |
| Merlin | ![Task 27](images/tasks/task27.PNG) | Created class UML diagrams. |

>>**_NOTE:_**  
An index page has been included for ease of the assessor for this assessment only. In a real-life situation, the user would only have access to the PoS device itself.

## Use Cases and Features

The following uses cases describe how users will use the application and outlines the system behaviour in response. 

| ID   | Actor    | Action     | System Behaviour |
| :------------- | :-------------  | :------------- | :-------------  |
| UC1   | Admin  | Admin enters the correct password and username.   | The admin page is opened. |
| UC2   | Admin  | Admin enters the incorrect password and username, or leaves a field blank.  | An error message is displayed and access to the admin page is denied. |   
| UC3   | Admin  | Admin configures the PoS device.   | The identity of the device is updated locally. | 
| UC4   | Admin |  Admin configures the PoS device but misses a field.  |  An error message is displayed and the identity of the device is not updated.    | 
| UC5   | Admin  | Admin enters an incorrect credit card number.   | The credit card number fails the Luhn check, an error message is displayed and the identity of the device is not updated. | 
| UC6   | User  |  User enters option that is neither payment or refund.  |  The device displays an error message and they are prompted to input again.   |
| UC7   | User  |  User enters option for a payment.   |  The device prompts them to enter their credit card details.   | 
| UC8   | User  |  User enters option for a refund.   |  The device prompts them to enter their credit card details.   | 
| UC9   | User  |  User enters incorrectly formatted card data.   |  The device displays an error message, and prompts them to input again. | 
| UC10   | User  |  User enters incorrect card number.   |  The application checks validity of the card number using the Luhn check, and displays an error message | 
| UC11   | User  |  User enters no card data.   | The device displays an error message and they are prompted to input again. | 
| UC12   | User  |  User confirms transaction.   | The device displays an "Approved" message and the transaction is completed.<br/> The application transfers money between accounts and logs the transaction to the local log file (excluding cvv code). |
| UC13   | User  |  User does not have sufficient balance in account to complete transaction.   | The device displays a "Declined" message and the transaction is aborted.<br/>The application logs the transaction to the local log file (excluding cvv code and with the amount as *null*). |
| UC14   | User  |  User cancels a transaction before it is completed.  | The device returns to main menu. No monies are transferred and nothing is logged. |
| UC15   | User  |  User enters an incorrect path in the URL.  | An error page is displayed and they are prompted to return to the home page. |   

## Test Plan

| Test Case ID  | Use Case ID    | Preconditions     | Test Steps | Expected Post Conditions to Pass |
| :------------- | :-------------  | :------------- | :-------------  | :-------------  | 
| TC1 | UC1 | Tomcat server is running and Tester is on *index* page. | All steps as per TC1 except: <br/>1. "admin" is entered in the username field.<br/>2. "admin" is entered in the password field.<br/>3. *Login as Admin* button is clicked.| Admin page is opened. |
| TC2 | UC2 | Tomcat server is running and Tester is on *index* page. | All steps as per TC1 except: <br/>1. "test" is entered in the username field.<br/>2. Nothing is entered in the password field.<br/>3. *Login as Admin* button is clicked.| "Invalid username or password" error message is displayed and admin page is not opened.|
| TC3 | UC3 | Tomcat server is running and Tester is on *admin* page. | 1. "http://com528bank.ukwest.cloudapp.azure.com:8080/rest" is entered into *Bank URL* field.<br/>2. "admin" is entered into *Admin Username* field.<br/>3. "admin" is entered into *Admin Password* field.<br/>4."test" is entered into *Name on Credit Card* field.<br/>5."11/21" is entered into *Credit Card End Date* field.<br/>6."4285860000000021" is entered into *Credit Card Number* field.<br/>7."123" is entered into *CVV Number* field.<br/>8. "01" is entered into *Issue Number* field.<br/>10. "admin" is entered into username.<br/>11. "admin" is entered into password.<br/> 12. *Submit Configuration* button is clicked. | Page displays "PoS now configured" message. Local application.properties file updated to match inputted data. | 
| TC4 | UC4 | Tomcat server is running and Tester is on *admin* page. | All steps as per TC1 except: <br/>1. Any required field is left empty.| Page displays "Please fill all required fields" message. Local application.properties file is not updated.|
| TC5 | UC5 | Tomcat server is running and Tester is on *admin* page.| All steps as per TC1 except:<br/>5. "1234567891234567" is entered into *Credit Card Number* field.| Credit card number fails Luhn check within MVC Controller. Page displays "Invalid credit card number" message. Local application.properties file is not updated.|
| TC6 | UC6 | Tomcat server is running.<br/> Tester is on *PoS* page and the PoS displays reads "Please select an option:<br/>1) Payment <br/>2) Refund" | 1. "8" is entered.<br/>2. *Accept* button is clicked.| "Not a valid entry" is displayed at the top of the screen and the user is prompted to input again.| 
| TC7 | UC7 | Tomcat server is running.<br/> Tester is on *PoS* page and the PoS displays reads "Please select an option:<br/>1) Payment <br/>2) Refund" | 1. "1" is entered.<br/>2. *Accept* button is clicked.| The display of the PoS changes to "Please enter amount:"|
| TC8 | UC8 | Tomcat server is running.<br/> Tester is on *PoS* page and the PoS displays reads "Please select an option:<br/>1) Payment <br/>2) Refund" | 1. "2" is entered.<br/>2. *Accept* button is clicked.| The display of the PoS changes to "Please enter amount:"|
| TC9.1 | UC9 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"|1. "/" or "." is inputted for amount.<br/>2. *Accept* button is clicked.| "Please enter a number" is displayed at the top of the screen. |
| TC9.2 | UC9 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"| 1. "10.00" is inputted for amount.<br/>2. *Accept* button is clicked.<br/>3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/> 5. "1234" is inputted for credit card number.<br/>6. *Accept* button is clicked.| "Card numbers must be 16 digits" is displayed at the top of the screen and the user is prompted to input again. |
| TC9.3 | UC9 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"| 1. "10.00" is inputted for amount.<br/>2. *Accept* button is clicked.<br/>3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/>5. "5133880000000012" is inputted for card number.<br/>6. *Accept* button is clicked.<br/>7. "1" is inputted for expiry date.<br/>8. *Accept* button is clicked.| "Date must be formatted as MM/YY" is displayed at the top of the screen and the user is prompted to input again. |
| TC9.4 | UC9 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"| 1. "10.00" is inputted for amount.<br/>2. *Accept* button is clicked.<br/>3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/>5. "5133880000000012" is inputted for card number.<br/>6. *Accept* button is clicked.<br/>7. "11/21" is inputted for expiry date.<br/>8. *Accept* button is clicked.<br/>9. "1" is inputted for cvv.<br/>10. *Accept* button is clicked.| "CVV should be 3 or 4 digits" is displayed at the top of the screen and the user is prompted to input again. |
| TC9.5 | UC9 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"|1. "10.00" is inputted for amount.<br/>2. *Accept* button is clicked.<br/>3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/>5. "5133880000000012" is inputted for card number.<br/>6. *Accept* button is clicked.<br/>7. "11/21" is inputted for expiry date.<br/>8. *Accept* button is clicked.<br/>9. "123" is inputted for cvv.<br/>10. *Accept* button is clicked.<br/> 11. "1234" is inputted for issue number.<br/>12. *Accept* button is clicked.| "Issue Number should be 0-2 digits" is displayed at the top of the screen and the user is prompted to input again. |
| TC10 | UC10 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"<br/>*Amount* = "10.00"<br/>*Name* = "Bob"| 1. "1234567891234567"  is inputted for card number.<br/>2. *Accept* button is clicked.<br/>3. "11/24" is inputted for expiry date.<br/>5. *Accept* button is clicked.<br/>6. "123" is inputted for CVV.<br/>7. *Accept* button is clicked.<br/>8. Issue number is left black.<br/>9. *Accept* button is clicked.<br/> 10. *Accept* button is clicked to confirm the transaction.| "1234567891234567 is an invalid card number" is displayed at the top of the screen and the transaction is aborted. The log file is not updated. |
| TC11 | UC11 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "1"|1. Nothing is inputted for amount.<br/>2. *Accept* button is clicked.| "Required" is displayed at the top of the screen. |
| TC12 | UC12 | Tomcat server is running.<br/> Device identity card number is "4285860000000021". Tester is on *PoS* page and has inputted:<br/>*Option* = "1" | 1. "10.00" is inputted for amount<br/>2. *Accept* button is clicked.<br/> 3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/>5. "5133880000000012" is inputted for card number.<br/>6. *Accept* button is clicked.<br/>7. "11/21" is inputted for expiry date.<br/>8. *Accept* button is clicked.<br/>9. "01" is inputted for issue number.<br/>10. *Accept* button is clicked.<br/>11. *Accept* button is clicked to complete transaction.| "Approved" is displayed at the top of the screen. £10.00 is transfered from card 5133880000000012 to card 4285860000000021, which can be seen [here](http://com528bank.ukwest.cloudapp.azure.com:8080/banktransactions). Transaction log records transaction excluding cvv code.|
| TC13 | UC13 | Tomcat server is running.<br/> Device identity card number is "4285860000000021". Tester is on *PoS* page and has inputted:<br/>*Option* = "2" | 1. "500.00" is inputted for amount<br/>2. *Accept* button is clicked.<br/> 3. "bob" is inputted for name.<br/>4. *Accept* button is clicked.<br/>5. "5133880000000012" is inputted for card number.<br/>6. *Accept* button is clicked.<br/>7. "11/21" is inputted for expiry date.<br/>8. *Accept* button is clicked.<br/>9. "01" is inputted for issue number.<br/>10. *Accept* button is clicked.<br/>11. *Accept* button is clicked to complete transaction.| "Declined. Insufficient balance in account 428586 000000002" is displayed at the top of the screen. No monies have been transferred. Transaction log records transaction with amount="null" and excluding cvv code.|
| TC14 | UC14 | Tomcat server is running.<br/> Tester is on *PoS* page and has inputted:<br/>*Option* = "2" | 1. *Cancel* button is clicked. | The device returns to the front screen.|
| TC15 | UC15 | Tomcat server is running.| 1. "http://localhost:8080/Com528-Assessment/badpage" is entered into URL. | Error page displayed with link to the home page. |

## Development Design Structure ##

This project uses Agile ICONIX processes to detail and map out the application. The reason for using the ICONIX process is that it allows developers move from use cases to coding the application quickly and reliably. As this is only a small project Proper agile methodologies have not been implemented, however if in the future the application were to be developed into a large scale program, the use of agile would be effective in ensuring fast and reliable development.

It is important to note that the ICONIX process does not completely follow agile methodology. contrasting agile, the philosophy behind ICONIX is that UML diagrams are vital in keeping the code structured correctly and therefore as close to the customer requirements as possible.

A key principle of agile methodology is improving collaboration and communication within the team, something that the development team for this project upheld throughout, with daily meetings expressing concerns and talking about what has and needs to be done to complete the task.

In ICONIX process it is important that the requirements are correct from the beginning, this was acheived by creating and analysing UML diagrams as shown below. The analysis of UML creates a robust definition of the requirements of the application by removing all ambiguity. To sustain the robustness of the application design, the code and UML diagrams were compared and analysed throughout the development process, keeping the code as accurate as possible to the requirements.

## Use case text ##

Use case text defines the requirements of the application, these are stated at the top of the page.

## UML Diagrams

**Class Diagrams**

Class diagrams were created using easyUML, more information can be found [here](https://github.com/ossdcfos/easyuml)

**Client Class UML Diagram**


![Client Class Diagram](ClassDiagram/ClientClassDiagram.png)

**TO DO**
explain

**Web Class UML Diagram**

![Web Class Diagram](ClassDiagram/WebClassDiagram.png)

**TO DO**
explain

**Use Case UML Diagram**

Use cases are actions that perform tasks, they are the functions of the app. 

The use case diagram outlines the objects; such as people, systems and organisations; required for the application to function properly and their relationships. Realtionships are split into categories:

- Association Relationships are shown with lines, these are specified in the key of the image below.

- Include - Base use case requires included use case in order to complete the task.

- Extend -  Base use case can require extension use cases to complete the task, however extensions are not always necessary for task completion.

- Generalisation - Parent use case has children, parent is essentially a generalisation of the child use cases.



![Use Case UML Diagram](UMLDiagrams/UseCaseDiagram.jpg)




**Robustness UML Diagram**

![Robustness Analysis Diagram](UMLDiagrams/RobustnessDiagram.jpg)

**TO DO**
explain


**Sequence Diagram**

![Sequence Diagram](UMLDiagrams/SequenceDiagram.jpg)

**TO DO**
explain

