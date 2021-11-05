<%-- 
    Document   : admin
<<<<<<< HEAD
    Created on : 30 Oct 2021, 15:22:20
    Author     : Tom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="org.solent.ood.assessmentgroupa7.dao.WebObjectFactory"%>
<%@page import="org.solent.ood.assessmentgroupa7.dao.PropertiesDao"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/css/main.css">
        <title>Administrator</title>
    </head>
    <body>
        <h1>PoS Configuration</h1>
    </head>
    <body>
        <br/>

            <form action="./propertylist" method="POST">
                <div id="admin">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                                Name on Credit Card
                            </td>
                            <td>
                                <input type="text" name="name" value="${name}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card End Date 
                            </td>
                            <td>
                                <input type="text" name="enddate" value="${endDate}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card Number
                            </td>
                            <td>
                                <input type="text" name="cardno" value="${cardNumber}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                CVV Number
                            </td>
                            <td>
                                <input type="text" name="cvv" value="${cvv}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Issue Number
                            </td>
                            <td>
                                <input type="text" name="issueno" value="${issueNumber}">
                            </td>
                        </tr>
                    </table>
                </div>
                
                <br/>
                <input type="hidden" name="action" value="updateProperties">
                <button class="btn" type="submit">Update Properties</button>
            </form> 
        
    </body>
</html>
