# Design Documentation

## Requirements
For this project, we were required to develop a simple web-based Point of Sale application that allowed a user to enter their credit card details to make a transaction. The application would then need to communicate with the mock banking service using the ReST service supplied to either confirm or deny the transaction.

In addition, the Point of Sale device needed to be configurable by an admin user as transactions would require the data associated with the PoS device to transfer funds to or from the correct user account.

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

**To do**

Please note, the index page is included for ease of the assessor for this assessment only. In a real-life situation, the user would only have access to the PoS device itself.

## Use Cases and Features

The following uses cases describe how users will use the application and outlines the system behaviour in response. 

| Use Case ID   | Actor    | Action     | System Behaviour |
| :-------------: | :-------------:  | :-------------: | :-------------:  | 
| UC1   | Administrator  |    |     | 
| UC2   | Administrator |    |     | 
| UC3   | User  |    |     | 
| UC4   | User  |    |     | 

**To do**

## Test Plan

**To do**

## UML Diagrams

**Use Case UML Diagram**


![](UMLDiagrams/UseCaseDiagram.jpg)


**Robustness UML Diagram**

**To do**