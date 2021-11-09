# COM528 AE1 Assessment

This is the project for Object Oriented Design and Development by **Group A7**.

The students who developed this project (in alphabetical order) are:

| Name & Github        | Student ID    | 
| :------------- | :----------: | 
| [Jo Randall](http://github.com/jorandall)  | 15191117  | 
| [Jake Roblett](https://github.com/JakeRoblettUni)   | 15002543 |
| [Merlin Smith](https://github.com/MerlinSmith) | 14803194 |
| [Tom Strong](https://github.com/TomStrong) | 15071278 |

For design documentation, click [here](https://github.com/TomStrong/Com528-Assessment/blob/main/docs/documentation.md).

## Point of Sale Application
___
This project is simple a Point of Sale web application, which allows configuration of the device via the administrator, and also allows the user to enter a payment or refund via the PoS interface. The transaction is then posted to the following bank client:

[Simple Bank Simulator](http://com528bank.ukwest.cloudapp.azure.com:8080/home)

The application then logs the response and displays on the PoS device whether the transaction has been approved or declined.

The application uses Enterprise Java Technologies with Spring MVC framework and Java Server Page for the front-end. Javascript has been used to generate the UI and validate user inputs. 

## System Requirements
___
This application has been designed and tested on the following, therefore it is recommended that these are used as a minimum:

- Windows 10 
- Java JDK v8
- Apache Maven 3.8
- Apache Tomcat 9.0
- Web browsers: Google Chrome

Please note, Internet Explorer is not supported.

## User Instructions
___
Before the application is run, ensure the following command is run in the folder **Com528-AssessmentGroupA7**:

```
mvn clean install
```
The application can now be opened in Netbeans. Once the web module has been run and the Tomcat server started, the web app will be launched on: 

[http://localhost:8080/Com528-Assessment/](http://localhost:8080/Com528-Assessment/)

The web app will open on the home page, where the user can navigate between Administrator and PoS device. If either button is clicked, a new web page will be opened.

### Administrator
___

The first time the application is launched, the form within the administrator page will auto populate with the data from [default.properties](https://github.com/TomStrong/Com528-Assessment/blob/main/COM528-AssessmentGroupA7/web/src/main/resources/default.properties). This can be configured to update the PoS with the current user's details.

All fields are required, except for the issue number, and the form will not submit without these. If this is attempted, an error message will be displayed.

### PoS
___
The PoS device prompts the user to select either a payment or refund. Each step thereafter is the same, with the device prompting the user for input along the way.

The buttons on the device are updated depending on the field to input. For example, the amount, credit card number, cvv and issue number will only offer the user integer input, whereas the name will only offer characters. This has been automated in the Javascript and cannot be overridden.

The date will need to be entered in the format of mm/yy, otherwise the user will receive an prompt and the entry will not be accepted.

The action buttons on the device are as follows:

| Button        | Action    | 
| :------------- | :----------: | 
| ![](images/acceptButton.png) | Accept current input | 
| ![](images/backButton.png)   | Back (input only) |
| ![](images/cancelButton.png)  | Cancel transaction |

All fields are required, except for Issue Number, and will display an appropriate error message if no or invalid entry is made. 




